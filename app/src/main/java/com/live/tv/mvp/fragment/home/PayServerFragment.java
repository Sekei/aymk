package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.PayServerPresenter;
import com.live.tv.mvp.view.home.IPayServerView;
import com.live.tv.util.SpSingleInstance;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/12
 */

public class PayServerFragment extends BaseFragment<IPayServerView, PayServerPresenter> implements IPayServerView {
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
    private String three;
    private String four;
    private String five;
    private String channel = "balance_pay";//支付方式

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();


    public static PayServerFragment newInstance(String payType, String price, String three, String four, String five) {
        Bundle args = new Bundle();
        PayServerFragment fragment = new PayServerFragment();
        fragment.payType = payType;
        fragment.price = price;
        fragment.three = three;
        fragment.four = four;
        fragment.five = five;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_pay;
    }

    @Override
    public void initUI() {

        attentionContent.setText(R.string.attention);

        switch (payType) {
            case "1":
                tvTitle.setText(R.string.pay_server);
                typeTittle.setText(R.string.conditioning_program);
                typeName.setText(R.string.common_test);
                totalPrice.setText(price + getResources().getString(R.string.yuan));
                typeContent.setText(price + getResources().getString(R.string.yuan));
                break;
            case "2":
                tvTitle.setText(R.string.send_heart);
                typeTittle.setText(R.string.thank_doctor);
                typeName.setText(five);
                totalPrice.setText(price + getResources().getString(R.string.yuan));
                typeContent.setText("");
                break;

            case "phone":
                reserveTime.setVisibility(View.VISIBLE);
                changeTime.setVisibility(View.VISIBLE);
                time.setVisibility(View.VISIBLE);
                tvTitle.setText(R.string.pay_server);
                typeTittle.setText(R.string.phone);
                typeName.setText("王医生");
                typeContent.setText(price + getResources().getString(R.string.yuan_every));
                priceTittle.setText(R.string.reserve_time);
                time.setText(three);
                totalPrice.setText(price + getResources().getString(R.string.yuan_every));
                break;
            case "video":
                reserveTime.setVisibility(View.VISIBLE);
                changeTime.setVisibility(View.VISIBLE);
                time.setVisibility(View.VISIBLE);
                tvTitle.setText(R.string.pay_server);
                typeTittle.setText(R.string.video);
                typeName.setText("王医生");
                typeContent.setText(price + getResources().getString(R.string.yuan_every));
                priceTittle.setText(R.string.reserve_time);
                time.setText(three);
                totalPrice.setText(price + getResources().getString(R.string.yuan_every));
                break;
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

                startTestResultTabPlanProfessionFragment(three);


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
    public PayServerPresenter createPresenter() {
        return new PayServerPresenter(getApp());
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
    public void onBuyPlan(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        startTestResultTabPlanProfessionFragment(three);
        finish();
    }

    @Override
    public void onSendHeart(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onBuyConsult(String data) {
        startPaySuccessFragment();
        finish();
    }

    public void startOrderPayFragmentFragment(String s) {
        OrderPayFragment orderPayFragment = OrderPayFragment.newInstance(s);
        orderPayFragment.setOkClickListener(new OrderPayFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {

                switch (payType) {
                    case "1":
                        map.clear();
                        map.put("member_id", userBean.getMember_id());
                        map.put("member_token", userBean.getMember_token());
                        map.put("order_actual_price", price);
                        map.put("channel", channel);
                        map.put("member_pay_password", state);
                        map.put("health_record_id", three);
                        map.put("test_id", four);
                        getPresenter().buyPlan(map);
                        break;
                    case "2":
                        map.clear();
                        map.put("member_id", userBean.getMember_id());
                        map.put("member_token", userBean.getMember_token());
                        map.put("order_actual_price", price);
                        map.put("channel", channel);
                        map.put("member_pay_password", state);
                        map.put("doctor_id", three);
                        map.put("record_desc", four);
                        getPresenter().sendHeart(map);
                        break;

                    case "phone":
                        map.clear();
                        map.put("member_id", userBean.getMember_id());
                        map.put("member_token", userBean.getMember_token());
                        map.put("order_actual_price", price);
                        map.put("channel", channel);
                        map.put("member_pay_password", state);
                        map.put("doctor_id", five);
                        map.put("consult_start_time", three);
                        map.put("consult_end_time", four);
                        map.put("consult_time", three.substring(0, 11));
                        map.put("consult_type", payType);
                        getPresenter().buyConsult(map);

                    case "video":
                        map.clear();
                        map.put("member_id", userBean.getMember_id());
                        map.put("member_token", userBean.getMember_token());
                        map.put("order_actual_price", price);
                        map.put("channel", channel);
                        map.put("member_pay_password", state);
                        map.put("doctor_id", five);
                        map.put("consult_start_time", three);
                        map.put("consult_end_time", four);
                        map.put("consult_time", three.substring(0, 11));
                        map.put("consult_type", payType);
                        getPresenter().buyConsult(map);
                        break;
                }

            }

            @Override
            public void cancel() {
                ToastUtils.showToast(context.getApplicationContext(), "支付取消");
            }
        });
        orderPayFragment.show(getFragmentManager(), PayServerFragment.class.getSimpleName());

    }
}