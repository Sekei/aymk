package com.live.tv.mvp.adapter.communicate;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ImageListBean;
import com.live.tv.bean.PlateListBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Created by stone
 * @since 2018/1/18
 */

public class CommunityListAdapter extends RecyclerArrayAdapter<PlateListBean> {
    private List<PlateListBean.PostBeanBeanX.PostImageBeansBean> mlist;
    private List<PlateListBean.PostImageBeansBeanX> list;
    private List<ImageListBean> dataList=new ArrayList<>();

    public CommunityListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<PlateListBean> {
        private CircleImageView mivAvatar;
        private TextView mName,mMy_tittle,mTittle,mContent,mTime,mZan_num,mCommond_num,mShare_num;
        private RecyclerView recycleView;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_community_list);
            recycleView = $(R.id.recycleView);
            mivAvatar=$(R.id.ivAvatar);//头像
            mName=$(R.id.name);//名称
            mMy_tittle=$(R.id.my_tittle);//转发写的字
            mTittle=$(R.id.tittle);//标题
            mContent=$(R.id.content);//内容
            mTime=$(R.id.time);
            mZan_num=$(R.id.zan_num);
            mCommond_num=$(R.id.commond_num);
            mShare_num=$(R.id.share_num);
            recycleView.setLayoutManager(new GridLayoutManager(getContext(),3));

        }

        @Override
        public void setData(final PlateListBean data) {
            super.setData(data);


            if (data.getPostBean()!=null&&data.getPostBean().getForward_num()!=null){
                /**
                 * 转发
                 */
              //  mMy_tittle.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load(Constants.BASE_URL+data.getMember_head_image())
                        .error(R.mipmap.ic_launcher)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                                mivAvatar.setImageDrawable(resource);
                            }
                        });


                mName.setText(data.getMember_nick_name());
                mMy_tittle.setText(data.getPost_title());
                mTittle.setText(data.getPostBean().getPost_title());
                mContent.setText(data.getPostBean().getPost_desc());
                mlist=data.getPostBean().getPostImageBeans();
                mTime.setText(data.getPostBean().getPosting_date());
                mShare_num.setText(data.getPostBean().getForward_num());
                mZan_num.setText(data.getPostBean().getPraise_num());
                mCommond_num.setText(data.getPostBean().getComment_num());
if (mlist!=null&&mlist.size()>0){

    CommonAdapter<PlateListBean.PostBeanBeanX.PostImageBeansBean> adapter = new CommonAdapter<PlateListBean.PostBeanBeanX.PostImageBeansBean>(getContext(), R.layout.item_img, mlist) {
        @Override
        protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, PlateListBean.PostBeanBeanX.PostImageBeansBean s, int position) {

            Glide.with(getContext())
                    .load(Constants.BASE_URL+s.getPost_image())
                    .error(R.drawable.banner_default)
                    .placeholder(R.drawable.banner_default)
                    .into((ImageView) holder.getView(R.id.img));
        }
    };
   // recycleView.setAdapter(adapter);
    adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
            return false;
        }
    });

}
            }else {
                /**
                 * 自己发
                 */
                mMy_tittle.setVisibility(View.GONE);
                Glide.with(getContext())
                        .load(Constants.BASE_URL+data.getMember_head_image())
                        .error(R.mipmap.ic_launcher)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                                mivAvatar.setImageDrawable(resource);
                            }
                        });
                mName.setText(data.getMember_nick_name());
                mTittle.setText(data.getPost_title());
                mContent.setText(data.getPost_desc());
                list=data.getPostImageBeans();
                mTime.setText(data.getPosting_date());
                mShare_num.setText(data.getForward_num());
                mZan_num.setText(data.getPraise_num());
                mCommond_num.setText(data.getComment_num());
if (list!=null&&list.size()>0){

    CommonAdapter<PlateListBean.PostImageBeansBeanX> adapter = new CommonAdapter<PlateListBean.PostImageBeansBeanX>(getContext(), R.layout.item_img, list) {
        @Override
        protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, PlateListBean.PostImageBeansBeanX s, int position) {

            Glide.with(getContext())
                    .load(Constants.BASE_URL+s.getPost_image())
                    .error(R.drawable.banner_default)
                    .placeholder(R.drawable.banner_default)
                    .into((ImageView) holder.getView(R.id.img));
        }
    };
   // recycleView.setAdapter(adapter);
    adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            ToastUtils.showToast(getContext(),"自己发的");
        }

        @Override
        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
            return false;
        }
    });
}


            }









        }
    }
}