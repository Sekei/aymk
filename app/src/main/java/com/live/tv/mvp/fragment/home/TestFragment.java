package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public class TestFragment extends SimpleFragment {
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public static TestFragment newInstance() {
        Bundle args = new Bundle();
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test;
    }

    @Override
    public void initUI() {
        tvTitle.setText("健康测试");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("历史数据");

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivLeft,R.id.testOneImg,R.id.testTwoImg,R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.testOneImg:
                startCommonTestFragment();
                break;
            case R.id.testTwoImg:
                startTestDescriptionFragment();
                break;
            case R.id.tvRight:
                startTestHistoryFragment();
                break;
        }
    }
}
