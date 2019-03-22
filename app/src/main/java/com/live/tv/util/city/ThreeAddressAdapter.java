package com.live.tv.util.city;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;

import java.util.List;

/**
 * Created by lenove on 2017/7/25.
 */

public class ThreeAddressAdapter extends RecyclerArrayAdapter<DistrictBean> {

    public ThreeAddressAdapter(Context context, List<DistrictBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryHolder(parent, R.layout.pop_item_two);
    }

    class CategoryHolder extends BaseViewHolder<DistrictBean> {

        private TextView name;


        public CategoryHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            name = $(R.id.name);
        }

        @Override
        public void setData(DistrictBean data) {
            name.setText(data.getName());
         /*   name.setText(data.getTypeName());
            max.setText(data.getNums());
            name.setTextColor(ActivityCompat.getColor(getContext(),R.color.colorTextG9));
            max.setTextColor(ActivityCompat.getColor(getContext(),R.color.colorTextG9));*/

        }
    }

}




