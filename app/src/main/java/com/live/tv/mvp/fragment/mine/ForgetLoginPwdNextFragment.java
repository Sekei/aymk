package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.UpdateLoginPwdpresenter;
import com.live.tv.mvp.view.mine.IUpdateLoginPwdView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 修改登录密码
 * Created by sh-lx on 2017/7/12.
 */

public class ForgetLoginPwdNextFragment extends BaseFragment<IUpdateLoginPwdView, UpdateLoginPwdpresenter> implements IUpdateLoginPwdView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;
    @BindView(R.id.ed_pwd)
    EditText edPwd;
    @BindView(R.id.ed_pwd2)
    EditText edPwd2;

    private String phone_str = "";
    private String code_str = "";


    public static ForgetLoginPwdNextFragment newInstance(String phone_str, String code_str) {

        Bundle args = new Bundle();
        ForgetLoginPwdNextFragment fragment = new ForgetLoginPwdNextFragment();
        fragment.phone_str = phone_str;
        fragment.code_str = code_str;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_forget_login_pwd_next;
    }

    @Override
    public void initUI() {
        tvTitle.setText("找回密码");
    }

    @Override
    public void initData() {


    }

    @OnClick({R.id.ivLeft, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.getYzm:
                break;
            case R.id.ok:

                if (checkInputKey()) {
                    Map<String, String> map = new HashMap<>();
                    map.put("member_account", phone_str);
                    map.put("member_password", edPwd.getText().toString().trim());
                    map.put("code", code_str);
                    getPresenter().UpdateLoginPwd(map);
                }
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
    public UpdateLoginPwdpresenter createPresenter() {
        return new UpdateLoginPwdpresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onUpdateLoginPwd(String data) {

        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onSendCode(String data) {

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

    private boolean checkInputKey() {
        if (StringUtils.isBlank(edPwd.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), getResources().getString(R.string.hint_please_enter_new_password));
            return false;
        }
        if (StringUtils.isBlank(edPwd2.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), getResources().getString(R.string.hint_enter_the_password_again));
            return false;
        }

        if (!edPwd.getText().toString().trim().equals(edPwd2.getText().toString().trim())) {
            ToastUtils.showToast(context.getApplicationContext(), "两次密码不一致");
            return false;
        }

        return true;
    }
}
