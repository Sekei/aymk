package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.ChongzhiPresenter;
import com.live.tv.mvp.view.mine.IChongzhiView;
import com.live.tv.util.SpSingleInstance;
import com.live.tv.util.payutils.PayHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class ChongzhiFragment extends BaseFragment<IChongzhiView, ChongzhiPresenter> implements IChongzhiView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_money)
    EditText tvMoney;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_submit)
    RadioButton alipay;
    @BindView(R.id.wechat)
    RadioButton wechat;
    @BindView(R.id.mRadioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;
    private String channel = "alipay";//支付方式

    public static ChongzhiFragment newInstance() {

        Bundle args = new Bundle();

        ChongzhiFragment fragment = new ChongzhiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_chongzhi;
    }

    @Override
    public void initUI() {
        tvTitle.setText("充值");
        alipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    channel="alipay";
                }
            }
        });

        wechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    channel="wx";
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();

        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();

        if (userBean != null) {
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            getPresenter().getUserDetail(map);
        }

    }

    @OnClick({R.id.ivLeft, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.ok:
                if (TextUtils.isEmpty(tvMoney.getText().toString())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入金额");
                    return;
                }
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("channel", channel);
                map.put("order_actual_price", tvMoney.getText().toString().trim());
                map.put("type", "android");
                getPresenter().getRecharge(map);
                break;
        }
    }

    @Override
    public ChongzhiPresenter createPresenter() {
        return new ChongzhiPresenter(getApp());
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
    public void onGetUserDetail(UserBean data) {

        if (data!=null){

            tvBalance.setText(data.getMember_amount());
        }
    }

    @Override
    public void onRecharge(final String datas) {

        Log.i("dfc", "onRecharge: "+datas);
        PayHelper.getInstance(context).alipayAndWxPay(getActivity(),channel,datas)
                .setIPayListener(new PayHelper.IPayListener() {
            @Override
            public void onSuccess() {
                ToastUtils.showToast(context.getApplicationContext(),"支付成功");
            }

            @Override
            public void onFail() {
                ToastUtils.showToast(context.getApplicationContext(),"支付失败");
            }

            @Override
            public void onCancel() {
                ToastUtils.showToast(context.getApplicationContext(),"取消支付");
            }
        });

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
