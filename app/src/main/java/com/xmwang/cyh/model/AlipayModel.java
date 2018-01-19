package com.xmwang.cyh.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xmwang on 2018/1/17.
 */

public class AlipayModel {



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
        private String orderstr;
        private String order_no;

        public String getOrderstr() {
            return orderstr;
        }
        public void setOrderstr(String orderstr) {
            this.orderstr = orderstr;
        }
        public String getOrder_no() {
            return order_no;
        }
        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }
    }
}
