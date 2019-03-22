package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CollectionBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class CollectionServiceAdapter extends RecyclerArrayAdapter<CollectionBean> {
    public CollectionServiceAdapter(Context context, List<CollectionBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<CollectionBean> {
        private ImageView img;
        private TextView tv_name, tv_department, tv_consult;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_collection_service);
            img = $(R.id.img);
            tv_name = $(R.id.tv_name);
            tv_department = $(R.id.tv_department);
            tv_consult = $(R.id.tv_consult);


            
        }

        @Override
        public void setData(final CollectionBean data) {
            super.setData(data);
            //在这里设置数据

            tv_consult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.onItemChildClick(data);
                }
            });
        }
    }


    private onclick onclick;
    public interface  onclick{

        void  onItemChildClick(CollectionBean collectionBean);
    }

    public CollectionServiceAdapter.onclick getOnclick() {
        return onclick;
    }

    public void setOnclick(CollectionServiceAdapter.onclick onclick) {
        this.onclick = onclick;
    }
}