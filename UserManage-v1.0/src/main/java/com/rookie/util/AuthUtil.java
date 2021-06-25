package com.rookie.util;

import java.util.StringTokenizer;

public class AuthUtil {

    public static String[] getUserInfo(String userData) {
        StringTokenizer tokenizer = new StringTokenizer(userData, "\n");
        String username = tokenizer.nextToken().substring(9);
        String password = tokenizer.nextToken().substring(9);
        return new String[]{username, password};
    }
}
