package com.hiki.wxmessage.controller;

import com.google.gson.Gson;
import com.hiki.wxmessage.service.AlbumCategoryService;
import com.hiki.wxmessage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ：hiki
 * 2019/8/6 17:26
 */
@RestController
@RequestMapping("/albumcategory")
public class AlbumCategoryShowController {
    @Autowired
    private AlbumCategoryService albumCategoryService;
    /**
     * 获取Album的所有分类的id和banner
     * @return
     */
    @GetMapping("/getbannerlist")
    public Map<String, String> getBannerList(){
        Gson gson = new Gson();
        String data = gson.toJson(albumCategoryService.getBannerList());
        return ResultUtil.success_return(data);
    }

//    /**
//     * 通过Album的分类获取列表
//     * @param code
//     * @return
//     */
//    @GetMapping("/getalbumcategorylistbycode")
//    public Map<String, String> getAlbumCategoryListByCode(@RequestParam("code") String code){
//        Gson gson = new Gson();
//        String data = gson.toJson(albumCategoryService.getAlbumCategoryListByCode(code));
//        return ResultUtil.success_return(data);
//    }
}
