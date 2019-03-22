package com.live.tv.mvp.fragment.communicate;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CommAllBean;
import com.live.tv.bean.CommunityBannerBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.communicate.AllCommunityListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.communicate.CommuntiyFragmentPresenter;
import com.live.tv.mvp.view.communicate.ICommuntiyFragmentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author Created by stone
 * @since 2018/1/16
 */

public class CommunityFragment extends BaseFragment<ICommuntiyFragmentView, CommuntiyFragmentPresenter> implements ICommuntiyFragmentView {


    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;
    ViewPager viewPager;
    LinearLayout llPointGroup;
    private AllCommunityListAdapter adapter;
    private List<DoctorDetailBean> list;
    private List<CommAllBean> allList = new ArrayList<>();
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private ConvenientBanner<CommunityBannerBean> mConvenientBanner;
    private List<Fragment> listData = new ArrayList<>();
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private int position = 0;
    private int lastPosition;
    private Map<String, String> mapbanner = new HashMap<>();

    public static CommunityFragment newInstance() {
        Bundle args = new Bundle();
        CommunityFragment fragment = new CommunityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {

        return R.layout.fragment_community;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initUI() {
        adapter = new AllCommunityListAdapter(context, allList);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startCommunityListFragment(allList.get(position).getPlate_id() + "", adapter.getAllData().get(position).getPlate_name());
            }
        });


        easyRecyclerView.setRefreshingColorResources(R.color.colorPrimary);
        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (adapter != null) {
                    adapter.clear();
                }
                if (viewPagerFragmentAdapter != null && listData != null && listData.size() > 0) {
                    listData.clear();
                    viewPagerFragmentAdapter.notifyDataSetChanged();
                    llPointGroup.removeAllViews();
                }

                getPresenter().getCommuntiyBanner(mapbanner);

                getPresenter().getAllData(map);

            }
        });

        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = View.inflate(getContext(), R.layout.fragment_community_top, null);
                viewPager = (ViewPager) view.findViewById(R.id.viewPager);
                llPointGroup = (LinearLayout) view.findViewById(R.id.ll_point_group);

                initListener();
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                position = position % listData.size();
                llPointGroup.getChildAt(lastPosition).setEnabled(false);
                llPointGroup.getChildAt(position).setEnabled(true);

                lastPosition = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void initData() {
        getPresenter().getCommuntiyBanner(mapbanner);

        getPresenter().getAllData(map);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public CommuntiyFragmentPresenter createPresenter() {
        return new CommuntiyFragmentPresenter(getApp());
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    /**
     * 获取热门模块banner的数据
     *
     * @param bannerBean
     */
    @Override
    public void OnBannerdata(List<CommunityBannerBean> bannerBean) {
        Log.e(">>>", ">>>" + bannerBean);
        for (int i = 0; i < bannerBean.size(); i++) {
            listData.add(CommunityHotFragment.newInstance(bannerBean.get(i)));

        }
        // position = listData.size() / 2+listData.size() % 2;
        if (listData.size() > 1) {
            for (int i = 0; i < listData.size(); i++) {
                // 动态添加指示点
                ImageView point = new ImageView(getActivity());
                point.setBackgroundResource(R.drawable.selector_community_top_point);
                // 让第一个是黄点，其他的是白点
                if (i == position) {
                    point.setEnabled(true);
                } else {
                    point.setEnabled(false);
                }
                // 布局是一个类，子View也是一个类，布局参数也是一个独立的类
                // 任何一个布局，都有一个对应的静态类：布局参数
                // 当布局添加子view时，布局参数一定要和布局的类型保持一致
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, -2);
                layoutParams.leftMargin = 15; // 左边，15个象素的边距
                if (llPointGroup != null) {
                    llPointGroup.addView(point, layoutParams);
                }
            }
        }

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData);
      if (viewPager!=null){
          viewPager.setAdapter(viewPagerFragmentAdapter);
      }
    }

    /***
     * 整体模块的数据
     * @param commAllBeenList
     */
    @Override
    public void OnAllData(List<CommAllBean> commAllBeenList) {
        allList = commAllBeenList;
        if (adapter != null) {
            adapter.clear();
        }
        adapter.addAll(commAllBeenList);
        adapter.notifyDataSetChanged();

    }

}
