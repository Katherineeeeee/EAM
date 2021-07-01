package com.rookie.util.jsonmodel;

import com.rookie.model.Manager;
import com.rookie.util.DataUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 前台传来的Manager数据的封装类
 */
public class JsonManager {

    private String info;

    public Manager parse() {
        Map<String, String> json = toMap();
        Manager manager = new Manager();
        manager.setmName(json.get("name"));
        manager.setmPassword(json.get("password"));
        manager.setmPhone(json.get("phone"));
        manager.setmEmail(json.get("email"));
        try {
            manager.setMStatus(Integer.parseInt(json.get("status")));
        } catch (Exception e) {
        }
        return manager;
    }

    public Map<String, String> toMap() {
        String userData = DataUtil.decodeBase64(info, 3);
//        System.out.println(userData);
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
