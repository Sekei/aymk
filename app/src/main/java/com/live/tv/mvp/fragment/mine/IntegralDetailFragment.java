package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;

/**
 * 积分明细
 * Created by sh-lx on 2017/7/12.
 */

public class IntegralDetailFragment extends SimpleFragment {


    public static IntegralDetailFragment newInstance() {

        Bundle args = new Bundle();

        IntegralDetailFragment fragment = new IntegralDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_integral_detail;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

    }


}
