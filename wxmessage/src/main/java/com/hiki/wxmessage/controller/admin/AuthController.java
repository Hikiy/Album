package com.hiki.wxmessage.controller.admin;

import com.hiki.wxmessage.resultVO.ResultVO;
import com.hiki.wxmessage.util.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hiki
 * 2019/8/16 16:39
 */
@RestController
@RequestMapping("/admin")
public class AuthController {

    @RequestMapping("/login")
    public ResultVO login(@RequestParam("username") String username,@RequestParam("password") String password){

        return ResultUtil.success_return("");
    }
}
