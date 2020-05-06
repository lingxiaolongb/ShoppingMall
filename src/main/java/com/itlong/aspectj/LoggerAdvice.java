package com.itlong.aspectj;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAdvice {


    @Pointcut("execution(public * com.itlong.service.impl.LoggerServiceImpl.*(..))")
    public void pt1(){}

    @Before("pt1()")
    public void beforeHello(){
        System.out.println("Hello welcome");
    }


   @AfterThrowing(value = "pt1()",throwing = "ex")
    public void throwing(Exception ex){
       System.out.println(ex.getMessage());
    }
}
