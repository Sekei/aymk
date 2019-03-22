package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.mvp.base.SimpleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 医生个人介绍
 * Created by sh-lx on 2017/7/12.
 */

public class DoctorPersonalInfoFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_title)
    TextView tvTitles;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.content1)
    TextView content1;
    @BindView(R.id.content2)
    TextView content2;
    @BindView(R.id.content3)
    TextView content3;
    @BindView(R.id.content4)
    TextView content4;
    Unbinder unbinder;
    private DoctorDetailBean doctorDetailBean;

    public static DoctorPersonalInfoFragment newInstance(DoctorDetailBean doctorDetailBean) {

        Bundle args = new Bundle();
        DoctorPersonalInfoFragment fragment = new DoctorPersonalInfoFragment();
        fragment.doctorDetailBean = doctorDetailBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_doctor_personal_info;
    }

    @Override
    public void initUI() {
        tvTitle.setText("个人介绍");
    }

    @Override
    public void initData() {

        if (doctorDetailBean != null) {


            Glide.with(getContext()).load(Constants.BASE_URL + doctorDetailBean.getDoctor_head_image()).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(img);


            tvName.setText(doctorDetailBean.getDoctor_name());
            tvTitles.setText(doctorDetailBean.getDoctor_title());
            tvDepartment.setText(doctorDetailBean.getDepartment_level2());
            tvHospital.setText(doctorDetailBean.getDoctor_hospital());
            content1.setText(doctorDetailBean.getDoctor_introduce());
            content2.setText(doctorDetailBean.getDoctor_background());
            content3.setText(doctorDetailBean.getDoctor_winning());
            content4.setText(doctorDetailBean.getDoctor_remarks());


        }

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
