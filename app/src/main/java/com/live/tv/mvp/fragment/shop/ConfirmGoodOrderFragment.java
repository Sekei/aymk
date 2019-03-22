package com.live.tv.mvp.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AddressBean;
import com.live.tv.bean.CartBean;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.InsertOrder;
import com.live.tv.bean.InsertOrderBean;
import com.live.tv.bean.InsertOrderGoodBean;
import com.live.tv.bean.PriceBean;
import com.live.tv.bean.ShopCarBean;
import com.live.tv.mvp.adapter.shop.ConfirmGoodOrderAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.ConfirmGoodOrderPresenter;
import com.live.tv.mvp.view.shop.IConfirmGoodOrderView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 提交订单
 */

public class ConfirmGoodOrderFragment extends BaseFragment<IConfirmGoodOrderView, ConfirmGoodOrderPresenter> implements IConfirmGoodOrderView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_null_address)
    TextView tvNullAddress;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.con_address)
    ConstraintLayout conAddress;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_youfei)
    TextView tvYoufei;
    @BindView(R.id.tv_sum_price)
    TextView tvSumPrice;
    @BindView(R.id.tv_heji)
    TextView tvHeji;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    Unbinder unbinder;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.cb_jifen)
    CheckBox cbJifen;
    private String type = "";
    private List<CartBean> cartBeanList;
    private ConfirmGoodOrderAdapter confirmGoodOrderAdapter;
    private String address_id = "";

    public static ConfirmGoodOrderFragment newInstance(String type, List<CartBean> cartBeanList) {
        Bundle args = new Bundle();
        ConfirmGoodOrderFragment fragment = new ConfirmGoodOrderFragment();
        fragment.type = type;
        fragment.cartBeanList = cartBeanList;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_confim_good_order;
    }

    @Override
    public void initUI() {
        tvTitle.setText("确认订单");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setNestedScrollingEnabled(false);
        confirmGoodOrderAdapter = new ConfirmGoodOrderAdapter(new ArrayList<CartBean>(), this);
        mRecyclerView.setAdapter(confirmGoodOrderAdapter);
    }

    @Override
    public void initData() {

        if (cartBeanList != null) {
            confirmGoodOrderAdapter.setNewData(cartBeanList);
        }

        getAddress();//获取默认地址
        CalculationPrice();

    }

    private void CalculationPrice() {
        map = new HashMap<>();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("json", new Gson().toJson(insertOrderParm(cartBeanList)));
        getPresenter().getOrderPrice(map);
    }

    private void getAddress() {

        map = new HashMap<>();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getDefaultAddress(map);

    }

    @OnClick({R.id.ivLeft, R.id.tv_submit,R.id.tv_null_address,R.id.con_address})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_submit:
                map = new HashMap<>();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("json", new Gson().toJson(insertOrderParm(cartBeanList)));
                getPresenter().getConfirmOrder(map);
                Log.i("dfc", "onClick: " + new Gson().toJson(insertOrderParm(cartBeanList)));

                break;

            case R.id.tv_null_address:
                startAddressFragment("choose");
                break;
            case R.id.con_address:
                startAddressFragment("choose");
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
    public ConfirmGoodOrderPresenter createPresenter() {
        return new ConfirmGoodOrderPresenter(getApp());
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

        errorHandle(e);
    }

    @Override
    public void onDefaultAddress(AddressBean data) {
        if (data != null && data.getAddress_id() != null) {
            tvNullAddress.setVisibility(View.GONE);
            conAddress.setVisibility(View.VISIBLE);
            setAddress(data);
        } else {
            conAddress.setVisibility(View.GONE);
            tvNullAddress.setVisibility(View.VISIBLE);
            Log.i("dfc", "DefaultAddress: " + "没有地址");
        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== Constants.REQUESTCODE){
            if (data!=null){

                AddressBean addressBean = data.getParcelableExtra("addressBean");
                tvNullAddress.setVisibility(View.GONE);
                conAddress.setVisibility(View.VISIBLE);
                setAddress(addressBean);

            }


        }
    }

    @Override
    public void ConfirmOrder(PriceBean data) {

        if (data!=null){
            EventBus.getDefault().post(new FirstEvent("confirm_success"));
            startBuyGoodFragment(data.getOrder_ids(),data.getOrder_total_actual_price());
            finish();
        }
    }

    @Override
    public void getOrderPrice(PriceBean data) {
        if (data!=null){
            tvSumPrice.setText("¥"+data.getOrder_total_actual_price());
            tvYoufei.setText("邮费¥"+data.getExpress_total_price());

            for (int i = 0; i <confirmGoodOrderAdapter.getData().size() ; i++) {
                confirmGoodOrderAdapter.getData().get(i).setExpress_price(data.getOrderDivideBean().get(i).getExpress_price());
            }


            confirmGoodOrderAdapter.notifyDataSetChanged();

        }
    }

    private void setAddress(AddressBean data) {
        tvName.setText(data.getAddress_name());
        tvPhone.setText(data.getAddress_mobile());
        tvAddress.setText("收货地址：" + data.getAddress_province() + data.getAddress_city() + data.getAddress_country() + data.getAddress_detailed());
        address_id = data.getAddress_id();
    }


    private InsertOrder insertOrderParm(List<CartBean> cartlist) {
        String carid_str = "";
        InsertOrder inserOrder = new InsertOrder();

        inserOrder.setAddress_id(address_id);//收货地址id
        inserOrder.setMember_id(userBean.getMember_id());
        if (cbJifen.isChecked()) {
            inserOrder.setIs_deduct_integral("1");
        } else {
            inserOrder.setIs_deduct_integral("0");
        }


        List<InsertOrderBean> orderList = new ArrayList<>();

        for (CartBean cart : cartlist) {
            InsertOrderBean orderBean = new InsertOrderBean();
            orderBean.setMerchants_id(cart.getMerchantsBean().getMerchants_id()+"");
            orderBean.setOrder_type("common_goods");
            orderBean.setOrder_remark(cart.getOrder_remark());//留言
            List<InsertOrderGoodBean> orderGoodsList = new ArrayList<>();
            for (ShopCarBean shopGoodsBeans : cart.getShopCarBeans()) {
                InsertOrderGoodBean ogb = new InsertOrderGoodBean();
                ogb.setGoods_id(shopGoodsBeans.getGoods_id());
                ogb.setSpecification_id(shopGoodsBeans.getSpecification_id());
                ogb.setGoods_num(shopGoodsBeans.getGoods_num());
                if (shopGoodsBeans.getCar_id() != null && !shopGoodsBeans.getCar_id().equals("")) {
                    carid_str += shopGoodsBeans.getCar_id() + ",";
                }
                orderGoodsList.add(ogb);
            }
            orderBean.setOrderGoodsBeans(orderGoodsList);
            orderList.add(orderBean);

        }
        if (!carid_str.equals("")) {
            carid_str = carid_str.substring(0, carid_str.length() - 1);
            inserOrder.setShop_car_ids(carid_str);//购物车id
        }
        inserOrder.setOrderBeans(orderList);
        return inserOrder;

    }

}
