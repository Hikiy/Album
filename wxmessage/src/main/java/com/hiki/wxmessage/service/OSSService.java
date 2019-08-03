package com.hiki.wxmessage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author ：hiki
 * 2019/7/29 14:36
 */
public interface OSSService {
    public byte[] getFileByte(String filename)throws IOException;
    public Map<String, String> uploadFile(MultipartFile file);
}
