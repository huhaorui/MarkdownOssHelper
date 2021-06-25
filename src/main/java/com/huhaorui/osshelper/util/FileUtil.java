package com.huhaorui.osshelper.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author HHR
 */
public class FileUtil {
    public static InputStream getFileInputStream(String path) throws FileNotFoundException {
        File file = new File(path);
        return new FileInputStream(file);
    }

    public static String getFileAsString(String path) throws IOException {
        InputStream inputStream = getFileInputStream(path);
        int length = inputStream.available();
        byte[] bytes = new byte[length];
        inputStream.read(bytes);
        inputStream.close();
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
