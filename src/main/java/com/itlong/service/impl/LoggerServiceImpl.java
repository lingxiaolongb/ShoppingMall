package com.itlong.service.impl;

import com.itlong.service.ILoggerService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl implements ILoggerService {

    @Override
    public void hello() {
        System.out.println("我司张三");
    }

    @Override
    public void sea() {
//        ((ILoggerService)AopContext.currentProxy()).hello();
        hello();
        System.out.println("我看见大海");
    }
}
