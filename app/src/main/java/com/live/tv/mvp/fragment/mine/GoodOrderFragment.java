package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 * 商品订单
 */

public class GoodOrderFragment extends SimpleFragment {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private String state;//类型
    private List<Fragment> listData;
    private List<CharSequence> listTitle;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    public static GoodOrderFragment newInstance(String state) {

        Bundle args = new Bundle();

        GoodOrderFragment fragment = new GoodOrderFragment();
        fragment.state = state;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_good_order;
    }

    @Override
    public void initUI() {
        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
        listTitle.add(getText(R.string.all_order));
        listTitle.add("待支付");
        listTitle.add("待发货");
        listTitle.add("待收货");
        listTitle.add("待评价");
        listData.add(GoodOrderListFragment.newInstance(""));
        listData.add(GoodOrderListFragment.newInstance("wait_pay"));
        listData.add(GoodOrderListFragment.newInstance("wait_send"));
        listData.add(GoodOrderListFragment.newInstance("wait_receive"));
        listData.add(GoodOrderListFragment.newInstance("wait_assessment"));
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        switch (state) {
            case "0":
                viewPager.setCurrentItem(0);
                break;
            case "1":
                viewPager.setCurrentItem(1);
                break;
            case "2":
                viewPager.setCurrentItem(2);
                break;
            case "3":
                viewPager.setCurrentItem(3);
                break;
            case "4":
                viewPager.setCurrentItem(4);
                break;


        }

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initData() {

    }


}
