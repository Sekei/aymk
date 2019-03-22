package com.live.tv.mvp.activity.live.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.hyphenate.chat.EMMessage;
import com.ysjk.health.iemk.R;

import java.util.List;



public class RoomMessageAdapter extends BaseRecyclerViewAdapter<EMMessage> {

    public RoomMessageAdapter(Context mContext, List<EMMessage> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public BaseViewHolder getItemHolder(Context mContext, OnItemClickRecyclerListener listener, ViewGroup parent) {
        return new RoomMessageItemHolder(mContext, listener, LayoutInflater.from(mContext).
                inflate(R.layout.holder_item_room_msg, parent, false));
    }
}
