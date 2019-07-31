package com.hiki.wxmessage.service;

import com.aliyun.oss.OSS;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ：hiki
 * 2019/7/29 14:36
 */
public interface OSSService {
    public InputStream getFileStream(String filename);
    public byte[] getFileByte(String filename)throws IOException;
}
