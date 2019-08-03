package com.hiki.wxmessage.controller;

import com.hiki.wxmessage.service.OSSService;
import com.hiki.wxmessage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/uploadfile")
    @ResponseBody
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            Long time = System.currentTimeMillis();
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String filename = file.getName() + time;
            filename = "works/" + DigestUtils.md5DigestAsHex(filename.getBytes()) + ext;
            Map<String, String> result = ossService.uploadFile(file, filename);
            if( result.get("ret").equals("0")){
                return ResultUtil.success_return(result.get("data"));
            }else{
                return result;
            }
        } else {
            return ResultUtil.miss_param();
        }
    }
}
