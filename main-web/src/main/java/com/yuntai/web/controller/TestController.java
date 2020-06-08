package com.yuntai.web.controller;

import com.yuntai.web.domain.entity.YtUser;
import com.yuntai.web.service.YtUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyemustang
 * @title
 * @description
 * @Data 20/5/28 下午5:22
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    YtUserService userService;

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello YT";
    };

    @GetMapping(value = "/yuntai")
    public void yuntai(){
        YtUser user = new YtUser();
        user.setName( "呼呼哈晚点" );
        user.setAction( 1 );
        //加个人
        userService.testTransaction(user);
        //记个账

    };

}
