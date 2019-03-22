package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthPlanBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/17
 */

public class HealthStepAdapter extends RecyclerArrayAdapter<HealthPlanBean> {
    public HealthStepAdapter(Context context, List<HealthPlanBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<HealthPlanBean> {

        private TextView tittle, progress;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_health_step);
            tittle = $(R.id.tittle);
            progress = $(R.id.progress);

        }

        @Override
        public void setData(HealthPlanBean data) {
            super.setData(data);
            tittle.setText(data.getPlan_name());
            progress.setText(data.getDone_num()+"/"+data.getSum_num());



            if (getDataPosition() == 0) {
                tittle.setBackgroundColor(getContext().getResources().getColor(R.color.bg_1));
                tittle.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                progress.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            } else {
                tittle.setBackgroundColor(getContext().getResources().getColor(R.color.pure_white));
                tittle.setTextColor(getContext().getResources().getColor(R.color.colorTextG6));
                progress.setTextColor(getContext().getResources().getColor(R.color.colorTextG9));
            }

        }
    }
}
