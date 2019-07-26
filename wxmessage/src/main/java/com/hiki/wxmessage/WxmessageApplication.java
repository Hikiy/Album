package com.hiki.wxmessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WxmessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxmessageApplication.class, args);
    }

}
