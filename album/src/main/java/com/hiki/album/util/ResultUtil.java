package com.hiki.album.util;

import com.hiki.album.resultVO.ResultVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：hiki
 * 2019/8/3 10:50
 */
public class ResultUtil {
    private static Map<String, String> map= new HashMap();

    //成功返回
    public static ResultVO success_return(Object data){
        ResultVO result = new ResultVO();
        result.setRet(0);
        result.setMsg("ok");
        result.setData(data);
        return result;
    }

    //数据库错误
    public static ResultVO db_error(){
        ResultVO result = new ResultVO();
        result.setRet(-1);
        result.setMsg("db error");
        return result;
    }

    //缺参
    public static ResultVO miss_param(){
        ResultVO result = new ResultVO();
        result.setRet(-2);
        result.setMsg("miss param");
        return result;
    }

    //上传文件失败
    public static ResultVO upload_error(){
        ResultVO result = new ResultVO();
        result.setRet(-3);
        result.setMsg("upload error");
        return result;
    }

    //删除文件失败
    public static ResultVO delete_error(){
        ResultVO result = new ResultVO();
        result.setRet(-4);
        result.setMsg("delete error");
        return result;
    }

    //code重复
    public static ResultVO code_exist(){
        ResultVO result = new ResultVO();
        result.setRet(-5);
        result.setMsg("code exist");
        return result;
    }

    //账号或密码错误
    public static ResultVO login_error(){
        ResultVO result = new ResultVO();
        result.setRet(-6);
        result.setMsg("login error");
        return result;
    }

    //账号已存在
    public static ResultVO username_exist(){
        ResultVO result = new ResultVO();
        result.setRet(-7);
        result.setMsg("username exist");
        return result;
    }

    //非法操作
    public static ResultVO illegal_option(){
        ResultVO result = new ResultVO();
        result.setRet(-8);
        result.setMsg("illegal option");
        return result;
    }

    //重复登录
    public static ResultVO login_repeat(){
        ResultVO result = new ResultVO();
        result.setRet(-9);
        result.setMsg("login repeat");
        return result;
    }

    //验证码错误
    public static ResultVO code_error(){
        ResultVO result = new ResultVO();
        result.setRet(-10);
        result.setMsg("code error");
        return result;
    }
}
