package com.example.hello.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

public class MD5Utils {
    private static final String salt = "jfiaw*(f";

    public static String encrypt(String dataStr) {
        try {
            String base = dataStr + salt;
            String md5String = DigestUtils.md5DigestAsHex(base.getBytes("UTF-8"));
            return md5String;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
