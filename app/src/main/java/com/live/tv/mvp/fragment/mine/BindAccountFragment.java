package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 绑定账户列表
 * Created by sh-lx on 2017/7/12.
 */

public class BindAccountFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_bind_alipay)
    TextView tvBindAlipay;
    @BindView(R.id.tv_bind_wx)
    TextView tvBindWx;
    Unbinder unbinder;

    public static BindAccountFragment newInstance() {
        Bundle args = new Bundle();
        BindAccountFragment fragment = new BindAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_bind_account;
    }

    @Override
    public void initUI() {
        tvTitle.setText("绑定提现账户");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivLeft,R.id.tv_bind_alipay, R.id.tv_bind_wx})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_bind_alipay:
                startVerifyPhoneFragment("alipay");
                break;
            case R.id.tv_bind_wx:
                startVerifyPhoneFragment("wx");
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
