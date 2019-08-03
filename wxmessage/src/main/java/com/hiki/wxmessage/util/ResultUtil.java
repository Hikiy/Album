package com.hiki.wxmessage.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：hiki
 * 2019/8/3 10:50
 */
public class ResultUtil {
    private static Map<String, String> map= new HashMap();
    public static Map<String, String> success_return(String data){
        map.put("ret", "0");
        map.put("data", data);

        return map;
    }
    public static Map<String, String> db_error(){
        map.put("ret", "-1");
        map.put("data", "db error");

        return map;
    }

    public static Map<String, String> miss_param(){
        map.put("ret", "-2");
        map.put("data", "miss param");

        return map;
    }

    public static Map<String, String> upload_error(){
        map.put("ret", "-3");
        map.put("data", "upload error");

        return map;
    }
}
