package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ServiceCommentBean;
import com.live.tv.bean.ServiceCopeBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/8/29
 */

public class ServicePingLunItemAdapter extends RecyclerArrayAdapter<ServiceCommentBean.AssessmentImgBeansBean> {
    public ServicePingLunItemAdapter(Context context, List<ServiceCommentBean.AssessmentImgBeansBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<ServiceCommentBean.AssessmentImgBeansBean> {
        private ImageView img;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_image);
            img = $(R.id.img);
        }
        @Override
        public void setData(final ServiceCommentBean.AssessmentImgBeansBean data) {
            super.setData(data);
            //在这里设置数据
            Glide.with(getContext()).load(Constants.BASE_URL+data.getAssessment_img())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults)
                    .centerCrop()
                    .into(img);
        }
    }


}