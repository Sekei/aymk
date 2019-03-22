package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthManagerBeans;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/12
 */

public class HealthManagePriceAdapter extends RecyclerArrayAdapter<HealthManagerBeans> {
    public HealthManagePriceAdapter(Context context, List<HealthManagerBeans> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }
    private int position=0;
    public void setPosition(int position) {
        this.position = position;
    }
    public class ViewHolder extends BaseViewHolder<HealthManagerBeans> {
        private TextView peice;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_health_manager_price);
            peice = $(R.id.peice);

        }
        @Override
        public void setData(HealthManagerBeans data) {
            super.setData(data);
            peice.setText(data.getService_title()+"("+data.getService_price()+"元)");
            if (position==getDataPosition()){
                peice.setTextColor(getContext().getResources().getColor(R.color.pure_white));
                peice.setBackgroundResource(R.drawable.setbar_mm);
            }else{
                peice.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                peice.setBackgroundResource(R.drawable.setbar_seach);
            }

        }
    }
}