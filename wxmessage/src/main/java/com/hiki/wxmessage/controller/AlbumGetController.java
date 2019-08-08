package com.hiki.wxmessage.controller;

import com.hiki.wxmessage.entity.Photos;
import com.hiki.wxmessage.resultVO.ResultVO;
import com.hiki.wxmessage.service.PhotosService;
import com.hiki.wxmessage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hiki
 * 2019/8/8 17:19
 */
@RestController
@RequestMapping("/album")
public class AlbumGetController {
    @Autowired
    PhotosService photosService;

    @GetMapping("/getphotolistbyacid")
    public ResultVO getPhotoListByAcid(@RequestParam("acid")int acid){
        return ResultUtil.success_return(photosService.getPhotoListByAcid(acid));
    }
}
