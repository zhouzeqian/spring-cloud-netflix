package com.kxll.service;

import org.springframework.stereotype.Component;

@Component
public class TestServiceFallback implements TestService{

    @Override
    public String getHello(String name) {
        return "系统无法访问";
    }
}
