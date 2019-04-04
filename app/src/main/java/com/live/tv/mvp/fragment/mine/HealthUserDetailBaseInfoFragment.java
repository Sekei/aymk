package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.mvp.base.SimpleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


import static com.ysjk.health.iemk.R.id.tv_edit_2;
import static com.ysjk.health.iemk.R.id.tv_hospital;

/**
 * @author Created by stone
 * @since 2018/2/1
 */

public class HealthUserDetailBaseInfoFragment extends SimpleFragment {


    @BindView(R.id.heightTittle)
    TextView heightTittle;
    @BindView(tv_hospital)
    TextView tvHospital;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(tv_edit_2)
    TextView tvEdit2;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_person_info)
    TextView tvPersonInfo;
    Unbinder unbinder;

    public static HealthUserDetailBaseInfoFragment newInstance() {

        Bundle args = new Bundle();

        HealthUserDetailBaseInfoFragment fragment = new HealthUserDetailBaseInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_healthuser_dealth_base_info;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {


    }

    public void SetDoctorInfo(HealthRecordDetailBean data) {


        if (data!=null){

            tvHospital.setText(data.getHeight());
            tvTitle.setText(data.getWeight());//体重
            tvEdit2.setText(data.getIs_marriage());//婚姻
            tvType.setText(data.getAllergy()+"");//是否过敏
            tvPersonInfo.setText(data.getAllergy_desc()+"");//过敏项

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
