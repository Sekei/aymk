package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/11
 */

public class CommonTestAdapter extends RecyclerArrayAdapter<DoctorDetailBean> {
    public CommonTestAdapter(Context context, List<DoctorDetailBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<DoctorDetailBean> {
        private ImageView img;
        private TextView num;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_common_test);
            img = $(R.id.img);
            num = $(R.id.num);


        }

        @Override
        public void setData(DoctorDetailBean data) {
            super.setData(data);

           /* Glide.with(getContext()).load(Constants.BASE_URL + data.getDoctor_certificate_img())
                    .error(R.drawable.home_doctor_ava)
                    .into(img);*/
           // num.setText(data.getDoctor_name());

        }
    }
}