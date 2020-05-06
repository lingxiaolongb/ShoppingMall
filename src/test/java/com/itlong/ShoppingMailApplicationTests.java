package com.itlong;

import com.itlong.bean.OrderInfo;
import com.itlong.bean.ProductInfo;
import com.itlong.mapper.ProductInfoMapper;
import com.itlong.service.CartInfoService;
import com.itlong.service.ILoggerService;
import com.itlong.service.OrderInfoService;
import com.itlong.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.AfterThrowing;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ShoppingMailApplicationTests {

    @Autowired
   ApplicationContext applicationContext;

//    @Autowired
//    ILoggerService loggerService;
    @Test
    void contextLoads() {
        UserInfoService bean = applicationContext.getBean(UserInfoService.class);
        bean.transferAccount("lxl3615","zs123456",new BigDecimal("10000.0"));

//        loggerService.sea();

    }

}
