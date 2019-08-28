package com.hiki.album.resultVO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author ：hiki
 * 2019/8/7 14:14
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
