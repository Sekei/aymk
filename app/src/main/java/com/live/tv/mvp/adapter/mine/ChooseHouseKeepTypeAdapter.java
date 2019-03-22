package com.live.tv.mvp.adapter.mine;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HouseKeepTypeBean;

import java.util.List;


/**
 *医生职称
 */

public class ChooseHouseKeepTypeAdapter extends BaseQuickAdapter<HouseKeepTypeBean, BaseViewHolder> {


    public ChooseHouseKeepTypeAdapter(List<HouseKeepTypeBean> data) {
        super(R.layout.item_doctor_title, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, HouseKeepTypeBean item) {

        helper.setText(R.id.tv_title,item.getService_type());


    }
}
