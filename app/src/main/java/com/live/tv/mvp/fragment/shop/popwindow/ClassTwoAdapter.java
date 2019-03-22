package com.live.tv.mvp.fragment.shop.popwindow;

import android.content.Context;
import android.support.annotation.LayoutRes;
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

public class ClassTwoAdapter extends RecyclerArrayAdapter<ClassBean> {

    private int checkPosition = 0;

    public void setCheckPosition(int checkPosition) {
        this.checkPosition = checkPosition;
    }

    public ClassTwoAdapter(Context context, List<ClassBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryHolder(parent, R.layout.item_class_two);
    }

    class CategoryHolder extends BaseViewHolder<ClassBean> {

        private TextView name;


        public CategoryHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            name = $(R.id.tv_class_name);

        }

        @Override
        public void setData(ClassBean data) {
            name.setText(data.getClass_name());

        }
    }

}
