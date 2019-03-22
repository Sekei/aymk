package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AllDoctorAssessesBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public class DoctorCommentAdapter extends RecyclerArrayAdapter<AllDoctorAssessesBean> {
    public DoctorCommentAdapter(Context context, List<AllDoctorAssessesBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<AllDoctorAssessesBean> {

        private TextView name, time, content;
        private RatingBar comments_star;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_doctor_comment);
            name = $(R.id.name);
            time = $(R.id.time);
            comments_star = $(R.id.comments_star);
            content = $(R.id.content);

        }

        @Override
        public void setData(AllDoctorAssessesBean data) {
            super.setData(data);
            name.setText(data.getMember_nick_name().substring(0,1)+"**");
            time.setText(data.getCreate_time());
            content.setText(data.getAssessment_desc());
            int i = Integer.parseInt(data.getAssessment_star());
            comments_star.setRating(i);
        }
    }
}