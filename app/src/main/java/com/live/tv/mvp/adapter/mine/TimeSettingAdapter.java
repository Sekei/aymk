package com.live.tv.mvp.adapter.mine;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.FetureDateBeans;

import java.util.List;


/**
 *健康管理设置
 */

public class TimeSettingAdapter extends BaseQuickAdapter<FetureDateBeans, BaseViewHolder> {
    public TimeSettingAdapter(List<FetureDateBeans> data) {
        super(R.layout.item_time, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FetureDateBeans item) {

        helper.setText(R.id.content,item.getWeekday());



    }
}
