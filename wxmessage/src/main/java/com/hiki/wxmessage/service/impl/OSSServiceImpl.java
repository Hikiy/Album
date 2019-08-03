package com.hiki.wxmessage.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.hiki.wxmessage.service.OSSService;
import com.hiki.wxmessage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.Map;


/**
 * @author ：hiki
 * 2019/7/29 14:39
 */
@Service
public class OSSServiceImpl implements OSSService {
    @Value("${OSS.endpoint}")
    private String endpoint;

    @Value("${OSS.accessKeyId}")
    private String accessKeyId;

    @Value("${OSS.accessKeySecret}")
    private String accessKeySecret;

    @Value("${OSS.bucketName}")
    private String bucketName;

    /**
     * 获取OSS实例
     * @return
     */
    private OSS getOSSClient() {
        String endpoint = this.endpoint;
        String accessKeyId = this.accessKeyId;
        String accessKeySecret = this.accessKeySecret;

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }

    @Override
    public byte[] getFileByte(String filename) throws IOException{
        byte[] bytes = null;

        //从OSS获取文件流
        OSS ossClient = this.getOSSClient();

        String bucketName = this.bucketName;
        String objectName = filename;

        OSSObject ossObject = ossClient.getObject(new GetObjectRequest(bucketName, objectName));
        InputStream fileStream = ossObject.getObjectContent();

        //将流处理为byte数组
        ByteArrayOutputStream output = new ByteArrayOutputStream();
            try{
                byte[] buf = new byte[1024];
                int numBytesRead = 0;
                while ((numBytesRead = fileStream.read(buf)) != -1) {
                    output.write(buf, 0, numBytesRead);
                }
                bytes = output.toByteArray();
            }catch (Exception e){
                System.out.println(e.toString());
            }finally {
                output.close();
                fileStream.close();
                ossObject.close();
            }
        return bytes;
    }

    @Override
    public Map<String, String> uploadFile(MultipartFile file) {
        File thefile = (File)file;

        OSS ossClient = this.getOSSClient();
        Long time = System.currentTimeMillis();
        String filename = file.getName() + time;
        filename = "Blog/works/" + DigestUtils.md5DigestAsHex(filename.getBytes());
        ossClient.putObject(this.bucketName, filename, thefile);

        return ResultUtil.success_return(filename);
    }
}
