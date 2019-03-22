package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.mvp.adapter.mine.SchemeAdapter;
import com.live.tv.mvp.base.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 调理方案
 *
 * @author Created by stone
 * @since 2018/2/1
 */

public class HealthUserDetailSchemeFragment extends SimpleFragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private SchemeAdapter mAdapter;
    public static HealthUserDetailSchemeFragment newInstance() {

        Bundle args = new Bundle();

        HealthUserDetailSchemeFragment fragment = new HealthUserDetailSchemeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_health_user_detail_scheme;
    }

    @Override
    public void initUI() {

        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        mAdapter= new SchemeAdapter(new ArrayList<HealthRecordDetailBean.HealthPlanBeansBean>());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {


    }

    public void SetDoctorInfo(HealthRecordDetailBean data) {
        if (data!=null) {

            List<HealthRecordDetailBean.HealthPlanBeansBean> healthPlanBeansBean = data.getHealthPlanBeans();

            if (healthPlanBeansBean!=null&&healthPlanBeansBean.size()>0){

                mAdapter.setNewData(healthPlanBeansBean);
            }

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
