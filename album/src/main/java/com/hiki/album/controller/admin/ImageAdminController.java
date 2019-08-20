package com.hiki.album.controller.admin;

import com.hiki.album.entity.AlbumCategory;
import com.hiki.album.entity.Albums;
import com.hiki.album.entity.Photos;
import com.hiki.album.resultVO.ResultVO;
import com.hiki.album.service.AlbumCategoryService;
import com.hiki.album.service.AlbumsService;
import com.hiki.album.service.OSSService;
import com.hiki.album.service.PhotosService;
import com.hiki.album.util.ResultUtil;
import com.hiki.album.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：hiki
 * 2019/8/16 10:55
 */
@RestController
@RequestMapping("/admin/image")
public class ImageAdminController {
    @Autowired
    AlbumsService albumsService;

    @Autowired
    AlbumCategoryService albumCategoryService;

    @Autowired
    PhotosService photosService;

    @Autowired
    OSSService ossService;

    /**
     * 压缩图片并上传到OSS
     * @param file
     * @param acid
     * @param description
     * @param time
     * @return
     */
    @PostMapping("/uploadphoto")
    @ResponseBody
    public ResultVO uploadPhoto(@RequestParam("file") MultipartFile file, @RequestParam("acid") Integer acid, @RequestParam("description") String description, String time){
        if ( file.isEmpty() || acid == null || description == null) {
            return ResultUtil.miss_param();
        }

        //获取相册分类code
        ResultVO result = null;
        String categoryCode = null;

        AlbumCategory albumCategory = albumCategoryService.getAlbumCategoryById(acid);
        if( albumCategory == null || albumCategory.getCode().equals("")){
            return ResultUtil.db_error();
        }
        categoryCode = albumCategory.getCode();
        int aid = albumCategory.getAid();

        //获取相册code
        Albums album = albumsService.getAlbumsByAid(aid);
        if( album == null ){
            return ResultUtil.db_error();
        }
        String albumsCode = album.getCode();

        //上传图片到OSS
        if( categoryCode!= null ) {
            result = ossService.uploadPhotoByThumbnail(file, albumsCode, categoryCode, 3);
        }else {
            return ResultUtil.miss_param();
        }

        //写入数据库
        try{
            Photos photo = new Photos();
            photo.setAcid(acid);
            photo.setDescription(description);
            photo.setLink(result.getData().toString());
            photo.setTime(TimeUtil.strToTime(time));
            Boolean success = photosService.addPhoto(photo);

            if( !success ){
                //写入数据库失败则删除
                ossService.deleteFile(result.getData().toString());
                return ResultUtil.db_error();
            }
        }catch (Exception e){
            //写入数据库失败则删除
            ossService.deleteFile(result.getData().toString());
            return ResultUtil.db_error();
        }

        if( result.getRet() != 0 ){
            return result;
        }
        return ResultUtil.success_return(result.getData());
    }
}
