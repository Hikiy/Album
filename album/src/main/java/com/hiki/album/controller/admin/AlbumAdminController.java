package com.hiki.album.controller.admin;

import com.hiki.album.entity.Albums;
import com.hiki.album.resultVO.ResultVO;
import com.hiki.album.service.AlbumCategoryService;
import com.hiki.album.service.AlbumsService;
import com.hiki.album.service.OSSService;
import com.hiki.album.service.PhotosService;
import com.hiki.album.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：hiki
 * 2019/8/16 10:51
 */
@RestController
@RequestMapping("/admin/album")
public class AlbumAdminController {
    @Autowired
    AlbumsService albumsService;

    @Autowired
    AlbumCategoryService albumCategoryService;

    @Autowired
    PhotosService photosService;

    @Autowired
    OSSService ossService;

    /**
     * 添加新相册
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/addalbum")
    public ResultVO addAlbum(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        String name = request.getParameter("name");
        String code = request.getParameter("code");

        if( name == null || code == null || name.equals("") || code.equals("")){
            return ResultUtil.miss_param();
        }

        //code 重复
        Albums albums = albumsService.getAlbumsByCode(code);
        if( albums != null ){
            return ResultUtil.code_exist();
        }

        //上传图片到OSS
        ResultVO result = ossService.uploadPhotoByThumbnail(file, code, "", 1);

        if( result.getRet() != 0 ){
            return result;
        }
        //写入数据库
        Boolean success = null;
        try{
            success = albumsService.addAlbum(name, code, result.getData().toString());
        }catch (Exception e){
            //数据库写入失败则删除OSS图片
            ossService.deleteFile(result.getData().toString());
            return ResultUtil.db_error();
        }

        if( !success ){
            //数据库写入失败则删除OSS图片
            ossService.deleteFile(result.getData().toString());
            return ResultUtil.db_error();
        }
        return ResultUtil.success_return("");
    }

    @PostMapping("/updatealbumbyaid")
    public ResultVO updateAlbumByAid(@RequestParam("aid") int aid, @RequestParam("name") String name, @RequestParam("code") String code, @RequestParam("file") MultipartFile file){
        //上传图片到OSS
        ResultVO result = ossService.uploadPhotoByThumbnail(file, code, "", 1);

        if( result.getRet() != 0 ){
            return result;
        }
        //写入数据库
        Boolean success = false;
        try{
            success = albumsService.updateAlbumByAid(aid, name, code, result.getData().toString());
        }catch (Exception e){
            //数据库写入失败则删除OSS图片
            ossService.deleteFile(result.getData().toString());
            return ResultUtil.db_error();
        }

        if( !success ){
            //数据库写入失败则删除OSS图片
            ossService.deleteFile(result.getData().toString());
            return ResultUtil.db_error();
        }
        return ResultUtil.success_return("");
    }
}
