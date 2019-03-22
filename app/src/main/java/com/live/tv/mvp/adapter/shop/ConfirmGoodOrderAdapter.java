package com.live.tv.mvp.adapter.shop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CartBean;
import com.live.tv.mvp.fragment.shop.ConfirmGoodOrderFragment;

import java.util.List;


public class ConfirmGoodOrderAdapter extends BaseQuickAdapter<CartBean, BaseViewHolder> {
    private ConfirmGoodOrderFragment cartFragment;

    public ConfirmGoodOrderAdapter(List<CartBean> data, ConfirmGoodOrderFragment cartFra) {
        super(R.layout.item_confirm_order_merchants, data);
        cartFragment=cartFra;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final CartBean item) {

        Glide.with(mContext).load(Constants.BASE_URL+item.getMerchantsBean().getMerchants_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into((ImageView) helper.getView(R.id.img_merchants));
        helper.setText(R.id.merchants_name,item.getMerchantsBean().getMerchants_name());

        RecyclerView mRl_class=helper.getView(R.id.recyclerView);
        mRl_class.setLayoutManager(new LinearLayoutManager(mContext));
        final ConfirmGoodAdapter cartGoodAdapter= new ConfirmGoodAdapter(item.getShopCarBeans(),helper.getLayoutPosition());
        mRl_class.setAdapter(cartGoodAdapter);
        EditText ed_liuyan=helper.getView(R.id.ed_liuyan);
        TextView youfei=helper.getView(R.id.textView60);


            youfei.setText("¥"+item.getExpress_price());


        ed_liuyan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setOrder_remark(s.toString());
             String   liuyan_str=s.toString();
                Log.i("dfc", "afterTextChanged: "+liuyan_str);
            }
        });



    }
}
