package com.xmwang.cyh.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xmwang.cyh.R;
import com.xmwang.cyh.application.GlideApp;
import com.xmwang.cyh.common.RetrofitHelper;
import com.xmwang.cyh.model.OrderModel;


public class MyorderHolder extends BaseViewHolder<OrderModel.DataBean> {
    TextView txtTitle;
    TextView txtTitleRight;
    ImageView img;
    TextView txtGoodsName;
    TextView txtGoodsDetail;
    TextView txtUnitPrice;
    TextView txtUnitNumber;
    TextView txtNumber;
    TextView txtPrice;

    public MyorderHolder(ViewGroup parent) {
        super(parent, R.layout.item_my_order);
        txtTitle = $(R.id.txt_title);
        txtTitleRight = $(R.id.txt_title_right);
        img = $(R.id.img);
        txtGoodsName = $(R.id.txt_goods_name);
        txtGoodsDetail = $(R.id.txt_goods_detail);
        txtUnitPrice = $(R.id.txt_unit_price);
        txtUnitNumber = $(R.id.txt_unit_number);
        txtNumber = $(R.id.txt_number);
        txtPrice = $(R.id.txt_price);
    }

    @Override
    public void setData(final OrderModel.DataBean data) {


    }
}
