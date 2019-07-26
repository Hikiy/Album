package com.hiki.wxmessage.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hiki
 * 2019/7/25 14:53
 */
@Data
public class Weather {
    private String city;
    private String update_time;
    private List<WeatherDetail> data = new ArrayList<>();
}
