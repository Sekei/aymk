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

public class ClassGoodsListAdapter extends RecyclerArrayAdapter<GoodsBean> {
    public ClassGoodsListAdapter(Context context, List<GoodsBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<GoodsBean> {
        private ImageView img;
        private TextView name;
        private TextView price;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_shop_class_goods);
            img= $(R.id.img);
            name= $(R.id.name);
            price= $(R.id.price);
        }

        @Override
        public void setData(GoodsBean data) {
            super.setData(data);
            name.setText(data.getGoods_name());
            price.setText("¥"+data.getGoods_now_price());

            Glide.with(getContext()).load(Constants.BASE_URL+data.getGoods_img())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults).centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(img);
        }
    }
}
