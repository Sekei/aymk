package com.live.tv.mvp.fragment.communicate;

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
 * Created by sh-lx on 2017/7/12.
 * 交流社区
 */

public class CommunicateFragment extends SimpleFragment {

    @BindView(R.id.iv_back)
    ImageView ivback;
    @BindView(R.id.community)
    RadioButton community;
    @BindView(R.id.group)
    RadioButton group;
    Unbinder unbinder;
    private String mType;

    private CommunityFragment communityFragment;
    private GroupFragment groupFragment;

    public static CommunicateFragment newInstance() {
        Bundle args = new Bundle();
        CommunicateFragment fragment = new CommunicateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CommunicateFragment newInstance(String str) {
        Bundle args = new Bundle();
        CommunicateFragment fragment = new CommunicateFragment();
        fragment.mType = str;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_communicate;
    }

    @Override
    public void initUI() {

        if (mType!=null&&mType.equals("1")){
           ivback.setVisibility(View.VISIBLE);
        }else {
            ivback.setVisibility(View.GONE);
        }
        community.setChecked(true);
        showCommunityFragment();
    }

    @Override
    public void initData() {

    }


    public void showCommunityFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (communityFragment == null) {
            communityFragment = CommunityFragment.newInstance();
            fragmentTransaction.add(R.id.fragmentContent, communityFragment);
        }
        commitShowFragment(fragmentTransaction, communityFragment);
    }

    public void showGroupFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (groupFragment == null) {
            groupFragment = groupFragment.newInstance();
            fragmentTransaction.add(R.id.fragmentContent, groupFragment);
        }
        commitShowFragment(fragmentTransaction, groupFragment);
    }


    public void commitShowFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        hideFragment(fragmentTransaction, communityFragment);
        hideFragment(fragmentTransaction, groupFragment);

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

    @OnClick({R.id.community, R.id.group, R.id.msg,R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.community:
                showCommunityFragment();
                break;
            case R.id.group:
                showGroupFragment();
                break;
            case R.id.msg:
                startMsgFragment();
                break;
            case R.id.iv_back:

                finish();
                break;
        }
    }


}
