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
        //Todo
    }

    public Map<String, String> toMap() {
        //Todo
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
