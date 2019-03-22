package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.utils.GlideCircleTransform;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.UserBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/16
 */

public class FansAdapter extends RecyclerArrayAdapter<UserBean> {
    public FansAdapter(Context context, List<UserBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<UserBean> {
        private ImageView img;
        private TextView name;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_fans);
            img = $(R.id.img);
            name = $(R.id.name);


        }

        @Override
        public void setData(UserBean data) {
            super.setData(data);

            Glide.with(getContext()).load(Constants.BASE_URL + data.getMember_head_image())
                    .transform(new GlideCircleTransform(getContext()))
                    .error(R.drawable.pic_defaults)
                    .into(img);
            name.setText(data.getMember_nick_name());

        }
    }
}