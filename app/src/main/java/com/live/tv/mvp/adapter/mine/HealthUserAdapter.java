package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthRecordDetailBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class HealthUserAdapter extends RecyclerArrayAdapter<HealthRecordDetailBean> {
    public HealthUserAdapter(Context context, List<HealthRecordDetailBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<HealthRecordDetailBean> {
        private CircleImageView avatar;
        private TextView name, sex, verified, tv_person_info, age;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_health_user);
            avatar = $(R.id.avatar);
            name = $(R.id.name);
            sex = $(R.id.sex);
            verified = $(R.id.verified);
            tv_person_info = $(R.id.tv_person_info);
            age = $(R.id.age);


        }

        @Override
        public void setData(HealthRecordDetailBean data) {
            super.setData(data);

            Glide.with(getContext()).load(Constants.BASE_URL + data.getHead_image())
                    .error(R.drawable.home_doctor_ava)
                    .into(avatar);
            name.setText(data.getReal_name());
            sex.setText(data.getSex());
            verified.setText(data.getService_type_show());
            tv_person_info.setText(data.getPlan_state());
            age.setText(data.getAge());

        }
    }
}