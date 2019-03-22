package com.live.tv.mvp.adapter.mine;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.easeui.utils.GlideCircleTransform;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ConsultBean;

import java.util.List;


/**
 *咨询列表
 */

public class ConsultListAdapter extends BaseQuickAdapter<ConsultBean, BaseViewHolder> {


    public ConsultListAdapter(List<ConsultBean> data) {
        super(R.layout.item_doctor_consult, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ConsultBean item) {
        Glide.with(mContext).load(Constants.BASE_URL + item.getMember_head_image())
                .transform(new GlideCircleTransform(mContext))
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.img));

        helper.setText(R.id.name,item.getMember_nick_name());

        String sex=item.getMember_sex();
        String show_sex="";

        if ("m".equals(sex)){
            show_sex="男";
        }else if ("n".equals(sex)){
            show_sex="女";
        }else {
            show_sex="未知";
        }


        helper.setText(R.id.department,show_sex+" "+item.getMember_age());
        helper.setText(R.id.type,item.getType_show());
        helper.setText(R.id.content,item.getConsult_record_desc());
        helper.setText(R.id.step,item.getState_show());
        helper.setText(R.id.time,item.getCreate_time());


    }
}
