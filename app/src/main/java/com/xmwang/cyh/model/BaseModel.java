package com.xmwang.cyh.model;

import java.util.List;

/**
 * Created by xmwang on 2017/12/29.
 */

public class BaseModel {

    /**
     * code : 200
     * message : 成功发布信息
     * data : []
     */

    private int code;
    private String message;
    private List<?> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
