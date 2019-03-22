package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.AssessmentDoctorPresenter;
import com.live.tv.mvp.view.mine.IAssessmentDoctorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class AssessmentDoctorFragment extends BaseFragment<IAssessmentDoctorView, AssessmentDoctorPresenter> implements IAssessmentDoctorView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tv_desc)
    EditText tvDesc;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    Unbinder unbinder;
    private String doctor_id="";

    public static AssessmentDoctorFragment newInstance(String doctor_id) {
        Bundle args = new Bundle();
        AssessmentDoctorFragment fragment = new AssessmentDoctorFragment();
        fragment.doctor_id=doctor_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_assessment_doctor;
    }

    @Override
    public void initUI() {
        tvTitle.setText("评价");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivLeft,R.id.tv_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_submit:

                break;
        }
    }

    @Override
    public AssessmentDoctorPresenter createPresenter() {
        return new AssessmentDoctorPresenter(getApp());
    }

    @Override
    public void onUploadImgs(String[] data) {

    }

    @Override
    public void onAssessementDoctor(String data) {

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
