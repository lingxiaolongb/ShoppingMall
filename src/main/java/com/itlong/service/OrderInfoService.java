package com.itlong.service;

import com.itlong.bean.OrderInfo;
import com.itlong.dto.ShopInfo;

import java.math.BigDecimal;
import java.util.List;

public interface OrderInfoService {

    List<OrderInfo>  selectOrderInfoByUgdId(String userId);

    String UpdateCartWithOrder(String userId, String orderId, BigDecimal totalPrice);

    int insertOrders(List<OrderInfo> list);


    List<OrderInfo> selectOrderById(String orderId);
}
