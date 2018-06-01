package com.hoooopa.hoopa.hoopa.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Pray on 2018/6/1.
 */

public class StringUtils {


    public static void main(String[] args) throws UnsupportedEncodingException {

        String s = UnicodeToCN("\u6839\u636e\u914d\u7f6e\u6570\u636e\u52a8\u6001\u8bbe\u7f6e\u5e94\u7528\u5185\u7684icon\uff0c\u4e0d\u9700\u4fee\u6539\u4ee3\u7801\u5c31\u5b8c\u6210\u5566\uff01");
        System.out.println(s);
    }


    public static String UnicodeToCN(String unicode){
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = unicode.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = unicode.substring(start + 2, unicode.length());
            } else {
                charStr = unicode.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

}
