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
import com.live.tv.bean.HomeButtonBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class HomeButtonAdapter extends RecyclerArrayAdapter<HomeButtonBean> {
    public HomeButtonAdapter(Context context, List<HomeButtonBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<HomeButtonBean> {
        private ImageView img;
        private TextView tittle;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_home_button);
            img = $(R.id.img);
            tittle = $(R.id.tittle);
        }

        @Override
        public void setData(HomeButtonBean data) {
            super.setData(data);
            if (data.getImg() == 0) {
                Glide.with(getContext()).load(Constants.BASE_URL + data.getButton_url()).placeholder(R.drawable.home_icon_case).error(R.drawable.home_icon_case).into(img);
            } else {
                Glide.with(getContext()).load(data.getImg()).placeholder(R.drawable.home_icon_case).error(R.drawable.home_icon_case).into(img);
            }
            tittle.setText(data.getButton_name());
        }
    }
}
