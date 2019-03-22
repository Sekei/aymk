package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.GoodsBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class SearchGoodAdapter extends RecyclerArrayAdapter<GoodsBean> {
    public SearchGoodAdapter(Context context, List<GoodsBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<GoodsBean> {
        private ImageView img;
        private TextView tv_name,  tv_saless;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_search_good);
            img = $(R.id.img);
            tv_name = $(R.id.tv_name);
            tv_saless = $(R.id.tv_saless);

        }

        @Override
        public void setData(GoodsBean data) {
            super.setData(data);

            Glide.with(getContext()).load(Constants.BASE_URL + data.getGoods_img()).placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults)
                    .into(img);
            tv_name.setText(data.getGoods_name());
            tv_saless.setText("销量："+data.getMonth_sales());

        }
    }
}