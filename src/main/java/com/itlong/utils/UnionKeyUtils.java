package com.itlong.utils;

import com.itlong.bean.OrderInfo;
import com.itlong.bean.ProductInfo;
import com.itlong.dto.OrderItem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UnionKeyUtils {


    public static Map<String,OrderItem> mapCastListByOrderInfo(List<OrderInfo> list){
        Map<String,OrderItem> map=new LinkedHashMap<>();
        for(OrderInfo oi:list){
            if(!map.containsKey(oi.getOrderId())){
                OrderItem item=new OrderItem();
                Map<ProductInfo,Integer> proInfos=new HashMap<>();
                item.setOrderId(oi.getOrderId());
                proInfos.put(oi.getProductInfo(),oi.getQuantity());
                item.setProductInfos(proInfos);
                item.setUgdId(oi.getUgdId());
                item.setTotalPrice(oi.getProductInfo().getPrice().
                        multiply(new BigDecimal(oi.getQuantity()+"")));
                item.setPurchaseTime(oi.getPurchaseTime());
                item.setExpressFee(oi.getExpressFee());
                item.setOrderStatus(oi.getOrderStatus());
                map.put(oi.getOrderId(),item);
            }else{
                OrderItem item = map.get(oi.getOrderId());
                item.setTotalPrice(item.getTotalPrice().add(
                        oi.getProductInfo().getPrice().multiply(
                                new BigDecimal(oi.getQuantity()+""))));
                item.getProductInfos().put(oi.getProductInfo(),oi.getQuantity());
            }
        }
        return map;

    }


}
