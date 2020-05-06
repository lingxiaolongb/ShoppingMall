package com.itlong.service;

import com.itlong.bean.UserInfo;

import java.math.BigDecimal;

public interface UserInfoService {

    UserInfo selectInfoByUsername(String username);
    int updateByPrimaryKey(UserInfo record);

    UserInfo selectByPrimaryKey(String userId);

    void rechargeMoney(String userId,BigDecimal money);

    String withdrawalMoney(String userId,BigDecimal money);

    String transferAccount(String sourceName, String targetName, BigDecimal money);

}
