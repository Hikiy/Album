package com.hiki.wxmessage.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：hiki
 * 2019/8/17 10:33
 */
@Controller
public class ErrorViewController {
    @RequestMapping("/auth_error")
    public String authError(){
        return "auth_error";
    }
}
