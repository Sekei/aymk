package com.live.tv.mvp.adapter.shop;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.OrderGoodsBeans;

import java.util.List;

public class GoodOrderDetailGoodAdapter extends BaseQuickAdapter<OrderGoodsBeans, BaseViewHolder> {

    private String  state="";
    public GoodOrderDetailGoodAdapter(List<OrderGoodsBeans> data) {
        super(R.layout.item_order_goods_order, data);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderGoodsBeans item) {

        Glide.with(mContext).load(Constants.BASE_URL + item.getGoods_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.img_merchants));
                helper.setText(R.id.tv_good_name,item.getGoods_name());
                helper.setText(R.id.tv_cancel,"x"+item.getGoods_num());
                helper.setText(R.id.tv_nums,item.getSpecification_names());
                helper.setText(R.id.tv_price,"¥"+item.getSpecification_price());

        TextView textView=helper.getView(R.id.wait_send);
        helper.addOnClickListener(R.id.wait_send);
        if (state.equals("wait_send")){
            textView.setVisibility(View.VISIBLE);


        }else if (state.equals("wait_receive")){
            textView.setVisibility(View.VISIBLE);


        }else if (state.equals("wait_use")){
            textView.setVisibility(View.VISIBLE);


        }else{

            textView.setVisibility(View.GONE);
        }
    }
}
