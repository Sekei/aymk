package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
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

public class MoreSearchMerchantsAdapter extends RecyclerArrayAdapter<MerchantsBean> {
    public MoreSearchMerchantsAdapter(Context context, List<MerchantsBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<MerchantsBean> {
        private ImageView img;
        private TextView tv_name, tv_department;
        private RatingBar  comments_star;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_more_search_merchants);
            img = $(R.id.img);
            tv_name = $(R.id.tv_name);
            tv_department = $(R.id.tv_department);
            comments_star = $(R.id.comments_star);

        }

        @Override
        public void setData(final MerchantsBean data) {
            super.setData(data);
            //在这里设置数据
            //在这里设置数据
            Glide.with(getContext()).load(Constants.BASE_URL+data.getMerchants_img())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults)
                    .centerCrop()
                    .into(img);

            tv_name.setText(data.getMerchants_name());
            tv_department.setText("商家信息");
            int  num= Integer.parseInt(data.getMerchants_star1());
            comments_star.setNumStars(num);


        }
    }


}