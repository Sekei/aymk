package com.live.tv.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/12
 */

public class ImageAdapter extends RecyclerArrayAdapter<String> {


    public ImageAdapter(Context context, List<String> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_image_layout);
    }

    public class MyImageHolder extends BaseViewHolder<String> {

        private ImageView img;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            img = $(R.id.item_image);
        }

        @Override
        public void setData(final String data) {
            Glide.with(getContext())
                    .load(data)
                    .error(R.drawable.menu_comment_camera)
                    .into(img);

        }
    }

}
