package com.live.tv.mvp.adapter.shop;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;

import java.util.List;

/**
 * Created by mac1010 on 2018/5/11.
 */

public class MediaResultAdapter extends RecyclerArrayAdapter<String> {

    public MediaResultAdapter(Context context, List<String> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assessment_doctor_img, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        view.setMinimumHeight(height);
        return new MyViewHolder(view);
    }
    private class MyViewHolder extends BaseViewHolder<String>{
        ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = $(R.id.img);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        @Override
        public void setData(String data) {
            super.setData(data);

            if (TextUtils.isEmpty(data)){
                Glide.with(getContext()).load(R.drawable.consult_icon_camerax).into(mImageView);
            }else {
                Glide.with(getContext()).load(data).into(mImageView);
            }
        }
    }
}
