package com.itlong.service.impl;


import com.itlong.bean.UserLogin;
import com.itlong.mapper.UserLoginMapper;
import com.itlong.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserLoginMapper userLoginMapper;

    @Override
    public UserLogin selectCheckAccount(String username) {
        return userLoginMapper.selectCheckAccount(username);
    }

    @Override
    public UserLogin selectByTargetName(String targetName) {
        return userLoginMapper.selectByTargetName(targetName);
    }
}
