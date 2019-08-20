package com.hiki.album.controller.admin;

import com.hiki.album.entity.AlbumCategory;
import com.hiki.album.entity.Albums;
import com.hiki.album.resultVO.ResultVO;
import com.hiki.album.service.AlbumCategoryService;
import com.hiki.album.service.AlbumsService;
import com.hiki.album.service.OSSService;
import com.hiki.album.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：hiki
 * 2019/8/6 11:15
 */
@RestController
@RequestMapping("/admin/albumcategory")
public class AlbumCategoryAdminController {
    @Autowired
    private AlbumCategoryService albumCategoryService;

    @Autowired
    private OSSService ossService;

    @Autowired
    private AlbumsService albumsService;

    /**
     * 添加album中的分类
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/addalbumcategory")
    public ResultVO addAlbumCategory(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        int aid = Integer.valueOf(request.getParameter("aid"));
        int priority = Integer.valueOf(request.getParameter("priority"));

        if( name == null || code == null ){
            return ResultUtil.miss_param();
        }

        //获取相册code
        Albums album = albumsService.getAlbumsByAid(aid);
        if( album == null){
            return ResultUtil.db_error();
        }
        String albumCode= album.getCode();

        //上传图片到OSS
        ResultVO result = ossService.uploadPhotoByThumbnail(file, albumCode, code, 2);
        if( result.getRet() != 0 ){
            return ResultUtil.upload_error();
        }

        //写入数据库
        AlbumCategory albumCategory = new AlbumCategory();
        albumCategory.setAid(aid);
        albumCategory.setName(name);
        albumCategory.setCode(code);
        albumCategory.setPriority(priority);
        albumCategory.setBanner(result.getData().toString());

        Boolean success = null;
        try{
            success = albumCategoryService.addAlbumCategory(albumCategory);
        }catch (Exception e){
            //写入库失败则删除OSS图片
            ossService.deleteFile(result.getData().toString());
            return ResultUtil.db_error();
        }
        if( !success ){
            //写入库失败则删除OSS图片
            ossService.deleteFile(result.getData().toString());
            return ResultUtil.db_error();
        }

        return ResultUtil.success_return("");
    }

    @PostMapping("/updatealbumcategorybyacid")
    public ResultVO updateAlbumCategoryByAcid(@RequestParam("acid") int acid, @RequestParam("aid")int aid, @RequestParam("name") String name, @RequestParam("code") String code, @RequestParam("priority") int priority, @RequestParam("file") MultipartFile file){
        //获取相册code
        Albums album = albumsService.getAlbumsByAid(aid);
        if( album == null){
            return ResultUtil.db_error();
        }
        String albumCode= album.getCode();

        //上传图片到OSS
        ResultVO result = ossService.uploadPhotoByThumbnail(file, albumCode, code, 2);
        if( result.getRet() != 0 ){
            return ResultUtil.upload_error();
        }

        Boolean success = false;
        try{
            success = albumCategoryService.updateAlbumCategoryByAcid(acid, aid, name , code, priority, result.getData().toString());
        }catch (Exception e){
            //写入库失败则删除OSS图片
            ossService.deleteFile(result.getData().toString());
            return ResultUtil.db_error();
        }
        if( !success ){
            //写入库失败则删除OSS图片
            ossService.deleteFile(result.getData().toString());
            return ResultUtil.db_error();
        }

        return ResultUtil.success_return("");
    }
}
