package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 2018/1/12
 */

public class TestCompleteFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private String health_record_id;
    private static TestCompleteFragment mfragment;
    public static TestCompleteFragment newInstance(String health_record_id) {

        Bundle args = new Bundle();

        TestCompleteFragment fragment = new TestCompleteFragment();
        mfragment=fragment;
        fragment.health_record_id = health_record_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test_complete;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.health_test);
        new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (mfragment!=null&&mfragment.isAdded()){
                    startTestDiagnoseReportFragment(health_record_id);
                    finish();
                }
            }
        }.start();
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