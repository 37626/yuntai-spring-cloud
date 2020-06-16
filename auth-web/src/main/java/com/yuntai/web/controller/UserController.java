package com.yuntai.web.controller;

import com.yuntai.web.domain.entity.YtUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/MyLogin")
    public String MyLogin(){
        return "/lem.html";
    }
    @RequestMapping("/abc")
    public String axios(){
        System.err.println("访问axios");
        return "/js/abc.html";
    }
    @RequestMapping("/vue")
    public String vue(){
        System.err.println("访问vue");
        return "/vue.js";
    }
}
