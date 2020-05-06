package com.itlong.service;

import com.itlong.bean.UserLogin;

public interface UserLoginService {

    UserLogin selectCheckAccount(String username);

    UserLogin selectByTargetName(String targetName);
}
