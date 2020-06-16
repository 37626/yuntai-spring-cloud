package com.yuntai.web.controller;

import com.yuntai.web.base.AjaxResult;
import com.yuntai.web.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * @Description: shiro
 * @Author: ErMao
 * @Company: yuntai
 * @Date: 2020/6/16 0016 16:35
 **/

@RestController
public class ShiroController {
    /*
     * @Description: 登录认证
     * @Author: ErMao
     * @Date: 2020/6/16 0016 16:44
     * @Param: * @param account:
     * @param password:
     * @param model:
     * @Return: * @return: java.lang.String
     **/
    @PostMapping("/login")
    public AjaxResult dengLu(@RequestBody  User user){
        AjaxResult ajaxResult = new AjaxResult();
        //1.获取subject
        Subject subject =  SecurityUtils.getSubject();
        // 2.封装token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        //提交登录
        try{
            subject.login(token);
            ajaxResult.setCode(200);
        }catch(UnknownAccountException e){
            ajaxResult.setDesc("用户名不存在");
        }catch(IncorrectCredentialsException i){
            ajaxResult.setDesc("密码错误");
        }
        //没有抛出异常返回菜单

        return ajaxResult;

    }
}
