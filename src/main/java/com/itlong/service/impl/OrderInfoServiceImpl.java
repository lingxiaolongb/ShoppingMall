package com.itlong.service.impl;

import com.itlong.bean.OrderInfo;
import com.itlong.bean.ProductInfo;
import com.itlong.bean.UserGoodsAddress;
import com.itlong.constant.Cache;
import com.itlong.dto.ShopInfo;
import com.itlong.mapper.CartInfoMapper;
import com.itlong.mapper.OrderInfoMapper;
import com.itlong.mapper.ProductInfoMapper;
import com.itlong.mapper.UserGoodsAddressMapper;
import com.itlong.service.CartInfoService;
import com.itlong.service.OrderInfoService;
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

    @Override
    @Cacheable(value = Cache.ORDER_INFO,key = "#userId")
    @Transactional(readOnly = true)
    public List<OrderInfo> selectOrderInfoByUgdId(String userId) {
        List<String> ugdIds = userGoodsAddressMapper.selectUgdId(userId);
        return orderInfoMapper.selectOrderInfoByUgdId(ugdIds);
    }

    @Override
    @CacheEvict(value = Cache.ORDER_INFO,key = "#userId",beforeInvocation = true)
    @Transactional
    public void UpdateCartWithOrder(String ugdId,String userId, List<ShopInfo> tempShopInfos) {
        //需要用户id,多个商品id,收货地址id
        List<String> prodIds=new ArrayList<>();
        for(ShopInfo si:tempShopInfos){
            prodIds.add(si.getProdId());
        }
        //1.根据保存的收货地址id查询收货地址具体信息
        UserGoodsAddress ugdInfo = userGoodsAddressMapper.selectByPrimaryKey(ugdId);
        //2.查询根据id商品的具体信息
        List<ProductInfo> prodInfos = productInfoMapper.selectByProdIds(prodIds);
        //3.根据商品id和用户id购物车里的商品去除
        cartInfoService.deleteItemByProdId(userId,prodIds);
        //4.插入订单
        List<OrderInfo> orderList=new ArrayList<>();
        for(int i=0;i<prodIds.size();i++){
            int num = tempShopInfos.get(i).getNum();
            OrderInfo orderInfo=new OrderInfo();
            orderInfo.setOrderId(UuidUtils.getOrderUUid());
            orderInfo.setProdId(tempShopInfos.get(i).getProdId());
            orderInfo.setProdName(prodInfos.get(i).getProdName());
            orderInfo.setQuantity(num);
            orderInfo.setTotalPrice(new BigDecimal(num).multiply(tempShopInfos.get(i).getPrice()));
            orderInfo.setUgdId(ugdId);
            orderInfo.setConsignee(ugdInfo.getConsignee());
            orderInfo.setExpressFee((byte)0);
            orderInfo.setOrderStatus((byte)0);
            orderInfo.setPurchaseTime(new Date());
            orderList.add(orderInfo);
        }
        orderInfoMapper.insertOrders(orderList);
    }
}
