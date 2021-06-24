package com.huhaorui.osshelper.factory;

import com.huhaorui.osshelper.model.OssType;
import com.huhaorui.osshelper.oss.AliyunOssOperator;
import com.huhaorui.osshelper.oss.OssOperator;

import java.util.Scanner;

/**
 * @author HHR
 */
public class OssOperatorFactory {
    public static OssOperator getInstance(OssType ossType) {
        switch (ossType) {
            case Aliyun:
                return getAliyunOssInstance();
            case Qiniu:
                return getQiniuOssInstance();
            default:
                return null;
        }
    }

    private static OssOperator getAliyunOssInstance() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入OSS的EndPoint");
        String endPoint = scanner.nextLine();
        System.out.println("请输入OSS的AccessKeyId");
        String accessKeyId = scanner.nextLine();
        System.out.println("请输入OSS的AccessKeySecret");
        String accessKeySecret = scanner.nextLine();
        System.out.println("请输入OSS的BucketName");
        String bucketName = scanner.nextLine();
        return new AliyunOssOperator(endPoint, accessKeyId, accessKeySecret, bucketName);
    }

    private static OssOperator getQiniuOssInstance() {
        return null;
    }
}
