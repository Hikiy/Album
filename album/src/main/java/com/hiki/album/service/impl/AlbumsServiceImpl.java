package com.hiki.album.service.impl;

import com.hiki.album.entity.AlbumCategory;
import com.hiki.album.entity.Albums;
import com.hiki.album.repository.AlbumsRepository;
import com.hiki.album.resultVO.ResultVO;
import com.hiki.album.service.AlbumCategoryService;
import com.hiki.album.service.AlbumsService;
import com.hiki.album.service.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：hiki
 * 2019/8/15 10:49
 */
@Service
@Slf4j
public class AlbumsServiceImpl implements AlbumsService {
    @Autowired
    private AlbumsRepository albumsRepository;
    @Autowired
    private AlbumCategoryService albumCategoryService;
    @Autowired
    private OSSService ossService;

    @Override
    public Albums getAlbumsByAid(int aid) {
        return albumsRepository.findByAid(aid);
    }

    @Override
    public List<Albums> getAlbumsList() {
        return albumsRepository.findAll();
    }

    /**
     * 新增相册
     * @param name
     * @param code
     * @param banner
     * @return
     */
    @Override
    public Boolean addAlbum(String name, String code, String banner) {
        int time = (int)(new Date().getTime()/1000);

        Albums album = new Albums();
        album.setName(name);
        album.setCode(code);
        album.setBanner(banner);
        album.setUpdated(time);

        albumsRepository.save(album);
        if( album.getAid() > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 通过code查询，用于查重
     * @param code
     * @return
     */
    @Override
    public Albums getAlbumsByCode(String code) {
        return albumsRepository.findByCode(code);
    }

    /**
     * 更新相册信息
     * @param aid
     * @param name
     * @param banner
     * @return
     */
    @Override
    public Boolean updateAlbumByAid(int aid, String name, String code, String banner) {
        int time = (int)(new Date().getTime()/1000);

        Albums album = new Albums();
        album.setAid(aid);
        album.setName(name);
        album.setCode(code);
        album.setBanner(banner);
        album.setUpdated(time);

        try{
            albumsRepository.save(album);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    /**
     * 删除相册以及其中的相册分类还有分类中的所有照片
     * @param aid
     * @return
     */
    @Override
    public Boolean deleteAlbumByAid(int aid) {
        try{
            List<AlbumCategory> albumCategories = albumCategoryService.getAlbumCategoryListByAid(aid);
            for(AlbumCategory albumCategory : albumCategories){
                Boolean d = albumCategoryService.deleteAlbumCategoryByAcid(albumCategory.getAcid());
                if( !d ){
                    log.error("删除相册时，删除相册分类失败！acid为" + albumCategory.getAcid());
                    return false;
                }
            }
            Albums album = albumsRepository.findByAid(aid);
            ResultVO result = ossService.deleteFile(album.getBanner());
            if( result.getRet() == 0 ){
                albumsRepository.deleteByAid(aid);
            }else{
                log.error("删除相册失败！aid为" + aid);
                return false;
            }
        }catch (Exception e){
            log.error("删除相册失败！aid为" + aid);
            return false;
        }
        return true;
    }
}
