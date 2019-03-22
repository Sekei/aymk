package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.BalanceBean;
import com.live.tv.mvp.adapter.mine.BalanceDetailAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.BalanceDetailPresenter;
import com.live.tv.mvp.view.mine.IBalanceDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 余额明细
 * Created by sh-lx on 2017/7/12.
 */

public class BalanceDetailFragment extends BaseFragment<IBalanceDetailView, BalanceDetailPresenter> implements IBalanceDetailView {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private BalanceDetailAdapter mAdapter;

    public static BalanceDetailFragment newInstance() {

        Bundle args = new Bundle();

        BalanceDetailFragment fragment = new BalanceDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_balance_detail;
    }

    @Override
    public void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BalanceDetailAdapter(new ArrayList<BalanceBean>());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

        map.clear();
        map.put("member_id",userBean.getMember_id());
        map.put("member_token",userBean.getMember_token());
        getPresenter().MyProfit(map);
    }


    @Override
    public BalanceDetailPresenter createPresenter() {
        return new BalanceDetailPresenter(getApp());
    }

    @Override
    public void onBalanceList(List<BalanceBean> data) {

        if (data!=null){

            mAdapter.setNewData(data);
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

    }


}
