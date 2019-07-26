package com.hiki.wxmessage.controller;

import com.hiki.wxmessage.entity.InMessageEntity;
import com.hiki.wxmessage.entity.OutMessageEntity;
import com.hiki.wxmessage.service.WXMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hiki
 * 2019/7/25 17:18
 */
@RestController
@Slf4j
public class WXMessageController {
    @Autowired
    private WXMessageService wxMessageService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 接收微信的消息
     * @param inMessage
     * @return
     */
    @PostMapping("/recevice")
    public OutMessageEntity recevice(InMessageEntity inMessage){
        if( inMessage == null || inMessage.getMsgType().isEmpty()){
            log.error("微信传来的信息错误！");
            throw new RuntimeException("微信传来的信息错误！");
        }

        String userName = inMessage.getFromUserName();
        String myName = inMessage.getToUserName();

        OutMessageEntity out = new OutMessageEntity();

        //接收文字消息
        if( inMessage.getMsgType().equals("text") ){
            out = wxMessageService.getText(inMessage);
        }

        out.setFromUserName(myName);
        out.setToUserName(userName);

        return out;
    }
}
