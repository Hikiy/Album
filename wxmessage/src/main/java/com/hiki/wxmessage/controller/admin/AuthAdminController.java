package com.hiki.wxmessage.controller.admin;

import com.hiki.wxmessage.entity.Users;
import com.hiki.wxmessage.resultVO.ResultVO;
import com.hiki.wxmessage.service.AuthService;
import com.hiki.wxmessage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
        Boolean result = authService.register(username, password, name);
        if( !result ){
            return ResultUtil.db_error();
        }
        return ResultUtil.success_return("");
    }

    /**
     * 删除账号
     * @param uid
     * @return
     */
    @PostMapping("/deletebyuid")
    public ResultVO deletebyuid(HttpServletRequest request, @RequestParam("uid") int uid){
        Users user = authService.getUserByUid(uid);
        if( user == null ){
            return ResultUtil.db_error();
        }
        Boolean result = authService.deleteByUid(user.getUid());

        if( !result ){
            return ResultUtil.db_error();
        }

        int currentuid = (int)request.getSession().getAttribute("uid");
        if( currentuid == uid ){
            request.setAttribute("uid",-1);
        }
        return ResultUtil.success_return("");
    }

    /**
     * 获取账号列表
     * @return
     */
    @GetMapping("/getuserslist")
    public ResultVO getuserslist(){
        return ResultUtil.success_return(authService.getUsersList());
    }

    @PostMapping("/updatepassword")
    public ResultVO updatepassword(@RequestParam("uid") int uid, @RequestParam("oldpassword") String oldPassword, @RequestParam("newpassword") String newPassword){
        Users user = authService.getUserByUid(uid);
        if( user == null ){
            return ResultUtil.db_error();
        }

        Boolean result = authService.updatePassword(uid, oldPassword, newPassword);

        if( !result ){
            return ResultUtil.db_error();
        }
        return ResultUtil.success_return("");
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @GetMapping("/logout")
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
