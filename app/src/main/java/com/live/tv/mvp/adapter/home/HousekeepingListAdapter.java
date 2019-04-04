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
import com.live.tv.bean.HousekeepBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class HousekeepingListAdapter extends RecyclerArrayAdapter<HousekeepBean> {
    public HousekeepingListAdapter(Context context, List<HousekeepBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }
    public class ViewHolder extends BaseViewHolder<HousekeepBean> {
        private ImageView img;
        private TextView tv_address,tv_desc;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_housekeeping);
            img = $(R.id.img);
            tv_address = $(R.id.tv_address);
            tv_desc = $(R.id.tv_desc);
        }

        @Override
        public void setData(HousekeepBean data) {
            super.setData(data);
            Glide.with(getContext()).load(Constants.BASE_URL + data.getHouse_service_image())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(img);
            tv_address.setText(data.getHouse_address());
            tv_desc.setText(data.getHouse_service_name());

        }
    }
}