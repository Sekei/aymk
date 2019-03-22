package com.live.tv.mvp.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;

import java.util.List;


/**
 *上传病例的图片
 */

public class UploadCaseImageAdapter extends BaseQuickAdapter< String, BaseViewHolder> {


    public UploadCaseImageAdapter(List<String> data) {
        super(R.layout.item_case_img, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {

        ImageView img=helper.getView(R.id.img);

        if (item.equals("")){

            helper.getView(R.id.img_del).setVisibility(View.GONE);

            Glide.with(mContext).load(R.drawable.consult_icon_camerax)
                    .placeholder(R.drawable.consult_icon_camerax)
                    .error(R.drawable.consult_icon_camerax)
                    .centerCrop()
                    .into(img);

        }else {
            helper.getView(R.id.img_del).setVisibility(View.VISIBLE);

            Glide.with(mContext).load(item)
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img);

        }


        Log.i("dfc", "convert: "+helper.getLayoutPosition());
        if (helper.getLayoutPosition()==9){
            img.setVisibility(View.GONE);
        }else {
            img.setVisibility(View.VISIBLE);
        }

        helper.addOnClickListener(R.id.img_del);
    }
}
