package com.hiki.wxmessage.controller;

import com.hiki.wxmessage.entity.AlbumCategory;
import com.hiki.wxmessage.enums.PhotoTypeEnums;
import com.hiki.wxmessage.service.AlbumCategoryService;
import com.hiki.wxmessage.service.OSSService;
import com.hiki.wxmessage.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AlbumCategoryService albumCategoryService;

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

    /**
     * 压缩图片并上传到OSS
     * @param file
     * @param type  上传图片的类型 1:album
     * @return
     */
    @PostMapping("/uploadphoto")
    @ResponseBody
    public Map<String, String> uploadPhoto(@RequestParam("file") MultipartFile file, @RequestParam("type") Integer type, @RequestParam("category") Integer category){
        if ( file.isEmpty() || type == null || this.getTypeName(type).equals("") ) {
            return ResultUtil.miss_param();
        }

        String typeName = this.getTypeName(type);
        Map<String, String> result = null;

        AlbumCategory albumCategory = albumCategoryService.getAlbumCategoryById(category);
        if( albumCategory.getCode().equals("")){
            return ResultUtil.db_error();
        }

        if( !typeName.equals("") ){
            String code = albumCategory.getCode();
            result = ossService.uploadPhotoByThumbnail(file, typeName, code);
        }else{
            return ResultUtil.miss_param();
        }

        if( result.get("ret").equals("0")){
            return ResultUtil.success_return(result.get("data"));
        }else{
            return result;
        }
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

    //    @GetMapping(value = "/showimagebylocal", produces = "image/jpeg")
//    @ResponseBody
//    public byte[] showImageByLocal(HttpServletRequest request){
////        String name = request.getParameter("name");
//        String filename = request.getParameter("filename");
////        System.out.println(name);
////        System.out.println(filename);
////
////        if( filename.isEmpty()){
////            return null;
////        }
//
//        byte[] bytes = null;
//        try {
//            File file = new File("E:/" + filename);
//            FileInputStream inputStream = new FileInputStream(file);
//            bytes = new byte[inputStream.available()];
//            inputStream.read(bytes, 0, inputStream.available());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return bytes;
//    }
}
