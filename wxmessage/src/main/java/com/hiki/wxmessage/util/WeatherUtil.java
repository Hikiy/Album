package com.hiki.wxmessage.util;

import com.google.gson.Gson;
import com.hiki.wxmessage.entity.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：hiki
 * 2019/7/26 9:53
 */
@Slf4j
public class WeatherUtil {

    public static String getWeather(String city) {
        String msg;
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("https://www.tianqiapi.com/api/?version=v1&city=" + city, String.class);
        Weather weather = new Weather();
        try {
            weather = gson.fromJson(response, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("天气获取失败！");
        }

        msg = "今日" + weather.getCity() + "天气：" + weather.getData().get(0).getWea() + "\n" + "气温范围："
                + weather.getData().get(0).getTem2() + "~" + weather.getData().get(0).getTem1()
                + "。平均气温：" + weather.getData().get(0).getTem() + "\n" + "空气质量："
                + weather.getData().get(0).getAir_level() + "。" + weather.getData().get(0).getAir_tips()
                + "\n 更新时间：" + weather.getUpdate_time();
        return msg;
    }
}
