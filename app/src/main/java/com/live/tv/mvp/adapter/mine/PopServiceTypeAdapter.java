package com.live.tv.mvp.adapter.mine;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;

import java.util.List;


/**
 * Created by wql on 2017/3/29.
 */

public class PopServiceTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PopServiceTypeAdapter(List<String> data) {
        super(R.layout.pop_service_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title,item);


    }
}
