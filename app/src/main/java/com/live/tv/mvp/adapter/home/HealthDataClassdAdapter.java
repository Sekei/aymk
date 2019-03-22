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

public class HealthDataClassdAdapter extends RecyclerArrayAdapter<HealthDataBean> {
    public HealthDataClassdAdapter(Context context, List<HealthDataBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    private int position = -1;

    public void setPosition(int position) {
        this.position = position;
    }

    public class ViewHolder extends BaseViewHolder<HealthDataBean> {

        private TextView name;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_medical_class);
            name=$(R.id.name);
        }

        @Override
        public void setData(HealthDataBean data) {
            super.setData(data);
            if (position == getDataPosition()) {
                name.setTextColor(getContext().getResources().getColor(R.color.pure_white));
                name.setBackgroundResource(R.color.colorPrimary);
            } else {
                name.setTextColor(getContext().getResources().getColor(R.color.colorTextG6));
                name.setBackgroundResource(R.color.pure_white);
            }
            name.setText(data.getHealth_class_title());
        }
    }
}