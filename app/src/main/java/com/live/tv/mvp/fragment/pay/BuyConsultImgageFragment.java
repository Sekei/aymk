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

import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.TextConsultBean;
import com.live.tv.bean.TextandImgBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.home.OrderPayFragment;
import com.live.tv.mvp.fragment.home.RequestOneFragment;
import com.live.tv.mvp.presenter.pay.BuyConsultImagePresenter;
import com.live.tv.mvp.view.pay.IBuyConsultImageView;
import com.live.tv.util.SpSingleInstance;
import com.live.tv.util.payutils.PayHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 购买图文咨询服务
 *
 * @author Created by stone
 * @since 2018/1/12
 */

public class BuyConsultImgageFragment extends BaseFragment<IBuyConsultImageView, BuyConsultImagePresenter> implements IBuyConsultImageView {
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
    private String orderid;//订单价格
    private String channel = "alipay";//支付方式

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
   // private TextConsultBean consultTimesBean;
    private String doctorName = "";
    private String is_set_password = "";
    private List<String> files;
    private String Hx_account;
    private String price="";
   // private List<String> list=new ArrayList<>();
    private String desc="";



    public static BuyConsultImgageFragment newInstance(String orderid, String doctorName, String Hx_account,String price,List<String> list,String desc) {
        Bundle args = new Bundle();
        BuyConsultImgageFragment fragment = new BuyConsultImgageFragment();
        fragment.orderid = orderid;
        fragment.doctorName = doctorName;
        fragment.Hx_account = Hx_account;
        fragment.price=price;
        fragment.files=list;
        fragment.desc=desc;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_buy_consult_img;
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
    public void initUI() {

        attentionContent.setText(R.string.attention);

        tvTitle.setText(R.string.pay_server);
        typeName.setText(doctorName);
        typeContent.setText(price + getResources().getString(R.string.yuan_every));
        totalPrice.setText(price + getResources().getString(R.string.yuan));


        alipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    channel = "alipay";
                }
            }
        });

        wechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    channel = "wx";
                }
            }
        });

        wallet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    channel = "balance_pay";
                }
            }
        });
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

                if (alipay.isChecked()) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("channel", channel);
                    map.put("type", "android");
                    map.put("order_id",orderid);
                    getPresenter().buyConsult(map);


                } else if (wechat.isChecked()) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("channel", channel);
                    map.put("type", "android");
                    map.put("order_id",orderid);
                    getPresenter().buyConsult(map);

                } else if (wallet.isChecked()) {


                    if ("1".equals(is_set_password)) {
                        startOrderPayFragmentFragment(price);

                    } else {
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
    public BuyConsultImagePresenter createPresenter() {
        return new BuyConsultImagePresenter(getApp());
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
    public void onBuyConsult(String data) {

        Log.i("dfc", "onBuyConsult: " + data);
        if ("balance_pay".equals(channel)) {
            ToastUtils.showToast(context.getApplicationContext(), data);

            if (files != null && files.size() > 0) {
                sendImage(files, Hx_account);
            }

            sendtext(desc, Hx_account);
            RequestOneFragment.instance.getActivity().finish();

            finish();
        } else {


            PayHelper.getInstance(context).alipayAndWxPay(getActivity(), channel, data)
                    .setIPayListener(new PayHelper.IPayListener() {
                        @Override
                        public void onSuccess() {
                            ToastUtils.showToast(context.getApplicationContext(), "支付成功");
                            if (files != null && files.size() > 0) {
                                sendImage(files, Hx_account);
                            }
                            sendtext(desc, Hx_account);

                            finish();
                        }

                        @Override
                        public void onFail() {
                            ToastUtils.showToast(context.getApplicationContext(), "支付失败");
                        }

                        @Override
                        public void onCancel() {
                            ToastUtils.showToast(context.getApplicationContext(), "取消支付");
                        }
                    });
        }
    }



    @Override
    public void onGetUserDetail(UserBean data) {
        is_set_password = data.getIs_set_password();
    }


    public void startOrderPayFragmentFragment(String s) {
        OrderPayFragment orderPayFragment = OrderPayFragment.newInstance(s);
        orderPayFragment.setOkClickListener(new OrderPayFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {

                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
               // map.put("order_actual_price", price);
                map.put("channel", channel);
                map.put("type", "android");
                map.put("order_id",orderid);
                map.put("member_pay_password", state);
                getPresenter().buyConsult(map);
            }

            @Override
            public void cancel() {
                ToastUtils.showToast(context.getApplicationContext(), "支付取消");
            }
        });
        orderPayFragment.show(getFragmentManager(), BuyConsultImgageFragment.class.getSimpleName());

    }


    private void sendtext(String content, String toChatUsername) {
        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        message.setAttribute("from_user_id", userBean.getMember_id());
        message.setAttribute("from_head_image", EaseConstant.BASE_URL+ userBean.getMember_head_image());
        message.setAttribute("from_username", userBean.getMember_nick_name());


        EMClient.getInstance().chatManager().sendMessage(message);
    }

    private void sendImage(List<String> files, String toChatUsername) {
        for (int i = 0; i < files.size(); i++) {
            EMMessage message = EMMessage.createImageSendMessage(files.get(i), false, toChatUsername);
            message.setAttribute("from_user_id", userBean.getMember_id());
            message.setAttribute("from_head_image", EaseConstant.BASE_URL+ userBean.getMember_head_image());
            message.setAttribute("from_username", userBean.getMember_nick_name());

            EMClient.getInstance().chatManager().sendMessage(message);
        }
    }

}