package com.itlong.config;

import com.itlong.interceptor.SettlementController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    SettlementController settlementController;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(settlementController).addPathPatterns("/cart/Payment_order");
    }
}
