package com.kxll.controller;

import com.kxll.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    TestService testService;
    @RequestMapping("feignHello")
    public String feignHello(String name){
     return testService.getHello(name);
    }
}
