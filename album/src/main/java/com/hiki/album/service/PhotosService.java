package com.hiki.album.service;

import com.hiki.album.entity.Photos;
import com.hiki.album.resultVO.PhotoShowVO;

import java.util.List;

/**
 * @author ：hiki
 * 2019/8/7 10:35
 */
public interface PhotosService {
    public Boolean addPhoto(Photos photo);

    public List<PhotoShowVO> getPhotoListByAcid(int acid);

    public List<PhotoShowVO> getPhotoListByAcid(int acid, int page, int size);

    public Boolean deletePhotoByPid(int pid);

    public PhotoShowVO getPhotoByPid(int pid);
}
