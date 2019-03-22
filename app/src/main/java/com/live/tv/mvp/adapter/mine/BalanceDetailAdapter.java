package com.live.tv.mvp.adapter.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.BalanceBean;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * 我的收益列表
 */

public class BalanceDetailAdapter extends BaseQuickAdapter<BalanceBean, BaseViewHolder> {
    public BalanceDetailAdapter(List<BalanceBean> data) {
        super(R.layout.item_my_profit, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BalanceBean item) {
        final  TextView tv_price=helper.getView(R.id.tv_price);
        final ImageView imageView=helper.getView(R.id.imageView8);

        helper.setText(R.id.tv_cancel,item.getOrder_date());
        tv_price.setText(item.getOrder_actual_price()+"元");



      final   RecyclerView recyclerView =helper.getView(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        BalanceDetail2Adapter myProfitDetailAdapter = new BalanceDetail2Adapter(new ArrayList<BalanceBean>());
        recyclerView.setAdapter(myProfitDetailAdapter);
        myProfitDetailAdapter.setNewData(item.getOrderBeans());

       final TextView  tv2 =helper.getView(R.id.tv2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (recyclerView.getVisibility()== View.VISIBLE)  {
                 recyclerView.setVisibility(View.GONE);
                 tv2.setVisibility(View.GONE);
                 Glide.with(mContext).load(R.drawable.downarro).placeholder(R.drawable.downarro)
                         .error(R.drawable.downarro)
                         .into(imageView);

             }else {
                 recyclerView.setVisibility(View.VISIBLE);
                 tv2.setVisibility(View.VISIBLE);
                 Glide.with(mContext).load(R.drawable.uparro).placeholder(R.drawable.uparro)
                         .error(R.drawable.uparro)
                         .into(imageView);
             }


            }
        });



    }
}
