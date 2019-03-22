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
import com.live.tv.bean.CommunityBannerBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/18
 */

public class CommunityHotListAdapter extends RecyclerArrayAdapter<CommunityBannerBean.CommunityPlateBeansBean> {
public CommunityHotListAdapter(Context context, List<CommunityBannerBean.CommunityPlateBeansBean> objects) {
        super(context, objects);
        }

@Override
public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
        }


public class ViewHolder extends BaseViewHolder<CommunityBannerBean.CommunityPlateBeansBean> {
    private ImageView img;
    private TextView name;

    public ViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_commounity_hot);
          img = $(R.id.img);
        name=$(R.id.content);


    }

    @Override
    public void setData(CommunityBannerBean.CommunityPlateBeansBean data) {
        super.setData(data);

            Glide.with(getContext())
                    .load(Constants.BASE_URL + data.getPlate_image())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .thumbnail(0.1f)
                    .into(img);
            name.setText(data.getPlate_name());

    }
}
}