package com.live.tv.mvp.fragment.pay;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.home.OrderPayFragment;
import com.live.tv.mvp.presenter.pay.GiveHeartPresenter;
import com.live.tv.mvp.view.pay.IGiveHeartView;
import com.live.tv.util.LoadingUtil;
import com.live.tv.util.SpSingleInstance;
import com.live.tv.util.payutils.PayHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *
 * 送心意
 *支付
 * @author Created by stone
 * @since 2018/1/12
 */

public class GiveHeartFragment extends BaseFragment<IGiveHeartView, GiveHeartPresenter> implements IGiveHeartView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.typeTittle)
    TextView typeTittle;
    @BindView(R.id.typeName)
    TextView typeName;
    @BindView(R.id.type_content)
    TextView typeContent;
    @BindView(R.id.totalPrice)
    TextView totalPrice;


    @BindView(R.id.tv_submit)
    RadioButton alipay;
    @BindView(R.id.wechat)
    RadioButton wechat;
    @BindView(R.id.wallet)
    RadioButton wallet;
    @BindView(R.id.mRadioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.attentionContent)
    TextView attentionContent;
    Unbinder unbinder;
    @BindView(R.id.priceTittle)
    TextView priceTittle;
    @BindView(R.id.reserveTime)
    TextView reserveTime;
    @BindView(R.id.changeTime)
    TextView changeTime;
    @BindView(R.id.time)
    TextView time;
    private String channel = "alipay";//支付方式
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();



    private String doctor_name="";
    private String doctor_id="";
    private String record_desc="";
    private String order_actual_price="";
    private String is_set_password="";



    public static GiveHeartFragment newInstance(String doctor_name, String doctor_id,String record_desc,String order_actual_price) {
        Bundle args = new Bundle();
        GiveHeartFragment fragment = new GiveHeartFragment();
        fragment.doctor_name=doctor_name;
        fragment.doctor_id=doctor_id;
        fragment.record_desc=record_desc;
        fragment.order_actual_price=order_actual_price;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_give_heart;
    }

    @Override
    public void initUI() {

        attentionContent.setText(R.string.attention);

        tvTitle.setText(R.string.txt_give_heart);
        totalPrice.setText(order_actual_price+"元");
        typeName.setText(doctor_name);


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

        wallet .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    channel="balance_pay";
                }
            }
        });
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
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();



    }

    @OnClick({R.id.ivLeft, R.id.ok, R.id.tv_submit, R.id.wechat, R.id.wallet, R.id.changeTime})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.changeTime:
                finish();
                break;
            case R.id.ok:

                if (alipay.isChecked()){
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("order_actual_price", order_actual_price);
                    map.put("channel", channel);
                    map.put("doctor_id", doctor_id);
                    map.put("type", "android");
                    map.put("record_desc", record_desc);
                    getPresenter().buyConsult(map);



                }else if (wechat.isChecked()) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("order_actual_price", order_actual_price);
                    map.put("channel", channel);
                    map.put("doctor_id", doctor_id);
                    map.put("type", "android");
                    map.put("record_desc", record_desc);
                    getPresenter().buyConsult(map);

            }else if (wallet.isChecked()){

                    if ("1".equals(is_set_password)){
                        startOrderPayFragmentFragment(order_actual_price);

                    }else {
                        startUpdatePayPwdFragment("setting");
                    }

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
    public GiveHeartPresenter createPresenter() {
        return new GiveHeartPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void onBuyConsult(final String data) {

        Log.i("dfc", "onBuyConsult: "+data);

        if ("balance_pay".equals(channel)){
            ToastUtils.showToast(context.getApplicationContext(),data);
            finish();

        }else {

            PayHelper.getInstance(context).alipayAndWxPay(getActivity(),channel,data)
                    .setIPayListener(new PayHelper.IPayListener() {
                        @Override
                        public void onSuccess() {
                            ToastUtils.showToast(context.getApplicationContext(),"支付成功");
                            EventBus.getDefault().post(new FirstEvent("send_success"));
                            finish();
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


    }

    @Override
    public void onGetUserDetail(UserBean data) {
        is_set_password=data.getIs_set_password();
    }


    public void startOrderPayFragmentFragment(String s) {
        OrderPayFragment orderPayFragment = OrderPayFragment.newInstance(s);
        orderPayFragment.setOkClickListener(new OrderPayFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("order_actual_price", order_actual_price);
                map.put("channel", channel);
                map.put("doctor_id", doctor_id);
                map.put("type", "android");
                map.put("record_desc", record_desc);
                map.put("member_pay_password", state);
                getPresenter().buyConsult(map);
            }

            @Override
            public void cancel() {
                ToastUtils.showToast(context.getApplicationContext(), "支付取消");
            }
        });
        orderPayFragment.show(getFragmentManager(), GiveHeartFragment.class.getSimpleName());

    }
}