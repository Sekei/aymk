package com.live.tv.mvp.adapter.mine;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AssociatorBean;

import java.util.List;


/**
 * Created by wql on 2017/3/29.
 */

public class MyVipAdapter extends BaseQuickAdapter<AssociatorBean, BaseViewHolder> {
    public MyVipAdapter(List<AssociatorBean> data) {
        super(R.layout.item_my_vip, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AssociatorBean item) {
       helper.setText(R.id.title,item.getAssociator_title());
       helper.setText(R.id.content,item.getAssociator_desc());
        Glide.with(mContext).load(Constants.BASE_URL + item.getAssociator_img())
                .placeholder(R.drawable.banner_default)
                .error(R.drawable.banner_default)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.img));



    }
}
