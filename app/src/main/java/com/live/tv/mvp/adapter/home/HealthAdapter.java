package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HomeHealthDetail;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class HealthAdapter extends RecyclerArrayAdapter<HomeHealthDetail.PostBeansBean> {
    private OnItemClicklisenter itemClicklisenter;

    public HealthAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<HomeHealthDetail.PostBeansBean> {
        private TextView mdepartment,mindications,mtime;
        private ImageView mimg;
        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_health);
           mdepartment=$(R.id.department);//标题
           mindications=$(R.id.indications);//描述
           mtime=$(R.id.time);//时间
            mimg=$(R.id.img);//图片
        }
        @Override
        public void setData(HomeHealthDetail.PostBeansBean data) {
            super.setData(data);
            if (data.getPostBean()!=null&&data.getPostBean().getForward_num()!=null){
                mdepartment.setText(data.getPostBean().getPost_title());
                mindications.setText(data.getPostBean().getPost_desc());
                mtime.setText(data.getPostBean().getMember_nick_name()+data.getPostBean().getPosting_date());
                Glide.with(getContext())
                        .load(Constants.BASE_URL + data.getPost_image())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .thumbnail(0.1f)
                        .into(mimg);

            }else {
                mdepartment.setText(data.getPost_title());
                mindications.setText(data.getPost_desc());
                mtime.setText(data.getMember_nick_name()+data.getPosting_date());
                Glide.with(getContext())
                        .load(Constants.BASE_URL + data.getPost_image())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .thumbnail(0.1f)
                        .into(mimg);
            }
        }
    }

  public void setOnclickLisenter(OnItemClicklisenter clicklisenter){

     this.itemClicklisenter=clicklisenter;

  }
  public interface  OnItemClicklisenter{

      void setPlateId(String plate);


  }
}