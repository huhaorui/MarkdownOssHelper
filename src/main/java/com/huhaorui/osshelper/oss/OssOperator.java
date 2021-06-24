package com.huhaorui.osshelper.oss;

import java.io.InputStream;
import java.net.URL;

/**
 * @author HHR
 */
public interface OssOperator {
    /**
     * 上传一个文件，返回它的访问URL
     *
     * @param filename    保存的文件名
     * @param inputStream 上传文件的流
     * @return 访问URL
     */
    URL uploadFile(String filename, InputStream inputStream);
}
