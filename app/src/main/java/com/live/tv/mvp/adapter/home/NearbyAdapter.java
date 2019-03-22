package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;

import java.text.DecimalFormat;

/**
 * @author Created by stone
 * @since 2018/1/9
 * 附近推荐
 */

public class NearbyAdapter extends RecyclerArrayAdapter<PoiItem> {
    public NearbyAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder
            (ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<PoiItem> {
        private TextView tv_name,tv_address ,tv_distance;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_bearby);
            tv_name=$(R.id.name);
            tv_address=$(R.id.address);
            tv_distance=$(R.id.distance);
        }
        @Override
        public void setData(PoiItem data) {
            super.setData(data);
            tv_name.setText(data.getTitle());
            if (data.getDistance()>1000){
                DecimalFormat    df   = new DecimalFormat("######0.00");

                tv_distance.setText(df.format((data.getDistance()*1.0)/1000)+" 千米");
            }else {
                tv_distance.setText(data.getDistance()+" 米");

            }
            tv_address.setText(data.getSnippet());
        }
    }
}