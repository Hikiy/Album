package com.hiki.wxmessage.service.impl;

import com.hiki.wxmessage.entity.AlbumCategory;
import com.hiki.wxmessage.repository.AlbumCategoryRepository;
import com.hiki.wxmessage.resultVO.AlbumBannerVO;
import com.hiki.wxmessage.service.AlbumCategoryService;
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
public class AlbumCategoryImpl implements AlbumCategoryService {
    @Autowired
    private AlbumCategoryRepository albumCategoryRepository;

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
}
