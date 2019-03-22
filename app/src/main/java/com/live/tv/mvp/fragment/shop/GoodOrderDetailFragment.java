package com.live.tv.mvp.fragment.shop;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import static com.ysjk.health.iemk.R.id.tv_assessment;
import static com.ysjk.health.iemk.R.id.tv_delete;
import static com.ysjk.health.iemk.R.id.tv_logistics;
import static com.ysjk.health.iemk.R.id.tv_pay;

/**
 * 商品订单详情
 * Created by sh-lx on 2017/7/12.
 */

public class GoodOrderDetailFragment extends BaseFragment<IGoodOrderDetailView, GoodOrderDetailPresenter> implements IGoodOrderDetailView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
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
    TextView tvCancel;


    @BindView(tv_delete)
    TextView tvDelete;
    @BindView(tv_logistics)
    TextView tvLogistics;
    @BindView(tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(tv_assessment)
    TextView tvAssessment;
    Unbinder unbinder;
    private String order_id = "";
    private OrderBean orderBean;
    private GoodOrderDetailGoodAdapter mAdapter;
    private Timer localTimer;
    private int sec = 0;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int seconds = (int) msg.getData().get("time");
                    int hour1 = seconds / 3600;
                    int minute1 = (seconds - hour1 * 3600) / 60;
                    int second1 = (seconds - hour1 * 3600 - minute1 * 60);
                    String ho1 = String.valueOf(hour1);
                    String mi1 = String.valueOf(minute1);
                    String se1 = String.valueOf(second1);

                    if (hour1 < 10) {
                        ho1 = "0" + hour1;
                    }
                    if (minute1 < 10) {
                        mi1 = "0" + minute1;
                    }
                    if (second1 < 10) {
                        se1 = "0" + second1;
                    }
                    Log.e("--------------------", "剩余时间 " + ho1 + ":" + mi1.toString() + ":" + se1.toString());
                    tvTime.setText(mi1 + "分" + se1 + "秒");
                    if (ho1.equals("00") & mi1.equals("00") & se1.equals("00")) {
                        localTimer.cancel();
                        ToastUtils.showToast(context.getApplicationContext(), "订单自动取消");
                        finish();
                    }
            }
        }
    };


    public static GoodOrderDetailFragment newInstance(String order_id) {
        Bundle args = new Bundle();
        GoodOrderDetailFragment fragment = new GoodOrderDetailFragment();
        fragment.order_id = order_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_good_order_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("订单详情");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new GoodOrderDetailGoodAdapter(new ArrayList<OrderGoodsBeans>());
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                //申请退款

                OrderGoodsBeans orderGoodsBeans = (OrderGoodsBeans) adapter.getData().get(position);
                startApplyAfterSaleFragment(orderGoodsBeans);
            }
        });
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


        tvName.setText("收货人：" + orderBean.getAddress_name());
        tvPhone.setText(orderBean.getAddress_mobile());
        tvAddress.setText("收货地址：" + orderBean.getAddress_province() + orderBean.getAddress_city() + orderBean.getAddress_country() + orderBean.getAddress_detailed());
        Glide.with(context).load(Constants.BASE_URL + orderBean.getMerchants_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((imgMerchants));
        merchantsName.setText(orderBean.getMerchants_name());
        tvSumPrice.setText("¥" + orderBean.getOrder_total_price());
        tvYuefei.setText("¥" + orderBean.getExpress_price());
        tvJifen.setText(orderBean.getDeduct_integral_value());
        tvLiuyan.setText(orderBean.getOrder_remark());
        tvShifu.setText("¥" + orderBean.getOrder_total_price());
        orderTime.setText("订单编号：" + orderBean.getOrder_no() + "\n"
                + "创建时间：" + orderBean.getCreate_time() + "\n"
                + "支付时间：" + orderBean.getPay_time() + "\n"
                + "发货时间：" + orderBean.getSend_time() + "\n"
                + "收货时间：" + orderBean.getReceive_time() + "\n");


        mAdapter.setState(orderBean.getOrder_state());
        mAdapter.setNewData(orderBean.getOrderGoodsBeans());

        String state_str = orderBean.getOrder_state();
        switch (state_str) {
            case "wait_pay":
                tvCancel.setVisibility(View.VISIBLE);
                tvPay.setVisibility(View.VISIBLE);
                tvDelete.setVisibility(View.GONE);
                tvConfirm.setVisibility(View.GONE);
                tvAssessment.setVisibility(View.GONE);
                tvLogistics.setVisibility(View.GONE);
                tvTime.setVisibility(View.VISIBLE);

                tvType.setText("支付剩余时间");
                long startT = fromDateStringToLong(dqsj());
                long endT = Long.parseLong(orderBean.getCancel_end_time());
                if (startT == endT) {
                    return;
                }
                sec = (int) (endT - startT) / 1000;
                if (localTimer != null) {
                    localTimer.cancel();
                }

                localTimer = new Timer();
                localTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int i = -1 + sec;
                        if (i == 0) {
                        }
                        sec = i;
                        if (i < 0) {
                            sec = 0;
                            localTimer.cancel();
                            return;
                        }
                        Message message = new Message();
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putInt("time", sec);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }, 1000L, 1000L);


                break;

            case "wait_send":
                tvCancel.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                tvDelete.setVisibility(View.GONE);
                tvConfirm.setVisibility(View.GONE);
                tvAssessment.setVisibility(View.GONE);
                tvLogistics.setVisibility(View.GONE);
                tvTime.setText(orderBean.getOrder_state_show());
                tvType.setVisibility(View.GONE);
                break;

            case "wait_receive":
                tvCancel.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                tvDelete.setVisibility(View.GONE);
                tvConfirm.setVisibility(View.VISIBLE);
                tvAssessment.setVisibility(View.GONE);
                tvLogistics.setVisibility(View.VISIBLE);

                tvTime.setText(orderBean.getOrder_state_show());
                tvType.setVisibility(View.GONE);
                break;
            case "wait_assessment":
                tvCancel.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                tvDelete.setVisibility(View.GONE);
                tvConfirm.setVisibility(View.GONE);
                tvAssessment.setVisibility(View.VISIBLE);
                tvLogistics.setVisibility(View.VISIBLE);

                tvTime.setText(orderBean.getOrder_state_show());
                tvType.setVisibility(View.GONE);
                break;
            case "cancel":
                tvCancel.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                tvDelete.setVisibility(View.VISIBLE);
                tvConfirm.setVisibility(View.GONE);
                tvAssessment.setVisibility(View.GONE);
                tvLogistics.setVisibility(View.GONE);
                tvTime.setText(orderBean.getOrder_state_show());
                tvType.setVisibility(View.GONE);
                break;


            case "end":

                tvCancel.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                tvDelete.setVisibility(View.VISIBLE);
                tvConfirm.setVisibility(View.GONE);
                tvAssessment.setVisibility(View.GONE);
                tvLogistics.setVisibility(View.VISIBLE);
                tvTime.setText(orderBean.getOrder_state_show());
                tvType.setVisibility(View.GONE);
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

    @OnClick({R.id.tv_cancel, tv_delete, tv_logistics, tv_pay, R.id.tv_confirm, tv_assessment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                cancelDialog(order_id);
                break;
            case tv_delete:
                deleteDialog(order_id);
                break;
            case tv_logistics:
                break;
            case tv_pay:
                startBuyGoodFragment(order_id, orderBean.getOrder_actual_price());
                break;
            case R.id.tv_confirm:
                break;
            case tv_assessment:
                startAssessmentOrderFragment(orderBean.getOrderGoodsBeans(), order_id);
                finish();
                break;
        }
    }


    private void cancelDialog(final String order_id) {

        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("是否取消订单？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("order_id", order_id);
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

    private void deleteDialog(final String order_id) {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("是否删除订单？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("order_id", order_id);
                getPresenter().getDelGoodOrder(map);

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


    private long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
        Date date = null;   //定义时间类型
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = inputFormat.parse(inVal); //将字符型转换成日期型
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();   //返回毫秒数
    }

    private static String dqsj() {  //此方法用于获得当前系统时间（格式类型2007-11-6 15:10:58）
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());  //返回当前时间
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (localTimer != null) {
            localTimer.cancel();
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
