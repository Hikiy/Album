package com.hiki.album.controller.get;

import com.hiki.album.resultVO.ResultVO;
import com.hiki.album.service.AlbumsService;
import com.hiki.album.service.PhotosService;
import com.hiki.album.util.ResultUtil;
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
    AlbumsService albumsService;

    @Autowired
    PhotosService photosService;

    @GetMapping("/getphotolistbyacid")
    public ResultVO getPhotoListByAcid(@RequestParam("acid")int acid){
        return ResultUtil.success_return(photosService.getPhotoListByAcid(acid));
    }

    @GetMapping("/getalbumlist")
    public ResultVO getAlbumList(){
        return ResultUtil.success_return(albumsService.getAlbumsList());
    }

    @GetMapping("/getalbumbyaid")
    public ResultVO getalbumbyaid(@RequestParam("aid") int aid){
        return ResultUtil.success_return(albumsService.getAlbumsByAid(aid));
    }
}
