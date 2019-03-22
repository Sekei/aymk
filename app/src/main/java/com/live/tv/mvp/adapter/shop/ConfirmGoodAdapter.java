package com.live.tv.mvp.adapter.shop;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ShopCarBean;

import java.util.List;


public class ConfirmGoodAdapter extends BaseQuickAdapter<ShopCarBean, BaseViewHolder> {

    public ConfirmGoodAdapter(List<ShopCarBean> data, int index) {
        super(R.layout.item_order_goods, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopCarBean item) {

        Glide.with(mContext).load(Constants.BASE_URL+item.getGoods_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into((ImageView) helper.getView(R.id.img_merchants));
        helper.setText(R.id.tv_good_name,item.getGoods_name());
        helper.setText(R.id.tv_nums,"规格："+item.getSpecification_names());
        helper.setText(R.id.tv_price,"¥"+item.getSpecification_price());
        helper.setText(R.id.tv_cancel,item.getGoods_num());


    }





}
