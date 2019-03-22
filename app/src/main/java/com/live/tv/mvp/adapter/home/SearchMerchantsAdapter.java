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
import com.live.tv.bean.MerchantsBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class SearchMerchantsAdapter extends RecyclerArrayAdapter<MerchantsBean> {
    public SearchMerchantsAdapter(Context context, List<MerchantsBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<MerchantsBean> {
        private ImageView img;
        private TextView tv_name,  tv_address;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_search_merchants);
            img = $(R.id.img);
            tv_name = $(R.id.tv_name);
            tv_address = $(R.id.tv_address);

        }

        @Override
        public void setData(MerchantsBean data) {
            super.setData(data);

            Glide.with(getContext()).load(Constants.BASE_URL + data.getMerchants_img()).placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults)
                    .into(img);
            tv_name.setText(data.getMerchants_name());
            tv_address.setText(data.getMerchants_province()+data.getMerchants_city()+data.getMerchants_country());

        }
    }
}