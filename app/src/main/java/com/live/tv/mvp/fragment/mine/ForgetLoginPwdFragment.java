package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.os.CountDownTimer;
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
 * 忘记登录密码
 * Created by sh-lx on 2017/7/12.
 */

public class ForgetLoginPwdFragment extends BaseFragment<IUpdateLoginPwdView,UpdateLoginPwdpresenter>implements IUpdateLoginPwdView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.getYzm)
    TextView getYzm;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;

    private int count = 60;
    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            count--;
            getYzm.setClickable(false);
            getYzm.setText(count + "秒");
        }

        @Override
        public void onFinish() {
            count = 60;
            getYzm.setClickable(true);
            getYzm.setText("获取验证码");
        }
    };

    public static ForgetLoginPwdFragment newInstance() {

        Bundle args = new Bundle();

        ForgetLoginPwdFragment fragment = new ForgetLoginPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_forget_login_pwd;
    }

    @Override
    public void initUI() {
        tvTitle.setText("修改登录密码");
    }

    @Override
    public void initData() {

        if (userBean!=null){

            etPhone.setText(userBean.getMember_account());
        }

    }

    @OnClick({R.id.ivLeft,R.id.getYzm, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.getYzm:
                timer.start();
                Map<String, String> yzmMap = new HashMap<>();
                yzmMap.put("mobile", etPhone.getText().toString());
                yzmMap.put("code_type", "forget_password");
                getPresenter().sendCode(yzmMap);

                break;
            case R.id.ok:

                if (checkInputKey()){
                    startForgetLoginPwdNextFragment(etPhone.getText().toString(),etCode.getText().toString().trim());
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
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    @Override
    public void onUpdateLoginPwd(String data) {

        ToastUtils.showToast(context.getApplicationContext(),data);
        finish();
    }

    @Override
    public void onSendCode(String data) {

        ToastUtils.showToast(context.getApplicationContext(),"发送成功");
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
        if (StringUtils.isBlank(etPhone.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), getResources().getString(R.string.hint_enter_the_phone));
            return false;
        }
           if (StringUtils.isBlank(etCode.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), getResources().getString(R.string.hint_enter_code));
            return false;
        }

        return true;
    }
}
