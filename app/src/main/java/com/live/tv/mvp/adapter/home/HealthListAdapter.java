package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthDataBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/15
 */

public class HealthListAdapter extends RecyclerArrayAdapter<HealthDataBean.HealthDataBeansBean> {
    public HealthListAdapter(Context context, List<HealthDataBean.HealthDataBeansBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<HealthDataBean.HealthDataBeansBean> {

        private TextView content;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_health_data);
            content=$(R.id.content);
        }

        @Override
        public void setData(HealthDataBean.HealthDataBeansBean data) {
            super.setData(data);
            content.setText(data.getHealth_data_desc());

        }
    }
}
