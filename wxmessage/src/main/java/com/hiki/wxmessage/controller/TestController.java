package com.hiki.wxmessage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hiki
 * 2019/8/6 14:23
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return System.getProperty("user.dir");
    }
}
