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
        Map<String, String> json = toMap();
        Property property = new Property();
        property.setpName(json.get("name"));
        property.setpBrand(json.get("brand"));
        property.setpModel(json.get("model"));
        property.setpSpec(json.get("spec"));
        property.setpTime(new Date());
        int sum = 1;
        try{
            sum = Integer.parseInt(json.get("sum"));
        } catch (Exception e) {
        }
        return Pair.of(property, sum);
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
