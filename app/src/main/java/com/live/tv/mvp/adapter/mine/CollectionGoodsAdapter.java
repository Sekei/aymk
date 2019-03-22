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

public class CollectionGoodsAdapter extends RecyclerArrayAdapter<CollectionBean> {
    public CollectionGoodsAdapter(Context context, List<CollectionBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<CollectionBean> {
        private ImageView img_merchants;
        private TextView tv_good_name, tv_nums, tv_price,tv_cancel;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_collection_goods);
            img_merchants = $(R.id.img_merchants);
            tv_good_name = $(R.id.tv_good_name);
            tv_nums = $(R.id.tv_nums);
            tv_price = $(R.id.tv_price);
            tv_cancel = $(R.id.tv_cancel);

            
        }

        @Override
        public void setData(final CollectionBean data) {
            super.setData(data);
            //在这里设置数据

          if (data!=null&&data.getGoodsBean()!=null){
              Glide.with(getContext()).load(Constants.BASE_URL+data.getGoodsBean().getGoods_img())
                      .placeholder(R.drawable.pic_defaults)
                      .error(R.drawable.pic_defaults)
                      .centerCrop()
                      .into(img_merchants);

              tv_good_name.setText(data.getGoodsBean().getGoods_name());
              tv_nums.setText("规格");
              tv_price.setText("¥"+data.getGoodsBean().getGoods_now_price());
          }


            tv_cancel.setOnClickListener(new View.OnClickListener() {
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

    public CollectionGoodsAdapter.onclick getOnclick() {
        return onclick;
    }

    public void setOnclick(CollectionGoodsAdapter.onclick onclick) {
        this.onclick = onclick;
    }
}