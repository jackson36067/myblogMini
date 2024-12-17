package com.jackson.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

public class AliOssUtils {

    public AliOssUtils() {
    }

    public AliOssUtils(String endpoint, String accessKeyId, String accessKeySecret, String bucketName) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
    }

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 通过alioss保存图片,并返回图片的地址
     *
     * @return
     */
    public String upload(MultipartFile image) {
        // 获取上传的文件的输入流
        InputStream inputStream = null;
        try {
            inputStream = image.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 避免文件覆盖
        String originalFilename = image.getOriginalFilename();
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);//参数分别表示上传到 OSS 的地方，文件名，上传的文件

        //文件访问路径
        // 文件访问路径
        String url = String.format("http://%s.%s/%s", bucketName, endpoint.split("//")[1], fileName);
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AliOssUtils that = (AliOssUtils) o;
        return Objects.equals(endpoint, that.endpoint) && Objects.equals(accessKeyId, that.accessKeyId) && Objects.equals(accessKeySecret, that.accessKeySecret) && Objects.equals(bucketName, that.bucketName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endpoint, accessKeyId, accessKeySecret, bucketName);
    }

    @Override
    public String toString() {
        return "AliOssUtils{" +
                "endpoint='" + endpoint + '\'' +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", bucketName='" + bucketName + '\'' +
                '}';
    }
}
