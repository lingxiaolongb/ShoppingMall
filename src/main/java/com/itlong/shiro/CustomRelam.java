package com.itlong.shiro;

import com.itlong.bean.UserLogin;
import com.itlong.mapper.UserInfoMapper;
import com.itlong.mapper.UserLoginMapper;
import com.itlong.service.UserLoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRelam extends AuthorizingRealm {

    @Autowired
    UserLoginMapper userLoginMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if(token.getPrincipal()==null)
            return null;
        String name=token.getPrincipal().toString();
        UserLogin ug=userLoginMapper.selectCheckAccount(name);
        if(ug==null){
            return null;
        }else{
            return  new SimpleAuthenticationInfo(name,ug.getPassword().toString(),getName());
        }

    }
}
