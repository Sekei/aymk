package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.utils.GlideCircleTransform;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.LiveBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class FamousDoctorLiveListAdapter extends RecyclerArrayAdapter<LiveBean> {
    public FamousDoctorLiveListAdapter(Context context, List<LiveBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<LiveBean> {
        private ImageView img,img_doctor;
        private TextView tv_name,tv_dep,tv_live_name,tv_state;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_famous_doctor_live_list);
            img = $(R.id.img);
            img_doctor = $(R.id.img_doctor);
            tv_name = $(R.id.tv_name);
            tv_dep = $(R.id.tv_dep);
            tv_live_name = $(R.id.tv_live_name);
            tv_state = $(R.id.tv_state);
//            name = $(R.id.name);


        }

        @Override
        public void setData(LiveBean data) {
            super.setData(data);

            Glide.with(getContext()).load( Constants.BASE_URL+data.getLive_img())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults)
                    .into(img);

            Glide.with(getContext()).load(Constants.BASE_URL + data.getDoctorBean().getDoctor_head_image())
                    .transform(new GlideCircleTransform(getContext()))
                    .placeholder(R.drawable.ava_defaultx)
                    .error(R.drawable.ava_defaultx)
                    .into(img_doctor);

            tv_live_name.setText(data.getLive_title());
            tv_name.setText(data.getDoctorBean().getDoctor_name());
           tv_dep.setText(data.getDoctorBean().getDoctor_department());


            if (data.getLive_state().equals("1")){
                tv_state.setText("直播");
            }else{
                tv_state.setText("回放");
            }

        }
    }
}