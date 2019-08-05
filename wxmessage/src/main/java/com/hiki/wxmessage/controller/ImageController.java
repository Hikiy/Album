package com.hiki.wxmessage.controller;

import com.hiki.wxmessage.enums.PhotoTypeEnums;
import com.hiki.wxmessage.service.OSSService;
import com.hiki.wxmessage.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Map;

/**
 * @author ：hiki
 * 2019/7/29 14:46
 */
@RestController
@RequestMapping("/image")
@Slf4j
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

    /**
     * 压缩图片并上传到OSS
     * @param request
     * @param file
     * @param type  上传图片的类型 1:album
     * @return
     */
    @PostMapping("/uploadphoto")
    @ResponseBody
    public Map<String, String> uploadPhoto(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("type") Integer type){

        if ( file.isEmpty() || type == null || this.getTypeName(type).equals("") ) {
            return ResultUtil.miss_param();
        }

        String typeName = this.getTypeName(type);
        String realDir = request.getSession().getServletContext().getRealPath("/");
        String dir = realDir + "upload";
        String ext = ".jpg";
        String sampleName = "sample" + ext;

        //生成临时文件夹
        File fileDir = new File(dir);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }

        //压缩图片
        File newFile = null;
        newFile = this.fileToThumbnail(file, dir + "/" + sampleName);

        //生成随机文件名
        Long time = System.currentTimeMillis();
        String filename = file.getName() + time;
        filename = typeName + "/" + DigestUtils.md5DigestAsHex(filename.getBytes()) + ext;

        //上传到阿里云OSS
        Map<String, String> result = ossService.uploadPhoto(newFile, filename);

        //删除临时文件
        newFile.delete();

        if( result.get("ret").equals("0")){
            return ResultUtil.success_return(result.get("data"));
        }else{
            return result;
        }
    }

    /**
     * 压缩图片
     * @param file
     * @param dir
     * @return
     */
    private File fileToThumbnail(MultipartFile file, String dir){
        File newFile = null;
        try{
            Thumbnails.of(file.getInputStream()).size(1920, 1080).toFile(dir);
            newFile = new File(dir);
        }catch (IOException e){
            log.error("压缩图失败:" + e.toString());
        }

        //如果缩小后依然大于1M则压缩一次
        while(newFile.length() > 1048576){
            System.out.println(newFile.length());
            try{
                Thumbnails.of(newFile).scale(1f).outputQuality(0.75f).toFile(dir);
            }catch (IOException e){
                log.error("压缩图片失败:" + e.toString());
            }
            newFile = new File(dir);
        }

        return newFile;
    }

    /**
     * 获取图片类型对应的名字
     * @param type
     * @return
     */
    private String getTypeName(Integer type){
        switch (type){
            case 1 :
                return PhotoTypeEnums.ALBUM.getType();
                default:
                    return "";
        }
    }
}
