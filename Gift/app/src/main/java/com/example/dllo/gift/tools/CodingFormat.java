package com.example.dllo.gift.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by dllo on 16/6/14.
 */
public class CodingFormat {
    public static String codingFormat(String str) {

        try {
            String utfStr = URLEncoder.encode(str, "utf-8");
            return utfStr;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "" ;
    }
}
