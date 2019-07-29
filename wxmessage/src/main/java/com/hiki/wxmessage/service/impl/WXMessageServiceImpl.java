package com.hiki.wxmessage.service.impl;


import com.hiki.wxmessage.entity.InMessageEntity;
import com.hiki.wxmessage.entity.OutMessageEntity;
import com.hiki.wxmessage.service.WXMessageService;
import com.hiki.wxmessage.util.MatcheUtil;
import com.hiki.wxmessage.util.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

/**
 * @author ：hiki
 * 2019/7/25 17:45
 */
@Service
public class WXMessageServiceImpl implements WXMessageService {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 回复文本消息
     * @author Hiki
     * @param inMessageEntity
     * @return
     */
    @Override
    public OutMessageEntity getText(InMessageEntity inMessageEntity) {
        String response="";
        String msg = inMessageEntity.getContent();

        OutMessageEntity out = new OutMessageEntity();

        //天气关键字回复
        if( MatcheUtil.matches("天气",msg) ){
            response = WeatherUtil.getWeather("");
        }else {
            Matcher m = MatcheUtil.matchesGroup(".*\\s*天气.*",msg);
            if( m.find() ) {
                String[] ms = m.group(0).split("天气");
                String city = ms[0].trim();

                if (!redisTemplate.hasKey("WEATHER_" + city)) {
                    redisTemplate.opsForValue().set("WEATHER_" + city, WeatherUtil.getWeather(city), 3600000, TimeUnit.MILLISECONDS);
                }

                if (!redisTemplate.hasKey("WEATHER_" + city)) {
                    throw new RuntimeException("redis写入失败啦！");
                }
                response = redisTemplate.opsForValue().get("WEATHER_" + city).toString();
            }
        }

        out.setContent(response);
        out.setCreateTime(new Date().getTime());
        out.setMsgType("text");
        return out;
    }
}
