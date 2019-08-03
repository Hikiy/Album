package com.hiki.wxmessage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：hiki
 * 2019/8/3 17:08
 */
@Controller
@RequestMapping("/album")
public class AlbumController {
    @GetMapping("/index")
    public String index(){
        return "album_index";
    }
}
