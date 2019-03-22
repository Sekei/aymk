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
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AfterSaleOrderBean;
import com.live.tv.bean.OrderGoodsBeans;
import com.live.tv.mvp.adapter.mine.GoodOrderGoodAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.AfterSaleOrderDetailPresenter;
import com.live.tv.mvp.view.shop.IAfterSaleOrderDetailView;
import com.live.tv.util.CustomDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class AfterSaleOrderDetailFragment extends BaseFragment<IAfterSaleOrderDetailView, AfterSaleOrderDetailPresenter> implements IAfterSaleOrderDetailView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.merchants_name)
    TextView merchantsName;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_yuanyin)
    TextView tvYuanyin;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.order_log)
    TextView orderLog;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_edit_log)
    TextView tvEditLog;
    @BindView(R.id.tv_update_log)
    TextView tvUpdateLog;
    Unbinder unbinder;
    private String refund_id = "";
    private AfterSaleOrderBean afterSaleOrderBean;
    GoodOrderGoodAdapter goodOrderGoodAdapter;

    public static AfterSaleOrderDetailFragment newInstance(String refund_id) {

        Bundle args = new Bundle();
        AfterSaleOrderDetailFragment fragment = new AfterSaleOrderDetailFragment();
        fragment.refund_id = refund_id;
        fragment.setArguments(args);
        return fragment;
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
    public int getRootViewId() {
        return R.layout.fragment_after_sale_order_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("售后详情");
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        goodOrderGoodAdapter = new GoodOrderGoodAdapter(new ArrayList<OrderGoodsBeans>());
        recyclerView.setAdapter(goodOrderGoodAdapter);


    }

    @Override
    public void initData() {

        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("refund_id", refund_id);
        getPresenter().getAfterSaleOrderDetail(map);
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
    public AfterSaleOrderDetailPresenter createPresenter() {
        return new AfterSaleOrderDetailPresenter(getApp());
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
    public void onAfterSaleOrderDetail(AfterSaleOrderBean data) {

        if (data != null) {
            afterSaleOrderBean = data;
            bindData();
        }
    }

    @Override
    public void onCancelRefundOrder(String data) {

        ToastUtils.showToast(context.getApplicationContext(),data);
        finish();
    }

    private void bindData() {
        if (afterSaleOrderBean.getOrderBean().getOrderGoodsBeans() != null) {
            goodOrderGoodAdapter.setNewData(afterSaleOrderBean.getOrderBean().getOrderGoodsBeans());
        }

        tvYuanyin.setText(afterSaleOrderBean.getReason_name());
        orderTime.setText("退款金额：" + "¥"+afterSaleOrderBean.getRefund_price() + "\n" +
                "申请件数：" + afterSaleOrderBean.getRefund_count() + "\n" +
                "申请时间：" + afterSaleOrderBean.getCreate_time() + "\n" +
                "退款编号：" + afterSaleOrderBean.getRefund_no() + "\n"

        );
        orderLog.setText("退货物流：" + "¥"+afterSaleOrderBean.getLogistics_name() + "\n" +
                "退货单号：" + afterSaleOrderBean.getLogistics_no() + "\n" +
                "申请时间：" + afterSaleOrderBean.getCreate_time() + "\n"

        );

        String state=afterSaleOrderBean.getRefund_state();

        switch (state){
            case "wait_review":
                tvCancel.setVisibility(View.VISIBLE);
                tvEditLog.setVisibility(View.GONE);
                tvUpdateLog.setVisibility(View.GONE);
                tvType.setText("等待商家处理");
                tvDesc.setText("您已经成功发起退款申请，请耐心等待商家处理！");
                orderLog.setVisibility(View.GONE);
                break;
            case "accept":
                tvCancel.setVisibility(View.VISIBLE);
                tvEditLog.setVisibility(View.VISIBLE);
                tvUpdateLog.setVisibility(View.GONE);
                tvType.setText("商家已同意");
                tvDesc.setText("商家已通过您的退款申请，请及时填写正确的物流信息！");
                orderLog.setVisibility(View.VISIBLE);
                break;
            case "refuse":
                tvCancel.setVisibility(View.VISIBLE);
                tvEditLog.setVisibility(View.GONE);
                tvUpdateLog.setVisibility(View.GONE);
                tvType.setText("商家已拒绝");
                tvDesc.setText("商家已拒绝您的退款申请，如有疑问请联系商家！");
                orderLog.setVisibility(View.GONE);
                break;
            case "end":
                tvCancel.setVisibility(View.GONE);
                tvEditLog.setVisibility(View.GONE);
                tvUpdateLog.setVisibility(View.GONE);
                tvType.setText("退款成功");
                tvDesc.setText("退款成功！");
                orderLog.setVisibility(View.VISIBLE);
                break;

            case "cancel":
                tvCancel.setVisibility(View.GONE);
                tvEditLog.setVisibility(View.GONE);
                tvUpdateLog.setVisibility(View.GONE);
                tvType.setText("已撤销申请");
                tvDesc.setText("已撤销申请！");
                orderLog.setVisibility(View.VISIBLE);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_cancel, R.id.tv_edit_log, R.id.tv_update_log})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                cancelDialog(afterSaleOrderBean.getRefund_id());

                break;
            case R.id.tv_edit_log:
                break;
            case R.id.tv_update_log:
                break;
        }
    }


    private void cancelDialog(final String refund_id) {

        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("是否撤销申请？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("member_id",userBean.getMember_id());
                map.put("member_token",userBean.getMember_token());
                map.put("refund_id", refund_id);
                getPresenter().getCancelRefundOrder(map);
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
