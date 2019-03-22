package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.utils.GlideCircleTransform;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CollectionBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/16
 */

public class FocusAdapter extends RecyclerArrayAdapter<CollectionBean> {
    public FocusAdapter(Context context, List<CollectionBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<CollectionBean> {
        private ImageView img;
        private TextView name;
        private TextView tv_cancel;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_focus);
            img = $(R.id.img);
            name = $(R.id.name);
            tv_cancel = $(R.id.tv_cancel);


        }

        @Override
        public void setData(final CollectionBean data) {
            super.setData(data);

            Glide.with(getContext()).load(Constants.BASE_URL + data.getDoctorBean().getDoctor_head_image())
                    .transform(new GlideCircleTransform(getContext()))
                    .error(R.drawable.pic_defaults)
                    .into(img);
            name.setText(data.getDoctorBean().getDoctor_name());

            tv_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onclick.onItemChildClick(data);
                }
            });

        }
    }

    public FocusAdapter.onclick getOnclick() {
        return onclick;
    }

    public void setOnclick(FocusAdapter.onclick onclick) {
        this.onclick = onclick;
    }

    private onclick onclick;
    public interface  onclick{

        void  onItemChildClick(CollectionBean collectionBean);
    }
}