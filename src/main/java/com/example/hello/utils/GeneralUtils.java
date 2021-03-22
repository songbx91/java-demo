package com.example.hello.utils;

import com.sun.istack.NotNull;

public class GeneralUtils {
    public static String getFriendshipToken(@NotNull Long userId1, @NotNull Long userId2) {
        StringBuilder sb = new StringBuilder();
        if (userId1 < userId2) {
            sb.append(userId1).append("_").append(userId2);
        } else {
            sb.append(userId2).append("_").append(userId1);
        }
        String encryptStr = MD5Utils.encrypt(sb.toString());
        return sb.append(":").append(encryptStr).toString();
    }
}
