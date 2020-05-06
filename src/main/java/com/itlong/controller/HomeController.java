package com.itlong.controller;

import com.itlong.bean.ProductInfo;
import com.itlong.bean.UserInfo;
import com.itlong.service.CartInfoService;
import com.itlong.service.ProductInfoService;
import com.itlong.service.UserInfoService;
import com.itlong.service.UserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    CartInfoService cartInfoService;

;

    @RequestMapping({"/","/index"})
    public String goToHome(Model model){
        List<ProductInfo> hotsPot = productInfoService.selectByMaxScore();
        List<ProductInfo> newProd = productInfoService.selectByMinScore();
        model.addAttribute("hotsPot",hotsPot);//已经加入缓存
        model.addAttribute("newProd",newProd);//已经加入缓存
        return "index";
    }


}
