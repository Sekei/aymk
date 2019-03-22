package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.MedicalListBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.MedicalMonthListAdapter;
import com.live.tv.mvp.base.SimpleFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 2018/1/15
 */

public class MedicalRecordsMonthFragment extends SimpleFragment {
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    private MedicalMonthListAdapter adapter;
    private List<MedicalListBean> medicalList;

    public static MedicalRecordsMonthFragment newInstance() {
        Bundle args = new Bundle();
        MedicalRecordsMonthFragment fragment = new MedicalRecordsMonthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_medical_record_month;
    }

    @Override
    public void initUI() {
        tvTitle.setText("电子病历");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("上传病历");
        tvRight.setTextColor(getResources().getColor(R.color.tx_4));

        medicalList = new ArrayList<>();
        adapter = new MedicalMonthListAdapter(context, medicalList);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);

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
