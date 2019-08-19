package com.hiki.wxmessage.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：hiki
 * 2019/8/17 11:15
 */
@Controller
@RequestMapping("/auth")
public class AuthViewController {
    @GetMapping("/loginindex")
    public String loginIndex(HttpServletRequest request){
        //检查是否重复登录
        Object uid = request.getSession().getAttribute("uid");
        if( uid !=null && (int)uid > 0 ){
            return "redirect:/admin/albumcategorymanagement";
        }
        return "login";
    }
}
