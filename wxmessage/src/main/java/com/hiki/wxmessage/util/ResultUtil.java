package com.hiki.wxmessage.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：hiki
 * 2019/8/3 10:50
 */
public class ResultUtil {
    private static Map<String, String> map= new HashMap();

    //成功
    public static Map<String, String> success_return(String data){
        map.put("ret", "0");
        map.put("data", data);

        return map;
    }

    //数据库错误
    public static Map<String, String> db_error(){
        map.put("ret", "-1");
        map.put("data", "db error");

        return map;
    }

    //缺参
    public static Map<String, String> miss_param(){
        map.put("ret", "-2");
        map.put("data", "miss param");

        return map;
    }

    //上传文件到OSS错误
    public static Map<String, String> upload_error(){
        map.put("ret", "-3");
        map.put("data", "upload error");

        return map;
    }

    //从OSS删除文件错误
    public static Map<String, String> delete_error(){
        map.put("ret", "-4");
        map.put("data", "delete error");

        return map;
    }
}
