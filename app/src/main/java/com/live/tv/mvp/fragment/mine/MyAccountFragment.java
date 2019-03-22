package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的账户
 * Created by sh-lx on 2017/7/12.
 */

public class MyAccountFragment extends SimpleFragment {


    @BindView(R.id.community)
    RadioButton community;
    @BindView(R.id.group)
    RadioButton group;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tv_mingxi)
    TextView tv_mingxi;


    private MyBalanceFragment myBalanceFragment;
    private MyIntegealFragment myIntegealFragment;

    public static MyAccountFragment newInstance() {
        Bundle args = new Bundle();
        MyAccountFragment fragment = new MyAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_my_account;
    }

    @Override
    public void initUI() {
        community.setChecked(true);
        showCommunityFragment();
    }

    @Override
    public void initData() {

    }


    public void showCommunityFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (myBalanceFragment == null) {
            myBalanceFragment = MyBalanceFragment.newInstance();
            fragmentTransaction.add(R.id.fragmentContent, myBalanceFragment);
        }
        commitShowFragment(fragmentTransaction, myBalanceFragment);
    }

    public void showGroupFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (myIntegealFragment == null) {
            myIntegealFragment = MyIntegealFragment.newInstance();
            fragmentTransaction.add(R.id.fragmentContent, myIntegealFragment);
        }
        commitShowFragment(fragmentTransaction, myIntegealFragment);
    }


    public void commitShowFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        hideFragment(fragmentTransaction, myBalanceFragment);
        hideFragment(fragmentTransaction, myIntegealFragment);

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
                showCommunityFragment();
                break;
            case R.id.group:
                showGroupFragment();
                break;

        }
    }


    @OnClick({R.id.ivLeft,R.id.tv_mingxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_mingxi:

                startDetailFragment();
                break;
        }
    }
}