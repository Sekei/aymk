package com.live.tv.mvp.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthFileBean;
import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.mvp.adapter.home.HealthManagePriceAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.HealthManagePresenter;
import com.live.tv.mvp.view.home.IHealthManageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.live.tv.Constants.REQUESTCODE;

/**
 * @author Created by stone
 * @since 2018/1/12
 */

public class HealthManageFragment extends BaseFragment<IHealthManageView, HealthManagePresenter> implements IHealthManageView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tittle)
    TextView tittle;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.qh)
    TextView qh;
    @BindView(R.id.typeTittle)
    TextView typeTittle;
    @BindView(R.id.typeName)
    TextView typeName;
    @BindView(R.id.type_content)
    TextView typeContent;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.servserTittle)
    TextView servserTittle;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;
    @BindView(R.id.descripe)
    TextView descripe;
    Unbinder unbinder;

    private HealthManagePriceAdapter adapter;
    private List<HealthManagerBeans> list;
    private String doctor_id;
    private String doctor_name;
    private String health_record_id = "";  //健康档案ID
    private String record_name = "";  //档案姓名
    private String relation = "";  //关系
    private HealthManagerBeans healthManager;

    public static HealthManageFragment newInstance(String doctor_id, String doctor_name) {

        Bundle args = new Bundle();

        HealthManageFragment fragment = new HealthManageFragment();
        fragment.doctor_id = doctor_id;
        fragment.doctor_name = doctor_name;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_health_manager;
    }

    @Override
    public void initUI() {
        tvTitle.setText("健康管理");
        descripe.setText(R.string.descripe);
        list = new ArrayList<>();
        adapter = new HealthManagePriceAdapter(context, list);
        easyRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                healthManager=adapter.getItem(position);
                adapter.setPosition(position);
                descripe.setText(adapter.getItem(position).getService_desc());
                adapter.notifyDataSetChanged();
            }
        });

        healthManager = new HealthManagerBeans();

    }

    @Override
    public void initData() {

        map.clear();
        map.put("doctor_id", doctor_id);
        getPresenter().getHealthManager(map);
        Map<String, String> mMap = new HashMap<>();
        mMap.put("member_id", userBean.getMember_id());
        mMap.put("member_token", userBean.getMember_token());
        getPresenter().recordList(mMap);

    }

    @OnClick({R.id.ivLeft, R.id.ok, R.id.qh})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:

                if (healthManager!=null&&!"".equals(health_record_id)) {
                    startBuyConsultHealthFragment(health_record_id, healthManager);
                }
                break;
            case R.id.qh:
                startMoreConsultantFragment();
                break;
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
    public HealthManagePresenter createPresenter() {
        return new HealthManagePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void onGetHealthManager(List<HealthManagerBeans> data) {

        if (data != null&&data.size()>0) {
            adapter.clear();
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
            descripe.setText(data.get(0).getService_desc());
            healthManager = data.get(0);
        }
    }

    @Override
    public void onRecordList(List<HealthFileBean> data) {

        if (data != null) {

            HealthFileBean  h=data.get(0);
            health_record_id=h.getHealth_record_id();
            record_name=h.getRecord_name();
            relation=h.getRelation();
            name.setText(record_name + "(" + relation + ")");
        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && null != data) {
            health_record_id = data.getStringExtra(Constants.HEALTH_RECORD_ID);
            record_name = data.getStringExtra(Constants.RECORD_NAME);
            relation = data.getStringExtra(Constants.RELATION);
            name.setText(record_name + "(" + relation + ")");
        }

    }
}