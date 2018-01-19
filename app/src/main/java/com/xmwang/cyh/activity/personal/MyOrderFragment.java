package com.xmwang.cyh.activity.personal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.xmwang.cyh.R;
import com.xmwang.cyh.common.Data;
import com.xmwang.cyh.common.LazyLoadFragment;
import com.xmwang.cyh.common.RetrofitHelper;
import com.xmwang.cyh.model.MyCouponsModel;
import com.xmwang.cyh.model.OrderModel;
import com.xmwang.cyh.viewholder.MyCouponsListHolder;
import com.xmwang.cyh.viewholder.MyorderHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xmWang on 2018/1/15.
 */

@SuppressLint("ValidFragment")
public class MyOrderFragment extends LazyLoadFragment {
    @BindView(R.id.erv_list)
    EasyRecyclerView ervList;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    Unbinder unbinder;
    private Context context;
    private int orderType = 1;
    private int pageIndex = 1;
    private RecyclerArrayAdapter<OrderModel.DataBean> adapter;
    public MyOrderFragment(Context context,int orderType) {
        this.context = context;
        this.orderType = orderType;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_my_coupons;
    }

    @Override
    protected void initFragmentData() {
        init();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void init() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getList();
            }
        });
        /**
         * 最新优惠
         */
        ervList.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }

        });
        DividerDecoration itemDecoration = new DividerDecoration(this.getResources().getColor(R.color.backgroundColor), 1, 0, 0);
        itemDecoration.setDrawLastItem(false);
        ervList.addItemDecoration(itemDecoration);

        ervList.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<OrderModel.DataBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new MyorderHolder(parent);
            }
        });

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
//                Intent intent = new Intent(context, FindSupplyDetailActivity.class);
//                intent.putExtra("id", ls_supply.get(position).getSupply_id());
//                startActivity(intent);
            }
        });

        getList();
    }

    private void getList(){
        retrofit2.Call<OrderModel> call = RetrofitHelper.instance.getApiUserService().myOrder(
                Data.instance.AdminId,
                Data.instance.getUserId(),
                1,
                6,
                pageIndex
        );

        call.enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(retrofit2.Call<OrderModel> call, Response<OrderModel> response) {
                swipeContainer.setRefreshing(false);
                OrderModel model = response.body();
                if (model.getCode() != RetrofitHelper.instance.SUCCESS_CODE) {
                    return;
                }
                if (model.getData().size() > 0) {
//                    sortList(ls);
                    if (pageIndex == 1) {
                        adapter.clear();
                    }
                    adapter.addAll(model.getData());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<OrderModel> call, Throwable t) {
                swipeContainer.setRefreshing(false);
            }
        });
    }
}
