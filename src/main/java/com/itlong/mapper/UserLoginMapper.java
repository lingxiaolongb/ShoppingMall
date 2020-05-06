package com.itlong.mapper;

import com.itlong.bean.UserLogin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userLoginMapper")
public interface UserLoginMapper {
    int deleteByPrimaryKey(String username);

    int insert(UserLogin record);

    UserLogin selectByPrimaryKey(String username);

    List<UserLogin> selectAll();

    int updateByPrimaryKey(UserLogin record);

    UserLogin selectCheckAccount(String username);

    UserLogin selectByTargetName(String targetName);
}