package com.huhaorui.osshelper.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author HHR
 */
public class AliyunOssOperator implements OssOperator {
    private final OSS ossClient;
    private final String bucketName;
    private final String endpoint;

    public AliyunOssOperator(String endpoint, String assessKeyId, String accessKeySecret, String bucketName) {
        ossClient = new OSSClientBuilder().build(endpoint, assessKeyId, accessKeySecret);
        this.endpoint = "https://" + bucketName + "." + endpoint;
        this.bucketName = bucketName;
    }

    /**
     * 上传一个文件，返回它的访问URL
     *
     * @param filename    保存的文件名
     * @param inputStream 上传文件的流
     * @return 访问URL
     */
    @Override
    public URL uploadFile(String filename, InputStream inputStream) {
        ossClient.putObject(bucketName, filename, inputStream);
        try {
            return new URL(endpoint + "/" + filename);
        } catch (MalformedURLException e) {
            return null;
        }
    }

}
