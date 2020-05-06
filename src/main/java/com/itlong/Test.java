package com.itlong;



import com.itlong.constant.WebConst;
import com.itlong.service.ILoggerService;
import com.itlong.utils.UuidUtils;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Test {

    static LinkedList<String>  list=new LinkedList<>();
    public static void main(String[] args) throws ParseException {

        list.add("111");

//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        for(int i=0;i<9;i++){
//           executorService.execute(()->{
//               list.add(Thread.currentThread().getName());
//
//           });
//        }
//        executorService.shutdown();
//        System.out.println(list.toString());
//    }
    }
}
