package com.live.tv.mvp.adapter.communicate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;

/**
 * @author Created by stone
 * @since 2018/1/18
 */

public class CommunityImageAdapter extends RecyclerArrayAdapter<String> {
    private Context mContext;
    public CommunityImageAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<String> {
        private ImageView icimg;
        private TextView tvname,tvaddress;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_nearby_recommend);
             icimg=$(R.id.img);
             tvname=$(R.id.name);
             tvaddress=$(R.id.address);

        }

        @Override
        public void setData(String data) {
            super.setData(data);
            tvname.setVisibility(View.GONE);
            tvaddress.setVisibility(View.GONE);
            Glide.with(mContext)
                    .load(R.mipmap.icon_zhifuchenggong)
                    .error(R.drawable.banner_default)
                    .into(icimg);

        }
    }
}