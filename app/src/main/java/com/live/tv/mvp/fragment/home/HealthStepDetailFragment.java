package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthPlanBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.HealthStepDetailPresenter;
import com.live.tv.mvp.view.home.IHealthStepDetailView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 健康阶段方案详情
 *
 * @author Created by stone
 * @since 2018/1/17
 */

public class HealthStepDetailFragment extends BaseFragment<IHealthStepDetailView, HealthStepDetailPresenter> implements IHealthStepDetailView {
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tittle)
    TextView tittle;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.content)
    TextView content;
    Unbinder unbinder;
    private String health_plan_id = "";

    public static HealthStepDetailFragment newInstance(String health_plan_id) {
        Bundle args = new Bundle();
        HealthStepDetailFragment fragment = new HealthStepDetailFragment();
        fragment.health_plan_id = health_plan_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_health_step_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("方案详情");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("咨询医生");
        tvRight.setTextColor(getResources().getColor(R.color.tx_4));
    }

    @Override
    public void initData() {
        Map<String, String> mMap = new HashMap<>();
        mMap.put("health_plan_id", health_plan_id);
        getPresenter().getHealthStageDetail(mMap);

    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startDoctorDetailFragment("1");
                break;
        }
    }

    @Override
    public HealthStepDetailPresenter createPresenter() {
        return new HealthStepDetailPresenter(getApp());
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

    @Override
    public void onGetHealthStageDetail(HealthPlanBean data) {

        if (data != null) {

            tittle.setText(data.getPlan_name());
            name.setText(data.getDoctor_name());
            type.setText(data.getDoctor_department());
            content.setText(data.getPlan_desc());

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
