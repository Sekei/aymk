package com.live.tv.mvp.adapter.shop;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bilibili.boxing_impl.view.SpacesItemDecoration;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.OrderGoodsBeans;
import java.util.List;

/**
 * Created by mac1010 on 2018/5/11.
 */

public class CommentsAdapter extends RecyclerArrayAdapter<OrderGoodsBeans> {

    public CommentsAdapter(Context context, List<OrderGoodsBeans> objects) {
        super(context, objects);
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(parent);
    }

    private class MyViewHolder extends BaseViewHolder<OrderGoodsBeans> {
        private ImageView img;
        private EditText et_goods_content;
        private RatingBar ratingbar;
        private RecyclerView mediaRecycleView;

        public MyViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_order_assessment);
            img = $(R.id.img_good);
            et_goods_content = $(R.id.ed_content);
            ratingbar = $(R.id.comments_star);
            mediaRecycleView = $(R.id.recyclerView_img);
            mediaRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            mediaRecycleView.addItemDecoration(new SpacesItemDecoration(8));

        }

        @Override
        public void setData(final OrderGoodsBeans data) {
            super.setData(data);

            Glide.with(getContext())
                    .load(Constants.BASE_URL+data.getGoods_img())
                    .placeholder(R.drawable.pic_defaults)
//                    .bitmapTransform(new CropCircleTransformation(getContext()))
                    .into(img);


            MediaResultAdapter adapter = new MediaResultAdapter(getContext(),data.getCommentsImgs());
            mediaRecycleView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (onClickListener!=null){
                        onClickListener.ClickImage(getDataPosition(),position);
                    }
                }
            });

            et_goods_content.setHint("请输入对该商品的评价");

            et_goods_content.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    data.setContent(s.toString());
                }
            });


            ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    data.setGoods_mark(rating);
                }
            });
        }
    }


    public void setOnClickListener(CommentsAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private onClickListener onClickListener;
    public interface onClickListener{
        void ClickImage(int prente, int img);
    }


}
