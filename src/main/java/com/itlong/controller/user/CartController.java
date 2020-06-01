package com.itlong.controller.user;

import com.itlong.bean.CartInfo;
import com.itlong.bean.OrderInfo;
import com.itlong.bean.ProductInfo;
import com.itlong.bean.UserGoodsAddress;
import com.itlong.dto.SendMessage;
import com.itlong.dto.ShopInfo;
import com.itlong.service.*;
import com.itlong.utils.ObjectCastListUtils;
import com.itlong.utils.UuidUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RequestMapping("/cart")
@Controller
public class CartController {



    @Autowired
    CartInfoService cartInfoService;

    @Autowired
    UserGoodsAddressService userGoodsAddressService;

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    UserInfoService userInfoService;


    @Autowired
    SendMessage sendMessage;

    @PostMapping("/add")
    @ResponseBody
    public String UpdateCartInfo(@RequestParam String prodId,String type) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) return "logout";
        String userId = (String) subject.getSession().getAttribute("userId");
        //这里缓存有问题
        CartInfo cartInfo = cartInfoService.selectProdDuplicate(userId, prodId);
        if(cartInfo==null){
            cartInfoService.insert(prodId, userId);
        }else if("sub".equals(type)){
            cartInfo.setNum(cartInfo.getNum()-1);
            cartInfoService.updateNumById(cartInfo,prodId);
        }else{
            cartInfo.setNum(cartInfo.getNum()+1);
            cartInfoService.updateNumById(cartInfo,prodId);
        }
        return "ok";
    }

    @PostMapping("/remove")
    @ResponseBody
    public void deleteItemsFromCart(@RequestBody List<String> prodIds) {
        Subject subject = SecurityUtils.getSubject();
        String userId = (String) subject.getSession().getAttribute("userId");
        cartInfoService.deleteItemByProdId(userId, prodIds);
    }



    //首选异步请求创建所需信息到session
    @RequestMapping("/add_product_temporarily")
    @ResponseBody
    public void tempSettleRedirect(@RequestBody List<String> prodIds){
        Session session = SecurityUtils.getSubject().getSession();
        String userId=(String) session.getAttribute("userId");
        List<String> e=null;
        List<CartInfo> cartInfos=null;
        BigDecimal totalPrice=new BigDecimal("0.00");
        if(prodIds!=null){
            e = prodIds;
            //查询产品信息
            cartInfos = cartInfoService.selectCartInfoByUserId(userId,e);
            for(CartInfo ci:cartInfos)
                totalPrice=totalPrice.add(
                        (ci.getPrice().multiply(
                                new BigDecimal(ci.getNum()+".00"))));
        }
        session.setAttribute("cartInfos",cartInfos);
        session.setAttribute("totalPrice",totalPrice);
        session.removeAttribute("orderId");
    }

    //根据session内容生成所需信息生成订单支付页面
    @RequestMapping("/Payment_order")
    public String RealSettle(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        String userId=(String) session.getAttribute("userId");
        List<UserGoodsAddress> ugas = userGoodsAddressService.selectByUserId(userId);
        model.addAttribute("ugas",ugas);
        session.removeAttribute("orderId");
        return  "user/user-settle";
    }



    //批量插入订单，删除购物车内的购买的商品，成功后跳转支付界面
    @PostMapping("/order_deal/{ugaId}")
    public  String OrderDeal(@PathVariable("ugaId") String ugaId){
        Session session = SecurityUtils.getSubject().getSession();
        Object obj = session.getAttribute("orderId");
        if(obj==null){
            String userId=(String) session.getAttribute("userId");
            List<String> prodIds=new ArrayList<>();
            List<CartInfo> cartInfos = ObjectCastListUtils.castList(
                    session.getAttribute("cartInfos"), CartInfo.class);
            List<OrderInfo> orderInfoList=new ArrayList<>();
            Date date=new Date();
            String orderId= UuidUtils.getOrderUUid();
            sendMessage.SendMessage(userId,orderId);
            for(CartInfo ci: cartInfos){
                prodIds.add(ci.getProdId());
                OrderInfo orderInfo=new OrderInfo();
                orderInfo.setOrderId(orderId);
                orderInfo.setUgdId(ugaId);
                orderInfo.setProdId(ci.getProdId());
                orderInfo.setQuantity(ci.getNum());
                orderInfo.setExpressFee((byte)0);
                orderInfo.setOrderStatus((byte)0);
                orderInfo.setPurchaseTime(date);
                orderInfoList.add(orderInfo);
            }
            orderInfoService.insertOrders(orderInfoList);
            session.setAttribute("orderId",orderId);
            //删除购物车的内容
            cartInfoService.deleteItemByProdId(userId,prodIds);
            session.removeAttribute("cartInfos");
        }
        return "user/user-payment";
    }






    @PostMapping("/payMoney/{orderId}")
    public String payMoney(@PathVariable String orderId,Model model){
        Session session = SecurityUtils.getSubject().getSession();
        String userId=(String) session.getAttribute("userId");
        List<OrderInfo> orderInfos = orderInfoService.selectOrderById(orderId);
        if(orderInfos==null) return "redirect:/my_jindong/order/"+userId;
        BigDecimal totalPrice=new BigDecimal("0");
        for(OrderInfo oi:orderInfos){
            totalPrice=totalPrice.add(oi.getProductInfo().getPrice().
                    multiply(new BigDecimal(oi.getQuantity()+"")));
        }

        session.setAttribute("orderId",orderId);
        session.setAttribute("totalPrice",totalPrice);
        return "user/user-payment";
    }

    //user/user-payment页面的提交后，进行扣钱操作
    @PostMapping("/payment")
    @ResponseBody
    public String PaymentPage(){
        Session session = SecurityUtils.getSubject().getSession();
        String userId=(String) session.getAttribute("userId");
        Object obj=session.getAttribute("orderId");
        BigDecimal totalPrice;
        if(obj!=null) {
            String orderId  = (String) session.getAttribute("orderId");
            totalPrice = (BigDecimal) session.getAttribute("totalPrice");
            sendMessage.getMessage(userId);
            return  orderInfoService.UpdateCartWithOrder(userId, orderId, totalPrice);
        }else{
            return "error";
        }

    }

    @RequestMapping("/order_success/{orderId}")
    public  String orderSuccess(@PathVariable String orderId){
        Session session = SecurityUtils.getSubject().getSession();
        session.removeAttribute("orderId");
        session.removeAttribute("totalPrice");
        return "user/order-success";
    }
}
