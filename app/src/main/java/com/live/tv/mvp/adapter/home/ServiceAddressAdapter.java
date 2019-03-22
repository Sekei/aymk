package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HousekeepBean;
import com.live.tv.bean.RelationsBean;
import com.live.tv.bean.ServiceCopeBean;

import java.util.List;

/**
 * Created by mac1010 on 2018/8/30.
 */

public class ServiceAddressAdapter extends RecyclerArrayAdapter<HousekeepBean.HouseAddressBeansBean> {
    public ServiceAddressAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServiceAddressAdapter.ViewHolder(parent);
    }
    public class ViewHolder extends BaseViewHolder<HousekeepBean.HouseAddressBeansBean> {
        private TextView address;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_service_address);
            address = $(R.id.tv_address);
        }

        @Override
        public void setData(HousekeepBean.HouseAddressBeansBean data) {
            super.setData(data);
            address.setText(data.getHouse_province()+data.getHouse_city()+data.getHouse_country()+data.getHouse_address());
        }
    }
}