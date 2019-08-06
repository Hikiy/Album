package com.hiki.wxmessage.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.hiki.wxmessage.service.OSSService;
import com.hiki.wxmessage.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;


/**
 * @author ：hiki
 * 2019/7/29 14:39
 */
@Service
@Slf4j
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

    /**
     * 以byte[]格式从OSS获取文件
     * @param filename
     * @return
     * @throws IOException
     */
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

    /**
     * 上传MultipartFile类型的文件到OSS
     * @param file
     * @return
     */
    @Override
    public Map<String, String> uploadMultipartFile(MultipartFile file, String filename) {
        filename = "blog/" + filename;
        InputStream fileStream;

        OSS ossClient = this.getOSSClient();
        try{
            fileStream = file.getInputStream();
            try{
                ossClient.putObject(this.bucketName, filename, fileStream);
            }catch (Exception e){
                log.error("OSS上传文件失败！");
                return ResultUtil.upload_error();
            }finally {
                ossClient.shutdown();
                fileStream.close();
            }
        }catch (Exception e){
            log.error(e.toString());
        }
        return ResultUtil.success_return(filename);
    }

    /**
     * 上传File类型的图片到OSS
     * @param file
     * @param filename
     * @return
     */
    @Override
    public Map<String, String> uploadPhoto(File file, String filename) {
        filename = "blog/" + filename;

        OSS ossClient = this.getOSSClient();
        try{
            ossClient.putObject(this.bucketName, filename, file);
        }catch (Exception e){
            log.error("OSS上传文件失败！");
            return ResultUtil.upload_error();
        }finally {
            ossClient.shutdown();
        }

        return ResultUtil.success_return(filename);
    }

    /**
     * 压缩MultipartFile类型图片并上传到OSS
     * @param file
     * @param typeName
     * @return
     */
    @Override
    public Map<String, String> uploadPhotoByThumbnail(MultipartFile file, String typeName, String code){
        String sampleName = "sample.jpg";
        String realDir = System.getProperty("user.dir");
        String dir = realDir + "\\src\\upload";

        //生成临时文件夹
        File fileDir = new File(dir);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }

        //压缩图片
        File newFile = null;
        newFile = this.fileToThumbnail(file, dir + "/" + sampleName);

        //生成随机文件名
        Long time = System.currentTimeMillis();
        String filename = file.getName() + time;
        filename = typeName + "/" + code + "/" + DigestUtils.md5DigestAsHex(filename.getBytes()) + ".jpg";

        //上传到阿里云OSS
        Map<String, String> result = this.uploadPhoto(newFile, filename);

        //删除临时文件
        newFile.delete();

        if( result.get("ret").equals("0")){
            return ResultUtil.success_return(result.get("data"));
        }else{
            return result;
        }
    }

    /**
     * 从OSS删除文件
     * @param filename
     * @return
     */
    public Map<String, String> deleteFile(String filename){
        OSS ossClient = this.getOSSClient();
        try{
            ossClient.deleteObject(this.bucketName, filename);
        }catch (Exception e){
            log.error("OSS删除文件失败！");
            return ResultUtil.delete_error();
        }finally {
            ossClient.shutdown();
        }
        return ResultUtil.success_return(filename);
    }

    /**
     * 压缩图片
     * @param file
     * @param dir
     * @return
     */
    private File fileToThumbnail(MultipartFile file, String dir){
        File newFile = null;
        try{
            Thumbnails.of(file.getInputStream()).size(1920, 1080).toFile(dir);
            newFile = new File(dir);
        }catch (IOException e){
            log.error("压缩图失败:" + e.toString());
        }

        //如果缩小后依然大于1M则压缩一次
        while(newFile.length() > 1048576){
            System.out.println(newFile.length());
            try{
                Thumbnails.of(newFile).scale(1f).outputQuality(0.75f).toFile(dir);
            }catch (IOException e){
                log.error("压缩图片失败:" + e.toString());
            }
            newFile = new File(dir);
        }

        return newFile;
    }
}
