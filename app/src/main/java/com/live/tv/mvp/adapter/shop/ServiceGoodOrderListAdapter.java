package com.live.tv.mvp.adapter.shop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.OrderBean;

import java.util.List;

public class ServiceGoodOrderListAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
    public ServiceGoodOrderListAdapter(List<OrderBean> data) {
        super(R.layout.item_service_order_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {

        String state_str = item.getOrder_state();
        TextView tv_cancel = helper.getView(R.id.tv_cancels);
        TextView tv_buy_again = helper.getView(R.id.tv_buy_again);
        TextView tv_pay = helper.getView(R.id.tv_pay);
        TextView tv_one_list = helper.getView(R.id.tv_one_list);
        TextView tv_see = helper.getView(R.id.tv_see);
        TextView tv_assessment = helper.getView(R.id.tv_assessment);
        switch (state_str) {
            case "wait_pay":
                tv_cancel.setVisibility(View.VISIBLE);
                tv_pay.setVisibility(View.VISIBLE);
                tv_see.setVisibility(View.GONE);
                tv_buy_again.setVisibility(View.GONE);
                tv_one_list.setVisibility(View.GONE);
                tv_assessment.setVisibility(View.GONE);

                break;

            case "wait_use":
                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_see.setVisibility(View.VISIBLE);
                tv_buy_again.setVisibility(View.GONE);
                tv_one_list.setVisibility(View.GONE);
                tv_assessment.setVisibility(View.GONE);

                break;

            case "wait_assessment":
                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_see.setVisibility(View.GONE);
                tv_buy_again.setVisibility(View.GONE);
                tv_one_list.setVisibility(View.VISIBLE);
                tv_assessment.setVisibility(View.VISIBLE);

                break;

            case "end":
                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_see.setVisibility(View.GONE);
                tv_buy_again.setVisibility(View.GONE);
                tv_one_list.setVisibility(View.VISIBLE);
                tv_assessment.setVisibility(View.GONE);

                break;

            case "cancel":
                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_see.setVisibility(View.GONE);
                tv_buy_again.setVisibility(View.VISIBLE);
                tv_one_list.setVisibility(View.GONE);
                tv_assessment.setVisibility(View.GONE);

                break;


        }


        helper.addOnClickListener(R.id.tv_cancels);
        helper.addOnClickListener(R.id.tv_pay);
        helper.addOnClickListener(R.id.tv_buy_again);
        helper.addOnClickListener(R.id.tv_one_list);
        helper.addOnClickListener(R.id.tv_assessment);
        helper.addOnClickListener(R.id.tv_see);


        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ServiceGoodOrderGoodAdapter goodOrderGoodAdapter = new ServiceGoodOrderGoodAdapter(item.getOrderGoodsBeans(),item.getOrder_state_show());
        recyclerView.setAdapter(goodOrderGoodAdapter);


    }
}
