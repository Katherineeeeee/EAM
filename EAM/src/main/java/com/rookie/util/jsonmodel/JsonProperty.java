package com.rookie.util.jsonmodel;

import com.rookie.model.Property;
import com.rookie.util.DataUtil;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 前台传来的Property数据的封装类
 */
public class JsonProperty {

    private String info;

    public Pair<Property, Integer> parse() {
        //Todo
    }

    public Map<String, String> toMap() {
        //Todo
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
