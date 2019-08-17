package com.hiki.wxmessage.controller.auth;

import com.hiki.wxmessage.entity.Users;
import com.hiki.wxmessage.resultVO.ResultVO;
import com.hiki.wxmessage.service.AuthService;
import com.hiki.wxmessage.service.CodeService;
import com.hiki.wxmessage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：hiki
 * 2019/8/16 16:39
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private CodeService codeService;

    /**
     * 登录
     * @param request
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResultVO login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password){
        //验证码错误
        Boolean checkCode = codeService.checkVerify(request);
        if( !checkCode ){
            return ResultUtil.code_error();
        }

        Object uid = request.getSession().getAttribute("uid");
        if( uid !=null && (int)uid > 0 ){
            return ResultUtil.login_repeat();
        }
        //检查账号是否存在
        Users user = authService.getUserByUsername(username);
        if( user == null ){
            return ResultUtil.login_error();
        }

        //检查密码是否正确
        Boolean correct = authService.checkPassword(username, password, user.getCreated(), user.getPassSalt(), user.getPassHash());
        if( !correct ){
            return ResultUtil.login_error();
        }

        //写入Session
        try{
            request.getSession().setAttribute("uid",user.getUid());
        }catch (Exception e){
            return ResultUtil.login_error();
        }

        return ResultUtil.success_return("");
    }

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

    /**
     * 登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public ResultVO logout(HttpServletRequest request){
        Object object = request.getSession().getAttribute("uid");
        if( object == null){
            return ResultUtil.illegal_option();
        }
        int uid = Integer.valueOf(request.getSession().getAttribute("uid").toString());
        if( uid < 1 ){
            return  ResultUtil.illegal_option();
        }
        request.getSession().setAttribute("uid",-1);
        return ResultUtil.success_return("");
    }
}
