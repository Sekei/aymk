package com.live.tv.mvp.adapter.mine;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.easeui.utils.GlideCircleTransform;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;

import java.util.List;


/**
 *我的医生
 */

public class MyDoctorAdapter extends BaseQuickAdapter<DoctorDetailBean, BaseViewHolder> {


    public MyDoctorAdapter(List<DoctorDetailBean> data) {
        super(R.layout.item_my_doctor, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, DoctorDetailBean item) {
        Glide.with(mContext).load(Constants.BASE_URL + item.getDoctor_head_image())
                .transform(new GlideCircleTransform(mContext))
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.img));

        helper.setText(R.id.tv_name,item.getDoctor_name());
        helper.setText(R.id.tv_department,item.getDoctor_department());
        helper.setText(R.id.tv_title,item.getDoctor_title());
        helper.setText(R.id.tv_hospital,item.getDoctor_hospital());
        helper.addOnClickListener(R.id.tv_consult);

    }
}
