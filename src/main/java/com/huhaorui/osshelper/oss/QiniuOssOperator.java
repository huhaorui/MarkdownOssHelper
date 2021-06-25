package com.huhaorui.osshelper.oss;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author HHR
 */
public class QiniuOssOperator implements OssOperator {

    private final UploadManager uploadManager;
    private final String uploadToken;
    private final String endPoint;

    public QiniuOssOperator(String endPoint, String accessKey, String secretKey, String bucket) {
        Configuration configuration = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(configuration);
        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(bucket);
        this.uploadManager = uploadManager;
        this.uploadToken = uploadToken;
        if (endPoint.endsWith("/")) {
            this.endPoint = endPoint;
        } else {
            this.endPoint = endPoint + "/";
        }
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
        try {
            uploadManager.put(inputStream, filename, uploadToken, null, null);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        try {
            return new URL(endPoint + filename);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
