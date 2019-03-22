package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HeartBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/11
 */

public class HeartListAdapter extends RecyclerArrayAdapter<HeartBean> {
    public HeartListAdapter(Context context, List<HeartBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<HeartBean> {
        private TextView name,price,content;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_heart);
            name = $(R.id.name);
            price = $(R.id.price);
            content = $(R.id.content);
        }

        @Override
        public void setData(HeartBean data) {
            super.setData(data);
           String str=data.getMember_nick_name();

            if (str!=null&&!str.equals("")&&str.length()>1){

                str=str.substring(0,1);
            }

            name.setText(str+"**");
            price.setText(data.getAmount()+"元");
            content.setText(data.getRecord_desc());
        }
    }
}