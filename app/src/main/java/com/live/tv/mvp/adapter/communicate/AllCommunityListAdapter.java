package com.live.tv.mvp.adapter.communicate;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CommAllBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/18
 */

public class AllCommunityListAdapter  extends RecyclerArrayAdapter<CommAllBean> {
    public AllCommunityListAdapter(Context context, List<CommAllBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<CommAllBean> {
        private ImageView img;
        private TextView name, type, department, hospital, indications, price, peopleNum;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_all_commounity);
            img = $(R.id.img);
            name = $(R.id.name);
           /* type = $(R.id.type);
            department = $(R.id.department);
            hospital = $(R.id.hospital);
            indications = $(R.id.indications);
            price = $(R.id.price);*/
            peopleNum = $(R.id.num);

        }

        @Override
        public void setData(CommAllBean data) {
            super.setData(data);

            Glide.with(getContext()).
                    load(Constants.BASE_URL + data.getPlate_image())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .thumbnail(0.1f)
                    .into(img);
            name.setText(data.getPlate_name());
            peopleNum.setText("话题"+data.getTopic_num());
          /*  type.setText(data.getDoctor_title());
            //department.setText(data.getDoctor_name());
            hospital.setText(data.getDoctor_hospital());
            //indications.setText(data.getDoctor_name());
            //price.setText(data.getDoctor_name());
            //peopleNum.setText(data.getDoctor_name());*/
        }
    }
}