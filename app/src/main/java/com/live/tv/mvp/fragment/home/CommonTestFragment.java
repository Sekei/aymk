package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.CommonTestAdapter;
import com.live.tv.mvp.base.SimpleFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/11
 */

public class CommonTestFragment extends SimpleFragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecycleView;
    Unbinder unbinder;
    private CommonTestAdapter adapter;
    private List<DoctorDetailBean> list;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    public static CommonTestFragment newInstance() {

        Bundle args = new Bundle();

        CommonTestFragment fragment = new CommonTestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_common_test;
    }

    @Override
    public void initUI() {
        tvTitle.setText("普通测试");
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new DoctorDetailBean());
        }
        adapter = new CommonTestAdapter(context, list);
        easyRecycleView.setLayoutManager(new LinearLayoutManager(context));
        easyRecycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startTestHomePageFragment();
            }
        });
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