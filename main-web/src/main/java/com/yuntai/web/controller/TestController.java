package com.yuntai.web.controller;

import com.yuntai.web.domain.entity.YtUser;
import com.yuntai.web.service.YtUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuanyemustang
 * @title
 * @description
 * @Data 20/5/28 下午5:22
 */
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    @Autowired
    YtUserService userService;

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello YT";
    };

    @GetMapping(value = "/yuntai")
    public String yuntai(List<Integer> i) throws Exception {
        YtUser user = new YtUser();
        user.setAccount( "呼呼哈晚点" );
        user.setAction( 1 );
        userService.add(user);
        userService.getDetail(user.getId());
        return "";
    };

    @GetMapping(value = "/log")
    public void logTest() throws Exception {
        //级别从低至高，可在nacos里面通过level动态调节
        log.debug( "======debug======" );
        log.info( "======info======" );
        log.warn( "======warn======" );
        log.error( "======error======" );
    };

}
