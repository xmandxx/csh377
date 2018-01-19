package com.xmwang.cyh.activity.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.xmwang.cyh.BaseActivity;
import com.xmwang.cyh.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponsActivity extends BaseActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    public List<Fragment> ls_fragment = new ArrayList();
    private MyCouponsFragment myCouponsFragment;
    private QiangCouponsFragment qiangCouponsFragment;
    private ExceedCouponsFragment exceedCouponsFragment;
//    private Map<Integer, ViewHolder> map_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_coupons);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }

    private void init(){
        tabLayout.addTab(tabLayout.newTab().setText("我的优惠券"));
        tabLayout.addTab(tabLayout.newTab().setText("抢优惠"));
        tabLayout.addTab(tabLayout.newTab().setText("已过期券"));


        //初始化fragment
        myCouponsFragment = new MyCouponsFragment(this);
        qiangCouponsFragment = new QiangCouponsFragment(this);
        exceedCouponsFragment = new ExceedCouponsFragment(this);

        ls_fragment.add(myCouponsFragment);
        ls_fragment.add(qiangCouponsFragment);
        ls_fragment.add(exceedCouponsFragment);

        viewPager.setAdapter(new MyFrageStatePagerAdapter(
                getFragmentManager()));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(5);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
}
