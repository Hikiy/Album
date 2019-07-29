package com.hiki.wxmessage.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.hiki.wxmessage.service.OSSService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author ：hiki
 * 2019/7/29 14:39
 */
@Service
public class OSSServiceImpl implements OSSService {
    @Override
    public InputStream getFileStream(String filename){
        OSS ossClient = this.getOSSClient();
        String bucketName = "<yourBucketName>";
        String objectName = filename;
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        InputStream fileStream = ossObject.getObjectContent();
        return fileStream;
    }

    /**
     * 获取OSS实例
     * @return
     */
    @Override
    public OSS getOSSClient() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "<yourAccessKeyId>";
        String accessKeySecret = "<yourAccessKeySecret>";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }
}
