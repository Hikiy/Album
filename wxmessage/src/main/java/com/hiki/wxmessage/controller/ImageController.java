package com.hiki.wxmessage.controller;

import com.hiki.wxmessage.entity.AlbumCategory;
import com.hiki.wxmessage.entity.Photos;
import com.hiki.wxmessage.enums.PhotoTypeEnums;
import com.hiki.wxmessage.resultVO.ResultVO;
import com.hiki.wxmessage.service.AlbumCategoryService;
import com.hiki.wxmessage.service.OSSService;
import com.hiki.wxmessage.service.PhotosService;
import com.hiki.wxmessage.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private AlbumCategoryService albumCategoryService;

    @Autowired
    private PhotosService photosService;

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
     * @param type 传到的相册 1:Album
     * @param category  相册中的分类
     * @return
     */
    @PostMapping("/uploadphoto")
    @ResponseBody
    public ResultVO uploadPhoto(@RequestParam("file") MultipartFile file, @RequestParam("type") Integer type, @RequestParam("category") Integer category, @RequestParam("description") String description, String time){
        if ( file.isEmpty() || type == null || this.getTypeName(type).equals("") ) {
            return ResultUtil.miss_param();
        }

        String typeName = this.getTypeName(type);
        if( typeName.equals("") ){
            return ResultUtil.miss_param();
        }

        ResultVO result = null;
        String code = null;
        if( type == 1){
            AlbumCategory albumCategory = albumCategoryService.getAlbumCategoryById(category);
            if( albumCategory == null || albumCategory.getCode().equals("")){
                return ResultUtil.db_error();
            }
            code = albumCategory.getCode();
        }else{
            return ResultUtil.miss_param();
        }

        if( code!= null ) {
            result = ossService.uploadPhotoByThumbnail(file, typeName, code);
        }else {
            return ResultUtil.miss_param();
        }

        try{
            Photos photo = new Photos();
            photo.setAcid(category);
            photo.setDescription(description);
            photo.setLink(result.getData().toString());
            photo.setTime(time);
            Boolean success = photosService.addPhoto(photo);

            if( !success ){
                ossService.deleteFile(result.getData().toString());
                return ResultUtil.db_error();
            }
        }catch (Exception e){
            ossService.deleteFile(result.getData().toString());
            return ResultUtil.db_error();
        }
        if( result.getRet() != 0 ){
            return result;
        }
        return ResultUtil.success_return(result.getData());

    }

    @GetMapping("/upload")
    public String index(){
        return "upload_photo";
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
