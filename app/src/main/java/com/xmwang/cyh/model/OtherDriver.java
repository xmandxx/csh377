package com.xmwang.cyh.model;

import java.util.List;

/**
 * Created by xmwang on 2017/12/29.
 */

public class OtherDriver {

    /**
     * code : 200
     * message : 成功发布信息
     * data : {"orderId":"21"}
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
         * orderId : 21
         */

        private String order_id;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String orderId) {
            this.order_id = order_id;
        }
    }
}
