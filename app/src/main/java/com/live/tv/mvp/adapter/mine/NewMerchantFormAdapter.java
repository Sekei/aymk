package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HousekeepBean;
import com.live.tv.bean.OrderBean;

/**
 * Created by mac1010 on 2018/8/30.
 */

public class NewMerchantFormAdapter extends RecyclerArrayAdapter<OrderBean> {
 private OnClickLisenter mlisenter;




    private Context mContext;
    public NewMerchantFormAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewMerchantFormAdapter.ViewHolder(parent);
    }
    public class ViewHolder extends BaseViewHolder<OrderBean> {
        private TextView tvtitle,tvnum,tvtime,tvprice,tvLeft,tvRight;
        private ImageView ivheard;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_merchantform_list);
            tvtitle = $(R.id.tv_title);
            tvnum = $(R.id.tv_number);
            tvtitle = $(R.id.tv_title);
            tvtime=$(R.id.tv_time);
            tvprice=$(R.id.tv_sum_price);
            ivheard=$(R.id.img_merchants);
            tvLeft=$(R.id.tv_left);
            tvRight=$(R.id.tv_right);
        }

        @Override
        public void setData(final OrderBean data) {
            super.setData(data);
            tvtitle.setText(data.getMerchants_name());
            tvprice.setText("¥"+data.getOrder_actual_price());
            tvnum.setText("数量: 10");
            tvtime.setText(data.getCreate_time());
            Glide.with(mContext).load(Constants.BASE_URL + data.getMerchants_img())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivheard);
            switch (data.getOrder_state()) {
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

            tvLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlisenter!=null){
                        mlisenter.onleftLisenter(data);
                    }
                }
            });

            tvRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlisenter!=null){
                        mlisenter.OnRightLisenter(data);
                    }
                }
            });

        }
    }



    public void SetOnButtonLisenter(OnClickLisenter lisenter){
        mlisenter=lisenter;

    }


   public interface OnClickLisenter{
       void onleftLisenter(OrderBean lorderBean);
       void OnRightLisenter(OrderBean rorderBean);

    }
}