package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.MedicalListBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/15
 */

public class MedicalMonthListAdapter extends RecyclerArrayAdapter<MedicalListBean> {
    public MedicalMonthListAdapter(Context context, List<MedicalListBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<MedicalListBean> {

        private TextView time;
        private EasyRecyclerView easyRecyclerView;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_medical_list_month);
            time = $(R.id.time);
            easyRecyclerView= $(R.id.easyRecyclerView);
            easyRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        }

        @Override
        public void setData(final MedicalListBean data) {
            super.setData(data);
            time.setText(data.getCreate_time());

            CommonAdapter<MedicalListBean.MedicalImgBeansBean> commonAdapter = new CommonAdapter<MedicalListBean.MedicalImgBeansBean>(getContext(), R.layout.item_medical_list_img_two, data.getMedicalImgBeans()) {
                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, MedicalListBean.MedicalImgBeansBean bean, int position) {
                    Glide.with(getContext())
                            .load(Constants.BASE_URL + bean.getMedical_img())
                            .placeholder(R.color.pure_white)
                            .error(R.color.pure_white)
                            .into((ImageView) holder.getView(R.id.img));
                    holder.setText(R.id.content, data.getMedical_desc());
                }
            };
            easyRecyclerView.setAdapter(commonAdapter);
            commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    onClickListener.onOpen(data,position);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    private OnClickListener onClickListener;
    public interface OnClickListener {
        void onOpen(MedicalListBean medicalListBean,int pos);
    }


}
