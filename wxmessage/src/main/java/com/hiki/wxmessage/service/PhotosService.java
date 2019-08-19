package com.hiki.wxmessage.service;

import com.hiki.wxmessage.entity.Photos;
import com.hiki.wxmessage.resultVO.PhotoShowVO;

import java.util.List;

/**
 * @author ：hiki
 * 2019/8/7 10:35
 */
public interface PhotosService {
    public Boolean addPhoto(Photos photo);

    public List<PhotoShowVO> getPhotoListByAcid(int acid);

    public Boolean deletePhotoByPid(int pid);

    public PhotoShowVO getPhotoByPid(int pid);
}
