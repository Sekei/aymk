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
 * 医生  咨询列表
 * Created by sh-lx on 2017/7/12.
 */

public class DoctorConsultationFragment extends SimpleFragment {


    @BindView(R.id.community)
    RadioButton community;
    @BindView(R.id.group)
    RadioButton group;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;


    private DoctorConsultImageFragment communityFragment;
    private DoctorConsultVideoFragment groupFragment;

    public static DoctorConsultationFragment newInstance() {
        Bundle args = new Bundle();
        DoctorConsultationFragment fragment = new DoctorConsultationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_doctor_consultation;
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
        if (communityFragment == null) {
            communityFragment = DoctorConsultImageFragment.newInstance();
            fragmentTransaction.add(R.id.fragmentContent, communityFragment);
        }
        commitShowFragment(fragmentTransaction, communityFragment);
    }

    public void showGroupFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (groupFragment == null) {
            groupFragment = DoctorConsultVideoFragment.newInstance();
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

    @OnClick({R.id.community, R.id.group,R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.community:
                showCommunityFragment();
                break;
            case R.id.group:
                showGroupFragment();
                break;

        }
    }



}
