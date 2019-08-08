package com.hiki.wxmessage.resultVO;

import lombok.Data;

/**
 * @author ：hiki
 * 2019/8/7 14:14
 */
@Data
public class ResultVO<T> {
    /**
     * 返回码
     */
    private Integer ret;
    /**
     * 提示信息
     */
    private String msg;

    private T data;
}
