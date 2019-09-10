package com.hiki.album.resultVO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author ：hiki
 * 2019/9/10 10:07
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResultPageVO<T> {
    /**
     * 返回码
     */
    private Integer ret;
    /**
     * 提示信息
     */
    private String msg;

    private int count;

    private T data;
}
