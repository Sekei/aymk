package com.live.tv.mvp.adapter.mine;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ProfitBean;

import java.util.List;


/**
 *
 * 我的收益列表
 */

public class MyProfitDetailAdapter extends BaseQuickAdapter<ProfitBean, BaseViewHolder> {
    public MyProfitDetailAdapter(List<ProfitBean> data) {
        super(R.layout.item_my_profit_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProfitBean item) {


        helper.setText(R.id.tv_cancel,item.getProfit_date());
        helper.setText(R.id.tv_price,"+"+item.getAmount());
        helper.setText(R.id.tv_title,item.getTitle());




    }
}
