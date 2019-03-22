package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.UpdatePayPwdpresenter;
import com.live.tv.mvp.view.mine.IUpdatePayPwdView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *
 *修改支付密码
 * Created by sh-lx on 2017/7/12.
 */

public class UpdatePayPwdFragment extends BaseFragment<IUpdatePayPwdView,UpdatePayPwdpresenter>implements IUpdatePayPwdView {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    TextView etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd2)
    EditText etPwd2;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.getYzm)
    TextView getYzm;
    @BindView(R.id.ok)
    TextView ok;

    private int count = 60;
    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            count--;
            getYzm.setClickable(false);
            getYzm.setText(count + "秒后重新发送");
        }

        @Override
        public void onFinish() {
            count = 60;
            getYzm.setClickable(true);
            getYzm.setText("获取验证码");
        }
    };

    private String type="";
    public static UpdatePayPwdFragment newInstance(String type) {
        Bundle args = new Bundle();
        UpdatePayPwdFragment fragment = new UpdatePayPwdFragment();
        fragment.type=type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_update_pay_pwd;
    }

    @Override
    public void initUI() {

        if ("setting".equals(type)){
            tvTitle.setText("设置支付密码");
        }else {
            tvTitle.setText("修改支付密码");
        }

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
                yzmMap.put("code_type", "pay_password");
                getPresenter().sendCode(yzmMap);

                break;
            case R.id.ok:

                if (checkInputKey()){
                    Map<String, String> map = new HashMap<>();
                    map.put("member_id",userBean.getMember_id());
                    map.put("member_token",userBean.getMember_token());
                   // map.put("member_account",etPhone.getText().toString());
                    map.put("member_pay_password", etPwd.getText().toString().trim());
                    map.put("code", etCode.getText().toString().trim());
                    getPresenter().UpdatePayPwd(map);

                }
                break;
        }
    }


    @Override
    public UpdatePayPwdpresenter createPresenter() {
        return new UpdatePayPwdpresenter(getApp());
    }

    @Override
    public void onUpdatePayPwd(String data) {
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



    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
    private boolean checkInputKey() {
        if (StringUtils.isBlank(etPwd.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), getResources().getString(R.string.hint_please_enter_new_password));
            return false;
        }
        if (StringUtils.isBlank(etPwd2.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), getResources().getString(R.string.hint_enter_the_password_again));
            return false;
        }      if (StringUtils.isBlank(etCode.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), getResources().getString(R.string.hint_enter_code));
            return false;
        }

        return true;
    }
}
