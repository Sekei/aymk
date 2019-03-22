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
import com.live.tv.bean.DoctorDetailBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class TuijianDoctorAdapter extends RecyclerArrayAdapter<DoctorDetailBean> {
    public TuijianDoctorAdapter(Context context, List<DoctorDetailBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<DoctorDetailBean> {
        private ImageView img;
        private TextView tv_name, tv_department, tv_title, tv_hospital, tv_consult;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_my_doctor);
            img = $(R.id.img);
            tv_name = $(R.id.tv_name);
            tv_department = $(R.id.tv_department);
            tv_title = $(R.id.tv_title);
            tv_hospital = $(R.id.tv_hospital);
            tv_consult = $(R.id.tv_consult);


        }

        @Override
        public void setData(DoctorDetailBean data) {
            super.setData(data);

            Glide.with(getContext()).load(Constants.BASE_URL + data.getDoctor_head_image())
                    .error(R.drawable.ava_defaultx)
                    .into(img);
            tv_name.setText(data.getDoctor_name());
            tv_department.setText(data.getDoctor_department());
            tv_title.setText(data.getDoctor_title());
            tv_hospital.setText(data.getDoctor_hospital());


        }
    }
}