package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HeartMoneyBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/11
 */

public class HeartMoneyAdapter extends RecyclerArrayAdapter<HeartMoneyBean> {
    public HeartMoneyAdapter(Context context, List<HeartMoneyBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }
    private int position=0;
    public void setPosition(int position) {
        this.position = position;
    }
    public class ViewHolder extends BaseViewHolder<HeartMoneyBean> {
        private TextView peice, content;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_heart_money);
            peice = $(R.id.peice);
            content = $(R.id.content);
        }
        @Override
        public void setData(HeartMoneyBean data) {
            super.setData(data);

            if (getDataPosition() != 4) {
                peice.setText(data.getMoney() + data.getUnit());
                content.setText(data.getContent());
            } else {
                if (TextUtils.isEmpty(data.getMoney())){
                    peice.setText("更多\n金额");
                }else
                {
                    peice.setText(data.getMoney() + data.getUnit()+"\n修改");
                }
                content.setText(data.getContent());

            }
            if (position==getDataPosition()){
                peice.setTextColor(getContext().getResources().getColor(R.color.pure_white));
                peice.setBackgroundResource(R.drawable.setbar_fh_two);
                content.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            }else{
                peice.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                peice.setBackgroundResource(R.drawable.setbar_fh);
                content.setTextColor(getContext().getResources().getColor(R.color.colorTextG9));
            }

        }
    }
}