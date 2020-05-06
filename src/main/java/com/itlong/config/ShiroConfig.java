package com.itlong.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.itlong.shiro.CustomRelam;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager dwsy){
        ShiroFilterFactoryBean sfb=new ShiroFilterFactoryBean();
        sfb.setSecurityManager(dwsy);
        Map<String,String> filter=new LinkedHashMap<>();

        filter.put("/cart/add","anon");
        filter.put("/logout", "logout");
        filter.put("/market/**","authc");
        filter.put("/my_jindong/**","authc");
        filter.put("/cart/**","authc");
        filter.put("/balance/**","authc");
        filter.put("/delivery/**","authc");
        sfb.setFilterChainDefinitionMap(filter);
        sfb.setLoginUrl("/toLogin");
        sfb.setSuccessUrl("/");
        return  sfb;
    }

    /**
     * 返回安全管理器
     * @param customRelam
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(CustomRelam customRelam){
        DefaultWebSecurityManager dwsy=new DefaultWebSecurityManager();
        dwsy.setRealm(customRelam);
        return  dwsy;
    }

    @Bean
    public CustomRelam getCustomRelam(){
        return new CustomRelam();
    }

    @Bean //结合thymeleaf使用
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}
