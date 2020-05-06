package com.itlong.service.impl;

import com.itlong.bean.UserGoodsAddress;
import com.itlong.constant.Cache;
import com.itlong.mapper.UserGoodsAddressMapper;
import com.itlong.service.UserGoodsAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userGoodsAddressService")
public class UserGoodsAddressServiceImpl implements UserGoodsAddressService {

    @Autowired
    UserGoodsAddressMapper userGoodsAddressMapper;


    @Override
    @Cacheable(value = Cache.DELIVERY_INFO,key = "#userId")
    public List<UserGoodsAddress> selectByUserId(String userId) {
        return userGoodsAddressMapper.selectByUserId(userId);
    }

    @Override
    @Transactional
    @CacheEvict(value = Cache.DELIVERY_INFO,key = "#userGoodsAddress.userId",beforeInvocation = true)
    public int saveUserAddress(UserGoodsAddress userGoodsAddress) {
        return  userGoodsAddressMapper.insert(userGoodsAddress);
    }

    @Override
    public List<String> selectUgdId(String userId) {
        return userGoodsAddressMapper.selectUgdId(userId);
    }

    @Override
    @Transactional
    @CacheEvict(value = Cache.DELIVERY_INFO,key = "#userId" )
    public int updateByPrimaryKey(String newUgdId, String userId) {
        UserGoodsAddress news = userGoodsAddressMapper.selectByPrimaryKey(newUgdId);
        UserGoodsAddress olds = userGoodsAddressMapper.selectByDefaultAddress(userId);
        news.setDefaultAddress((byte)1);
        olds.setDefaultAddress((byte)0);
        userGoodsAddressMapper.updateByPrimaryKey(news);
        return userGoodsAddressMapper.updateByPrimaryKey(olds);
    }

    @Override
    @Transactional
    @CacheEvict(value = Cache.DELIVERY_INFO,key = "#userGoodsAddress.userId",beforeInvocation = true)
    public int updateUserAddress(UserGoodsAddress userGoodsAddress) {
        UserGoodsAddress defaultAddress =userGoodsAddressMapper.selectByDefaultAddress(userGoodsAddress.getUserId());
        if(defaultAddress!=null){
            defaultAddress.setDefaultAddress((byte)0);
            userGoodsAddressMapper.updateDefaultAddress(defaultAddress);
        }
        return saveUserAddress(userGoodsAddress);
    }


    @Override
    @CacheEvict(value = Cache.DELIVERY_INFO,beforeInvocation = true,allEntries = true)
    public int deleteByPrimaryKey(String ugdId,String userId) {
        return userGoodsAddressMapper.deleteByPrimaryKey(ugdId,userId);
    }

    @Override
    public int selectRow(String userId) {
        return userGoodsAddressMapper.selectRow(userId);
    }

    @Override
    public UserGoodsAddress selectByDefaultAddress(String userId) {
        return userGoodsAddressMapper.selectByDefaultAddress( userId);
    }




}
