package com.xmwang.cyh.model;

import java.util.List;

/**
 * Created by xmWang on 2017/12/21.
 */

public class Charging {

    /**
     * code : 200
     * message : 计费模板
     * data : [{"charging_id":2,"charging":"默认计费模板"}]
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
         * charging_id : 2
         * charging : 默认计费模板
         */

        private int charging_id;
        private String charging;

        public int getCharging_id() {
            return charging_id;
        }

        public void setCharging_id(int charging_id) {
            this.charging_id = charging_id;
        }

        public String getCharging() {
            return charging;
        }

        public void setCharging(String charging) {
            this.charging = charging;
        }
    }
}
