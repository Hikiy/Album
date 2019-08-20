package com.hiki.album.controller;

import com.hiki.album.service.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @author ：hiki
 * 2019/7/29 14:46
 */
@Controller
@RequestMapping("/image")
@Slf4j
public class ImageController {
    @Autowired
    private OSSService ossService;

    /**
     * 从阿里云获取图片并显示到前端
     * @param request
     * @return
     */
    @GetMapping(value = "/showimage", produces = "image/jpeg")
    @ResponseBody
    public byte[] showImage(HttpServletRequest request){
        String filename = request.getParameter("filename");
        if( filename.isEmpty()){
            return null;
        }
        byte[] bytes = null;

        try{
            bytes = ossService.getFileByte(filename);
        }catch (IOException e){
            log.error("OSS获取图片失败:" + e.toString());
        }

        return bytes;
    }
}
