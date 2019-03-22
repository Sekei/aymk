package com.live.tv.mvp.fragment.shop;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CartBean;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.ShopCarBean;
import com.live.tv.mvp.adapter.shop.CartAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.CartPresenter;
import com.live.tv.mvp.view.shop.ICartView;
import com.live.tv.util.CustomDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ysjk.health.iemk.R.id.tv_heji;


/**
 * 购物车
 * Created by sh-lx on 2017/7/12.
 */

public class CartFragment extends BaseFragment<ICartView, CartPresenter> implements ICartView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.cb_merchants)
    CheckBox selectAll;
    @BindView(R.id.tv_youfei)
    TextView tvYoufei;
    @BindView(R.id.tv_sum_price)
    TextView tvSumPrice;
    @BindView(tv_heji)
    TextView tvHeji;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;


    Unbinder unbinder;
    private String good_id = "";
    public static String CART_STATE = "finish";//edit  finish;


    private CartAdapter cartAdapter;

    public static CartFragment newInstance() {
        Bundle args = new Bundle();
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_cart;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("confirm_success")) {
            map.clear();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            getPresenter().getCartList(map);//刷新数据
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initUI() {
        tvTitle.setText("购物车");
        tvRight.setText("编辑");
        tvRight.setVisibility(View.VISIBLE);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setNestedScrollingEnabled(false);
        cartAdapter = new CartAdapter(new ArrayList<CartBean>(), this);
        mRecyclerView.setAdapter(cartAdapter);


        cartAdapter.setmCartUpdateCartListener(new CartAdapter.CartUpdateCartListener() {
            @Override
            public void updateCartNum(String goods_id, String num) {

                Log.i("dfc", "商品id: " + goods_id + "商品数量：" + num);
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("car_ids", goods_id);
                map.put("goods_nums", num);
                getPresenter().getUpdateCart(map);
            }
        });


    }

    @Override
    public void initData() {
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getCartList(map);
    }

    @OnClick({R.id.ivLeft, R.id.cb_merchants, R.id.tv_submit, R.id.tvRight, R.id.tv_delete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.cb_merchants:
                for (CartBean cb : cartAdapter.getData()) {
                    cb.setSelected(selectAll.isChecked());
                    for (ShopCarBean scb : cb.getShopCarBeans()) {
                        scb.setSelected(selectAll.isChecked());
                    }
                }
                cartAdapter.notifyDataSetChanged();
                updatePrice(-1);

                break;

            case R.id.tv_submit:

                List<CartBean> data = new ArrayList<>();
                for (CartBean cb : cartAdapter.getData()) {//排除未选择项
                    CartBean cart = new CartBean();
                    if (cb.isValid()) {
                        List<ShopCarBean> shoppingCarBeans = new ArrayList<>();
                        for (ShopCarBean scb : cb.getShopCarBeans()) {

                            if (scb.isSelected()) {
                                shoppingCarBeans.add(scb);
                            }
                        }
                        cart.setMerchantsBean(cb.getMerchantsBean());
                        cart.setShopCarBeans(shoppingCarBeans);
                        data.add(cart);
                    }
                }

                if (data != null && data.size() > 0) {
                    startConfirmGoodOrderFragment("cart", data);
                } else {
                    ToastUtils.showToast(context.getApplicationContext(), "未选择商品");

                }

                break;


            case R.id.tvRight:
                String str_state = tvRight.getText().toString();
                if (str_state.equals("编辑")) {
                    tvRight.setText("完成");

                    CART_STATE = "edit";
                    tvDelete.setVisibility(View.VISIBLE);
                    tvSubmit.setVisibility(View.GONE);
                    tvHeji.setVisibility(View.GONE);
                    tvSumPrice.setVisibility(View.GONE);
                    cartAdapter.notifyDataSetChanged();


                } else {
                    tvRight.setText("编辑");
                    CART_STATE = "finish";
                    tvDelete.setVisibility(View.GONE);
                    tvSubmit.setVisibility(View.VISIBLE);
                    tvHeji.setVisibility(View.VISIBLE);
                    tvSumPrice.setVisibility(View.VISIBLE);
                    cartAdapter.notifyDataSetChanged();
                    updatePrice(-1);
                    cartAdapter.notifyDataSetChanged();

                }


                break;

            case R.id.tv_delete:
                String cart_id = "";
                for (CartBean cb : cartAdapter.getData()) {//排除未选择项
                    if (cb.isValid()) {
                        for (ShopCarBean scb : cb.getShopCarBeans()) {

                            if (scb.isSelected()) {
                                cart_id += scb.getCar_id() + ",";
                            }
                        }

                    }
                }
                if (!cart_id.equals("")) {
                    cart_id = cart_id.substring(0, cart_id.length() - 1);
                    deleteDialog(cart_id);
//                    map = new HashMap<>();
//                    map.put("member_id", userBean.getMember_id());
//                    map.put("member_token", userBean.getMember_token());
//                    map.put("car_ids", cart_id);
//                    getPresenter().getDelCart(map);

                } else {

                    ToastUtils.showToast(context.getApplicationContext(), "未选择商品");
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
    public CartPresenter createPresenter() {
        return new CartPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void CartList(List<CartBean> data) {

        if (data != null) {
            cartAdapter.setNewData(data);
            selectAll.setChecked(false);
            updatePrice(-1);
        }
    }

    @Override
    public void DelCart(String data) {
        ToastUtils.showToast(getContext(), data);

        map = new HashMap<>();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getCartList(map);
    }

    @Override
    public void UpdateCart(String data) {

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


    private double price_count = 0;
    private int checked_count = 0;
    private int good_count = 0;


    public void updatePrice(int shop_position) {

        good_count = 0;
        checked_count = 0;
        price_count = 0;
        int shop_good_count;
        int shop_checked_count;

        for (CartBean cb : cartAdapter.getData()) {
            shop_good_count = 0;
            shop_checked_count = 0;
            cb.setValid(false);
            for (ShopCarBean scb : cb.getShopCarBeans()) {
                good_count++;
                shop_good_count++;
                if (scb.isSelected()) {
                    cb.setValid(true);
                    checked_count++;
                    shop_checked_count++;
                    float single_price = 0;
                    if (scb.getSpecification_price() != null) {
                        single_price = Float.parseFloat(scb.getSpecification_price()) * Integer.parseInt(scb.getGoods_num());
                    }
                    price_count += single_price;

                    Log.i("dfc", "shop_checked_count=" + shop_checked_count);
                    Log.i("dfc", "shop_good_count=" + shop_good_count);
                }
                if (shop_checked_count == shop_good_count) {
                    cb.setSelected(true);
                } else {
                    cb.setSelected(false);
                }
            }

        }

        if (good_count == checked_count) {
            selectAll.setChecked(true);
        } else {
            selectAll.setChecked(false);
        }
        if (shop_position == -1) {
            cartAdapter.notifyDataSetChanged();
        } else {
            cartAdapter.notifyItemChanged(shop_position);
        }

        tvSumPrice.setText("¥" + String.format("%.2f", price_count));

    }


    private void deleteDialog(final String cart_id) {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("是否删除商品？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                map = new HashMap<>();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("car_ids", cart_id);
                getPresenter().getDelCart(map);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }


}
