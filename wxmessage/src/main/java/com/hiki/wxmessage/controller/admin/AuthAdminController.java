package com.hiki.wxmessage.controller.admin;

import com.hiki.wxmessage.entity.Users;
import com.hiki.wxmessage.resultVO.ResultVO;
import com.hiki.wxmessage.service.AuthService;
import com.hiki.wxmessage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hiki
 * 2019/8/19 15:16
 */
@RestController
@RequestMapping("/admin/auth")
public class AuthAdminController {
    @Autowired
    private AuthService authService;
    /**
     * 注册
     * @param username
     * @param password
     * @param name
     * @return
     */
    @PostMapping("/register")
    public ResultVO register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("name") String name){
        Users user = authService.getUserByUsername(username);
        if( user != null ){
            return ResultUtil.username_exist();
        }
        authService.register(username, password, name);

        return ResultUtil.success_return("");
    }
}
