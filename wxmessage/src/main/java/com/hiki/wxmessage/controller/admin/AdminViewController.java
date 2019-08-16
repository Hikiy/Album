package com.hiki.wxmessage.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：hiki
 * 2019/8/16 9:45
 */
@Controller
@RequestMapping("/admin")
public class AdminViewController {
    @GetMapping("/albumedit")
    public String albumedit(){
        return "album_edit";
    }

    @GetMapping("/upload")
    public String index(){
        return "upload_photo";
    }

    @GetMapping("/albumcategoryedit")
    public String albumcategoryedit(){
        return "album_category_edit";
    }
}
