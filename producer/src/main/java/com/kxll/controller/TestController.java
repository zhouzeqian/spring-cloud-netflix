package com.kxll.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping("hello")
    public String hello(String name, HttpServletRequest request){
        return "你好:"+name+" 服务地址"+request.getLocalAddr()+":"+request.getLocalPort()+" 服务名:"+request.getLocalName();
    }
}
