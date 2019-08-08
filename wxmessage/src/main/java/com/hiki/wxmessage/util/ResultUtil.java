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

    //成功
//    public static Map<String, String> success_return(String data){
//        map.put("ret", "0");
//        map.put("data", data);
//
//        return map;
//    }

    //数据库错误
//    public static Map<String, String> db_error(){
//        map.put("ret", "-1");
//        map.put("data", "db error");
//
//        return map;
//    }
    public static ResultVO db_error(){
        ResultVO result = new ResultVO();
        result.setRet(-1);
        result.setMsg("db error");
        return result;
    }

    //缺参
//    public static Map<String, String> miss_param(){
//        map.put("ret", "-2");
//        map.put("data", "miss param");
//
//        return map;
//    }
    public static ResultVO miss_param(){
        ResultVO result = new ResultVO();
        result.setRet(-2);
        result.setMsg("miss param");
        return result;
    }


    //上传文件到OSS错误
//    public static Map<String, String> upload_error(){
//        map.put("ret", "-3");
//        map.put("data", "upload error");
//
//        return map;
//    }
    public static ResultVO upload_error(){
        ResultVO result = new ResultVO();
        result.setRet(-3);
        result.setMsg("upload error");
        return result;
    }

    //从OSS删除文件错误
//    public static Map<String, String> delete_error(){
//        map.put("ret", "-4");
//        map.put("data", "delete error");
//
//        return map;
//    }
    public static ResultVO delete_error(){
        ResultVO result = new ResultVO();
        result.setRet(-4);
        result.setMsg("delete error");
        return result;
    }


    public static ResultVO success_return(Object data){
        ResultVO result = new ResultVO();
        result.setRet(0);
        result.setMsg("ok");
        result.setData(data);
        return result;
    }
}
