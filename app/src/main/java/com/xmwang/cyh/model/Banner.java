package com.xmwang.cyh.model;

import java.util.List;

/**
 * Created by xmwang on 2018/1/5.
 */

public class Banner {
    /**
     * code : 200
     * message :
     * data : [{"ad_code":"/data/afficheimg/1503511878430925950.jpg"},{"ad_code":"/data/afficheimg/1504889780753624424.jpg"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ad_code : /data/afficheimg/1503511878430925950.jpg
         */

        private String ad_code;

        public String getAd_code() {
            return ad_code;
        }

        public void setAd_code(String ad_code) {
            this.ad_code = ad_code;
        }
    }
}
