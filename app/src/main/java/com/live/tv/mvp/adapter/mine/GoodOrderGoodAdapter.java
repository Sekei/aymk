package com.live.tv.mvp.adapter.mine;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.OrderGoodsBeans;

import java.util.List;

public class GoodOrderGoodAdapter extends BaseQuickAdapter<OrderGoodsBeans, BaseViewHolder> {
    public GoodOrderGoodAdapter(List<OrderGoodsBeans> data) {
        super(R.layout.item_order_goods, data);
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

    }
}
