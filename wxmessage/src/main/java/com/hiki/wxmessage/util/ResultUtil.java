package com.hiki.wxmessage.util;

import com.hiki.wxmessage.resultVO.ResultVO;

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
}
