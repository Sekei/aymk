package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.TestRecordBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.TestResultTabPresenter;
import com.live.tv.mvp.view.home.ITestResultTabView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 2018/1/12
 */

public class TestResultTabFragment  extends BaseFragment<ITestResultTabView, TestResultTabPresenter> implements ITestResultTabView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private String test_id;
    private Map<String, String> map = new HashMap<>();
    private String is_buy;//是否购买专业调理方案 0未购买 1已购买

    public static TestResultTabFragment newInstance(String test_id) {

        Bundle args = new Bundle();

        TestResultTabFragment fragment = new TestResultTabFragment();
        fragment.test_id = test_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test_result_tab;
    }

    @Override
    public void initUI() {
        tvTitle.setText("目录");
    }

    @Override
    public void initData() {
        map.put("test_id", test_id);
        getPresenter().testRecord(map);

    }

    @Override
    public void onTestRecord(TestRecordBean data) {
        is_buy = data.getIs_buy();
    }

    @OnClick({R.id.ivLeft, R.id.next, R.id.one, R.id.two, R.id.three, R.id.four})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.one:
                startTestResultTabNextFragment(test_id, "1");
                break;
            case R.id.two:
                startTestResultTabNextFragment(test_id, "2");
                break;
            case R.id.three:
                startTestResultTabNextFragment(test_id, "3");
                break;
            case R.id.four:

                startTestResultTabPlanFragment(test_id);
//                if ("0".equals(is_buy)) {
//
//                } else {
//                    startTestResultTabPlanProfessionFragment(test_id);
//                }
                break;
            case R.id.next:
                startTestResultTabNextFragment(test_id, "1");
                break;
        }
    }

    @Override
    public TestResultTabPresenter createPresenter() {

        return new TestResultTabPresenter(getApp());
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




}
