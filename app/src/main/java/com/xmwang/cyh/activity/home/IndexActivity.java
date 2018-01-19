package com.xmwang.cyh.activity.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xmwang.cyh.BaseActivity;
import com.xmwang.cyh.R;
import com.xmwang.cyh.utils.ActivityManager;
import com.xmwang.cyh.utils.GetTime;
import com.xmwang.cyh.utils.ToastUtils;
import com.xmwang.cyh.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexActivity extends BaseActivity {
    public List<Fragment> ls_fragment = new ArrayList();
    @BindView(R.id.home_viewpager)
    NoScrollViewPager homeViewpager;
    @BindView(R.id.home_tabLayout)
    TabLayout homeTabLayout;
    @BindView(R.id.line)
    View line;
    private HomeFragment homeFragment;
    private BusinessFragment businessFragment;
    private DriverFragment driverFragment;
    private TravelFragment travelFragment;
    private PersonalFragment personFragment;
    private Map<Integer, ViewHolder> map_view;
    private int currIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_index);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //初始化fragment
        homeFragment = new HomeFragment(this);
        businessFragment = new BusinessFragment(this);
        driverFragment = new DriverFragment(this);
        travelFragment = new TravelFragment(this);
        personFragment = new PersonalFragment(this);

        ls_fragment.add(homeFragment);
        ls_fragment.add(businessFragment);
        ls_fragment.add(driverFragment);
        ls_fragment.add(travelFragment);
        ls_fragment.add(personFragment);

        homeViewpager.setAdapter(new MyFrageStatePagerAdapter(
                getFragmentManager()));
        homeViewpager.setCurrentItem(0);
        homeViewpager.setOffscreenPageLimit(5);

        View tabView1 = LayoutInflater.from(this).inflate(R.layout.home_action_1, null);
        View tabView2 = LayoutInflater.from(this).inflate(R.layout.home_action_2, null);
        View tabView3 = LayoutInflater.from(this).inflate(R.layout.home_action_3, null);
        View tabView4 = LayoutInflater.from(this).inflate(R.layout.home_action_4, null);
        View tabView5 = LayoutInflater.from(this).inflate(R.layout.home_action_5, null);
        //绑定textview，用于修改字体颜色
        map_view = new HashMap<>();
        map_view.put(0, new ViewHolder(tabView1));
        map_view.put(1, new ViewHolder(tabView2));
//        map_view.put(2, new ViewHolder(tabView3));
        map_view.put(3, new ViewHolder(tabView4));
        map_view.put(4, new ViewHolder(tabView5));

        homeTabLayout.addTab(homeTabLayout.newTab().setCustomView(tabView1));
        homeTabLayout.addTab(homeTabLayout.newTab().setCustomView(tabView2));
        homeTabLayout.addTab(homeTabLayout.newTab().setCustomView(tabView3));
        homeTabLayout.addTab(homeTabLayout.newTab().setCustomView(tabView4));
        homeTabLayout.addTab(homeTabLayout.newTab().setCustomView(tabView5));

        homeTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 2) {
                    TabLayout.Tab currTab = homeTabLayout.getTabAt(currIndex);
                    currTab.select();
                    return;
                }
                homeViewpager.setCurrentItem(tab.getPosition(), false);
                currIndex = tab.getPosition();
                View tempView = map_view.get(tab.getPosition()).tv;
                tempView.setSelected(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @OnClick(R.id.ll_driver)
    public void onViewClicked() {
        startActivity(new Intent(this, com.xmwang.cyh.daijia.IndexActivity.class));
    }

    @Override
    public void onBackPressed() {
//        自己处理返回按键的内容
//        //super.onBackPressed();
        // 当popupWindow 正在展示的时候 按下返回键 关闭popupWindow 否则关闭activity

        if (GetTime.getInstance().isFastDoubleClick(2000)) {
            ActivityManager.getInstance().removeAllActivity();
        } else {
            ToastUtils.getInstance().toastShow("再按一次退出程序");
        }
    }

    /**
     * viewpager适配器
     */
    class MyFrageStatePagerAdapter extends FragmentPagerAdapter {
        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ls_fragment.get(position);
        }

        @Override
        public int getCount() {
            return ls_fragment.size();
        }
    }

    /**
     * 用于绑定tv（其实不用这么麻烦，懒得改）
     */
    class ViewHolder {
        TextView tv;
        View parent;

        public ViewHolder(View parent) {
            this.tv = (TextView) parent.findViewById(R.id.home_action_item_tv);
            this.parent = parent;
        }
    }
}
