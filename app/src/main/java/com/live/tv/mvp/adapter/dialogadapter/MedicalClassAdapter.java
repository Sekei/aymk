package com.live.tv.mvp.adapter.dialogadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.MedicalClassBean;

import java.util.List;


/**
 *病历分类
 */

public class MedicalClassAdapter extends BaseQuickAdapter<MedicalClassBean, BaseViewHolder> {


    public MedicalClassAdapter(List<MedicalClassBean> data) {
        super(R.layout.item_doctor_title, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MedicalClassBean item) {

        helper.setText(R.id.tv_title,item.getMedical_class_title());

    }
}
