package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.MemberMsgsBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/27
 */

public class SystemMessageAdapter extends RecyclerArrayAdapter<MemberMsgsBean> {


    public SystemMessageAdapter(Context context, List<MemberMsgsBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    public class ViewHolder extends BaseViewHolder<MemberMsgsBean> {

        private TextView content,time,name;



        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_system_message);
            content = $(R.id.content);
            time = $(R.id.time);
            name=$(R.id.name);

        }

        @Override
        public void setData(final MemberMsgsBean person) {
            Log.i("ViewHolder", "position" + getDataPosition());
            content.setText(person.getMsg_desc());
            time.setText(person.getCreate_time());
            name.setText(person.getMsg_title());

        }
    }


}
