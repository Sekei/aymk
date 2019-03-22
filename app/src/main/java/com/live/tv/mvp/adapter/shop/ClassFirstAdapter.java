package com.live.tv.mvp.adapter.shop;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ClassBean;

import java.util.List;


/**
 * Created by wql on 2017/4/22.
 */

public class ClassFirstAdapter extends BaseQuickAdapter<ClassBean, BaseViewHolder> {
    private int selectPosition = 0;

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public ClassFirstAdapter(List<ClassBean> data) {
        super(R.layout.item_first_class, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ClassBean item) {
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lastSelectPosition = selectPosition;
                selectPosition = helper.getLayoutPosition() - getHeaderLayoutCount();
                notifyItemChanged(lastSelectPosition);
                notifyItemChanged(selectPosition);
            }
        });
        if (selectPosition == helper.getLayoutPosition() - getHeaderLayoutCount()) {
            helper.setBackgroundColor(R.id.layout_bg, mContext.getResources().getColor(R.color.home_bg));
            helper.setVisible(R.id.indicator, true);
            helper.setTextColor(R.id.tv_sort_name, mContext.getResources().getColor(R.color.colorAccent));
        } else {
            helper.setBackgroundColor(R.id.layout_bg, mContext.getResources().getColor(R.color.pure_white));
            helper.setVisible(R.id.indicator, false);
            helper.setTextColor(R.id.tv_sort_name, mContext.getResources().getColor(R.color.colorTextG3));
        }
        helper.setText(R.id.tv_sort_name, item.getClass_name());
    }



}
