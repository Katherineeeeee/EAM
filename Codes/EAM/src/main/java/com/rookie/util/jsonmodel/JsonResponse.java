package com.rookie.util.jsonmodel;

/**
 * 向前台传数据的封装类
 */
public class JsonResponse {
    private int status;

    private String msg;

    public JsonResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
