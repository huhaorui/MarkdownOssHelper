package com.huhaorui.osshelper;

import com.huhaorui.osshelper.factory.OssOperatorFactory;
import com.huhaorui.osshelper.model.OssType;
import com.huhaorui.osshelper.model.Pair;
import com.huhaorui.osshelper.oss.OssOperator;
import com.huhaorui.osshelper.util.FileUtil;
import com.huhaorui.osshelper.util.RegexUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
        assert ossOperator != null;
        Pair<List<String>, List<String>> stringList;
        try {
            stringList = RegexUtil.getImageUrlList(FileUtil.getFileAsString(path));
        } catch (IOException e) {
            System.out.println("文件不存在");
            return;
        }
        List<String> paths = stringList.getFirst();
        List<String> margins = stringList.getSecond();
        List<String> urls = new ArrayList<>();
        paths.forEach(it -> {
            try {
                urls.add(String.valueOf(ossOperator.uploadFile(FileUtil.getFileName(it), FileUtil.getFileInputStream(it))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                urls.add("");
            }
        });
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < urls.size(); i++) {
            result.append(margins.get(i));
            result.append(urls.get(i));
        }
        result.append(margins.get(margins.size() - 1));
        try {
            FileUtil.writeStringToFile(result.toString(), "new_" + FileUtil.getFileName(path));
            System.out.println("处理完成，文件输出成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
