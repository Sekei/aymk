package com.live.tv.mvp.fragment.mine;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.DepartmentLevelOneBean;
import com.live.tv.bean.DepartmentLevelTwoBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.DoctorTitleBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.ChooseDoctorTitleDialog;
import com.live.tv.mvp.fragment.dialog.EditHospitalFragment;
import com.live.tv.mvp.fragment.dialog.EditJobLevelFragment;
import com.live.tv.mvp.fragment.dialog.EditZhuzhiTypeFragment;
import com.live.tv.mvp.presenter.mine.BaseInfoPresenter;
import com.live.tv.mvp.view.mine.IBaseInfoView;
import com.live.tv.util.city.DepartmentPopwindowBottom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ysjk.health.iemk.R.id.tv_hospital;

/**
 * @author Created by stone
 * @since 2018/2/1
 */

public class BaseInfoFragment extends BaseFragment<IBaseInfoView,BaseInfoPresenter> implements IBaseInfoView {


    @BindView(R.id.heightTittle)
    TextView heightTittle;
    @BindView(tv_hospital)
    TextView tvHospital;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_edit_2)
    TextView tvEdit2;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_person_info)
    TextView tvPersonInfo;
    Unbinder unbinder;
    @BindView(R.id.t1)
    TextView t1;

    private DoctorDetailBean DoctorInfo;
    Map<String,String>  mMap= new HashMap<>();
    private DepartmentPopwindowBottom departmentPopwindow;
    private List<DoctorTitleBean> doctorTitleBeanList;

    public static BaseInfoFragment newInstance() {
        Bundle args = new Bundle();
        BaseInfoFragment fragment = new BaseInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.base_info;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

        mMap.clear();
        getPresenter().getDepartments(mMap);
        getPresenter().getDoctorTitle();
    }

    public void SetDoctorInfo(DoctorDetailBean userBean) {
        DoctorInfo=userBean;
        tvHospital.setText(userBean.getDoctor_hospital());
        tvTitle.setText(userBean.getJob_level());
        tvDepartment.setText(userBean.getDepartment_level2());
        tvType.setText(userBean.getDoctor_attendingtype());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public BaseInfoPresenter createPresenter() {
        return new BaseInfoPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({tv_hospital, R.id.tv_title, R.id.tv_edit_2, R.id.tv_department, R.id.tv_type, R.id.tv_person_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case tv_hospital:
                startEditHospitalFragment(tvHospital.getText().toString(), context);
                break;
            case R.id.tv_title:

                startEditJobLevelFragment(DoctorInfo.getJob_level(), context);
                break;
            case R.id.tv_edit_2:
                break;
            case R.id.tv_department:

                showDepartmentPop();
                break;
            case R.id.tv_type:
                startEditZhuzhiTypeFragment(tvType.getText().toString(), context);

                break;
            case R.id.tv_person_info:

                if (DoctorInfo!=null){
                    startEditPersonalInfoFragment(DoctorInfo.getDoctor_id(),DoctorInfo.getDoctor_introduce(),DoctorInfo.getDoctor_background(),DoctorInfo.getDoctor_winning(),DoctorInfo.getDoctor_remarks());
                }


                break;
        }
    }



    public void startEditHospitalFragment(String hospitalName, Context context) {
        final EditHospitalFragment fragment = EditHospitalFragment.newInstance(hospitalName, context);
        fragment.setOkClickListener(new EditHospitalFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                tvHospital.setText(state);
                mMap.clear();
                mMap.put("member_id",userBean.getMember_id());
                mMap.put("member_token",userBean.getMember_token());
                mMap.put("doctor_id",DoctorInfo.getDoctor_id());
                mMap.put("doctor_hospital",state);
               getPresenter().UpdateDoctor(mMap);


                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), JoinDoctorFragment.class.getSimpleName());

    }
 public void startEditZhuzhiTypeFragment(String Data, Context context) {
        final EditZhuzhiTypeFragment fragment = EditZhuzhiTypeFragment.newInstance(Data, context);
        fragment.setOkClickListener(new EditZhuzhiTypeFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                tvType.setText(state);
                mMap.clear();
                mMap.put("member_id",userBean.getMember_id());
                mMap.put("member_token",userBean.getMember_token());
                mMap.put("doctor_id",DoctorInfo.getDoctor_id());
                mMap.put("doctor_attendingtype",state);
               getPresenter().UpdateDoctor(mMap);


                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), JoinDoctorFragment.class.getSimpleName());

    } public void startEditJobLevelFragment(String Data, Context context) {
        final EditJobLevelFragment fragment = EditJobLevelFragment.newInstance(Data, context);
        fragment.setOkClickListener(new EditJobLevelFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                tvTitle.setText(state);
                mMap.clear();
                mMap.put("member_id",userBean.getMember_id());
                mMap.put("member_token",userBean.getMember_token());
                mMap.put("doctor_id",DoctorInfo.getDoctor_id());
                mMap.put("job_level",state);
               getPresenter().UpdateDoctor(mMap);


                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), JoinDoctorFragment.class.getSimpleName());

    }

    @Override
    public void onUpdateDoctor(String data) {

    }

    private ArrayList<DepartmentLevelOneBean> listOne = new ArrayList<>();

    @Override
    public void onGetDepartments(List<DepartmentLevelOneBean> data) {
        listOne.clear();
        listOne.addAll(data);
    }

    @Override
    public void onDoctorTitleList(List<DoctorTitleBean> data) {
        if (data != null) {

            doctorTitleBeanList = data;
        }
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

    public void startChosoeDoctortitle(List<DoctorTitleBean> doctorTitleBeanList, Context context) {
        final ChooseDoctorTitleDialog fragment = ChooseDoctorTitleDialog.newInstance(doctorTitleBeanList, context);
        fragment.setOkClickListener(new ChooseDoctorTitleDialog.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                tvTitle.setText(state);
                mMap.clear();
                mMap.put("member_id",userBean.getMember_id());
                mMap.put("member_token",userBean.getMember_token());
                mMap.put("doctor_id",DoctorInfo.getDoctor_id());
                mMap.put("job_level",state);
                getPresenter().UpdateDoctor(mMap);

                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), JoinDoctorFragment.class.getSimpleName());

    }

    private String department_level1 = "";
    private String department_level2 = "";
    //科室
    private void showDepartmentPop() {
        if (departmentPopwindow == null) {
            departmentPopwindow = new DepartmentPopwindowBottom(context).setOutsideTouchable(false);
        }
        if (!departmentPopwindow.isShowing()) {
            departmentPopwindow.setData(listOne).cerate().setOnItemClickListener(new DepartmentPopwindowBottom.onItemClickListener() {
                @Override
                public void onclick(DepartmentLevelOneBean departmentLevelOneBean, DepartmentLevelTwoBean data) {
                    if (data != null) {

                        department_level1 = departmentLevelOneBean.getDepartment_name();
                        department_level2 = data.getDepartment_name();
                        tvDepartment.setText(department_level2);

                        mMap.clear();
                        mMap.put("member_id",userBean.getMember_id());
                        mMap.put("member_token",userBean.getMember_token());
                        mMap.put("doctor_id",DoctorInfo.getDoctor_id());
                        mMap.put("department_level1",department_level1);
                        mMap.put("department_level2",department_level2);
                        getPresenter().UpdateDoctor(mMap);

                    }
                    departmentPopwindow.dismiss();
                }
            }).show(tvDepartment);
        }
    }
}
