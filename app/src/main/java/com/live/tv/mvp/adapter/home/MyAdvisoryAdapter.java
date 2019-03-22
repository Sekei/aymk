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
import com.live.tv.bean.ConsultBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/31
 */

public class MyAdvisoryAdapter extends RecyclerArrayAdapter<ConsultBean> {
    public MyAdvisoryAdapter(Context context, List<ConsultBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<ConsultBean> {
        private ImageView img;
        private TextView time;
        private TextView name;
        private TextView department;
        private TextView type;
        private TextView content;
        private TextView step;


        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_my_advisory);

            img = $(R.id.img);
            time = $(R.id.time);
            name = $(R.id.name);
            department = $(R.id.department);
            type = $(R.id.type);
            content = $(R.id.content);
            step = $(R.id.step);

        }

        @Override
        public void setData(ConsultBean data) {
            super.setData(data);
              Glide.with(getContext()).load(Constants.BASE_URL + data.getMember_head_image())
                      .transform(new GlideCircleTransform(getContext()))
                      .placeholder(R.drawable.home_doctor_ava)
                    .error(R.drawable.home_doctor_ava)
                    .into(img);

            time.setText(data.getConsult_start_time());
            name.setText(data.getDoctor_name());
            department.setText(data.getDoctor_department());
            type.setText(data.getType_show());
            content.setText(data.getDoctor_name());
            step.setText(data.getState_show());

        }
    }
}