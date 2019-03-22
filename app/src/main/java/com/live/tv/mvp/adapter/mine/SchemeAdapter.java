package com.live.tv.mvp.adapter.mine;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthRecordDetailBean;

import java.util.List;


/**
 *健康管理设置
 */

public class SchemeAdapter extends BaseQuickAdapter<HealthRecordDetailBean.HealthPlanBeansBean, BaseViewHolder> {
    public SchemeAdapter(List<HealthRecordDetailBean.HealthPlanBeansBean> data) {
        super(R.layout.item_scheme, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthRecordDetailBean.HealthPlanBeansBean item) {

        helper.setText(R.id.tv_title,item.getPlan_name()+"");
        helper.setText(R.id.tv_cancel,"完成度"+item.getDone_num()+"/"+item.getSum_num());



    }
}
