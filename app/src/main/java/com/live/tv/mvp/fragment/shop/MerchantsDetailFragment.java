package com.live.tv.mvp.fragment.shop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.MerchantsBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.MerchantsDetailPresenter;
import com.live.tv.mvp.view.shop.IMerchantsDetailView;
import com.live.tv.util.TablayoutUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class MerchantsDetailFragment extends BaseFragment<IMerchantsDetailView, MerchantsDetailPresenter> implements IMerchantsDetailView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.img)
    ImageView mimg;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;
    private String merchants_id = "";
    private List<Fragment> listData;
    private List<CharSequence> listTitle;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private MerchantsBean  merchantsBean;
    private MerchantsInfoFragment merchantsInfoFragment;
    private MerchantsGoodListFragment merchantsGoodListFragment;

    public static MerchantsDetailFragment newInstance(String merchants_id) {
        Bundle args = new Bundle();
        MerchantsDetailFragment fragment = new MerchantsDetailFragment();
        fragment.merchants_id = merchants_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_merchants_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("商家详情");

        listTitle = new ArrayList<>();
        listData = new ArrayList<>();

        listTitle.add("商家简介");
        listTitle.add("商家商品");

        merchantsInfoFragment=MerchantsInfoFragment.newInstance("");
        merchantsGoodListFragment=MerchantsGoodListFragment.newInstance("");

        listData.add(merchantsInfoFragment);
        listData.add(merchantsGoodListFragment);
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        TablayoutUtil.showTabTextAdapteIndicator(tabLayout, context.getApplicationContext());
        tabLayout.setupWithViewPager(viewPager);


       viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

               if (position==0){
                   if (merchantsGoodListFragment!=null){
                       merchantsGoodListFragment.dismissPopWindow();
                   }
               }
           }

           @Override
           public void onPageSelected(int position) {

           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });

    }

    @Override
    public void initData() {

        map.clear();

        if (userBean!=null){
            map.put("member_id",userBean.getMember_id());
        }
        map.put("merchants_id",merchants_id);
        getPresenter().getMerchantsDetail(map);
    }

    @OnClick({R.id.ivLeft,R.id.tv_collection})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_collection:

                if (userBean!=null){
                    map.clear();
                    map.put("member_id",userBean.getMember_id());
                    map.put("member_token",userBean.getMember_token());
                    map.put("collection_type","merchants");
                    map.put("merchants_id",merchants_id);
                    getPresenter().getinsertCollection(map);

                }else {
                    startLogin();
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public MerchantsDetailPresenter createPresenter() {
        return new MerchantsDetailPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void oninsertCollection(String data) {

        ToastUtils.showToast(context.getApplicationContext(),"收藏成功");
    }

    @Override
    public void onMerchantsDetail(MerchantsBean data) {
        if (data!=null){
            Glide.with(context).load(Constants.BASE_URL+data.getBackground_image())
                    .error(R.drawable.banner_default)
                    .into(mimg);
            merchantsInfoFragment.SetMerchantsInfo(data);
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        errorHandle(e);
    }
}
