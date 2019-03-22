package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
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

public class CollectionMerchantsAdapter extends RecyclerArrayAdapter<CollectionBean> {
    public CollectionMerchantsAdapter(Context context, List<CollectionBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<CollectionBean> {
        private ImageView img;
        private TextView tv_name, tv_department, tv_consult;
        private RatingBar  comments_star;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_collection_shop);
            img = $(R.id.img);
            tv_name = $(R.id.tv_name);
            tv_department = $(R.id.tv_department);
            tv_consult = $(R.id.tv_consult);
            comments_star = $(R.id.comments_star);

        }

        @Override
        public void setData(final CollectionBean data) {
            super.setData(data);
            //在这里设置数据
            //在这里设置数据
            Glide.with(getContext()).load(Constants.BASE_URL+data.getMerchantsBean().getMerchants_img())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults)
                    .centerCrop()
                    .into(img);

            tv_name.setText(data.getMerchantsBean().getMerchants_name());
            tv_department.setText("商家信息");
            int  num = 0;
           try {
               num = Integer.parseInt(data.getMerchantsBean().getMerchants_star1());

           }catch (Exception e){
               e.printStackTrace();
           }
            comments_star.setNumStars(num);
            tv_consult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onclick.onItemChildClick(data);
                }
            });

        }
    }

    private onclick onclick;

    public CollectionMerchantsAdapter.onclick getOnclick() {
        return onclick;
    }

    public void setOnclick(CollectionMerchantsAdapter.onclick onclick) {
        this.onclick = onclick;
    }

    public interface  onclick{

        void  onItemChildClick(CollectionBean collectionBean);
    }
}