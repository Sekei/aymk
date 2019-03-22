package com.live.tv.mvp.fragment.shop;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.OrderBean;
import com.live.tv.bean.OrderGoodsBeans;
import com.live.tv.mvp.adapter.shop.GoodOrderDetailGoodAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.GoodOrderDetailPresenter;
import com.live.tv.mvp.view.shop.IGoodOrderDetailView;
import com.live.tv.util.CustomDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 商品订单详情
 * Created by sh-lx on 2017/7/12.
 */

public class ServiceGoodOrderDetailFragment extends BaseFragment<IGoodOrderDetailView, GoodOrderDetailPresenter> implements IGoodOrderDetailView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.textView68)
    TextView textView68;
    @BindView(R.id.img_merchants)
    ImageView imgMerchants;
    @BindView(R.id.merchants_name)
    TextView merchantsName;
    @BindView(R.id.tv_order_state)
    TextView tvOrderState;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.sum_price)
    TextView sumPrice;
    @BindView(R.id.tv_sum_price)
    TextView tvSumPrice;
    @BindView(R.id.yuefei)
    TextView yuefei;
    @BindView(R.id.tv_yuefei)
    TextView tvYuefei;
    @BindView(R.id.jifen)
    TextView jifen;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.liuyan)
    TextView liuyan;
    @BindView(R.id.tv_liuyan)
    TextView tvLiuyan;
    @BindView(R.id.shifu)
    TextView shifu;
    @BindView(R.id.tv_shifu)
    TextView tvShifu;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    @BindView(R.id.tv_phone)
    TextView tv_phone;



    Unbinder unbinder;
    @BindView(R.id.ll_11)
    LinearLayout ll11;
    @BindView(R.id.tv_quanma)
    TextView tvQuanma;
    @BindView(R.id.ll_13)
    LinearLayout ll13;
    @BindView(R.id.ll_12)
    LinearLayout ll12;
    @BindView(R.id.tv_one_list)
    TextView tv_one_list;
    @BindView(R.id.tv_pay)
    TextView tv_pay;
    @BindView(R.id.tv_see)
    TextView tv_see;
    @BindView(R.id.tv_assessment)
    TextView tv_assessment;
    @BindView(R.id.tv_buy_again)
    TextView tv_buy_again;


    private String order_id = "";
    private OrderBean orderBean;
    private GoodOrderDetailGoodAdapter mAdapter;

    public static ServiceGoodOrderDetailFragment newInstance(String order_id) {
        Bundle args = new Bundle();
        ServiceGoodOrderDetailFragment fragment = new ServiceGoodOrderDetailFragment();
        fragment.order_id = order_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_service_order_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("订单详情");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new GoodOrderDetailGoodAdapter(new ArrayList<OrderGoodsBeans>());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("order_id", order_id);
        getPresenter().getGoodOrderDetail(map);
    }

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }

    @Override
    public GoodOrderDetailPresenter createPresenter() {
        return new GoodOrderDetailPresenter(getApp());
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
    public void onGoodOrderDetail(OrderBean data) {

        orderBean = data;
        binddata();
    }

    @Override
    public void CancelOrder(String data) {

        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void DelOrder(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();
    }

    private void binddata() {


        Glide.with(context).load(Constants.BASE_URL + orderBean.getMerchants_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((imgMerchants));
        merchantsName.setText(orderBean.getMerchants_name());
        tvSumPrice.setText("¥" + orderBean.getOrder_total_price());
        tvYuefei.setText("¥" + "0.00");
        tvJifen.setText("¥"+orderBean.getDeduct_integral_value());
        tvLiuyan.setText(orderBean.getOrder_remark());
        tvShifu.setText("¥"+orderBean.getOrder_actual_price());

        tv_phone.setText(orderBean.getContact_phone());
        tvQuanma.setText(orderBean.getCoupon_code());
        orderTime.setText("订单编号：" + orderBean.getOrder_no() + "\n"
                + "创建时间：" + orderBean.getCreate_time() + "\n"
                + "支付时间：" + orderBean.getPay_time() + "\n"
                + "发货时间：" + orderBean.getSend_time() + "\n"
                + "收货时间：" + orderBean.getReceive_time() + "\n");

        mAdapter.setNewData(orderBean.getOrderGoodsBeans());

        String state_str = orderBean.getOrder_state();
        switch (state_str) {
            case "wait_pay":
                tv_cancel.setVisibility(View.VISIBLE);
                tv_pay.setVisibility(View.VISIBLE);
                tv_see.setVisibility(View.GONE);
                tv_buy_again.setVisibility(View.GONE);
                tv_one_list.setVisibility(View.GONE);
                tv_assessment.setVisibility(View.GONE);
                ll11.setVisibility(View.VISIBLE);
                ll13.setVisibility(View.GONE);

                break;

            case "wait_use":
                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_see.setVisibility(View.VISIBLE);
                tv_buy_again.setVisibility(View.GONE);
                tv_one_list.setVisibility(View.GONE);
                tv_assessment.setVisibility(View.GONE);
                ll11.setVisibility(View.GONE);
                ll13.setVisibility(View.VISIBLE);
                break;

            case "wait_assessment":
                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_see.setVisibility(View.GONE);
                tv_buy_again.setVisibility(View.GONE);
                tv_one_list.setVisibility(View.VISIBLE);
                tv_assessment.setVisibility(View.VISIBLE);
                ll11.setVisibility(View.GONE);
                ll13.setVisibility(View.VISIBLE);
                break;

            case "end":
                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_see.setVisibility(View.GONE);
                tv_buy_again.setVisibility(View.GONE);
                tv_one_list.setVisibility(View.VISIBLE);
                tv_assessment.setVisibility(View.GONE);
                ll11.setVisibility(View.GONE);
                ll13.setVisibility(View.VISIBLE);
                break;

            case "cancel":
                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_see.setVisibility(View.GONE);
                tv_buy_again.setVisibility(View.VISIBLE);
                tv_one_list.setVisibility(View.GONE);
                tv_assessment.setVisibility(View.GONE);
                ll11.setVisibility(View.GONE);
                ll13.setVisibility(View.VISIBLE);
                break;


        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintColor(R.color.colorPrimary);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);//状态栏所需颜色
            // tintManager.setTintColor(R.color.pure_white);//文字颜色
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




    private void cancelDialog(final String order_id) {

        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("是否取消订单？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("order_id", order_id);
                map.put("member_id",userBean.getMember_id());
                map.put("member_token",userBean.getMember_token());
                getPresenter().getCancelGoodOrder(map);
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


    @OnClick({R.id.tv_one_list, R.id.tv_pay, R.id.tv_see, R.id.tv_assessment,R.id.tv_cancel,R.id.tv_buy_again})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_one_list:
                if (orderBean.getOrderGoodsBeans()!=null) {
                    startServiceGoodDetailFragment(orderBean.getOrderGoodsBeans().get(0).getGoods_id());
                }

                break;
            case R.id.tv_pay:
                startBuyGoodFragment(order_id, orderBean.getOrder_actual_price());
                break;
            case R.id.tv_see:
                break;
            case R.id.tv_assessment:
                break;
            case R.id.tv_cancel:
                cancelDialog(order_id);
                break;
            case R.id.tv_buy_again:
                if (orderBean.getOrderGoodsBeans()!=null) {
                    startServiceGoodDetailFragment(orderBean.getOrderGoodsBeans().get(0).getGoods_id());
                }
                break;

        }
    }
}
