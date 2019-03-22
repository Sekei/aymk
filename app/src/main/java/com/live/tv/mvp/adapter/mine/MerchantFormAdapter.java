package com.live.tv.mvp.adapter.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.OrderBean;

import java.util.List;

public class MerchantFormAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
    public MerchantFormAdapter(List<OrderBean> data) {
        super(R.layout.item_merchantform_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {

         Glide.with(mContext).load(Constants.BASE_URL + item.getMerchants_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.img_merchants));
        helper.setText(R.id.tv_title, item.getMerchants_name());
        helper.setText(R.id.tv_sum_price, "¥"+item.getOrder_actual_price());
        helper.setText(R.id.tv_number, "数量: 10");
        helper.setText(R.id.tv_time,"18-8-8");

        String state_str = item.getOrder_state();
        TextView tvLeft =helper.getView(R.id.tv_left);
        TextView tvRight =helper.getView(R.id.tv_right);
        switch (state_str) {
            case "wait_pay":
                tvLeft.setText("取消订单");
                tvRight.setText("确认支付");
                break;

            case "wait_send":


                break;

            case "wait_receive":
                tvLeft.setText("申请退款");
                tvRight.setText("等待确认");
                break;
            case "wait_assessment":
//评价
                tvLeft.setText("删除订单");
                tvRight.setText("立即评价");

                break;
            case "cancel":

                break;


            case "end":
//已完成
                tvLeft.setText("删除订单");
                tvRight.setVisibility(View.GONE);
                break;


        }

        helper.addOnClickListener(R.id.tv_left);
        helper.addOnClickListener(R.id.tv_right);






    }
}
