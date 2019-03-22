package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.MyIntegralPresenter;
import com.live.tv.mvp.view.mine.IMyIntegralView;
import com.live.tv.util.SpSingleInstance;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的积分
 * Created by sh-lx on 2017/7/12.
 */

public class MyIntegealFragment extends BaseFragment<IMyIntegralView, MyIntegralPresenter> implements IMyIntegralView {

    @BindView(R.id.tv_balance)
    TextView tvBalance;
    Unbinder unbinder;

    public static MyIntegealFragment newInstance() {

        Bundle args = new Bundle();

        MyIntegealFragment fragment = new MyIntegealFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_my_integral;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();

        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();

        if (userBean != null) {
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            getPresenter().getUserDetail(map);
        }

    }

    @Override
    public MyIntegralPresenter createPresenter() {
        return new MyIntegralPresenter(getApp());
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

    @Override
    public void onGetUserDetail(UserBean data) {
        if (data != null) {
            tvBalance.setText(data.getIntegral_value());
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
