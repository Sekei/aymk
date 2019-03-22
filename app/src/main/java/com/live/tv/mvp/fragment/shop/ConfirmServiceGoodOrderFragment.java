package com.live.tv.mvp.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.live.tv.MainActivity;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.GoodsBean;
import com.live.tv.bean.InsertOrder;
import com.live.tv.bean.InsertOrderBean;
import com.live.tv.bean.InsertOrderGoodBean;
import com.live.tv.bean.PriceBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.ConfirmServiceGoodOrderPresenter;
import com.live.tv.mvp.view.shop.IConfirmServiceGoodOrderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class ConfirmServiceGoodOrderFragment extends BaseFragment<IConfirmServiceGoodOrderView, ConfirmServiceGoodOrderPresenter> implements IConfirmServiceGoodOrderView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.img_merchants)
    ImageView imgMerchants;
    @BindView(R.id.tv_good_name)
    TextView tvGoodName;
    @BindView(R.id.tv_nums)
    TextView tvGuige;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_jia)
    TextView tvJia;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_jian)
    TextView tvJian;
    @BindView(R.id.tv_xiaoji)
    TextView tvXiaoji;
    @BindView(R.id.cb_jifen)
    CheckBox cbJifen;
    @BindView(R.id.tv_yingfu)
    TextView tvYingfu;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_update)
    TextView tvUpdate;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    Unbinder unbinder;
    private GoodsBean goodsBean;

    public static ConfirmServiceGoodOrderFragment newInstance(GoodsBean goodsBean) {
        Bundle args = new Bundle();
        ConfirmServiceGoodOrderFragment fragment = new ConfirmServiceGoodOrderFragment();
        fragment.goodsBean = goodsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.confirm_service_good_order_fragment;
    }

    @Override
    public void initUI() {
        tvTitle.setText("确认订单");
    }

    @Override
    public void initData() {

        if (goodsBean != null) {
            Glide.with(context).load(Constants.BASE_URL + goodsBean.getGoods_img())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults).centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imgMerchants);

            tvGuige.setText("有效期至：" + goodsBean.getExpiry_time());
            tvPrice.setText("¥" + goodsBean.getGoods_now_price());
            tvGoodName.setText(goodsBean.getGoods_name());

            tvPhone.setText(userBean.getMember_phone());

            CalculationPrice();
        }
    }
    private void CalculationPrice(){
        map = new HashMap<>();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("json", new Gson().toJson(insertOrderParm(goodsBean)));
        getPresenter().getOrderPrice(map);
    }

    int count_good = 0;

    @OnClick({R.id.ivLeft, R.id.tv_submit, R.id.tv_jian, R.id.tv_jia, R.id.tv_update})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_submit:
                String phone_str = tvPhone.getText().toString().trim();
                if (phone_str == null || phone_str.equals("")) {
                    ToastUtils.showToast(context.getApplicationContext(), "请完善联系方式");
                    return;
                }

                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("json", new Gson().toJson(insertOrderParm(goodsBean)));
                getPresenter().getConfirmOrder(map);
                break;


            case R.id.tv_jia:

                count_good = Integer.parseInt(tvName.getText().toString());
                count_good++;
                tvName.setText(String.valueOf(count_good));
                CalculationPrice();
                Log.i("dfc", "onClick: " + count_good);
                break;
            case R.id.tv_jian:
                count_good = Integer.parseInt(tvName.getText().toString());

                if (count_good == 1) {

                    ToastUtils.showToast(context.getApplicationContext(), "数量不能小于1");
                } else {
                    count_good--;
                    tvName.setText(String.valueOf(count_good));
                    CalculationPrice();
                    Log.i("dfc", "onClick: " + count_good);
                }

                break;

            case R.id.tv_update:

                startConfirmOrderVerifyPhoneFragment("");

                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUESTCODE) {

            if (requestCode == 1000) {

                tvPhone.setText(data.getStringExtra("phone"));
            }
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
    public ConfirmServiceGoodOrderPresenter createPresenter() {
        return new ConfirmServiceGoodOrderPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private InsertOrder insertOrderParm(GoodsBean goodsBean) {
        InsertOrder inserOrder = new InsertOrder();
        inserOrder.setMember_id(userBean.getMember_id());
        if (cbJifen.isChecked()) {
            inserOrder.setIs_deduct_integral("1");
        } else {
            inserOrder.setIs_deduct_integral("0");
        }
        inserOrder.setExpiry_time(goodsBean.getExpiry_time());
        inserOrder.setContact_phone(tvPhone.getText().toString());
        List<InsertOrderBean> orderList = new ArrayList<>();
        InsertOrderBean orderBean = new InsertOrderBean();
        orderBean.setMerchants_id(goodsBean.getMerchantsBean().getMerchants_id()+"");
        orderBean.setOrder_type("service_goods");
        List<InsertOrderGoodBean> orderGoodsList = new ArrayList<>();
        InsertOrderGoodBean ogb = new InsertOrderGoodBean();
        ogb.setGoods_id(goodsBean.getGoods_id());
        ogb.setSpecification_id(goodsBean.getGoodsSpecificationBeans().get(0).getSpecification_id());
        ogb.setGoods_num(tvName.getText().toString());
        orderGoodsList.add(ogb);
        orderBean.setOrderGoodsBeans(orderGoodsList);
        orderList.add(orderBean);
        inserOrder.setOrderBeans(orderList);
        return inserOrder;

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
    public void ConfirmOrder(PriceBean data) {
        if (data != null) {
            ToastUtils.showToast(context.getApplicationContext(), "提交成功");
            startBuyGoodFragment(data.getOrder_ids(), data.getOrder_total_actual_price());
            finish();
        }


    }

    @Override
    public void getOrderPrice(PriceBean data) {
        if (data != null) {
            tvXiaoji.setText("¥" + data.getOrder_total_actual_price());
            tvYingfu.setText("¥" + data.getOrder_total_actual_price());

        }
    }
}
