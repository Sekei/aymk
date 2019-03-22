package com.live.tv.mvp.adapter.communicate;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.PlateListBean;

/**
 * @author Created by stone
 * @since 2018/1/18
 */

public class CommunityShowimgAdapter extends RecyclerArrayAdapter<PlateListBean.PostImageBeansBeanX> {
    private Context mContext;
    public CommunityShowimgAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<PlateListBean.PostImageBeansBeanX> {
        private ImageView icimg;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_img_recommend);
             icimg=$(R.id.img);


        }

        @Override
        public void setData(PlateListBean.PostImageBeansBeanX data) {
            super.setData(data);
            Glide.with(mContext)
                    .load(R.mipmap.icon_zhifuchenggong)
                    .error(R.drawable.banner_default)
                    .into(icimg);

        }
    }
}