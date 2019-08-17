package com.hiki.wxmessage.service.impl;

import com.hiki.wxmessage.entity.Photos;
import com.hiki.wxmessage.repository.PhotosRepository;
import com.hiki.wxmessage.service.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Photos> getPhotoListByAcid(int acid) {
        return photosRepository.findAllByAcid(acid);
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
    public Photos getPhotoByPid(int pid) {
        return photosRepository.findByPid(pid);
    }
}
