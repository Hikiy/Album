package com.hiki.album.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：hiki
 * 2019/8/19 9:44
 */
public class TimeUtil {
    public static int strToTime(String str){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long ltime = sdf.parse(str).getTime()/1000;
            return (int)ltime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String TimeToStr(int time){
        long ltime = (long)time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(ltime*1000));
    }
}
