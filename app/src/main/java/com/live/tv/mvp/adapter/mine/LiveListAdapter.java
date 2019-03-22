package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.LiveBean;
import com.live.tv.util.IMTimeUtils;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class LiveListAdapter extends RecyclerArrayAdapter<LiveBean> {
    public LiveListAdapter(Context context, List<LiveBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<LiveBean> {
        private ImageView img;
        private TextView tv_time;
        private TextView tv_title;
        private TextView tv_num;
        private TextView tv_state;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_live_list);
            img = $(R.id.img);
            tv_time = $(R.id.tv_time);
            tv_title = $(R.id.tv_title);
            tv_num = $(R.id.tv_num);
            tv_state = $(R.id.tv_state);

        }

        @Override
        public void setData(LiveBean data) {
            super.setData(data);
            long timeL = IMTimeUtils.StringTimeToMillisecond(data.getCreate_time());
            String timeS = IMTimeUtils.convertTimeToFormat(timeL);
            tv_time.setText(timeS);
            Glide.with(getContext()).load(Constants.BASE_URL+ data.getLive_img())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults)
                    .into(img);
            tv_title.setText(data.getLive_title());
            tv_num.setText(data.getViewing_num());
            if (data.getLive_state().equals("1")){
                tv_state.setText("直播");
            }else{
                tv_state.setText("回放");
            }

        }
    }
}