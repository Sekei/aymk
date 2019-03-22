package com.live.tv.mvp.adapter.mine;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.BalanceBean;

import java.util.List;


/**
 *
 * 我的收益列表
 */

public class BalanceDetail2Adapter extends BaseQuickAdapter<BalanceBean, BaseViewHolder> {
    public BalanceDetail2Adapter(List<BalanceBean> data) {
        super(R.layout.item_my_profit_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BalanceBean item) {

        helper.setText(R.id.tv_cancel,item.getOrder_date());
        helper.setText(R.id.tv_title,item.getMember_nick_name());

        TextView tv_price=helper.getView(R.id.tv_price);




        if ("recharge".equals(item.getOrder_type())){
            tv_price.setText("+"+item.getOrder_actual_price());
            tv_price.setTextColor(mContext.getResources().getColor(R.color.colorAccent));

        }else {

            tv_price.setText("-"+item.getOrder_actual_price());
            tv_price.setTextColor(mContext.getResources().getColor(R.color.tx_2));
        }

    }
}
