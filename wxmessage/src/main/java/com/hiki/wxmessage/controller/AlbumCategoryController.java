package com.hiki.wxmessage.controller;

import com.hiki.wxmessage.entity.AlbumCategory;
import com.hiki.wxmessage.service.AlbumCategoryService;
import com.hiki.wxmessage.service.OSSService;
import com.hiki.wxmessage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ：hiki
 * 2019/8/6 11:15
 */
@RestController
@RequestMapping("/albumcategoryadmin")
public class AlbumCategoryController {
    @Autowired
    private AlbumCategoryService albumCategoryService;

    @Autowired
    private OSSService ossService;

    /**
     * 添加album中的分类
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/addalbumcategory")
    public Map<String, String> addAlbumCategory(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        String name = request.getParameter("name");
        String code = request.getParameter("code");

        Map<String, String> result = ossService.uploadPhotoByThumbnail(file,"album", code);
        if( !result.get("ret").equals("0") ){
            return ResultUtil.upload_error();
        }
        AlbumCategory albumCategory = new AlbumCategory();
        albumCategory.setName(name);
        albumCategory.setCode(code);
        albumCategory.setBanner(result.get("data"));

        Boolean success = albumCategoryService.addAlbumCategory(albumCategory);

        if( !success ){
            ossService.deleteFile(result.get("data"));
            return ResultUtil.db_error();
        }

        return ResultUtil.success_return("");
    }
}
