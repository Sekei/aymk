package com.live.tv.mvp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.platform.comapi.map.E;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.king.base.util.LogUtils;
import com.king.base.util.ToastUtils;
import com.live.tv.MainActivity;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.PlateListBean;
import com.live.tv.bean.UserBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.LoginPresenter;
import com.live.tv.mvp.view.ILoginView;
import com.live.tv.util.LoadingUtil;
import com.live.tv.util.SPUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mac1010 on 17/7/29.
 */

public class LoginFragment extends BaseFragment<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;
    @BindView(R.id.seePwd)
    ImageView seePwd;
    private Map<String, String> map = new HashMap<>();

    //第三方登录
    UMShareAPI mShareAPI;
    private SHARE_MEDIA platform;
    private String type = "";
    private String uid = "";
    private String name = "";
    private String iconurl = "";
    private String union_id = "";
    private boolean IS_SEE_PWD = false;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintColor(R.color.colorPrimary);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);//状态栏所需颜色
            // tintManager.setTintColor(R.color.pure_white);//文字颜色
        }
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initUI() {
        tvTitle.setText("登录");
        mShareAPI = UMShareAPI.get(context.getApplicationContext());
    }

    @Override
    public void initData() {
        String phone_str = SPUtils.getString(context, "phone");
        if (phone_str != null && !"".equals(phone_str)) {
            phone.setText(phone_str);
        }

    }

    @Override
    public void onLoginMember(UserBean data) {

        if (EMClient.getInstance().isLoggedInBefore()) {
            //如果登陆过，判断是否连接
            if (EMClient.getInstance().isConnected()) {
                Log.d("dfc", "登录过，并且已连接！");
                saveUser(data);
                setdata(data);
                startActivity(MainActivity.class);
                finish();
            } else {
                Log.d("dfc", "登录过，已短开！");
                EMClient.getInstance().logout(true);
                loginHx(data);
            }
        } else {
            //开始登陆
            loginHx(data);
        }


        LoadingUtil.hideLoading();


    }

    @Override
    public void onThridLogin(UserBean data, String str, String code) {
        if (data != null && data.getMember_id() != null) {

            if (EMClient.getInstance().isLoggedInBefore()) {
                //如果登陆过，判断是否连接
                if (EMClient.getInstance().isConnected()) {
                    Log.d("dfc", "登录过，并且已连接！");
                    saveUser(data);
                    setdata(data);

                    startActivity(MainActivity.class);
                    finish();
                } else {
                    Log.d("dfc", "登录过，已短开！");
                    EMClient.getInstance().logout(true);
                    loginHx(data);
                }
            } else {
                //开始登陆
                loginHx(data);
            }


        } else {
            statrRegistered("2", str, code);
            LoadingUtil.hideLoading();

        }
    }

    @Override
    public void ondeviceToken(String devicetoken) {
        //   ToastUtils.showToast(getActivity(),devicetoken);
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
                setdata(data);
                startActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(int code, String message) {
                Log.d("dfc", "登录聊天服务器失败！" + code + message);
            }
        });
    }

    private void setdata(final UserBean data) {
        SharedPreferences sharedPre = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        final String deviceToken = sharedPre.getString("deviceToken", "");
        Log.e("deviceTokens1", "7777777: " + deviceToken);

        new Thread() {
            @Override
            public void run() {

                if (!deviceToken.equals("")) {
                    Map<String, String> params = new HashMap<>();
                    params.put("member_id", data.getMember_id());
                    params.put("device_token", deviceToken);
                    getPresenter().deviceToken(params);
                }

            }
        }.start();
    }


    @Override
    public void showProgress() {
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        LoadingUtil.hideLoading();
        ToastUtils.showToast(context.getApplicationContext(), e.getMessage());
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(getApp());
    }


    @OnClick({R.id.ivLeft, R.id.registed, R.id.ok, R.id.qq, R.id.wechat, R.id.forgotPwd, R.id.seePwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.registed:
                statrRegistered("1", "", "");
                break;
            case R.id.ok:
                if (TextUtils.isEmpty(phone.getText().toString())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(pwd.getText().toString())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入密码");
                    return;
                }
                saveLoginInfo(context, phone.getText().toString());
                map.put("member_account", phone.getText().toString());
                map.put("member_password", pwd.getText().toString());
                getPresenter().loginMember(map);
                LoadingUtil.showLoadingNew(context, "登陆中...");
                break;
            case R.id.qq:
                platform = SHARE_MEDIA.QQ;
                type = "qq";
                mShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, authListener);
                break;

            case R.id.wechat:
                platform = SHARE_MEDIA.WEIXIN;
                type = "wx";
                mShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
                break;

            case R.id.forgotPwd:

                startForgetLoginPwdFragment();
                break;

            case R.id.seePwd:


                if (!IS_SEE_PWD) {
                    pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    IS_SEE_PWD = true;

                } else {
                    pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    IS_SEE_PWD = false;
                }

                break;
        }
    }


    /**
     * 使用SharedPreferences保存用户登录信息
     *
     * @param context
     * @param username
     */
    public static void saveLoginInfo(Context context, String username) {
        //获取SharedPreferences对象
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor = sharedPre.edit();
        //设置参数
        editor.putString("username", username);
        //提交
        editor.commit();
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            LoadingUtil.showLoading(getActivity(), "第三方登录中...");

                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }
                });

            }

            //  ToastUtils.showToast(context.getApplicationContext(), "onstart");
        }


        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            String mopenId = data.get("openid");
            if (platform == SHARE_MEDIA.QQ) {
                map.put("qq_openid", mopenId);
                map.put("member_head_img", data.get("iconurl"));
                getPresenter().getThridLogin(map, "qq", mopenId);
            } else if (platform == SHARE_MEDIA.WEIXIN) {
                map.put("wx_openid", mopenId);
                map.put("member_head_img", data.get("iconurl"));
                getPresenter().getThridLogin(map, "wx", mopenId);
            }

        }


        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            ToastUtils.showToast(context.getApplicationContext(), "失败：" + t.getMessage());
            LoadingUtil.hideLoading();
            Log.i("dfc", "onError: " + t.getMessage());
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtils.showToast(context.getApplicationContext(), "授权取消");
            LoadingUtil.hideLoading();
        }
    };
}
