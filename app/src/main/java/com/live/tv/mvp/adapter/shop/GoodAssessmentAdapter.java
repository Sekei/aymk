package com.live.tv.mvp.adapter.shop;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AssessmentBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public class GoodAssessmentAdapter extends RecyclerArrayAdapter<AssessmentBean> {

    public GoodAssessmentAdapter(Context context, List<AssessmentBean> objects) {
        super(context, objects);

    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<AssessmentBean> {

        private TextView name, time, content;
        private RatingBar comments_star;
        private RecyclerView recyclerView_img;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_good_assessment);
            name = $(R.id.name);
            time = $(R.id.time);
            comments_star = $(R.id.comments_star);
            content = $(R.id.content);
            recyclerView_img = $(R.id.recyclerView_img);

        }

        @Override
        public void setData(AssessmentBean data) {
            super.setData(data);
            name.setText(data.getMember_nick_name().substring(0,1)+"**");
            time.setText(data.getCreate_time());
            content.setText(data.getAssessment_desc());
            float i = Float.parseFloat(data.getAssessment_star1());
            comments_star.setRating(i);


            recyclerView_img.setLayoutManager(new GridLayoutManager(getContext(),3));
            AssessmentImgAdapter  mAdapter = new AssessmentImgAdapter(getContext(),data.getAssessmentImgBeans());
            recyclerView_img.setAdapter(mAdapter);


        }
    }
}