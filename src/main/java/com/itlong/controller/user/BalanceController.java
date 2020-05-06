package com.itlong.controller.user;

import com.itlong.bean.UserInfo;
import com.itlong.bean.UserLogin;
import com.itlong.service.UserInfoService;
import com.itlong.service.UserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserLoginService userLoginService;

    @PostMapping("/add")
    public void addBalance(@RequestParam String balance){
        Session session = SecurityUtils.getSubject().getSession();
        String userId = (String)session.getAttribute("userId");
        BigDecimal money=new BigDecimal(balance);
        userInfoService.rechargeMoney(userId,money);
    }

    @PostMapping("/sub")
    public String subBalance(@RequestParam String balance){
        Session session = SecurityUtils.getSubject().getSession();
        String userId = (String)session.getAttribute("userId");
        BigDecimal money=new BigDecimal(balance);
        String status=userInfoService.withdrawalMoney(userId,money);
        return status;
    }


    @PostMapping("/transfer")
    public String transferAccounts(@RequestParam String targetName,@RequestParam String balance){
        UserLogin userLogin = userLoginService.selectByTargetName(targetName);
        if(userLogin==null) return "notExist";
        UserInfo targetInfo = userInfoService.selectInfoByUsername(userLogin.getUsername());
        Session session = SecurityUtils.getSubject().getSession();
        String userId = (String)session.getAttribute("userId");
        if(targetInfo.getUserId().equals(userId)) return "cantYourself";
        BigDecimal money=new BigDecimal(balance);
        String status=userInfoService.transferAccount(userId,targetInfo.getUserId(),money);
        return status;
    }
}
