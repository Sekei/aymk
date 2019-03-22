package com.live.tv.mvp.adapter.shop;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ClassBean;

import java.util.List;


public class ClassTwoAdapter extends BaseQuickAdapter<ClassBean, BaseViewHolder> {
    public ClassTwoAdapter(List<ClassBean> data) {
        super(R.layout.item_class_two, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassBean item) {

        helper.setText(R.id.tv_class_name,item.getClass_name());



    }
}
