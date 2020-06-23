package com.yuntai.web.config;


import com.yuntai.web.domain.entity.YtPerms;
import com.yuntai.web.domain.entity.YtRole;
import com.yuntai.web.model.User;
import com.yuntai.web.service.impl.YtPermsServiceImpl;
import com.yuntai.web.service.impl.YtRoleServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    YtRoleServiceImpl roleService;
    @Autowired
    YtPermsServiceImpl permsService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取登录认证后的登录对象
        Subject subjct = SecurityUtils.getSubject();
        User user = (User)subjct.getPrincipal();

        System.err.println("授权认证");

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }

   /* protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.err.println("登录认证");

        UsernamePasswordToken token =  (UsernamePasswordToken)authenticationToken;

        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");


        //从数据库查询账户
       // User user = userService.getById(token.getUsername());
        //判断账号
        if(user == null){
            return null;
            //返回null默认抛出账户不存在的异常
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }*/
}
