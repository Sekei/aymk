package com.live.tv.mvp.adapter.shop;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ClassBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/11
 */

public class ShopClassAdapter extends RecyclerArrayAdapter<ClassBean> {
    private List<ClassBean> classBeanList;
    public ShopClassAdapter(Context context, List<ClassBean> objects) {
        super(context, objects);
        classBeanList=objects;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<ClassBean> {
        private ImageView img;
        private TextView tittle;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_shop_class);
            img= $(R.id.img);
            tittle= $(R.id.tittle);
        }

        @Override
        public void setData(ClassBean data) {
            super.setData(data);


            int pos=getDataPosition()+1;

            if (pos== getAllData().size()){

                Glide.with(getContext()).load(R.drawable.shop_icon_calssify_1x)
                        .placeholder(R.drawable.shop_icon_calssify_1x)
                        .error(R.drawable.shop_icon_calssify).into(img);

                tittle.setText("全部");


            }else {
                Glide.with(getContext()).load(Constants.BASE_URL + data.getClass_img())
                        .placeholder(R.drawable.shop_icon_calssify)
                        .error(R.drawable.shop_icon_calssify).into(img);

                tittle.setText(data.getClass_name());

            }


        }
    }
}
