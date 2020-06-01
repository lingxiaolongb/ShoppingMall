package com.itlong.service.impl;

import com.itlong.bean.OrderInfo;
import com.itlong.bean.ProductInfo;
import com.itlong.bean.UserGoodsAddress;
import com.itlong.constant.Cache;
import com.itlong.dto.ShopInfo;
import com.itlong.mapper.*;
import com.itlong.service.CartInfoService;
import com.itlong.service.OrderInfoService;
import com.itlong.service.UserInfoService;
import com.itlong.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    UserGoodsAddressMapper userGoodsAddressMapper;

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    CartInfoService cartInfoService;

    @Autowired
    UserInfoService userInfoService;

    @Override
//    @Cacheable(value = Cache.ORDER_INFO,key = "#userId")
    @Transactional(readOnly = true)
    public List<OrderInfo> selectOrderInfoByUgdId(String userId) {
        List<String> ugdIds = userGoodsAddressMapper.selectUgdId(userId);
        return orderInfoMapper.selectOrderInfoByUgdId(ugdIds);
    }


    @Override
    @CacheEvict(value = Cache.ORDER_INFO,key = "#userId",beforeInvocation = true)
    @Transactional
    public String UpdateCartWithOrder(String userId, String orderId,BigDecimal totalPrice) {
        List<OrderInfo> orderInfos = orderInfoMapper.selectOrderById(orderId);
        if(orderInfos==null) return "2";
        //测试余额是否充足
        BigDecimal balance=userInfoService.selectByPrimaryKey(userId).getBalance();
        String s = userInfoService.withdrawalMoney(userId, totalPrice);
        if(s.equals("notEnough"))  return s;

        //修改订单为已支付
        orderInfoMapper.updateOrderById(orderId,1);
        return s;
    }

    @Override
    public int insertOrders(List<OrderInfo> list) {
        return orderInfoMapper.insertOrders(list);
    }

    @Override
    public List<OrderInfo> selectOrderById(String orderId) {
        return orderInfoMapper.selectOrderById(orderId);
    }
}
