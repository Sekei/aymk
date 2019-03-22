package com.live.tv.mvp.adapter.shop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AfterSaleOrderBean;
import com.live.tv.mvp.adapter.mine.GoodOrderGoodAdapter;

import java.util.List;

public class AfterSalesOrderListAdapter extends BaseQuickAdapter<AfterSaleOrderBean, BaseViewHolder> {
    public AfterSalesOrderListAdapter(List<AfterSaleOrderBean> data) {
        super(R.layout.item_after_order_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AfterSaleOrderBean item) {

        Glide.with(mContext).load(Constants.BASE_URL + item.getMerchants_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.img_merchants));
        helper.setText(R.id.merchants_name, item.getMerchants_name());

        String state=item.getRefund_state();

        switch (state){
            case "wait_review":
                helper.setText(R.id.tv_order_state,"审核中");
                break;

            case "accept":
                helper.setText(R.id.tv_order_state,"退款中");
                break;

            case "refuse":
                helper.setText(R.id.tv_order_state,"未通过");
                break;

            case "end":
                helper.setText(R.id.tv_order_state,"已退款");
                break;

            case "cancel":
                helper.setText(R.id.tv_order_state,"已取消");
                break;


        }





        RecyclerView recyclerView =helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        GoodOrderGoodAdapter goodOrderGoodAdapter = new GoodOrderGoodAdapter(item.getOrderBean().getOrderGoodsBeans());
        recyclerView.setAdapter(goodOrderGoodAdapter);



    }
}
