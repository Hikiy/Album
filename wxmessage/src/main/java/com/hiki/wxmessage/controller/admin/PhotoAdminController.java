package com.hiki.wxmessage.controller.admin;

import com.hiki.wxmessage.entity.Photos;
import com.hiki.wxmessage.resultVO.PhotoShowVO;
import com.hiki.wxmessage.resultVO.ResultVO;
import com.hiki.wxmessage.service.OSSService;
import com.hiki.wxmessage.service.PhotosService;
import com.hiki.wxmessage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hiki
 * 2019/8/17 16:23
 */
@RestController
@RequestMapping("/admin/photo")
public class PhotoAdminController {
    @Autowired
    PhotosService photosService;

    @Autowired
    OSSService ossService;

    @PostMapping("/deletebypid")
    public ResultVO removePhotoBypid(@RequestParam("pid") int pid){
        PhotoShowVO photo = photosService.getPhotoByPid(pid);
        if( photo == null ){
            return ResultUtil.db_error();
        }

        String filename = photo.getLink();
        ResultVO ossResult = ossService.deleteFile(filename);
        if( ossResult.getRet() != 0){
            return ResultUtil.delete_error();
        }

        Boolean result= photosService.deletePhotoByPid(photo.getPid());

        if( result ){
            return ResultUtil.success_return("");
        }
        return ResultUtil.db_error();
    }
}
