package com.xmwang.cyh.model;

import java.util.List;

/**
 * Created by xmwang on 2018/1/5.
 */

public class TopNews {

    /**
     * code : 200
     * message : 头条新闻
     * data : [{"article_id":139,"title":"郑州一日游"},{"article_id":20,"title":"商家服务"}]
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
         * article_id : 139
         * title : 郑州一日游
         */

        private int article_id;
        private String title;

        public int getArticle_id() {
            return article_id;
        }

        public void setArticle_id(int article_id) {
            this.article_id = article_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
