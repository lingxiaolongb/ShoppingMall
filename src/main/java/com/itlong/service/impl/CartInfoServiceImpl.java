package com.itlong.service.impl;

import com.itlong.bean.CartInfo;
import com.itlong.bean.ProductInfo;
import com.itlong.bean.UserInfo;
import com.itlong.constant.Cache;
import com.itlong.mapper.CartInfoMapper;
import com.itlong.mapper.ProductInfoMapper;
import com.itlong.service.CartInfoService;
import com.itlong.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service("cartInfoService")
public class CartInfoServiceImpl implements CartInfoService {

    @Autowired
    CartInfoMapper cartInfoMapper;

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
   // @Cacheable(value = Cache.CART_INFO,key = "#userId")
    public List<CartInfo> selectByUserId(String userId) {
        return cartInfoMapper.selectByUserId(userId);
    }

    @Override
    public int updateNumById(CartInfo cartInfo, String prodId) {
        return cartInfoMapper.updateNumById(cartInfo,prodId);
    }

    @Override
    @CacheEvict(value = Cache.CART_INFO,key = "#userId")
    public int insert(String prodId, String userId) {
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(prodId);
        CartInfo cartInfo=new CartInfo();
        cartInfo.setCartId(UuidUtils.getCartUUid());
        cartInfo.setPrice(productInfo.getPrice());
        cartInfo.setProdId(prodId);
        cartInfo.setNum(1);
        cartInfo.setProdName(productInfo.getProdName());
        cartInfo.setUserId(userId);
        cartInfo.setAddTime(LocalDateTime.now());
        return cartInfoMapper.insert(cartInfo);
    }

    @Override
    public int selectRow() {
        return cartInfoMapper.selectRow() ;
    }

 //没有update,delete

    @Override
//    @Cacheable(value = Cache.CART_INFO,key = "#userId+':'+#prodId")
    public CartInfo selectProdDuplicate(String userId, String prodId) {
        return cartInfoMapper.selectProdDuplicate(userId,prodId);

    }

    @Override
    @CacheEvict(value = Cache.CART_INFO,allEntries = true,beforeInvocation = true)
    public int deleteItemByProdId(String userId, List<String> prodIds) {
        return cartInfoMapper.deleteItemByProdId(userId,prodIds);
    }

    @Override
    public List<CartInfo> selectCartInfoByUserId(String userId, List<String> prodIds) {
        return cartInfoMapper.selectCartInfoByUserId(userId,prodIds);
    }
}
