package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/11
 */

public class WeekAdapter extends RecyclerArrayAdapter<String> {
    public WeekAdapter(Context context, List<String> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    int position=-1;

    public void setPosition(int position) {
        this.position = position;
    }

    public class ViewHolder extends BaseViewHolder<String> {
        private TextView one;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_week);
            one = $(R.id.one);
        }

        @Override
        public void setData(String data) {
            super.setData(data);
            one.setText(data);
          if (position==getDataPosition()){
                one.setTextColor(getContext().getResources().getColor(R.color.pure_white));
                one.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
            }else {
                one.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
                one.setBackgroundColor(getContext().getResources().getColor(R.color.pure_white));
            }

        }
    }
}
