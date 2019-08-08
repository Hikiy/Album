package com.hiki.wxmessage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：hiki
 * 2019/8/3 17:08
 */
@Controller
@RequestMapping("/album")
@Slf4j
public class AlbumShowController {

    /**
     * 相册首页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "album_index";
    }

}
