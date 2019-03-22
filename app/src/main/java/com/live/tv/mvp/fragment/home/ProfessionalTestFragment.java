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
 * @since 2018/1/11
 */

public class ProfessionalTestFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public static ProfessionalTestFragment newInstance() {

        Bundle args = new Bundle();

        ProfessionalTestFragment fragment = new ProfessionalTestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_professional_test;
    }

    @Override
    public void initUI() {
        tvTitle.setText("健康测试");
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