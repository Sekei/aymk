package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.FankuiPresenter;
import com.live.tv.mvp.view.mine.IFankuiView;
import com.live.tv.util.LoadingUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class FankuiFragment extends BaseFragment<IFankuiView, FankuiPresenter> implements IFankuiView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ed1)
    EditText ed1;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.radio_phone)
    RadioButton radioPhone;
    @BindView(R.id.radio_qq)
    RadioButton radioQq;
    @BindView(R.id.radio_wx)
    RadioButton radioWx;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.ed2)
    EditText ed2;
    Unbinder unbinder;

    private int Current_id;

    public static FankuiFragment newInstance() {

        Bundle args = new Bundle();
        FankuiFragment fragment = new FankuiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_fankui;
    }

    @Override
    public void initUI() {
        tvTitle.setText("意见反馈");
    }

    @Override
    public void initData() {

        Current_id=R.id.radio_phone;
    }

    @OnClick({R.id.ivLeft, R.id.tv_submit,R.id.radio_phone,R.id.radio_qq,R.id.radio_wx})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_submit:
                map.clear();
                map.put("member_id",userBean.getMember_id());
                map.put("member_token",userBean.getMember_token());
                map.put("advice_desc",ed1.getText().toString().trim());
                if (Current_id==R.id.radio_phone){
                    map.put("advice_mobile",ed2.getText().toString().trim());
                }else   if (Current_id==R.id.radio_qq){
                    map.put("advice_qq",ed2.getText().toString().trim());

                }else  if (Current_id==R.id.radio_wx){
                    map.put("advice_wx",ed2.getText().toString().trim());

                }
                getPresenter().GetFankui(map);
                LoadingUtil.showLoadingNew(context,"提交中...");
                    break;
            case R.id.radio_phone:
                Current_id= R.id.radio_phone;
                 break;
            case R.id.radio_qq:
                Current_id= R.id.radio_qq;
                break;
            case R.id.radio_wx:
                Current_id= R.id.radio_wx;
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
    public FankuiPresenter createPresenter() {
        return new FankuiPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onFankui(String data) {
        LoadingUtil.hideLoading();
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
        LoadingUtil.hideLoading();
        errorHandle(e);
    }
}
