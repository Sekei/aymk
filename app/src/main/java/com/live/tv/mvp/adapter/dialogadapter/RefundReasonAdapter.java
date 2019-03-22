package com.live.tv.mvp.adapter.dialogadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.RefundsReasons;

import java.util.List;


/**
 *病历分类
 */

public class RefundReasonAdapter extends BaseQuickAdapter<RefundsReasons, BaseViewHolder> {


    public RefundReasonAdapter(List<RefundsReasons> data) {
        super(R.layout.item_refund_reason, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RefundsReasons item) {

        helper.setText(R.id.tv_item,item.getReason_name());

    }
}
