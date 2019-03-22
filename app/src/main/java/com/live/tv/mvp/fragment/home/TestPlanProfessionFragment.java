package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.PlanDetailBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.TestPlanProfessionPresenter;
import com.live.tv.mvp.view.home.ITestPlanProfessionView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/26
 */

public class TestPlanProfessionFragment extends BaseFragment<ITestPlanProfessionView, TestPlanProfessionPresenter> implements ITestPlanProfessionView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.content)
    TextView content;
    Unbinder unbinder;
    private String test_id;
    private String type;

    private Map<String, String> map = new HashMap<>();

    public static TestPlanProfessionFragment newInstance(String test_id, String type) {
        Bundle args = new Bundle();
        TestPlanProfessionFragment fragment = new TestPlanProfessionFragment();
        fragment.test_id = test_id;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test_plan_profession;
    }

    @Override
    public void initUI() {
        tvTitle.setText(type);
    }

    @Override
    public void initData() {
        map.put("test_id", test_id);
        map.put("plan_title", type);
        getPresenter().getPlanDetail(map);
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
    public TestPlanProfessionPresenter createPresenter() {
        return new TestPlanProfessionPresenter(getApp());
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

    @Override
    public void onGetPlanDetail(List<PlanDetailBean> data) {
        String s = "";
        for (int i = 0; i < data.size(); i++) {
            s = s + data.get(i).getPlan_desc() + "\n";
        }
        content.setText(s);
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
