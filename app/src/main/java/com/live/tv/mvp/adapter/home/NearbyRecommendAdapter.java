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
import com.live.tv.bean.MerchantHotBean;

import static com.ysjk.health.iemk.R.id.img;


/**
 * @author Created by stone
 * @since 2018/1/9
 * 热门商家
 */

public class NearbyRecommendAdapter extends RecyclerArrayAdapter<MerchantHotBean> {

    private Context mContext;
    public NearbyRecommendAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<MerchantHotBean> {
         private ImageView imageView;
        private TextView tvname,tvaddress;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_nearby_recommend);
            tvname=$(R.id.name);
            tvaddress=$(R.id.address);
            imageView=$(img);
        }

        @Override
        public void setData(MerchantHotBean data) {
            super.setData(data);

            Glide.with(mContext).load(Constants.BASE_URL+data.getMerchants_img())
                    .placeholder(R.drawable.banner_default)
                    .error(R.drawable.banner_default)
                    .centerCrop()
                    .into(imageView);
            tvname.setText(data.getMerchants_name());
            tvaddress.setText(data.getMerchants_address());



        }
    }
}