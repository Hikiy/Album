package com.hiki.wxmessage.entity;

import lombok.Data;

/**
 * @author ：hiki
 * 2019/7/25 14:57
 */
@Data
public class WeatherDetail {
    //天气
    private String wea;

    //空气质量
    private String air_level;
    //建议
    private String air_tips;

    //最高气温
    private String tem1;
    //最低气温
    private String tem2;
    //平均气温
    private String tem;
}
