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
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.PlateListBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class NewMyPostedAdapter extends RecyclerArrayAdapter<PlateListBean> {
    public NewMyPostedAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<PlateListBean> {
        private ImageView img;
        private TextView name, content, time;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_posted);
            img = $(R.id.ivAvatar);
            name = $(R.id.tv_title);
            content = $(R.id.tv_desc);
            time = $(R.id.tv_time);



        }

        @Override
        public void setData(PlateListBean data) {
            super.setData(data);

            Glide.with(getContext()).load(Constants.BASE_URL + data.getMember_head_image()).placeholder(R.drawable.home_doctor_ava)
                    .error(R.drawable.home_doctor_ava)
                    .into(img);
            name.setText(data.getPost_title());
            content.setText(data.getPost_desc());
            time.setText(data.getCreate_time());

        }
    }
}