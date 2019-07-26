package com.hiki.wxmessage.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * @author ：hiki
 * 2019/7/25 15:11
 */
@JacksonXmlRootElement
@Data
public class OutMessageEntity {
    // 发送方的账号
    private String FromUserName;

    // 接收方的账号(OpenID)
    private String ToUserName;

    // 消息创建时间
    private Long CreateTime;
    /**
     * 消息类型
     * text 文本消息
     * image 图片消息
     * voice 语音消息
     * video 视频消息
     * music 音乐消息
     * news 图文消息
     */
    private String MsgType;
    // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
//    @XmlElementWrapper(name="Image")
//    private String[] MediaId ;

    // 文本内容
    private String Content;
}
