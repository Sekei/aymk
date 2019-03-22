package com.live.tv.mvp.adapter.mine;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthManagerBeans;

import java.util.List;


/**
 * Created by wql on 2017/3/29.
 */

public class TimeSetting2Adapter extends BaseQuickAdapter<HealthManagerBeans, BaseViewHolder> {
    public TimeSetting2Adapter(List<HealthManagerBeans> data) {
        super(R.layout.item_time2, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthManagerBeans item) {

        String price=item.getService_price();
        String title=item.getService_title();

        helper.setText(R.id.content,title+"("+price+"元)");

    }
}
