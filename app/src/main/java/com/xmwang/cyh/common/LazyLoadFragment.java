package com.xmwang.cyh.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * @Description: 延迟加载fragment (实现延迟加载和绑定、解绑ButterKnife、绑定、解绑EventBus)
 * @author: Cts
 * @date: 2016-11-21 14:11
 */

public abstract class LazyLoadFragment extends Fragment {
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    private Unbinder unbinder;
    private boolean isEventBus = false;

    public void setEventBus(boolean eventBus) {
        isEventBus = eventBus;
        //初始化EventBus
        if (isEventBus) {

            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragmentData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    protected abstract int setLayout();

    protected abstract void initFragmentData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {
    }


    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (isEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }
}