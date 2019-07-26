package com.hiki.wxmessage.entity;

import lombok.Data;

/**
 * @author ：hiki
 * 2019/7/25 17:27
 */
@Data
public class InMessageEntity {
    // 开发者微信号
    protected String FromUserName;
    // 发送方帐号（一个OpenID）
    protected String ToUserName;
    // 消息创建时间
    protected Long CreateTime;
    /**
     * 消息类型
     * text 文本消息
     * image 图片消息
     * voice 语音消息
     * video 视频消息
     * music 音乐消息
     */
    protected String MsgType;
    // 消息id
    protected Long MsgId;
    // 文本内容
    private String Content;
    // 图片链接（由系统生成）
    private String PicUrl;
    // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
    private String MediaId;
}
