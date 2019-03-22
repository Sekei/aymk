package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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
import com.live.tv.mvp.presenter.mine.VerifyPhonePresenter;
import com.live.tv.mvp.view.mine.IVerifyPhoneView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 验证手机号
 * Created by sh-lx on 2017/7/12.
 */

public class VerifyPhoneFragment extends BaseFragment<IVerifyPhoneView,VerifyPhonePresenter>implements IVerifyPhoneView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvPhone)
    EditText editPhone;
    TextView tvGetCode;
    @BindView(R.id.ed_Code)
    EditText edCode;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;

    private String bind_type = "";

    Map<String, String> map;
    private int count = 60;
    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            count--;
            tvGetCode.setClickable(false);
            tvGetCode.setText(count + "秒后重新发送");
        }

        @Override
        public void onFinish() {
            count = 60;
            tvGetCode.setClickable(true);
            tvGetCode.setText("获取验证码");
        }
    };

    public static VerifyPhoneFragment newInstance(String bind_type) {
        Bundle args = new Bundle();
        VerifyPhoneFragment fragment = new VerifyPhoneFragment();
        fragment.bind_type = bind_type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_verify_phone;
    }

    @Override
    public void initUI() {
        tvTitle.setText("验证手机号");
        tvGetCode = findView(R.id.tv_code);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivLeft,R.id.tv_code, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_code:

                if (!checkPhone()) return;
                timer.start();

                map= new HashMap<>();
                map.put("mobile",editPhone.getText().toString().trim());
                map.put("code_type","ver_mobile");
                getPresenter().sendCode(map);

                break;
            case R.id.ok:

                if (checkPhone_code()){
                    Map<String,String> mMap = new HashMap<>();
                    mMap.put("mobile",editPhone.getText().toString().trim());
                    mMap.put("code_type","ver_mobile");
                    mMap.put("code",edCode.getText().toString().trim());
                    getPresenter().VerifyPhone(mMap);
                    Log.i("dfc", "onClick: ");
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
    public VerifyPhonePresenter createPresenter() {
        return new VerifyPhonePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onSendCode(String data) {

        ToastUtils.showToast(context.getApplicationContext()," 发送成功");
    }

    @Override
    public void onVerifyPhone(String data) {
        if (null!=data){

            if ("alipay".equals(bind_type)){
                startBindAlipayFragment();

            }else if ("wx".equals(bind_type)){
                startBindWxFragment();

            }

            finish();
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
        errorHandle(e);
    }



    private boolean checkPhone() {

        if (StringUtils.isBlank(editPhone.getText().toString().trim())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入手机号");
            return false;
        }
        return true;
    }

    private boolean checkPhone_code() {

        if (StringUtils.isBlank(editPhone.getText().toString().trim())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入手机号");
            return false;
        }
        if (StringUtils.isBlank(edCode.getText().toString().trim())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入验证码");
            return false;
        }


        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.onFinish();
        }
    }
}
