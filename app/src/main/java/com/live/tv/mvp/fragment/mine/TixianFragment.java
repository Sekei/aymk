package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.live.tv.mvp.fragment.home.OrderPayFragment;
import com.live.tv.mvp.fragment.pay.BuyConsultHealthFragment;
import com.live.tv.mvp.presenter.mine.TixianPresenter;
import com.live.tv.mvp.view.mine.ITixianView;
import com.live.tv.util.SpSingleInstance;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class TixianFragment extends BaseFragment<ITixianView, TixianPresenter> implements ITixianView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_money)
    EditText tvMoney;
    @BindView(R.id.tv_submit)
    RadioButton alipay;
    @BindView(R.id.wechat)
    RadioButton wechat;
    @BindView(R.id.mRadioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.textView55)
    TextView textView55;
    @BindView(R.id.textView38)
    TextView textView38;
    Unbinder unbinder;
    private String channel = "alipay";//支付方式

    private UserBean userBeanInfo;

    private String  type;//0 账户余额，1 我的收益余额
    public static TixianFragment newInstance(String type) {
        Bundle args = new Bundle();
        TixianFragment fragment = new TixianFragment();
        fragment.type=type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_tixian;
    }

    @Override
    public void initUI() {
        tvTitle.setText("提现");
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



        tvMoney.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable edt)
            {
                String temp = edt.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2)
                {
                    edt.delete(posDot + 3, posDot + 4);
                }
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
        });
    }

    @Override
    public void initData() {



    }

    @OnClick({R.id.ivLeft,R.id.ok,R.id.textView55})
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

                double m1=Double.parseDouble(tvMoney.getText().toString().trim());
                double m2=Double.parseDouble(tvBalance.getText().toString());

                if (m1>m2){
                    ToastUtils.showToast(context.getApplicationContext(),"余额不足");
                    return;
                }



                if (channel.equals("alipay")){

                    if ("1".equals(userBeanInfo.getIs_binding_alipay())){


                        startOrderPayFragmentFragment(tvMoney.getText().toString().trim());

                    }else {
                        startBindAccountFragment();
                        return;
                    }


                }else if (channel.equals("wx")){

                    if ("1".equals(userBeanInfo.getIs_binding_wx())){
                        if (TextUtils.isEmpty(tvMoney.getText().toString())) {
                            ToastUtils.showToast(context.getApplicationContext(), "请输入金额");
                            return;
                        }

                        startOrderPayFragmentFragment(tvMoney.getText().toString().trim());

                    }else {
                        startBindAccountFragment();
                        return;
                    }
                }



                break;

            case R.id.textView55:
                startBindAccountFragment();
                break;
        }
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

    @Override
    public TixianPresenter createPresenter() {
        return new TixianPresenter(getApp());
    }

    @Override
    public void onGetUserDetail(UserBean data) {
        if (data != null) {
            userBeanInfo=data;
            if ("0".equals(type)){
                tvBalance.setText(data.getMember_amount());
                textView38.setText("账户余额(元)");
            }else if ("1".equals(type)){

                tvBalance.setText(data.getProfit_amount());//这里要设置成我的收益
                textView38.setText("收益余额(元)");
            }


        }
    }

    @Override
    public void onapplyWithdrawals(String data) {

        ToastUtils.showToast(context.getApplicationContext(),data);
        finish();
    }

    @Override
    public void applyProfitWithdrawals(String data) {

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

    public void startOrderPayFragmentFragment(String s) {
        OrderPayFragment orderPayFragment = OrderPayFragment.newInstance(s);
        orderPayFragment.setOkClickListener(new OrderPayFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {

                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("withdrawals_type", channel);
                map.put("price", tvMoney.getText().toString().trim());
                map.put("member_pay_password", state);
                if ("0".equals(type)){
                    getPresenter().applyWithdrawals(map);//用户提现
                }else if ("1".equals(type)){

                    getPresenter().applyProfitWithdrawals(map);//医生端请求我的收益提现
                }



            }

            @Override
            public void cancel() {
                ToastUtils.showToast(context.getApplicationContext(), "提现取消");
            }
        });
        orderPayFragment.show(getFragmentManager(), BuyConsultHealthFragment.class.getSimpleName());

    }
}
