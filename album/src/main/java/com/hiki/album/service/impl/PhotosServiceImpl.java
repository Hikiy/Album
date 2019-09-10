package com.hiki.album.service.impl;


import com.hiki.album.entity.Photos;
import com.hiki.album.repository.PhotosRepository;
import com.hiki.album.resultVO.PhotoShowVO;
import com.hiki.album.service.PhotosService;
import com.hiki.album.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：hiki
 * 2019/8/7 10:37
 */
@Service
public class PhotosServiceImpl implements PhotosService {
    @Autowired
    private PhotosRepository photosRepository;

    /**
     * 添加照片
     * @param photo
     * @return
     */
    @Override
    public Boolean addPhoto(Photos photo) {
        int time = (int)(new Date().getTime()/1000);
        photo.setStatus(1);
        photo.setCreated(time);

        photosRepository.save(photo);

        if( photo.getPid() > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 通过acid查询照片列表
     * @param acid
     * @return
     */
    @Override
    public List<PhotoShowVO> getPhotoListByAcid(int acid) {
        List<Photos> list = photosRepository.findAllByAcidOrderByTimeDesc(acid);
        List<PhotoShowVO> theList = new ArrayList<>();
        for(Photos photos : list){
            theList.add(this.photosToShowVO(photos));
        }
        return theList;
    }

    @Override
    public List<PhotoShowVO> getPhotoListByAcid(int acid, int page, int size) {
        List<Photos> list = photosRepository.findAllByAcidOrderByTimeDesc(acid, PageRequest.of(page-1,size));
        List<PhotoShowVO> theList = new ArrayList<>();
        for(Photos photos : list){
            theList.add(this.photosToShowVO(photos));
        }
        return theList;
    }

    /**
     * 删除照片
     * @param pid
     * @return
     */
    @Override
    public Boolean deletePhotoByPid(int pid) {
        try{
            photosRepository.deleteByPid(pid);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 通过pid查询照片信息
     * @param pid
     * @return
     */
    @Override
    public PhotoShowVO getPhotoByPid(int pid) {
        Photos photos = photosRepository.findByPid(pid);
        if( photos == null ){
            return null;
        }
        return this.photosToShowVO(photos);
    }

    /**
     * 将Photos转换成PhotosShowVO
     * @param photos
     * @return
     */
    private PhotoShowVO photosToShowVO(Photos photos){
        PhotoShowVO photoShowVO = new PhotoShowVO();
        photoShowVO.setPid(photos.getPid());
        photoShowVO.setAcid(photos.getAcid());
        photoShowVO.setDescription(photos.getDescription());
        photoShowVO.setLink(photos.getLink());
        photoShowVO.setStatus(photos.getStatus());
        photoShowVO.setTime(TimeUtil.TimeToStr(photos.getTime()));
        photoShowVO.setCreated(TimeUtil.TimeToStr(photos.getCreated()));

        return photoShowVO;
    }
}
