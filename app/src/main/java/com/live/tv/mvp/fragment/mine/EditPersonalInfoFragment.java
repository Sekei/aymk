package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.EventBusMessage;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.EditPersonalInfoPresenter;
import com.live.tv.mvp.view.mine.IEditPersonalInfoView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 个人介绍
 * Created by sh-lx on 2017/7/12.
 */

public class EditPersonalInfoFragment extends BaseFragment<IEditPersonalInfoView,EditPersonalInfoPresenter> implements IEditPersonalInfoView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.ed1)
    EditText ed1;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.ed2)
    EditText ed2;
    @BindView(R.id.tv22)
    TextView tv22;
    @BindView(R.id.ed3)
    EditText ed3;
    @BindView(R.id.tv33)
    TextView tv33;
    @BindView(R.id.ed4)
    EditText ed4;
    @BindView(R.id.tv44)
    TextView tv44;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    Unbinder unbinder;
    private  DoctorDetailBean doctorDetailBean;

    private String  doctor_id="";
    private String  doctor_introduce="";
    private String  doctor_background="";
    private String  doctor_winning="";
    private String  doctor_remarks="";

    public static EditPersonalInfoFragment newInstance(String  doctor_id,String  doctor_introduce,String  doctor_background,String  doctor_winning,String  doctor_remarks) {

        Bundle args = new Bundle();
        EditPersonalInfoFragment fragment = new EditPersonalInfoFragment();
        fragment.doctor_id=doctor_id;
        fragment.doctor_introduce=doctor_introduce;
        fragment.doctor_background=doctor_background;
        fragment.doctor_winning=doctor_winning;
        fragment.doctor_remarks=doctor_remarks;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_edit_personal_info;
    }

    @Override
    public void initUI() {
        tvTitle.setText("个人介绍");

    }

    @Override
    public void initData() {
        ed1.setText(doctor_introduce);
        ed2.setText(doctor_background);
        ed3.setText(doctor_winning);
        ed4.setText(doctor_remarks);


    }

    @OnClick({R.id.ivLeft,R.id.tvRight, R.id.tv_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                break;
            case R.id.tv_submit:

                Map<String,String> mMap = new HashMap<>();

                mMap.put("member_id",userBean.getMember_id());
                mMap.put("member_token",userBean.getMember_token());
                mMap.put("doctor_introduce",ed1.getText().toString());//简介
                mMap.put("doctor_background",ed2.getText().toString());//背景
                mMap.put("doctor_winning",ed3.getText().toString());//荣誉
                mMap.put("doctor_remarks",ed4.getText().toString());//寄语
                mMap.put("doctor_id",doctor_id);//id
                getPresenter().UpdateDoctor(mMap);
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
    public EditPersonalInfoPresenter createPresenter() {
        return new EditPersonalInfoPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onUpdateDoctor(String data) {

        EventBus.getDefault().post(new EventBusMessage("setting_success"));
        ToastUtils.showToast(context.getApplicationContext(),data);
        finish();
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
}
