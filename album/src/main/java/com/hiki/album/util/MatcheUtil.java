package com.hiki.album.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：hiki
 * 2019/7/25 17:40
 */
public class MatcheUtil {
    public static Boolean matches(String param, String msg){
        return Pattern.matches(param,msg);
    }

    public static Matcher matchesGroup(String param, String msg){
        Pattern r = Pattern.compile(param);
        Matcher m = r.matcher(msg);
        return m;
    }
}
