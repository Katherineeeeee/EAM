package com.rookie.util.jsonmodel;

import com.rookie.model.User;
import com.rookie.util.DataUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 前台传来的User数据的封装类
 */
public class JsonUser {

    private String info;

    public User parse() {
        Map<String, String> json = toMap();
        User user = new User();
        user.setuName(json.get("username"));
        user.setuPassword(json.get("password"));
        user.setuEmail(json.get("email"));
        try {
            user.setuStatus(Integer.parseInt(json.get("status")));
        } catch (Exception e) {
        }
        return user;
    }

    public Map<String, String> toMap() {
        String userData = DataUtil.decodeBase64(info, 3);
        StringTokenizer tokenizer = new StringTokenizer(userData, "\n");
        Map<String, String> res = new HashMap<>();
        String s = null;
        while (tokenizer.hasMoreTokens()) {
            s = tokenizer.nextToken();
            int ix = s.indexOf(":");
            if (ix != -1) {
                res.put(s.substring(0, ix), s.substring(ix + 1));
            }
        }
        return res;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
