package com.live.tv.mvp.activity.live;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.PureActivity;

/**
 * 开始直播
 */

public class StartLiveActivity extends PureActivity {


    private StartLiveFragment startLiveFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_start_live;
    }

    @Override
    public void initUI() {
        showStartLiveFragment();

    }

    public void showStartLiveFragment() {
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (startLiveFragment == null) {
            startLiveFragment = StartLiveFragment.newInstance();
            fragmentTransaction.add(R.id.fragmentContent, startLiveFragment);
        }
        commitShowFragment(fragmentTransaction, startLiveFragment);
    }
    public void commitShowFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        hideFragment(fragmentTransaction, startLiveFragment);

    }

    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }



}
