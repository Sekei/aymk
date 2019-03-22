package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthRecordDetailBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/16
 */

public class HealthFileListAdapter extends RecyclerArrayAdapter<HealthRecordDetailBean.HealthPlanBeansBean> {
    public HealthFileListAdapter(Context context, List<HealthRecordDetailBean.HealthPlanBeansBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<HealthRecordDetailBean.HealthPlanBeansBean> {
        TextView tittle;
        TextView name;
        TextView type;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_health_file_list);
            tittle = $(R.id.tittle);
            name = $(R.id.name);
            type = $(R.id.type);
        }

        @Override
        public void setData(HealthRecordDetailBean.HealthPlanBeansBean data) {
            super.setData(data);

            tittle.setText(data.getPlan_name());
            name.setText(data.getDoctor_name());
            type.setText(data.getDoctor_department());
        }
    }
}
