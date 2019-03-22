package com.live.tv.util.city;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DepartmentLevelTwoBean;

import java.util.List;

/**
 * Created by lenove on 2017/7/25.
 */

public class TwoDepartmentAdapter extends RecyclerArrayAdapter<DepartmentLevelTwoBean> {

    public TwoDepartmentAdapter(Context context, List<DepartmentLevelTwoBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryHolder(parent, R.layout.pop_item_two);
    }

    class CategoryHolder extends BaseViewHolder<DepartmentLevelTwoBean> {

        private TextView name;


        public CategoryHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            name = $(R.id.name);
        }

        @Override
        public void setData(DepartmentLevelTwoBean data) {
            name.setText(data.getDepartment_name());
         /*   name.setText(data.getTypeName());
            max.setText(data.getNums());
            name.setTextColor(ActivityCompat.getColor(getContext(),R.color.colorTextG9));
            max.setTextColor(ActivityCompat.getColor(getContext(),R.color.colorTextG9));*/

        }
    }

}




