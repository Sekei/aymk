package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/27
 */

public class MsgFragment extends SimpleFragment {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    List<CharSequence> listTitle = new ArrayList<>();
    List<Fragment> listData = new ArrayList<>();
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    public static MsgFragment newInstance() {
        Bundle args = new Bundle();
        MsgFragment fragment = new MsgFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_msg;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.notification);
        listTitle.add(getResources().getString(R.string.order_msg));
        listTitle.add(getResources().getString(R.string.system_msg));
        for (int j = 0; j < listTitle.size(); j++) {
            listData.add(MsgListFragment.newInstance(j + ""));
        }

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        viewPager.setCurrentItem(0);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
