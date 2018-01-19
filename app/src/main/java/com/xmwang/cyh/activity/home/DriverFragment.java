package com.xmwang.cyh.activity.home;

import android.annotation.SuppressLint;
import android.content.Context;

import com.xmwang.cyh.R;
import com.xmwang.cyh.common.LazyLoadFragment;

/**
 * Created by xmwang on 2018/1/4.
 */
@SuppressLint("ValidFragment")
public class DriverFragment  extends LazyLoadFragment {

    private Context context;
    public DriverFragment(Context context) {
        this.context = context;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_driver;
    }

    @Override
    protected void initFragmentData() {

    }

    @Override
    protected void lazyLoad() {

    }
}
