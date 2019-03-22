package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的订单
 * Created by sh-lx on 2017/7/12.
 */

public class MyOrderFragment extends SimpleFragment {


    @BindView(R.id.community)
    RadioButton community;
    @BindView(R.id.group)
    RadioButton group;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
//    @BindView(R.id.tv_detailed)
//    TextView tvDetailed;


    private GoodOrderFragment goodOrderFragment;
    private ServiceOrderFragment serviceOrderFragment;
    private String type="";
    private String page="";

    public static MyOrderFragment newInstance(String type,String page) {
        Bundle args = new Bundle();
        MyOrderFragment fragment = new MyOrderFragment();
        fragment.type=type;
        fragment.page=page;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_my_order;
    }

    @Override
    public void initUI() {

        if ("good".equals(type)){
            community.setChecked(true);
            showCommunitysFragment(page);

        }else if ("service".equals(type)){

            group.setChecked(true);
            showGroupsFragment(page);
        }else {
            community.setChecked(true);
            showCommunitysFragment(page);
        }

         page="";

    }

    @Override
    public void initData() {

    }


    public void showCommunitysFragment(String page) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (goodOrderFragment == null) {
            goodOrderFragment = GoodOrderFragment.newInstance(page);
            fragmentTransaction.add(R.id.fragmentContent, goodOrderFragment);
        }
        commitShowFragment(fragmentTransaction, goodOrderFragment);
    }

    public void showGroupsFragment(String page) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (serviceOrderFragment == null) {
            serviceOrderFragment = ServiceOrderFragment.newInstance(page);
            fragmentTransaction.add(R.id.fragmentContent, serviceOrderFragment);
        }
        commitShowFragment(fragmentTransaction, serviceOrderFragment);
    }


    public void commitShowFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        hideFragment(fragmentTransaction, goodOrderFragment);
        hideFragment(fragmentTransaction, serviceOrderFragment);

    }

    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
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

    @OnClick({R.id.community, R.id.group})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.community:
                showCommunitysFragment(page);
                break;
            case R.id.group:
                showGroupsFragment("");
                break;

        }
    }

    @OnClick({R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
//            case R.id.tv_detailed:
//
//                startDetailFragment();
//                break;
        }
    }


}
