package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.ProfitBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.mine.MyProfitAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.MyProfitPresenter;
import com.live.tv.mvp.view.mine.IMyProfitView;
import com.live.tv.util.SpSingleInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的收益
 * Created by sh-lx on 2017/7/12.
 */

public class MyProfitFragment extends BaseFragment<IMyProfitView, MyProfitPresenter> implements IMyProfitView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private MyProfitAdapter mAdapter;

    public static MyProfitFragment newInstance() {

        Bundle args = new Bundle();

        MyProfitFragment fragment = new MyProfitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_my_profit;
    }

    @Override
    public void initUI() {
        tvTitle.setText("我的收益");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MyProfitAdapter(new ArrayList<ProfitBean>());
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void initData() {

        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if (userBean != null) {
            map= new HashMap<>();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            getPresenter().getUserDetail(map);
        }


        Map<String,String> mMap = new HashMap<>();
        mMap.put("member_id",userBean.getMember_id());
        mMap.put("member_token",userBean.getMember_token());
        mMap.put("doctor_id",userBean.getDoctorBean().getDoctor_id());
        getPresenter().MyProfit(mMap);

    }

    @OnClick({R.id.ivLeft,R.id.tv_tixian})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_tixian:
                startTixianFragment("1");
                break;
        }
    }

    @Override
    public MyProfitPresenter createPresenter() {
        return new MyProfitPresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        errorHandle(e);
    }

    @Override
    public void onMyProfit(List<ProfitBean> data) {

        if (data!=null){

            mAdapter.setNewData(data);
        }
    }

    @Override
    public void onGetUserDetail(UserBean data) {
        if (data!=null){
            tvBalance.setText(data.getProfit_amount());
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
