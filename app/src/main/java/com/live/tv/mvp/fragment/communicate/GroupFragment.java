package com.live.tv.mvp.fragment.communicate;

import android.os.Bundle;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;

/**
 * @author Created by stone
 * @since 2018/1/16
 */

public class GroupFragment extends SimpleFragment {

    public static GroupFragment newInstance() {
        Bundle args = new Bundle();
        GroupFragment fragment = new GroupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_group;
    }

    @Override
    public void initUI() {
    }

    @Override
    public void initData() {

    }

}
