package com.hiki.album.resultVO;

import lombok.Data;

/**
 * @author ：hiki
 * 2019/8/19 10:45
 */
@Data
public class PhotoShowVO {
    private int pid;
    private int acid;
    private String description;
    private String link;
    private int status;
    private String time;
    private String created;
}
