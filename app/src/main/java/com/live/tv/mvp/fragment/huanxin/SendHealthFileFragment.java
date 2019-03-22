package com.live.tv.mvp.fragment.huanxin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.mvp.adapter.home.HealthFileListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.SendHealthFilePresenter;
import com.live.tv.mvp.view.home.ISendHealthFileView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *聊天时  发送给医生的健康档案，也就是专属档案  调理方案
 * Created by sh-lx on 2017/7/12.
 */

public class SendHealthFileFragment extends BaseFragment<ISendHealthFileView, SendHealthFilePresenter> implements ISendHealthFileView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    Unbinder unbinder;
    private String health_record_id = "";
    private List<HealthRecordDetailBean.HealthPlanBeansBean> HealthPlanList;
    private HealthFileListAdapter adapter;

    public static SendHealthFileFragment newInstance(String health_record_id) {
        Bundle args = new Bundle();
        SendHealthFileFragment fragment = new SendHealthFileFragment();
        fragment.health_record_id = health_record_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_send_health_file;
    }

    @Override
    public void initUI() {
        tvTitle.setText("发送健康档案");


        HealthPlanList= new ArrayList<>();
        adapter = new HealthFileListAdapter(context, HealthPlanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HealthRecordDetailBean.HealthPlanBeansBean healthPlan=adapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("health_plan_id",healthPlan.getHealth_plan_id());
                intent.putExtra("health_record_id",health_record_id);
                intent.putExtra("doctor_id",healthPlan.getDoctor_id());
                getActivity().setResult(Constants.INTENT_HEALTH_FILE,intent);
                finish();

            }
        });
    }

    @Override
    public void initData() {

        map.clear();
        map.put("health_record_id", health_record_id);
        getPresenter().healthRecordDetail(map);

    }

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }

    @Override
    public SendHealthFilePresenter createPresenter() {
        return new SendHealthFilePresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        errorHandle(e);
    }

    @Override
    public void onHealthRecordDetail(HealthRecordDetailBean data) {


        if (data!=null){

            if (data.getHealthPlanBeans()!=null&&data.getHealthPlanBeans().size()>0){
                adapter.clear();
                adapter.addAll(data.getHealthPlanBeans());
                adapter.notifyDataSetChanged();
            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
