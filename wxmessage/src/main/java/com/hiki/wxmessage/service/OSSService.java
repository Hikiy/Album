package com.hiki.wxmessage.service;

import com.hiki.wxmessage.resultVO.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author ：hiki
 * 2019/7/29 14:36
 */
public interface OSSService {
    /**
     * 以byte[]格式从OSS获取文件
     * @param filename
     * @return
     * @throws IOException
     */
    public byte[] getFileByte(String filename)throws IOException;
    public ResultVO uploadMultipartFile(MultipartFile file, String filename);

    /**
     * 上传File类型的图片到OSS
     * @param file
     * @param filename
     * @return
     */
    public ResultVO uploadPhoto(File file, String filename);
//    public ResultVO uploadPhotoByThumbnail(MultipartFile file, String albumCode, String code);
    public ResultVO deleteFile(String filename);

    /**
     * 上传File类型的图片到OSS
     * @param file
     * @param albumCode
     * @param code
     * @param type  1:相册banner, 2:相册分类banner, 3:普通相片
     * @return
     */
    public ResultVO uploadPhotoByThumbnail(MultipartFile file, String albumCode, String code, int type);
}
