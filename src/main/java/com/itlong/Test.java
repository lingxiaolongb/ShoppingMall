package com.itlong;



import com.itlong.constant.WebConst;
import com.itlong.service.ILoggerService;
import com.itlong.utils.UuidUtils;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

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

    JdbcTemplate jdbcTemplate=null;

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
    }

}
