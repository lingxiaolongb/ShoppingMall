package com.itlong.utils;

import com.itlong.constant.WebConst;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

public class UuidUtils {


    /**
     * 随机产生购物车id
     */
    public static final int len = 16;

    public  static  String getCartUUid(){
        String uuid=UUID.randomUUID().toString().replaceAll("-","");
        int start=(int)Math.floor(Math.random()*17);
       return  uuid.substring(start, UuidUtils.len+start);
    }

    public  static  String getOrderUUid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public  static  String getUgdNum(){
        LocalDateTime now = LocalDateTime.now();
        return now.getMonthValue()+""
                +now.getDayOfMonth()+""
                +now.getHour()+""
                +now.getMinute()+""
                +now.getSecond()+"";
    }
}
