package com.live.tv.mvp.adapter.home;

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
import com.live.tv.bean.DoctorDetailBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class DoctorListAdapter extends RecyclerArrayAdapter<DoctorDetailBean> {
    public DoctorListAdapter(Context context, List<DoctorDetailBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<DoctorDetailBean> {
        private ImageView img;
        private TextView name, type, department, hospital, indications, price, peopleNum;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_doctor_list);
            img = $(R.id.img);
            name = $(R.id.name);
            type = $(R.id.type);
            department = $(R.id.department);
            hospital = $(R.id.hospital);
            indications = $(R.id.indications);
            price = $(R.id.price);
            peopleNum = $(R.id.peopleNum);

        }

        @Override
        public void setData(DoctorDetailBean data) {
            super.setData(data);

            Glide.with(getContext()).load(Constants.BASE_URL + data.getDoctor_head_image())
                    .transform(new GlideCircleTransform(getContext()))
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.drawable.ava_defaultx)
                    .into(img);
            name.setText(data.getDoctor_name());
            type.setText(data.getDoctor_title());
            department.setText(data.getDoctor_department());
            hospital.setText(data.getDoctor_hospital());
            indications.setText(data.getDoctor_attendingtype());
            price.setText(data.getGraphic_price()+"起");
            peopleNum.setText("已有"+data.getConsulting_count()+"人咨询");
        }
    }
}