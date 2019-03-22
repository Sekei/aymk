package com.live.tv.mvp.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.live.tv.MainActivity;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.PlateListBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.Registeredpresenter;
import com.live.tv.mvp.view.IRegisteredView;
import com.live.tv.util.LoadingUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by mac1010 on 17/7/29.
 * 注册或者绑定
 */

public class RegisteredFragment extends BaseFragment<IRegisteredView, Registeredpresenter> implements IRegisteredView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.yzm)
    EditText yzm;
    @BindView(R.id.getYzm)
    TextView getYzm;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.pwdTwo)
    EditText pwdTwo;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.seePwd)
    ImageView seePwd;
    @BindView(R.id.seePwdTwo)
    ImageView seePwdTwo;
    private String mType;//1注册2绑定
    Unbinder unbinder;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private String wxorqq;
    private String openid;

    public static RegisteredFragment newInstance(String type,String tag,String code) {
        Bundle args = new Bundle();
        RegisteredFragment fragment = new RegisteredFragment();
        fragment.mType = type;
        fragment.wxorqq=tag;
        fragment.openid=code;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);//通知栏所需颜色
            // tintManager.setTintColor(R.color.pure_white);
        }

    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_registered;
    }

    @Override
    public void initUI() {

        if (mType != null) {
            if (mType.equals("1")) {
                tvTitle.setText("注册");
                name.setVisibility(View.VISIBLE);
                pwd.setVisibility(View.VISIBLE);
                pwdTwo.setVisibility(View.VISIBLE);
                ok.setText("提交");

            } else {
                tvTitle.setText("绑定手机");
                name.setVisibility(View.GONE);
                pwd.setVisibility(View.GONE);
                pwdTwo.setVisibility(View.GONE);
                ok.setText("绑定");

            }

        } else {
            tvTitle.setText("注册");
            name.setVisibility(View.VISIBLE);
            pwd.setVisibility(View.VISIBLE);
            pwdTwo.setVisibility(View.VISIBLE);
            ok.setText("提交");

        }
    }

    @Override
    public void initData() {

    }


    @Override
    public void onRegisterMember(UserBean data) {
        ToastUtils.showToast(context.getApplicationContext(), "注册成功");
        timer.cancel();
        finish();

    }

    @Override
    public void onSendCode(String data) {
        ToastUtils.showToast(context.getApplicationContext(), "验证码已发送");
        timer.start();
    }

    /***
     * 绑定成功
     * @param data
     */
    @Override
    public void onbuildnumber(UserBean data) {

        ToastUtils.showToast(context.getApplicationContext(), "绑定成功");
        if(EMClient.getInstance().isLoggedInBefore()){
            //如果登陆过，判断是否连接
            if (EMClient.getInstance().isConnected()){
                Log.d("dfc", "登录过，并且已连接！");
                saveUser(data);
                startActivity(MainActivity.class);
                finish();
            }else {
                Log.d("dfc", "登录过，已短开！");
                EMClient.getInstance().logout(true);
                loginHx(data);
            }
        }else{
            //开始登陆
            loginHx(data);
        }


        LoadingUtil.hideLoading();

    }

    //登陆环信
    private void loginHx(final UserBean data) {

        EMClient.getInstance().login(data.getHx_account(), data.getHx_password(), new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("dfc", "登录聊天服务器成功！");
                saveUser(data);
                startActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(int code, String message) {
                Log.d("dfc", "登录聊天服务器失败！"+code+message);
            }
        });
    }


    CountDownTimer timer = new CountDownTimer(90000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            getYzm.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            getYzm.setEnabled(true);
            getYzm.setTextColor(getResources().getColor(R.color.colorPrimary));
            getYzm.setBackgroundResource(R.drawable.setbar_seach);
            getYzm.setText("获取验证码");
        }
    };


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showToast(context.getApplicationContext(), e.getMessage());
    }


    @Override
    public Registeredpresenter createPresenter() {
        return new Registeredpresenter(getApp());
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

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    private void clickZC() {

        if (mType != null && mType.equals("2")) {

            if (checkInputKey()) {
                Map<String, String> registerMap = new HashMap<>();
                registerMap.put("member_account", phone.getText().toString());
                registerMap.put("code", yzm.getText().toString());
                if (wxorqq.equals("qq")){
                    registerMap.put("qq_openid",openid);
                } else if (wxorqq.equals("wx")){
                    registerMap.put("wx_openid",openid);
                }
                getPresenter().getbuildnumber(registerMap);
            }


        } else {
            if (checkInputKey()) {
                Map<String, String> registerMap = new HashMap<>();
                registerMap.put("member_nick_name", name.getText().toString());
                registerMap.put("member_account", phone.getText().toString());
                registerMap.put("member_password", pwd.getText().toString());
                registerMap.put("code", yzm.getText().toString());
                getPresenter().registerMember(registerMap);
            }
        }

    }

    private boolean checkInputKey() {

        if (StringUtils.isBlank(phone.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入手机号");
            return false;
        }
        if (StringUtils.isBlank(yzm.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入验证码");
            return false;
        }
        if (mType != null && !mType.equals("2")) {

            if (TextUtils.isEmpty(pwd.getText().toString())) {
                ToastUtils.showToast(context.getApplicationContext(), "请输入密码");
                return false;
            }

            String s = pwd.getText().toString();
            if (s.length() < 6) {
                ToastUtils.showToast(context.getApplicationContext(), "密码长度不够");
                return false;
            }
            String mPwdTwo = pwdTwo.getText().toString();
            if (!mPwdTwo.equals(pwd.getText().toString())) {
                ToastUtils.showToast(context.getApplicationContext(), "密码两次输入不一致");
                return false;
            }

            if (StringUtils.isBlank(name.getText().toString())) {
                ToastUtils.showToast(context.getApplicationContext(), "请输入昵称");
                return false;
            }
        }

        return true;
    }


    private void clickYZM() {
        String mPhone = phone.getText().toString();
        if (TextUtils.isEmpty(mPhone)) {
            ToastUtils.showToast(context.getApplicationContext(), "请填写手机号");
            return;
        }
        if (!mPhone.matches("^1[3|4|5|7|8][0-9]{9}$")) {
            ToastUtils.showToast(context.getApplicationContext(), "请填写正确手机号");
            return;
        }
        getYzm.setEnabled(false);
        getYzm.setTextColor(getResources().getColor(R.color.colorTextG9));
        getYzm.setBackgroundResource(R.drawable.setbar_gray);
        Map<String, String> yzmMap = new HashMap<>();
        yzmMap.put("mobile", mPhone);
        yzmMap.put("code_type", "register");
        getPresenter().sendCode(yzmMap);
    }

    @OnClick({R.id.ivLeft, R.id.getYzm, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.getYzm:
                //获取验证码
                clickYZM();
                break;
            case R.id.ok:
                //提交
                clickZC();

                break;
        }
    }


}
