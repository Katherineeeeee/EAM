package com.rookie.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class DataUtil {

    /**
     * 解码info
     * @param info Base64加密串
     * @param times 解码次数
     * @return 解码后的数据
     */
    public static String decodeBase64(String info, int times) {
        String userData = info;
        for (int i = 0; i < times; i++) {
            userData = new String(Base64.decodeBase64(userData));
        }
        return userData;
    }

    /**
     * 编码info
     * @param info 待编码的数据
     * @param times 编码次数
     * @return 编码后的数据
     */
    public static String encodeBase64(String info, int times) {
        String res = info;
        for (int i = 0; i < times; i++) {
            res = new String(Base64.encodeBase64(res.getBytes()));
        }
        return res;
    }

    /**
     * MD5加密
     * @param info 待加密信息
     * @return 加密后的数据
     */
    public static String encodeMD5Hex(String info) {
        return DigestUtils.md5Hex(info);
    }
}
