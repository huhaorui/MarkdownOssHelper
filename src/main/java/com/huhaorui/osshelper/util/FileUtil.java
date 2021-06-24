package com.huhaorui.osshelper.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author HHR
 */
public class FileUtil {
    public InputStream getFileInputStream(String path) throws FileNotFoundException {
        File file = new File(path);
        return new FileInputStream(file);
    }
}
