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
import com.live.tv.mvp.presenter.mine.BindAliapyPresenter;
import com.live.tv.mvp.view.mine.IBindAlipayView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 绑定支付宝
 * Created by sh-lx on 2017/7/12.
 */

public class BindAliapayFragment extends BaseFragment<IBindAlipayView, BindAliapyPresenter> implements IBindAlipayView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_name)
    EditText edName;
    Unbinder unbinder;

    public static BindAliapayFragment newInstance() {
        Bundle args = new Bundle();
        BindAliapayFragment fragment = new BindAliapayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_bind_alipay;
    }

    @Override
    public void initUI() {
        tvTitle.setText("绑定支付宝");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivLeft,R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:

                if (checkPhone_code()) {
                    Map<String, String> mMap = new HashMap<>();
                    mMap.put("member_id", userBean.getMember_id());
                    mMap.put("member_token", userBean.getMember_token());
                    // mMap.put("member_account",edPhone.getText().toString().trim());
                    mMap.put("alipay_real_name", edName.getText().toString().trim());
                    mMap.put("alipay_no", edPhone.getText().toString().trim());
                    // mMap.put("code","");
                    getPresenter().bindAlipay(mMap);
                }
                break;
        }
    }

    @Override
    public BindAliapyPresenter createPresenter() {
        return new BindAliapyPresenter(getApp());
    }

    @Override
    public void onBindAilipay(String data) {

        ToastUtils.showToast(context.getApplicationContext(),data);
        finish();
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


    private boolean checkPhone_code() {

        if (StringUtils.isBlank(edPhone.getText().toString().trim())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入支付宝账号");
            return false;
        }
        if (StringUtils.isBlank(edName.getText().toString().trim())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入真实姓名");
            return false;
        }


        return true;
    }

}
