package com.itlong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@MapperScan("com.itlong.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class ShoppingMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingMailApplication.class, args);
    }

}
