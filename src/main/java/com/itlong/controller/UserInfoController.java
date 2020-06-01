package com.itlong.controller;

import com.itlong.bean.*;
import com.itlong.dto.OrderItem;
import com.itlong.service.*;
import com.itlong.utils.UnionKeyUtils;
import com.itlong.utils.UuidUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/my_jindong")
public class UserInfoController {

    @Autowired
    CartInfoService cartInfoService;

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    UserGoodsAddressService userGoodsAddressService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    ProductInfoService productInfoService;

    @GetMapping("/info/{userId}")
    public String userInfo(@PathVariable String userId, Model model){
        UserInfo userInfo = userInfoService.selectByPrimaryKey(userId);
        model.addAttribute("userInfo",userInfo);
        return "user/user-info";
    }

    @GetMapping("/hello")
    public String hello(Model model){
        return "user/user-hello";
    }

    @GetMapping ("/hello2")
    public String hello2(Model model){
        model.addAttribute("hello","hello");
        System.out.println("跳转中。。。。。。。。。。。。");
        return "user/hello2";
    }

    @GetMapping("/balance/{userId}")
    public String userBalance(@PathVariable String userId,Model model){
        UserInfo userInfo = userInfoService.selectByPrimaryKey(userId);
        model.addAttribute("balance",userInfo.getBalance());//要干掉与钱有关的Id
        return "user/user-balance";
    }

    @GetMapping("/cart/{userId}")//这是一个败笔
    public String userCart(@PathVariable String userId,Model model){
        int number = cartInfoService.selectRow();//不做
        List<CartInfo> cartInfos = cartInfoService.selectByUserId(userId);//已做
        //应该映射一对多,用购物车号与产品Id做主建标明一条记录(没有考虑周到)
        if(!cartInfos.isEmpty()){
            List<ProductInfo> prodInfos = productInfoService.selectByCartInfos(cartInfos);//不做
            model.addAttribute("prodInfos",prodInfos);
        }
        model.addAttribute("number",number);
        model.addAttribute("cartInfos",cartInfos);
        return "user/user-cart";


    }
    @GetMapping("/delivery/{userId}")
    public String userDelivery(@PathVariable String userId,Model model){
            List<UserGoodsAddress> userGoodsAddresses = userGoodsAddressService.selectByUserId(userId);
            model.addAttribute("addresses",userGoodsAddresses);
            model.addAttribute("number",userGoodsAddresses.size());
            return "user/user-delivery";

    }

    @GetMapping("/order/{userId}")
    public String userOrder(@PathVariable String userId,Model model){
            List<OrderInfo> orderInfos = orderInfoService.selectOrderInfoByUgdId(userId);//已做缓存
            Map<String, OrderItem> orderItems = UnionKeyUtils.mapCastListByOrderInfo(orderInfos);
          model.addAttribute("orderItems",orderItems);
         return "user/user-order";
    }


    @ResponseBody
    @PostMapping("/update.user")
    public void UpdateUser(@RequestParam String realName,
                             @RequestParam String gender,
                             @RequestParam String email,
                             @RequestParam String phone){
        Session session = SecurityUtils.getSubject().getSession();
        String userId = (String) session.getAttribute("userId");
        UserInfo userInfo=userInfoService.selectByPrimaryKey(userId);//已做缓存
        userInfo.setUserId(userId);
        userInfo.setEmail(email);
        userInfo.setRealName(realName);
        userInfo.setGender(gender);
        userInfo.setPhone(phone);
        userInfoService.updateByPrimaryKey(userInfo);//清除缓存
    }


    @PostMapping("/save.userAddress")
    public String saveUserAddress(@RequestParam String province,
                                  @RequestParam String city,
                                  @RequestParam String district,
                                  @RequestParam String detailedAddress,
                                  @RequestParam String zipCode,
                                  @RequestParam String consignee,
                                  @RequestParam String phone,
                                  String defaultAddress)

   {
       Session session = SecurityUtils.getSubject().getSession();
       String userId = (String) session.getAttribute("userId");
       UserGoodsAddress userGoodsAddress=new UserGoodsAddress();
       StringBuilder location=new StringBuilder();
       location.append(province+" ");
       location.append(city+" ");
       location.append(district);
       int row=userGoodsAddressService.selectRow(userId);

       userGoodsAddress.setUgdId(userId+UuidUtils.getUgdNum());
       userGoodsAddress.setLocation(location.toString());
       userGoodsAddress.setZipCode(zipCode);
       userGoodsAddress.setConsignee(consignee);
       userGoodsAddress.setPhone(phone);
       userGoodsAddress.setDetailedAddress(detailedAddress);
       userGoodsAddress.setUserId(userId);
       if(row==0 || (defaultAddress!=null && defaultAddress.equals("on"))) {
           userGoodsAddress.setDefaultAddress((byte) 1);
           userGoodsAddressService.updateUserAddress(userGoodsAddress);//已做缓存
       }else{
           userGoodsAddress.setDefaultAddress((byte)0);
           userGoodsAddressService.saveUserAddress(userGoodsAddress);//已做缓存
       }
      return "redirect:/my_jindong/delivery/"+userId;
    }


}
