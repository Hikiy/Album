package com.hiki.album.enums;

import lombok.Getter;

/**
 * @author ：hiki
 * 2019/8/5 17:17
 */
@Getter
public enum PhotoTypeEnums {
    ALBUM(1,"album","相册");

    private int code;
    private String type;
    private String name;
    PhotoTypeEnums(int code, String type, String name){
        this.code = code;
        this.type = type;
        this.name = name;
    }
}
