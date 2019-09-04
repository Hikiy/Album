package com.hiki.album.service;

import com.hiki.album.entity.AlbumCategory;
import com.hiki.album.resultVO.AlbumBannerVO;
import java.util.List;

/**
 * @author ：hiki
 * 2019/8/6 11:02
 */
public interface AlbumCategoryService {
    public List<AlbumCategory> getAlbumCategoryList();

    public List<AlbumBannerVO> getBannerList();

    public Boolean addAlbumCategory(AlbumCategory albumCategoryEntity);

    public AlbumCategory getAlbumCategoryById(Integer acid);

    public List<AlbumCategory> getAlbumCategoryListByCode(String code);

    public List<AlbumCategory> getAlbumCategoryListByAid(int aid);

    public Boolean updateAlbumCategoryByAcid(int acid, int aid, String name, String code, int priority, String banner);

    public Boolean deleteAlbumCategoryByAcid(int acid);
}
