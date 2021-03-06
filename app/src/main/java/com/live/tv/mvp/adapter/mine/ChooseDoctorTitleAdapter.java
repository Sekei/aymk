package com.live.tv.mvp.adapter.mine;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorTitleBean;

import java.util.List;


/**
 *医生职称
 */

public class ChooseDoctorTitleAdapter extends BaseQuickAdapter<DoctorTitleBean, BaseViewHolder> {


    public ChooseDoctorTitleAdapter(List<DoctorTitleBean> data) {
        super(R.layout.item_doctor_title, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, DoctorTitleBean item) {

        helper.setText(R.id.tv_title,item.getJob_level());


    }
}
