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
 * @since 2018/1/15
 */

public class HealthDataDetailFragment extends SimpleFragment {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public static HealthDataDetailFragment newInstance() {
        Bundle args = new Bundle();
        HealthDataDetailFragment fragment = new HealthDataDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_health_data_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("健康数据");

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
}
