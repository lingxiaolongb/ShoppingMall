package com.itlong.service.impl;

import com.itlong.bean.UserInfo;
import com.itlong.constant.Cache;
import com.itlong.mapper.UserInfoMapper;
import com.itlong.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectInfoByUsername(String username) {
        return userInfoMapper.selectInfoByUsername(username);
    }

    @Override
    @CacheEvict(value = Cache.USER_INFO,key = "#record.userId",beforeInvocation = true)
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    @Cacheable(value = Cache.USER_INFO,key = "#userId")
    public UserInfo selectByPrimaryKey(String userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional
    @CacheEvict(value = Cache.USER_INFO,key = "#userId",beforeInvocation = true)
    public void rechargeMoney(String userId,BigDecimal money) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        userInfo.setBalance(userInfo.getBalance().add(money));
        userInfoMapper.updateByPrimaryKey(userInfo);
    }

    @Override
    @Transactional
    @CacheEvict(value = Cache.USER_INFO,key = "#userId",beforeInvocation = true)
    public String withdrawalMoney(String userId,BigDecimal money) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        BigDecimal bd1=userInfo.getBalance();
        if(bd1.compareTo(money)==-1) return "notEnough";
        userInfo.setBalance(bd1.subtract(money));
        userInfoMapper.updateByPrimaryKey(userInfo);
        return "ok";
    }

    @Override
    @Transactional
    @CacheEvict(value = Cache.USER_INFO,allEntries = true,beforeInvocation = true)
    public String transferAccount(String sourceName, String targetName, BigDecimal money) {
        String msg = withdrawalMoney(sourceName, money);
        if(!msg.equals("ok")) return msg;
        rechargeMoney(targetName,money);
        return "ok";
    }
}
