package com.live.tv.mvp.fragment.communicate;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CommunityBannerBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.mvp.adapter.communicate.CommunityHotListAdapter;
import com.live.tv.mvp.base.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/18
 * 社区顶部
 fragment的内容*/

public class CommunityHotFragment extends SimpleFragment {

    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;
    Unbinder unbinder;

    private CommunityHotListAdapter adapter;
    private List<DoctorDetailBean> list;
    private int num;
    private CommunityBannerBean bannerBean;
    private List<CommunityBannerBean.CommunityPlateBeansBean> slist;

    public static CommunityHotFragment newInstance(CommunityBannerBean communityBannerBean) {
        Bundle args = new Bundle();
        CommunityHotFragment fragment = new CommunityHotFragment();
        fragment.bannerBean=communityBannerBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_community_hot;
    }

    @Override
    public void initUI() {

        list = new ArrayList<>();
if (bannerBean!=null){
    slist=bannerBean.getCommunityPlateBeans();

}


       /* for (int i = 0; i < num; i++) {
            list.add(new DoctorDetailBean());
        }*/
       if (slist!=null&&slist.size()>0){
           adapter = new CommunityHotListAdapter(context, slist);
           easyRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
           easyRecyclerView.setAdapter(adapter);
           adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
               @Override
               public void onItemClick(int position) {
                   //ToastUtils.showToast(context.getApplicationContext(),"何豆豆何铁头");
                   if (slist!=null){
                       startCommunityListFragment(slist.get(position).getPlate_id()+"",adapter.getAllData().get(position).getPlate_name());

                   }
               }
           });

       }


    }

    @Override
    public void initData() {

    }


}
