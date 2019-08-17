package com.hiki.wxmessage.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：hiki
 * 2019/8/17 11:15
 */
@Controller
@RequestMapping("/auth")
public class AuthViewController {
    @GetMapping("/loginindex")
    public String loginIndex(){
        return "login";
    }
}
