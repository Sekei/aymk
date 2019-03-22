package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.MybalancePresenter;
import com.live.tv.mvp.view.mine.IMyBalanceView;
import com.live.tv.util.SpSingleInstance;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的余额
 * Created by sh-lx on 2017/7/12.
 */

public class MyBalanceFragment extends BaseFragment<IMyBalanceView,MybalancePresenter>implements IMyBalanceView {

    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_chongzhi)
    TextView tvChongzhi;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    Unbinder unbinder;

    public static MyBalanceFragment newInstance() {

        Bundle args = new Bundle();

        MyBalanceFragment fragment = new MyBalanceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_my_balance;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public MybalancePresenter createPresenter() {
        return new MybalancePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_chongzhi, R.id.tv_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_chongzhi:
                startChongzhiFragment();
                break;
            case R.id.tv_tixian:

                startTixianFragment("0");
                break;
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

    @Override
    public void onGetUserDetail(UserBean data) {

        if (data!=null){
            tvBalance.setText(data.getMember_amount());
        }
    }
}
