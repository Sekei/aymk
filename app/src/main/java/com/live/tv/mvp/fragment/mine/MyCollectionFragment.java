package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/16
 */

public class MyCollectionFragment extends SimpleFragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private List<Fragment> listData;
    private List<CharSequence> listTitle;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    public static MyCollectionFragment newInstance() {
        Bundle args = new Bundle();
        MyCollectionFragment fragment = new MyCollectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_my_collection;
    }

    @Override
    public void initUI() {
        tvTitle.setText("收藏");
        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
//        listTitle.add("医生");
        listTitle.add(getString(R.string.shop));
        listTitle.add(getString(R.string.goods));
        listTitle.add(getString(R.string.house));
//        listData.add(AllCollectionFragment.newInstance());
        listData.add(CollectionMerchantsFragment.newInstance());
        listData.add(CollectionGoodsFragment.newInstance());
        listData.add(CollectionServiceFragment.newInstance());

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
}
