package com.xmwang.cyh.model;

import java.util.List;

/**
 * Created by xmWang on 2018/1/15.
 */

public class QiangCouponsModel {

    /**
     * code : 200
     * message : 获取成功
     * data : [{"coupon_id":10,"coupon_name":"10元抵用券","type":1,"start_time":"2017-12-24","end_time":"2017-12-29","coupon_num":20,"coupon_pic":"/data/favourable_action_pic/coupon0/original0_10_580x260.jpg","min_amount":"0.00","max_amount":"0.00","discount":"10.00","user_coupon_num":0,"supplier_id":0,"admin_id":1},{"coupon_id":9,"coupon_name":"10元抵用券","type":1,"start_time":"2017-12-24","end_time":"2017-12-29","coupon_num":10,"coupon_pic":"/data/favourable_action_pic/coupon12/original12_9_580x260.jpg","min_amount":"0.00","max_amount":"0.00","discount":"10.00","user_coupon_num":0,"supplier_id":12,"admin_id":1},{"coupon_id":8,"coupon_name":"30元抵用券","type":1,"start_time":"2017-12-24","end_time":"2017-12-30","coupon_num":5,"coupon_pic":"/data/favourable_action_pic/coupon9/original9_8_580x260.jpg","min_amount":"0.00","max_amount":"0.00","discount":"30.00","user_coupon_num":0,"supplier_id":9,"admin_id":1},{"coupon_id":7,"coupon_name":"shop优惠券","type":0,"start_time":"2017-05-31","end_time":"2017-06-03","coupon_num":1,"coupon_pic":"/data/favourable_action_pic/coupon9/original9_7_580x260.jpg","min_amount":"50.00","max_amount":"0.00","discount":"10.00","user_coupon_num":1,"supplier_id":9,"admin_id":1},{"coupon_id":6,"coupon_name":"消费满30元优惠2元","type":0,"start_time":"2017-04-26","end_time":"2017-05-30","coupon_num":2,"coupon_pic":"/data/favourable_action_pic/coupon9/original9_6_580x260.jpg","min_amount":"30.00","max_amount":"0.00","discount":"2.00","user_coupon_num":1,"supplier_id":9,"admin_id":1},{"coupon_id":5,"coupon_name":"消费满20元优惠1元","type":0,"start_time":"2017-04-26","end_time":"2017-06-29","coupon_num":5,"coupon_pic":"/data/favourable_action_pic/coupon9/original9_5_580x260.jpg","min_amount":"20.00","max_amount":"0.00","discount":"1.00","user_coupon_num":2,"supplier_id":9,"admin_id":1}]
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
         * coupon_id : 10
         * coupon_name : 10元抵用券
         * type : 1
         * start_time : 2017-12-24
         * end_time : 2017-12-29
         * coupon_num : 20
         * coupon_pic : /data/favourable_action_pic/coupon0/original0_10_580x260.jpg
         * min_amount : 0.00
         * max_amount : 0.00
         * discount : 10.00
         * user_coupon_num : 0
         * supplier_id : 0
         * admin_id : 1
         */

        private int coupon_id;
        private String coupon_name;
        private int type;
        private String start_time;
        private String end_time;
        private int coupon_num;
        private String coupon_pic;
        private String min_amount;
        private String max_amount;
        private String discount;
        private int user_coupon_num;
        private int supplier_id;
        private int admin_id;
        private String bai;
        public String getBai() {
            return bai;
        }

        public void setBai(String bai) {
            this.bai = bai;
        }

        public int getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(int coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getCoupon_num() {
            return coupon_num;
        }

        public void setCoupon_num(int coupon_num) {
            this.coupon_num = coupon_num;
        }

        public String getCoupon_pic() {
            return coupon_pic;
        }

        public void setCoupon_pic(String coupon_pic) {
            this.coupon_pic = coupon_pic;
        }

        public String getMin_amount() {
            return min_amount;
        }

        public void setMin_amount(String min_amount) {
            this.min_amount = min_amount;
        }

        public String getMax_amount() {
            return max_amount;
        }

        public void setMax_amount(String max_amount) {
            this.max_amount = max_amount;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public int getUser_coupon_num() {
            return user_coupon_num;
        }

        public void setUser_coupon_num(int user_coupon_num) {
            this.user_coupon_num = user_coupon_num;
        }

        public int getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(int supplier_id) {
            this.supplier_id = supplier_id;
        }

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }
    }
}
