package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.TestRecordBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.TestDataHuanxinPresenter;
import com.live.tv.mvp.view.home.ITestDataHuanxinView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/12
 */

public class TestDataHuanxinFragment extends BaseFragment<ITestDataHuanxinView, TestDataHuanxinPresenter> implements ITestDataHuanxinView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    Unbinder unbinder;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private String health_record_id;
    private String test_id;

    public static TestDataHuanxinFragment newInstance(String test_id) {

        Bundle args = new Bundle();

        TestDataHuanxinFragment fragment = new TestDataHuanxinFragment();
        fragment.test_id = test_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_diagnose_report;
    }

    @Override
    public void initUI() {
        tvTitle.setText(getResources().getString(R.string.test_report));
    }

    @Override
    public void initData() {
        map.put("test_id", test_id);
        getPresenter().testRecord(map);

    }



    @OnClick({R.id.ivLeft, R.id.see})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.see:
                startTestResultTabFragment(test_id);
                break;
        }
    }

    @Override
    public TestDataHuanxinPresenter createPresenter() {
        return new TestDataHuanxinPresenter(getApp());
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

    @Override
    public void onTestRecord(TestRecordBean data) {

        test_id=data.getTest_id()+"";
        name.setText(getResources().getString(R.string.test_people) + data.getReal_name());
        time.setText(getResources().getString(R.string.date) + data.getDone_factors_time());

    }
}