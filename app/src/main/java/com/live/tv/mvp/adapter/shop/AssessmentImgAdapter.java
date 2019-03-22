package com.live.tv.mvp.adapter.shop;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AssessmentImgBeans;
import com.live.tv.mvp.fragment.image.ShowPictureActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public class AssessmentImgAdapter extends RecyclerArrayAdapter<AssessmentImgBeans> {

    private List<AssessmentImgBeans> assessmentImgBeansList;
    public AssessmentImgAdapter(Context context, List<AssessmentImgBeans> objects) {
        super(context, objects);
        assessmentImgBeansList=objects;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<AssessmentImgBeans> {

        private ImageView img;


        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_img);
            img = $(R.id.img);


        }

        @Override
        public void setData(AssessmentImgBeans data) {
            super.setData(data);

            Glide.with(getContext()).load(Constants.BASE_URL+data.getAssessment_img())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults).centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(img);


            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    List<String> stringList = new ArrayList<String>();
                    for (AssessmentImgBeans findResourceBean:assessmentImgBeansList){

                        stringList.add(Constants.BASE_URL+findResourceBean.getAssessment_img());
                    }

                    Intent intent = new Intent(getContext(), ShowPictureActivity.class);
                    intent.putExtra("imagelist", (Serializable) stringList);
                    intent.putExtra("position",getPosition());
                    getContext().startActivity(intent);



                }
            });
        }
    }
}