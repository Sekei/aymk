package com.live.tv.mvp.adapter.communicate;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.PlateListBean;

/**
 * @author Created by stone
 * @since 2018/1/18
 */

public class CommunityAllMessageAdapter extends RecyclerArrayAdapter<PlateListBean.CommentPostBeansBeanX> {
    private Context mContext;
    public CommunityAllMessageAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<PlateListBean.CommentPostBeansBeanX> {
        private ImageView icimg;
        private TextView tvname,tvaddress;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_message_recommend);
             icimg=$(R.id.img);


        }

        @Override
        public void setData(PlateListBean.CommentPostBeansBeanX data) {
            super.setData(data);

            Glide.with(mContext)
                    .load(R.mipmap.icon_zhifuchenggong)
                    .error(R.drawable.banner_default)
                    .into(icimg);

        }
    }
}