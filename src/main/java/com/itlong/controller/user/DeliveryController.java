package com.itlong.controller.user;


import com.itlong.service.UserGoodsAddressService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    UserGoodsAddressService userGoodsAddressService;
//缓存giao定
    @PostMapping("/del/{ugdId}")
    public void deleteAddress(@PathVariable String ugdId){
        Session session = SecurityUtils.getSubject().getSession();
        String userId = (String)session.getAttribute("userId");
        userGoodsAddressService.deleteByPrimaryKey(ugdId,userId);
    }


    @PostMapping("/update/{ugdId}")
    public void UpdateAddress(@PathVariable String ugdId){
        Session session = SecurityUtils.getSubject().getSession();
        String userId = (String)session.getAttribute("userId");
        userGoodsAddressService.updateByPrimaryKey(ugdId,userId);
    }


}
