package com.live.tv.mvp.fragment.shop.popwindow;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActivityCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ClassBean;

import java.util.List;

/**
 * Created by mac1010 on 2017/11/9.
 */

public class ClassOnesAdapter extends RecyclerArrayAdapter<ClassBean> {

    private int checkPosition = 0;

    public void setCheckPosition(int checkPosition) {
        this.checkPosition = checkPosition;
    }

    public ClassOnesAdapter(Context context, List<ClassBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryHolder(parent, R.layout.pop_item);
    }

    class CategoryHolder extends BaseViewHolder<ClassBean> {

        private TextView name;


        public CategoryHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            name = $(R.id.name);

        }

        @Override
        public void setData(ClassBean data) {
            name.setText(data.getClass_name());
            if (checkPosition == getDataPosition()) {
                name.setText(data.getClass_name());
                name.setBackgroundColor(ActivityCompat.getColor(getContext(), R.color.bg_4));
                name.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorPrimary));
            } else {
                name.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorTextG3));
                name.setBackgroundColor(ActivityCompat.getColor(getContext(), R.color.pure_white));
            }
        }
    }

}
