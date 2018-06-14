package com.kxll.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "producer",fallback = TestServiceFallback.class)
public interface TestService {
    @RequestMapping(value = "/test/hello",method = RequestMethod.GET)
    public String getHello(@RequestParam(value = "name") String name);
}
