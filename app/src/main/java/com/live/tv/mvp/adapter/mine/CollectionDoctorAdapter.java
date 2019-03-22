package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CollectionBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class CollectionDoctorAdapter extends RecyclerArrayAdapter<CollectionBean> {
    public CollectionDoctorAdapter(Context context, List<CollectionBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<CollectionBean> {
        private ImageView img;
        private TextView tv_name, tv_title, tv_department, tv_hospital, tv_consult;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_collection_doctor);
            img = $(R.id.img);
            tv_name = $(R.id.tv_name);
            tv_title = $(R.id.tv_title);
            tv_department = $(R.id.tv_department);
            tv_hospital = $(R.id.tv_hospital);
            tv_consult = $(R.id.tv_consult);


        }

        @Override
        public void setData(final CollectionBean data) {
            super.setData(data);
            //在这里设置数据
            Glide.with(getContext()).load(Constants.BASE_URL+data.getDoctorBean().getDoctor_head_image())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults)
                    .centerCrop()
                    .into(img);

            tv_name.setText(data.getDoctorBean().getDoctor_name());
            tv_title.setText(data.getDoctorBean().getDoctor_title());
            tv_department.setText(data.getDoctorBean().getDoctor_department());
            tv_hospital.setText(data.getDoctorBean().getDoctor_hospital());
            tv_consult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onclick.onItemChildClick(data);
                }
            });

        }
    }

    public CollectionDoctorAdapter.onclick getOnclick() {
        return onclick;
    }

    public void setOnclick(CollectionDoctorAdapter.onclick onclick) {
        this.onclick = onclick;
    }

    private onclick onclick;
    public interface  onclick{

        void  onItemChildClick(CollectionBean collectionBean);
    }
}