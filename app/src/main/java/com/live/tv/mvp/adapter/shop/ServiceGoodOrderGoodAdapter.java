package com.live.tv.mvp.adapter.shop;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.OrderGoodsBeans;

import java.util.List;

public class ServiceGoodOrderGoodAdapter extends BaseQuickAdapter<OrderGoodsBeans, BaseViewHolder> {
    private String order_state_show="";
    public ServiceGoodOrderGoodAdapter(List<OrderGoodsBeans> data,String order_state) {
        super(R.layout.item_service_order_good, data);
        order_state_show=order_state;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderGoodsBeans item) {

        Glide.with(mContext).load(Constants.BASE_URL + item.getGoods_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.img_merchants));
                helper.setText(R.id.tv_good_name,item.getGoods_name());
                helper.setText(R.id.tv_cancel,"");
                helper.setText(R.id.tv_state,order_state_show);
                helper.setText(R.id.tv_nums,"数量："+item.getGoods_num());
                helper.setText(R.id.tv_price,"¥"+item.getSpecification_price());

    }
}
