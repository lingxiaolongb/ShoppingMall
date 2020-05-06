package com.itlong.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component("settlementController")
public class SettlementController implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        Object tempProdIds = session.getAttribute("tempShopInfos");
        if(tempProdIds==null) {
            response.sendRedirect("http://localhost:8080/duolaooni");
            return  false;
        }
        return true;
    }
}
