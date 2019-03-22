package com.live.tv.mvp.adapter.mine;

import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AddressBean;

import java.util.List;


/**
 * Created by wql on 2017/3/29.
 */

public class AddressAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {
    public AddressAdapter(List<AddressBean> data) {
        super(R.layout.item_address, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {
       helper.setText(R.id.tv_name,item.getAddress_name());
       helper.setText(R.id.tv_phone,item.getAddress_mobile());
       helper.setText(R.id.tv_address,item.getAddress_province()+item.getAddress_city()+item.getAddress_country()+item.getAddress_detailed());
        CheckBox checkBox=helper.getView(R.id.cb_default);
        if (item.getIs_default().equals("1")){
           // checkBox.setTextColor(mContext.getResources().getColor(R.color.home_price));
            checkBox.setText("默认地址");
            checkBox.setChecked(true);
        }else {


            //checkBox.setTextColor(mContext.getResources().getColor(R.color.home_text4));
            checkBox.setText("设为默认");
            checkBox.setChecked(false);
        }
       helper.addOnClickListener(R.id.cb_default);
       helper.addOnClickListener(R.id.tv_edit);
       helper.addOnClickListener(R.id.tv_del);

    }
}
