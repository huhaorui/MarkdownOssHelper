package com.huhaorui.osshelper.util;

import com.huhaorui.osshelper.model.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.compile;

/**
 * @author HHR
 */
public class RegexUtil {
    /**
     * 从文件中分割出需要的成分
     *
     * @param file 字符串形式保存的文件
     * @return Pair<其他正文, 需要替换的路径>
     */
    public static Pair<List<String>, List<String>> getImageUrlList(String file) {
        //仅适用于Windows
        Pattern pattern = compile("[A-Z]:\\\\.*(png|jpg|gif|jpeg)");
        Matcher matcher = pattern.matcher(file);
        List<String> urls = new ArrayList<>();
        while (matcher.find()) {
            urls.add(matcher.group());
        }
        return new Pair<>(urls, pattern.splitAsStream(file).collect(Collectors.toList()));
    }
}
