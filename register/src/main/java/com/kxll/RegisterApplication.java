package com.kxll;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegisterApplication {
    public static void main(String[] args){
        new SpringApplicationBuilder(RegisterApplication.class).run(args);
    }

}
