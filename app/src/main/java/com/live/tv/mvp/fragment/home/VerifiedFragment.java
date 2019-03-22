package com.live.tv.mvp.fragment.home;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 2018/2/1
 */

public class VerifiedFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private String health_record_id="";

    public static VerifiedFragment newInstance(String health_record_id) {
        Bundle args = new Bundle();
        VerifiedFragment fragment = new VerifiedFragment();
        fragment.health_record_id=health_record_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_verified;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.verified);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);//通知栏所需颜色
            // tintManager.setTintColor(R.color.pure_white);
        }

    }
    @OnClick({R.id.ivLeft,R.id.tv_submit,R.id.other})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_submit:
                startVerifiedSuccessFragment();
                break;
            case R.id.other:
                startVerifiedImgsFragment(health_record_id);
                finish();
                break;
        }
    }
}
