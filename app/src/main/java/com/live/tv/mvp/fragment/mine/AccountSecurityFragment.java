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
 * 修改登录密码
 * Created by sh-lx on 2017/7/12.
 */

public class AccountSecurityFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.update_login_pwd)
    TextView updateLoginPwd;
    @BindView(R.id.update_pay_pwd)
    TextView updatePayPwd;
    Unbinder unbinder;

    public static AccountSecurityFragment newInstance() {

        Bundle args = new Bundle();

        AccountSecurityFragment fragment = new AccountSecurityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_account_security;
    }

    @Override
    public void initUI() {
        tvTitle.setText("账号与安全");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivLeft,R.id.update_login_pwd, R.id.update_pay_pwd})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.update_login_pwd:

                startUpdateLoginPwdFragment();
                break;
            case R.id.update_pay_pwd:
                startUpdatePayPwdFragment("update");
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
