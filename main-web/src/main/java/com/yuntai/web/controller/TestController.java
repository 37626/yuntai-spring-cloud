package com.yuntai.web.controller;

import com.yuntai.web.domain.entity.YtUser;
import com.yuntai.web.service.YtUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.BaseResponse;

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

    @PostMapping(value = "/getDetail")
    public BaseResponse getDetail(Integer i) throws Exception {
        YtUser user = new YtUser();
        user.setAccount( "呼呼哈晚点" );
        user.setAction( 1 );
        YtUser userResult = userService.getDetail(user.getId());
        return BaseResponse.ok( userResult );
    };

    @PostMapping(value = "/getList")
    public BaseResponse<List<YtUser>> getList() throws Exception {
        YtUser user = new YtUser();
        user.setAccount( "呼呼哈晚点" );
        user.setAction( 1 );
        return BaseResponse.ok( userService.getList(user) );
    };

    @PostMapping(value = "/add")
    public BaseResponse add(Integer i) throws Exception {
        YtUser user = new YtUser();
        user.setAccount( "呼呼哈晚点" );
        user.setAction( 1 );
        YtUser userResult = userService.add(user);
        return BaseResponse.ok( userResult );
    };

    /*
     * @Description: 测试异常
     * @Author: YuanYe
     * @Date: 2020/6/17 2:12 下午
     * @Param:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping(value = "/ex")
    public BaseResponse ex() throws Exception {
        int i = 1/0;
        return BaseResponse.ok( "返回成功" );
    };

    @PostMapping(value = "/testHandler")
    public void testExceptionHandler()  {
        throw new NullPointerException();
    };

    @PostMapping(value = "/log")
    public void logTest() throws Exception {
        //级别从低至高，可在nacos里面通过level动态调节
        log.debug( "======debug======" );
        log.info( "======info======" );
        log.warn( "======warn======" );
        log.error( "======error======" );
    };

}
