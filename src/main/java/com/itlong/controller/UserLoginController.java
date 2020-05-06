package com.itlong.controller;

import com.itlong.bean.UserInfo;
import com.itlong.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserLoginController {

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/toLogin")
     public String toLogin(){
        return "/login/login";
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "/";
    }

    @RequestMapping("/switch.login")
    public String switchLogin(){
        SecurityUtils.getSubject().logout();
        return "redirect:/logout";
    }

    @RequestMapping("/login")
    public String toLogin(@RequestParam String username,
                          @RequestParam String password,
                          String remember,
                          Model model)
    {
        Subject currentUser= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        if(remember!=null) {
            token.setRememberMe(true);
        }
        try {
            currentUser.login(token);
        } catch (AuthenticationException  e) {
            model.addAttribute("errorTip","账号或密码错误");
        }
        System.out.println("xxx");
        if(currentUser.isAuthenticated()){
            Session session = currentUser.getSession();
            UserInfo userInfo = userInfoService.selectInfoByUsername(username);
            session.setAttribute("userId",userInfo.getUserId());
            session.setAttribute("memberName",userInfo.getMemberName());
            session.setAttribute("userPic",userInfo.getPicPath());
            return "redirect:/";
        }else{
            return "/login/login";
        }

    }


}
