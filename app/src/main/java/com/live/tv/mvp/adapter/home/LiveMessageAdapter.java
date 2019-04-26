package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.bean.MemberMsgsBean;
import com.ysjk.health.iemk.R;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/27
 */

public class LiveMessageAdapter extends RecyclerArrayAdapter<MemberMsgsBean> {


    public LiveMessageAdapter(Context context, List<MemberMsgsBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    public class ViewHolder extends BaseViewHolder<MemberMsgsBean> {

        private TextView name, content, time;


        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_live_message);
            name = $(R.id.name);
            content = $(R.id.content);
            time = $(R.id.tv_time);
        }

        @Override
        public void setData(final MemberMsgsBean person) {
            Log.i("ViewHolder", "position" + getDataPosition());
            name.setText("标题："+person.getMsg_title());
            time.setText("发送时间："+person.getCreate_time());
            content.setText(person.getMsg_desc());
        }
    }


}
