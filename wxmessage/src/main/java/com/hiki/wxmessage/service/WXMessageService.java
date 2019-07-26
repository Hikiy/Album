package com.hiki.wxmessage.service;

import com.hiki.wxmessage.entity.InMessageEntity;
import com.hiki.wxmessage.entity.OutMessageEntity;

/**
 * @author ：hiki
 * 2019/7/25 17:49
 */
public interface WXMessageService {
    public OutMessageEntity getText(InMessageEntity inMessageEntity);
}
