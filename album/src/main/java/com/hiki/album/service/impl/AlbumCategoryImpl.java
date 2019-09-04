package com.hiki.album.service.impl;

import com.hiki.album.entity.AlbumCategory;
import com.hiki.album.entity.Photos;
import com.hiki.album.repository.AlbumCategoryRepository;
import com.hiki.album.repository.PhotosRepository;
import com.hiki.album.resultVO.AlbumBannerVO;
import com.hiki.album.resultVO.ResultVO;
import com.hiki.album.service.AlbumCategoryService;
import com.hiki.album.service.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：hiki
 * 2019/8/6 11:03
 */
@Service
@Slf4j
public class AlbumCategoryImpl implements AlbumCategoryService {
    @Autowired
    private AlbumCategoryRepository albumCategoryRepository;
    @Autowired
    private PhotosRepository photosRepository;
    @Autowired
    private OSSService ossService;

    @Override
    public List<AlbumCategory> getAlbumCategoryList() {
        List<AlbumCategory> list = albumCategoryRepository.findAll();
        return list;
    }

    @Override
    public List<AlbumBannerVO> getBannerList() {
        List<Object> list = albumCategoryRepository.findBannerList();
        List<AlbumBannerVO> albumBannerList= new ArrayList<>();
        for(Object o:list){
            AlbumBannerVO albumBannerVO = new AlbumBannerVO();
            Object[] rowArray = (Object[]) o;
            albumBannerVO.setAcid((int)rowArray[0]);
            albumBannerVO.setBanner((String)rowArray[1]);
            albumBannerList.add(albumBannerVO);
        }
        return albumBannerList;
    }

    /**
     * 增加图片类别
     * @param albumCategory
     * @return
     */
    @Override
    public Boolean addAlbumCategory(AlbumCategory albumCategory) {
        int time = (int)(new Date().getTime()/1000);
        albumCategory.setCreated(time);
        albumCategory.setUpdated(time);
        albumCategory.setStatus(1);
        albumCategoryRepository.save(albumCategory);
        if( albumCategory.getAcid() > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public AlbumCategory getAlbumCategoryById(Integer acid){
        AlbumCategory albumCategory =albumCategoryRepository.findByAcid(acid);
        return albumCategory;
    }

    @Override
    public List<AlbumCategory> getAlbumCategoryListByCode(String code) {
        List<AlbumCategory> list = albumCategoryRepository.findByCode(code);
        return list;
    }

    @Override
    public List<AlbumCategory> getAlbumCategoryListByAid(int aid) {
        return albumCategoryRepository.findAllByAidOrderByPriorityDesc(aid);
    }

    /**
     * 修改上册分类信息
     * @param acid
     * @param aid
     * @param name
     * @param code
     * @param priority
     * @param banner
     * @return
     */
    public Boolean updateAlbumCategoryByAcid(int acid, int aid, String name , String code, int priority, String banner){
        int time = (int)(new Date().getTime()/1000);
        AlbumCategory oldAlbumCategory = albumCategoryRepository.findByAcid(acid);
        if(oldAlbumCategory == null){
            return false;
        }

        AlbumCategory albumCategory = new AlbumCategory();
        albumCategory.setAcid(acid);
        albumCategory.setAid(aid);
        albumCategory.setCode(code);
        albumCategory.setPriority(priority);
        albumCategory.setBanner(banner);
        albumCategory.setCreated(oldAlbumCategory.getCreated());
        albumCategory.setUpdated(time);
        try{
            albumCategoryRepository.save(albumCategory);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 删除相册分类以及分类下的所有照片
     * @param acid
     * @return
     */
    @Override
    public Boolean deleteAlbumCategoryByAcid(int acid) {
        try{
            List<Photos> photos= photosRepository.findAllByAcidOrderByTimeDesc(acid);

            for(Photos photo:photos){
                ResultVO pR = ossService.deleteFile(photo.getLink());
                if( pR.getRet() != 0 ){
                    log.error("删除分类时，删除照片失败！pid为" + photo.getPid());
                    return false;
                }
                photosRepository.deleteByPid(photo.getPid());
            }

            AlbumCategory albumCategory = albumCategoryRepository.findByAcid(acid);
            ResultVO result = ossService.deleteFile(albumCategory.getBanner());
            if( result.getRet() == 0 ){
                albumCategoryRepository.deleteByAcid(acid);
            }else{
                log.error("删除相册分类失败！acid为" + acid);
                return false;
            }
        }catch(Exception e){
            log.error("删除相册分类失败！acid为" + acid);
            return false;
        }
        return true;
    }
}
