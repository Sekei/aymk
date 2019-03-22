package com.live.tv.mvp.fragment.shop;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AfterSaleOrderBean;
import com.live.tv.mvp.adapter.shop.AfterSalesOrderListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.AfterSaleOrderListPresenter;
import com.live.tv.mvp.view.shop.IAfterSaleOrderListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class AfterSaleOrderListFragment extends BaseFragment<IAfterSaleOrderListView, AfterSaleOrderListPresenter> implements IAfterSaleOrderListView, SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Unbinder unbinder;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private AfterSalesOrderListAdapter mAdapter;
    private String order_state = "";
    private Map<String, String> params;
    private int currentPage = 1;


    public static AfterSaleOrderListFragment newInstance() {

        Bundle args = new Bundle();

        AfterSaleOrderListFragment fragment = new AfterSaleOrderListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_after_sale_order_list;
    }

    @Override
    public void initUI() {
        tvTitle.setText("售后列表");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new AfterSalesOrderListAdapter(new ArrayList<AfterSaleOrderBean>());
        recyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AfterSaleOrderBean orderBean = (AfterSaleOrderBean) adapter.getData().get(position);
//
                startAfterSaleOrderDetailFragment(orderBean.getRefund_id());
            }
        });

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                currentPage++;
                paramsInfo();//设置参数加载数据
                getPresenter().getMoreGoodOrderList(params);

            }
        }, recyclerView);
    }

    @Override
    public void initData() {
        paramsInfo();
        getPresenter().getGoodOrderList(params);
    }

    private void paramsInfo() {

        params = new ArrayMap<>();
        params.put("member_id", userBean.getMember_id());
        params.put("member_token", userBean.getMember_token());
        params.put("refund_state", order_state);
        params.put("page", String.valueOf(currentPage));


    }

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public AfterSaleOrderListPresenter createPresenter() {
        return new AfterSaleOrderListPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        if (userBean != null) {
            paramsInfo();//设置参数加载数据
            getPresenter().getGoodOrderList(params);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        mSwipeRefreshLayout.setRefreshing(false);
        errorHandle(e);
    }

    @Override
    public void onGoodOrderList(List<AfterSaleOrderBean> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (data != null && data.size() > 0) {

            mAdapter.setNewData(data);
            if (data.size() == 0) {
                //mAdapter.setEmptyView(getActivity().getLayoutInflater().inflate(R.layout.empty_address, (ViewGroup) mRecyclerView.getParent(), false));
            }
            if (data.size() < 10) {
                mAdapter.loadMoreEnd();
            }

        }
    }

    @Override
    public void onMoreGoodOrderList(List<AfterSaleOrderBean> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.addData(data);
        mAdapter.loadMoreComplete();
        if (data.size() < 10) {
            mAdapter.loadMoreEnd();

        }
    }


}
