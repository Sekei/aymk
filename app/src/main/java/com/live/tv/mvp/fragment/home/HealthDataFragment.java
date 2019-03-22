package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthDataBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.HealthDataClassdAdapter;
import com.live.tv.mvp.adapter.home.HealthListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.HealthDataPresenter;
import com.live.tv.mvp.view.home.IHealthDataView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/15
 */

public class HealthDataFragment extends BaseFragment<IHealthDataView, HealthDataPresenter> implements IHealthDataView {
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.all)
    TextView all;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    private HealthDataClassdAdapter healthClassAdapter;
    private List<HealthDataBean> healthDataClassdList;


    private HealthListAdapter adapter;
    private List<HealthDataBean.HealthDataBeansBean> list;

    public static HealthDataFragment newInstance() {
        Bundle args = new Bundle();
        HealthDataFragment fragment = new HealthDataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_medical_record;
    }

    @Override
    public void initUI() {
        tvTitle.setText("健康数据");
        healthDataClassdList = new ArrayList<>();
        LinearLayoutManager nearbyLinearLayoutManager = new LinearLayoutManager(context);
        nearbyLinearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        healthClassAdapter = new HealthDataClassdAdapter(context, healthDataClassdList);
        recyclerView.setLayoutManager(nearbyLinearLayoutManager);
        recyclerView.setAdapter(healthClassAdapter);
        healthClassAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                all.setTextColor(getResources().getColor(R.color.colorTextG6));
                all.setBackgroundResource(R.color.pure_white);
                healthClassAdapter.setPosition(position);
                healthClassAdapter.notifyDataSetChanged();
                map.clear();
                map.put("health_class_id", healthClassAdapter.getItem(position).getHealth_class_id());
                getPresenter().healthDtatList(map);

            }
        });

        list = new ArrayList<>();
        adapter = new HealthListAdapter(context, list);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startHealthDataDetailFragment();
            }
        });
    }

    @Override
    public void initData() {
        getPresenter().healthDtatList(map);
    }


    @Override
    public HealthDataPresenter createPresenter() {
        return new HealthDataPresenter(getApp());
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
    public void onHealthDtatList(List<HealthDataBean> data) {

        healthClassAdapter.clear();
        healthClassAdapter.addAll(data);
        healthClassAdapter.notifyDataSetChanged();
        List<HealthDataBean.HealthDataBeansBean> beanList=new ArrayList<>();
        for (int i=0;i<data.size();i++){
            beanList.addAll(data.get(i).getHealthDataBeans());
        }
        adapter.clear();
        adapter.addAll(beanList);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ivLeft, R.id.all})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.all:
                healthClassAdapter.setPosition(-1);
                healthClassAdapter.notifyDataSetChanged();
                all.setTextColor(getResources().getColor(R.color.pure_white));
                all.setBackgroundResource(R.color.colorPrimary);
                map.clear();
                getPresenter().healthDtatList(map);
                break;

        }
    }

}
