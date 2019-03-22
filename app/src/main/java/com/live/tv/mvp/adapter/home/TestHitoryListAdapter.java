package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ReportHistoryBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/12
 */

public class TestHitoryListAdapter extends RecyclerArrayAdapter<ReportHistoryBean> {
    public TestHitoryListAdapter(Context context, List<ReportHistoryBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<ReportHistoryBean> {
        private ImageView img;
        private TextView name, time;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_test_hitory);
            time = $(R.id.time);
            name = $(R.id.name);


        }

        @Override
        public void setData(ReportHistoryBean data) {
            super.setData(data);
            name.setText(getContext().getResources().getString(R.string.test_people) + data.getReal_name());
            time.setText(getContext().getResources().getString(R.string.date) + data.getDone_factors_time());
           /* Glide.with(getContext()).load(Constants.BASE_URL + data.getDoctor_certificate_img())
                    .error(R.drawable.home_doctor_ava)
                    .into(img);*/
            // num.setText(data.getDoctor_name());

        }
    }
}