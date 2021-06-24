package com.huhaorui.osshelper.oss;

import java.io.InputStream;
import java.net.URL;

/**
 * @author HHR
 */
public class QiniuOssOperator implements OssOperator {
    /**
     * 上传一个文件，返回它的访问URL
     *
     * @param filename    保存的文件名
     * @param inputStream 上传文件的流
     * @return 访问URL
     */
    @Override
    public URL uploadFile(String filename, InputStream inputStream) {
        //TODO 完善代码
        return null;
    }
}
