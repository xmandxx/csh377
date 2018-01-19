package com.xmwang.cyh.model;

import java.util.List;

/**
 * Created by xmwang on 2018/1/5.
 */

public class Utilities {
    /**
     * code : 200
     * message : 分类
     * data : [{"id":1,"name":"汽车金融","admin_img_url":"/data/navigator_img/i12.png"},{"id":2,"name":"违章查询","admin_img_url":"/data/navigator_img/i13.png"},{"id":3,"name":"油卡充值","admin_img_url":"/data/navigator_img/i14.png"},{"id":4,"name":"道路救援","admin_img_url":"/data/navigator_img/i15.png"},{"id":6,"name":"车辆估价","admin_img_url":"/data/navigator_img/i16.png"},{"id":7,"name":"车险","admin_img_url":"/data/navigator_img/i17.png"},{"id":8,"name":"油耗","admin_img_url":"/data/navigator_img/i18.png"},{"id":9,"name":"拼车","admin_img_url":"/data/navigator_img/i19.png"},{"id":10,"name":"代驾","admin_img_url":"/data/navigator_img/i19.png"},{"id":11,"name":"自驾游","admin_img_url":"/data/navigator_img/i20.png"}]
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
         * id : 1
         * name : 汽车金融
         * admin_img_url : /data/navigator_img/i12.png
         */

        private int id;
        private String name;
        private String admin_img_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdmin_img_url() {
            return admin_img_url;
        }

        public void setAdmin_img_url(String admin_img_url) {
            this.admin_img_url = admin_img_url;
        }
    }
}
