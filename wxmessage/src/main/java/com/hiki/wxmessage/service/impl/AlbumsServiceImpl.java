package com.hiki.wxmessage.service.impl;

import com.hiki.wxmessage.entity.Albums;
import com.hiki.wxmessage.repository.AlbumsRepository;
import com.hiki.wxmessage.service.AlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：hiki
 * 2019/8/15 10:49
 */
@Service
public class AlbumsServiceImpl implements AlbumsService {
    @Autowired
    private AlbumsRepository albumsRepository;

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
}
