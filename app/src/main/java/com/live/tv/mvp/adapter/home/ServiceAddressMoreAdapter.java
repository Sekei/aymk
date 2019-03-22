package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HousekeepBean;

import java.util.List;

/**
 * Created by mac1010 on 2018/8/30.
 */

public class ServiceAddressMoreAdapter extends RecyclerArrayAdapter<HousekeepBean.HouseAddressBeansBean> {
    private Context mContext;

    public List<HousekeepBean.HouseAddressBeansBean> getmList() {
        return mList;
    }

    public void setmList(List<HousekeepBean.HouseAddressBeansBean> mList) {
        this.mList = mList;
    }

    private List<HousekeepBean.HouseAddressBeansBean> mList;
    public boolean isshowCheckBox() {
        return isshowCheckBox;
    }

    public void setIsshowCheckBox(boolean isshowCheckBox) {
        this.isshowCheckBox = isshowCheckBox;
    }

    private boolean isshowCheckBox;
    public ServiceAddressMoreAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServiceAddressMoreAdapter.ViewHolder(parent);
    }
    public class ViewHolder extends BaseViewHolder<HousekeepBean.HouseAddressBeansBean> {
        private TextView address;
        private CheckBox checkBox;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_service_address);
             address = $(R.id.tv_address);
             checkBox=$(R.id.cb_default);

        }

        @Override
        public void setData(final HousekeepBean.HouseAddressBeansBean data) {
            super.setData(data);
            checkBox.setChecked(false);
            address.setText(data.getHouse_province()+data.getHouse_city()+data.getHouse_country()+data.getHouse_address());
            if (!isshowCheckBox){
                checkBox.setVisibility(View.GONE);
            }else {
                checkBox.setVisibility(View.VISIBLE);

                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            data.setIs_delete("1");
                        }else {
                            data.setIs_delete("0");

                        }

                    }
                });
            }

        }
    }


}