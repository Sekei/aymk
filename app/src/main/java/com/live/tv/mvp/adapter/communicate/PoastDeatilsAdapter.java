package com.live.tv.mvp.adapter.communicate;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ImageListBean;
import com.live.tv.bean.PlateListBean;
import com.live.tv.bean.PostContentBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Created by stone
 * @since 2018/1/18
 */

public class PoastDeatilsAdapter extends RecyclerArrayAdapter<PlateListBean.CommentPostBeansBeanX> {

    public PoastDeatilsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<PlateListBean.CommentPostBeansBeanX> {
        private CircleImageView mivAvatar;
        private TextView mName, mContent, mTime;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_post_details);
            mivAvatar = $(R.id.ivAvatar);//头像
            mName = $(R.id.name);//名称
            mContent = $(R.id.content);//内容
            mTime = $(R.id.tv_time);

        }

        @Override
        public void setData(final PlateListBean.CommentPostBeansBeanX data) {
            super.setData(data);
            Glide.with(getContext())
                    .load(Constants.BASE_URL + data.getMember_head_image())
                    .error(R.drawable.comm_pic_example)
                    .placeholder(R.drawable.comm_pic_example)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> glideAnimation) {
                            mivAvatar.setImageDrawable(resource);
                        }
                    });

            mName.setText(data.getMember_nick_name());
            mContent.setText(data.getComment_desc());
            mTime.setText(data.getCreate_time());

        }
    }
}