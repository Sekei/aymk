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
import com.live.tv.mvp.presenter.home.TestResultTabNextPresenter;
import com.live.tv.mvp.view.home.ITestResultTabNextView;

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

public class TestResultTabNextFragment extends BaseFragment<ITestResultTabNextView, TestResultTabNextPresenter> implements ITestResultTabNextView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    Unbinder unbinder;
    @BindView(R.id.content)
    TextView content;
    private String test_id;
    private String type;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    private String is_buy;//是否购买专业调理方案 0未购买 1已购买

    public static TestResultTabNextFragment newInstance(String test_id, String type) {
        Bundle args = new Bundle();
        TestResultTabNextFragment fragment = new TestResultTabNextFragment();
        fragment.test_id = test_id;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test_result_tab_next;
    }

    @Override
    public void initUI() {


        switch (type) {
            case "1":
                tvTitle.setText(R.string.comprehensive_analysis);
                break;
            case "2":
                tvTitle.setText(R.string.physical_causes);
                break;
            case "3":
                tvTitle.setText(R.string.physical_problems);
                break;
        }

    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("test_id", test_id);
        getPresenter().testRecord(map);
    }

    @Override
    public void onTestRecord(TestRecordBean data) {
        is_buy = data.getIs_buy();
        String s = "";
        switch (type) {
            case "1":
                for (int i = 0; i < data.getDreportBeans().size(); i++) {
                    s = s + data.getDreportBeans().get(i).getReport_desc() + "\n";
                }
                content.setText(s);
                break;
            case "2":
                for (int i = 0; i < data.getLeadFactorsBeans().size(); i++) {
                    s = s + data.getLeadFactorsBeans().get(i).getLead_factors_title() + "\n";
                }
                content.setText(s);
                break;
            case "3":
                for (int i = 0; i < data.getLeadResultBeans().size(); i++) {
                    s = s + data.getLeadResultBeans().get(i).getResult_title() + "\n";
                }
                content.setText(s);
                break;
        }
    }

    @OnClick({R.id.ivLeft, R.id.next})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.next:

                switch (type) {
                    case "1":
                        type = "2";
                        tvTitle.setText(R.string.physical_causes);
                        map.put("test_id", test_id);
                        getPresenter().testRecord(map);
                        break;
                    case "2":
                        type = "3";
                        tvTitle.setText(R.string.physical_problems);
                        map.put("test_id", test_id);
                        getPresenter().testRecord(map);
                        break;
                    case "3":
                        if ("0".equals(is_buy)) {
                            startTestResultTabPlanFragment(test_id);
                        } else {
                            startTestResultTabPlanProfessionFragment(test_id);
                        }
                        break;
                }
                break;
        }
    }

    @Override
    public TestResultTabNextPresenter createPresenter() {
        return new TestResultTabNextPresenter(getApp());
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
}
