package com.live.tv.mvp.adapter.communicate;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ServiceCommentBean;
import com.live.tv.mvp.adapter.mine.ServiceCopeItemAdapter;
import com.live.tv.mvp.adapter.mine.ServicePingLunItemAdapter;

/**
 * @author Created by stone
 * @since 2018/1/18
 */

public class CommunityAppraiseAdapter extends RecyclerArrayAdapter<ServiceCommentBean> {
    private ServicePingLunItemAdapter adapter;
    private Context mContext;
    public CommunityAppraiseAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<ServiceCommentBean> {
        private TextView name,time,content;
        private RatingBar mRatingBar;
        private RecyclerView recyclerView;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_appraise_commounity);
            mRatingBar=$(R.id.ratingBar);
            name=$(R.id.tv_name);
            time=$(R.id.tv_time);
            content=$(R.id.tv_content);
            recyclerView=$(R.id.recycler_view);
        }

        @Override
        public void setData(ServiceCommentBean data) {
            super.setData(data);
            adapter=new ServicePingLunItemAdapter(mContext,data.getAssessmentImgBeans());

            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) {
                @Override
                public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                    return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                }
            };
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);
            name.setText(data.getMember_nick_name());
            time.setText(data.getCreate_time());
            content.setText(data.getAssessment_desc());
            if (data.getAssessment_star1()!=null&&!data.getAssessment_star1().equals("")){
                mRatingBar.setRating(Float.parseFloat(data.getAssessment_star1()));

            }

        }
    }
}