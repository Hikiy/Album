package com.hiki.album.controller.get;

import com.hiki.album.resultVO.ResultPageVO;
import com.hiki.album.resultVO.ResultVO;
import com.hiki.album.service.AlbumsService;
import com.hiki.album.service.PhotosService;
import com.hiki.album.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public ResultPageVO getPhotoListByAcid(@RequestParam("acid")int acid, HttpServletRequest request){
        String pageObject = request.getParameter("page");
        String sizeObject = request.getParameter("size");

        if( pageObject != null && sizeObject != null){
            int page = Integer.valueOf(pageObject);
            int size = Integer.valueOf(sizeObject);
            return ResultUtil.success_return(photosService.getPhotoListByAcid(acid, page, size), photosService.getPhotoCountByAcid(acid));
        }
        return ResultUtil.success_return(photosService.getPhotoListByAcid(acid),0);
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
