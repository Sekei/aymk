package com.live.tv.mvp.adapter.shop;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.GoodsBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/11
 */

public class HotGoodsAdapter extends RecyclerArrayAdapter<GoodsBean> {
    public HotGoodsAdapter(Context context, List<GoodsBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<GoodsBean> {
private TextView name,content,price;
        private ImageView img;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_hot_goods);
            name = $(R.id.name);
            content = $(R.id.content);
            price = $(R.id.price);
            img = $(R.id.img);
        }

        @Override
        public void setData(GoodsBean data) {
            super.setData(data);

            Glide.with(getContext()).load(Constants.BASE_URL+data.getGoods_img())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults).centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(img);
            name.setText(data.getGoods_name());
            content.setText(data.getGoods_desc());
            price.setText("¥"+data.getGoods_now_price());
        }
    }
}