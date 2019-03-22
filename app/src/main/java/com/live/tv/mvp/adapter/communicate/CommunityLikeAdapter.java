package com.live.tv.mvp.adapter.communicate;

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

/**
 * @author Created by stone
 * @since 2018/1/18
 */

public class CommunityLikeAdapter extends RecyclerArrayAdapter<HousekeepBean> {
   private Context mContext;
    public CommunityLikeAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<HousekeepBean> {
        private ImageView img;
        private TextView name, address;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_like_commounity);
            img=$(R.id.img);
            name=$(R.id.tv_name);
            address=$(R.id.tv_address);

        }

        @Override
        public void setData(HousekeepBean data) {
            super.setData(data);
            Glide.with(mContext).load(Constants.BASE_URL+data.getHouse_service_image()).placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher).into(img);
            name.setText(data.getContact_name());
            address.setText(data.getService_range());

        }
    }
}