package com.hiki.wxmessage.service;

import com.hiki.wxmessage.entity.Albums;

import java.util.List;

/**
 * @author ：hiki
 * 2019/8/15 10:46
 */
public interface AlbumsService {
    public Albums getAlbumsByAid(int aid);
    public List<Albums> getAlbumsList();
    /**
     * 新增相册
     * @param name
     * @param code
     * @param banner
     * @return
     */
    public Boolean addAlbum(String name, String code, String banner);

    /**
     * 通过code查询，用于查重
     * @param code
     * @return
     */
    public Albums getAlbumsByCode(String code);

    /**
     * 更新相册信息
     * @param aid
     * @param name
     * @param banner
     * @return
     */
    public Boolean updateAlbumByAid(int aid, String name, String code, String banner);
}
