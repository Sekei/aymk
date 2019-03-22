package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.RelationsBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/24
 */

public class SelectRelationAdapter extends RecyclerArrayAdapter<RelationsBean> {
    public SelectRelationAdapter(Context context, List<RelationsBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }
    public class ViewHolder extends BaseViewHolder<RelationsBean> {
        private TextView name;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_relation);
            name = $(R.id.name);
        }

        @Override
        public void setData(RelationsBean data) {
            super.setData(data);
            name.setText(data.getRelation_name());
        }
    }
}