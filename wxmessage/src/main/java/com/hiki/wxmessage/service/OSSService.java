package com.hiki.wxmessage.service;

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
    public Map<String, String> uploadMultipartFile(MultipartFile file, String filename);

    /**
     * 上传File类型的图片到OSS
     * @param file
     * @param filename
     * @return
     */
    public Map<String, String> uploadPhoto(File file, String filename);
}
