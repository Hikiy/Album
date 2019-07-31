package com.hiki.wxmessage.controller;

import com.hiki.wxmessage.service.OSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @author ：hiki
 * 2019/7/29 14:46
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private OSSService ossService;
    @GetMapping(value = "/showimagebylocal", produces = "image/jpeg")
    @ResponseBody
    public byte[] showImageByLocal(HttpServletRequest request){
//        String name = request.getParameter("name");
        String filename = request.getParameter("filename");
//        System.out.println(name);
//        System.out.println(filename);
//
//        if( filename.isEmpty()){
//            return null;
//        }

        byte[] bytes = null;
        try {
            File file = new File("E:/" + filename);
            FileInputStream inputStream = new FileInputStream(file);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

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

        }

        return bytes;
    }
}
