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
import com.live.tv.bean.ConsultTimesBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.home.OrderPayFragment;
import com.live.tv.mvp.presenter.pay.BuyConsultVideoPresenter;
import com.live.tv.mvp.view.pay.IBuyConsultVideoView;
import com.live.tv.util.SpSingleInstance;
import com.live.tv.util.payutils.PayHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *
 * 购买语音和视频服务
 *
 * @author Created by stone
 * @since 2018/1/12
 */

public class BuyConsultVideoFragment extends BaseFragment<IBuyConsultVideoView, BuyConsultVideoPresenter> implements IBuyConsultVideoView {
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

    private String payType;//订单类型 1购买专业调理方案 2送心意  3电话咨询 4视频咨询
    private String price;//订单价格
    private String channel = "alipay";//支付方式

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private ConsultTimesBean consultTimesBean;
    private String doctorName="";

    private String is_set_password="";

    public static BuyConsultVideoFragment newInstance(String payType, String price,String doctorName, ConsultTimesBean consultTimesBean) {
        Bundle args = new Bundle();
        BuyConsultVideoFragment fragment = new BuyConsultVideoFragment();
        fragment.payType = payType;
        fragment.price = price;
        fragment.consultTimesBean = consultTimesBean;
        fragment.doctorName = doctorName;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_buy_consult_video;
    }

    @Override
    public void initUI() {

        attentionContent.setText(R.string.attention);

        switch (payType) {

            case "phone":
                tvTitle.setText(R.string.pay_server);
                typeTittle.setText(R.string.phone);
                typeName.setText(doctorName);
                typeContent.setText(price + getResources().getString(R.string.yuan_every));
                priceTittle.setText(R.string.reserve_time);
                reserveTime.setText("预约时间 "+consultTimesBean.getStart_time());
                totalPrice.setText(price + getResources().getString(R.string.yuan_every));
                break;
            case "video":
                tvTitle.setText(R.string.pay_server);
                typeTittle.setText(R.string.video);
                typeName.setText(doctorName);
                typeContent.setText(price + getResources().getString(R.string.yuan_every));
                priceTittle.setText(R.string.reserve_time);
                reserveTime.setText("预约时间 "+consultTimesBean.getStart_time());
                totalPrice.setText(price + getResources().getString(R.string.yuan_every));
                break;
        }


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

        wallet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    map.put("order_actual_price", price);
                    map.put("channel", channel);
                    map.put("doctor_id", consultTimesBean.getDoctor_id()+"");
                    map.put("consult_start_time", consultTimesBean.getStart_time());
                    map.put("consult_end_time", consultTimesBean.getEnd_time());
                    map.put("consult_time", consultTimesBean.getConsult_set_day());
                    map.put("consult_set_id", consultTimesBean.getConsult_set_id()+"");
                    map.put("consult_type", payType);
                    map.put("type", "android");
                    getPresenter().buyConsult(map);



                }else if (wechat.isChecked()) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("order_actual_price", price);
                    map.put("channel", channel);
                    map.put("doctor_id", consultTimesBean.getDoctor_id()+"");
                    map.put("consult_start_time", consultTimesBean.getStart_time());
                    map.put("consult_end_time", consultTimesBean.getEnd_time());
                    map.put("consult_time", consultTimesBean.getConsult_set_day());
                    map.put("consult_set_id", consultTimesBean.getConsult_set_id()+"");
                    map.put("consult_type", payType);
                    map.put("type", "android");
                    getPresenter().buyConsult(map);

            }else if (wallet.isChecked()){



                    if ("1".equals(is_set_password)){
                        startOrderPayFragmentFragment(price);

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
    public BuyConsultVideoPresenter createPresenter() {
        return new BuyConsultVideoPresenter(getApp());
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
        ToastUtils.showToast(context.getApplicationContext(), e.getMessage());


    }

    @Override
    public void onGetUserDetail(UserBean data) {

        is_set_password=data.getIs_set_password();
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



    public void startOrderPayFragmentFragment(String s) {
        OrderPayFragment orderPayFragment = OrderPayFragment.newInstance(s);
        orderPayFragment.setOkClickListener(new OrderPayFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {

                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("order_actual_price", price);
                map.put("channel", channel);
                map.put("doctor_id", consultTimesBean.getDoctor_id()+"");
                map.put("consult_start_time", consultTimesBean.getStart_time());
                map.put("consult_end_time", consultTimesBean.getEnd_time());
                map.put("consult_time", consultTimesBean.getConsult_set_day());
                map.put("consult_set_id", consultTimesBean.getConsult_set_id()+"");
                map.put("consult_type", payType);
                map.put("type", "android");
                map.put("member_pay_password", state);
                getPresenter().buyConsult(map);
            }

            @Override
            public void cancel() {
                ToastUtils.showToast(context.getApplicationContext(), "支付取消");
            }
        });
        orderPayFragment.show(getFragmentManager(), BuyConsultVideoFragment.class.getSimpleName());

    }
}