package com.huhaorui.osshelper;

import com.huhaorui.osshelper.factory.OssOperatorFactory;
import com.huhaorui.osshelper.model.OssType;
import com.huhaorui.osshelper.model.Pair;
import com.huhaorui.osshelper.oss.OssOperator;
import com.huhaorui.osshelper.util.FileUtil;
import com.huhaorui.osshelper.util.RegexUtil;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @author HHR
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OssOperator ossOperator;
        System.out.println("请输入markdown文件路径");
        String path = scanner.nextLine();
        System.out.println("请选择OSS类型:\n 1.阿里云OSS\n 2.七牛云");
        int op = scanner.nextInt();
        switch (op) {
            case 1: {
                ossOperator = OssOperatorFactory.getInstance(OssType.Aliyun);
                break;
            }
            case 2: {
                ossOperator = OssOperatorFactory.getInstance(OssType.Qiniu);
                break;
            }
            default: {
                System.out.println("你的输入有误");
                return;
            }
        }
        Pair<List<String>, List<String>> stringList;
        try {
            stringList = RegexUtil.getImageUrlList(FileUtil.getFileAsString(path));
        } catch (IOException e) {
            System.out.println("文件不存在");
            return;
        }
        //TODO 上传至OSS
        //TODO 更换链接
    }
}
