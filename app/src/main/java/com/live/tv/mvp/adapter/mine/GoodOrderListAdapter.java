package com.live.tv.mvp.adapter.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.OrderBean;

import java.util.List;

public class GoodOrderListAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
    public GoodOrderListAdapter(List<OrderBean> data) {
        super(R.layout.item_order_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {

        Glide.with(mContext).load(Constants.BASE_URL + item.getMerchants_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.img_merchants));
        helper.setText(R.id.merchants_name, item.getMerchants_name());
        helper.setText(R.id.tv_sum_price, "¥"+item.getOrder_total_price());
        helper.setText(R.id.tv_order_state, item.getOrder_state_show());
        String state_str = item.getOrder_state();
        TextView tv_cancel =helper.getView(R.id.tv_cancels);
        TextView tv_delete =helper.getView(R.id.tv_delete);
        TextView tv_pay =helper.getView(R.id.tv_pay);
        TextView tv_logistics =helper.getView(R.id.tv_logistics);
        TextView tv_confirm =helper.getView(R.id.tv_confirm);
        int  num=0;

        if (item.getOrderGoodsBeans()!=null&&item.getOrderGoodsBeans().size()>0) {

            for (int i = 0; i <item.getOrderGoodsBeans().size(); i++) {

                 int good_num= Integer.parseInt(item.getOrderGoodsBeans().get(i).getGoods_num());

                num+=good_num;
            }
        }


        helper.setText(R.id.tv_num,"共"+num+"件商品");

        TextView tv_assessment =helper.getView(R.id.tv_assessment);
        switch (state_str) {
            case "wait_pay":
                tv_cancel.setVisibility(View.VISIBLE);
                tv_pay.setVisibility(View.VISIBLE);
                tv_delete.setVisibility(View.GONE);
                tv_confirm.setVisibility(View.GONE);
                tv_assessment.setVisibility(View.GONE);
                tv_logistics.setVisibility(View.GONE);
                break;

            case "wait_send":
                tv_cancel.setVisibility(View.VISIBLE);
                tv_pay.setVisibility(View.GONE);
                tv_delete.setVisibility(View.GONE);
                tv_confirm.setVisibility(View.GONE);
                tv_assessment.setVisibility(View.GONE);
                tv_logistics.setVisibility(View.GONE);

                break;

            case "wait_receive":
                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_delete.setVisibility(View.GONE);
                tv_confirm.setVisibility(View.VISIBLE);
                tv_assessment.setVisibility(View.GONE);
                tv_logistics.setVisibility(View.VISIBLE);


                break;
            case "wait_assessment":
                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_delete.setVisibility(View.GONE);
                tv_confirm.setVisibility(View.GONE);
                tv_assessment.setVisibility(View.VISIBLE);
                tv_logistics.setVisibility(View.VISIBLE);


                break;
            case "cancel":
                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_delete.setVisibility(View.VISIBLE);
                tv_confirm.setVisibility(View.GONE);
                tv_assessment.setVisibility(View.GONE);
                tv_logistics.setVisibility(View.GONE);
                break;


            case "end":

                tv_cancel.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_delete.setVisibility(View.VISIBLE);
                tv_confirm.setVisibility(View.GONE);
                tv_assessment.setVisibility(View.GONE);
                tv_logistics.setVisibility(View.VISIBLE);

                break;


        }


        helper.addOnClickListener(R.id.tv_cancels);
        helper.addOnClickListener(R.id.tv_pay);
        helper.addOnClickListener(R.id.tv_delete);
        helper.addOnClickListener(R.id.tv_confirm);
        helper.addOnClickListener(R.id.tv_assessment);
        helper.addOnClickListener(R.id.tv_logistics);


        RecyclerView recyclerView =helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        GoodOrderGoodAdapter goodOrderGoodAdapter = new GoodOrderGoodAdapter(item.getOrderGoodsBeans());
        recyclerView.setAdapter(goodOrderGoodAdapter);
        goodOrderGoodAdapter.setOnItemClickListener(null);




    }
}
