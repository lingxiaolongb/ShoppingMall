package com.itlong.controller.user;

import com.itlong.bean.CartInfo;
import com.itlong.bean.ProductInfo;
import com.itlong.bean.UserGoodsAddress;
import com.itlong.dto.ShopInfo;
import com.itlong.service.*;
import com.itlong.utils.ObjectCastListUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @PostMapping("/add")
    @ResponseBody
    public String save(@RequestParam String prodId) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) return "logout";
        String userId = (String) subject.getSession().getAttribute("userId");
        CartInfo cartInfo = cartInfoService.selectProdDuplicate(userId, prodId);
        if(cartInfo!=null){
            return "duplicate";
        }else{
            cartInfoService.insert(prodId, userId);
            return "ok";
        }
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
    public void tempSettleRedirect(@RequestBody List<ShopInfo> ShopInfos){
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("tempShopInfos",ShopInfos);
        BigDecimal tp=null;
        for(ShopInfo si:ShopInfos){
            if(tp==null){
                tp=si.getPrice().multiply(new BigDecimal(si.getNum()));
            }else{
                tp=tp.add(si.getPrice().multiply(new BigDecimal(si.getNum())));
            }
        }
        session.setAttribute("totalPrice",tp);
    }

    //根据session内容生成所需信息生成订单支付页面
    @RequestMapping("/Payment_order")
    public String RealSettle(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        String userId=(String) session.getAttribute("userId");
        //先去查询收货地址,其实这里加个数量就可以
        List<UserGoodsAddress> ugas = userGoodsAddressService.selectByUserId(userId);
        model.addAttribute("ugas",ugas);
        Object tempShopInfos1 = session.getAttribute("tempShopInfos");
        List<ShopInfo> tempShopInfos = ObjectCastListUtils.castList(tempShopInfos1,ShopInfo.class);
        List<String> prodIds=new ArrayList<>();
        for(ShopInfo si:tempShopInfos){
            prodIds.add(si.getProdId());
        }
        //根据商品id查询商品信息
        List<ProductInfo> prodInfos = productInfoService.selectByProdIds(prodIds);//无需缓存，list不会缓
        model.addAttribute("prodInfos",prodInfos);
        return  "user/user-settle";
    }

    //异步请求，批量插入订单，删除购物车内的购买的商品
    @RequestMapping("/order_deal/{ugaId}")
    @ResponseBody
    public  String OrderDeal(@PathVariable String ugaId){
        Session session = SecurityUtils.getSubject().getSession();
        String userId=(String) session.getAttribute("userId");
        BigDecimal balance=userInfoService.selectByPrimaryKey(userId).getBalance();
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        String s = userInfoService.withdrawalMoney(userId, totalPrice);
        if(s.equals("notEnough")) return "not";
        Object tempShopInfo = session.getAttribute("tempShopInfos");
        List<ShopInfo> tempShopInfos = ObjectCastListUtils.castList(tempShopInfo,ShopInfo.class);
        orderInfoService.UpdateCartWithOrder(ugaId,userId,tempShopInfos);
        return "1";
    }

    //删除session的临时内容,也防止用户乱访问
    @RequestMapping("/order_success")
    public  String orderSuccess(){
        Session session = SecurityUtils.getSubject().getSession();
        session.removeAttribute("tempShopInfos");
        session.removeAttribute("totalPrice");
        return "user/order-success";
    }
}
