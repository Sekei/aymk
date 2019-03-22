package com.live.tv.mvp.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthPlanBean;
import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.AddPlanPresenter;
import com.live.tv.mvp.view.mine.IAddPlanView;
import com.live.tv.util.LoadingUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.addapp.pickers.picker.DatePicker;

/**
 * 医生端，给用户添加方案
 * Created by sh-lx on 2017/7/12.
 */

public class AddPlanFragment extends BaseFragment<IAddPlanView, AddPlanPresenter> implements IAddPlanView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.ed_title)
    EditText edTitle;
    @BindView(R.id.tv_txt_num)
    TextView tvTxtNum;


    @BindView(R.id.ed_content)
    EditText edContent;
    Unbinder unbinder;
    HealthRecordDetailBean.HealthPlanBeansBean healthPlanBeansBean;
    private String health_record_id = "";

    int mYear;
    int mMon;
    int mDay;

    private String current_time_str = "";
    String[] strs;

    public static AddPlanFragment newInstance(String health_record_id) {

        Bundle args = new Bundle();
        AddPlanFragment fragment = new AddPlanFragment();
        fragment.health_record_id = health_record_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_plan;
    }

    @Override
    public void initUI() {
        tvTitle.setText("添加方案");
        tvRight.setText("保存");
        tvRight.setVisibility(View.VISIBLE);

        edTitle.addTextChangedListener(mTextWatcher);
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);//年
        mMon = c.get(Calendar.MONTH) + 1;//月
        mDay = c.get(Calendar.DATE);//日

//        if (mMon < 10) {
//
//            if (mDay < 10) {
//
//                current_time_str = mYear + "-" + "0" + mMon + "-" + "0" + mDay;
//            } else {
//                current_time_str = mYear + "-" + "0" + mMon + "-" + mDay;
//            }
//
//        } else {
//
//            if (mDay < 10) {
//
//                current_time_str = mYear + "-" + mMon + "-" + "0" + mDay;
//            } else {
//                current_time_str = mYear + "-" + mMon + "-" + mDay;
//            }
//
//
//        }
    }


    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            temp = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
//          mTextView.setText(s);//将输入的内容实时显示
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            editStart = edTitle.getSelectionStart();
            editEnd = edTitle.getSelectionEnd();
            tvTxtNum.setText(temp.length() + "/16");

            if (temp.length() > 16) {
                ToastUtils.showToast(context.getApplicationContext(), "输入字数超限");
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                edTitle.setText(s);
                edTitle.setSelection(tempSelection);
            }
        }
    };

    @Override
    public void initData() {
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("health_record_id", health_record_id);
        map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
        getPresenter().getHealthPlan(map);


    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tv_submit, R.id.tv_start,R.id.tv_end})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:

                if (checkInputKey()){
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("start_time", tvStart.getText().toString());
                    map.put("end_time", tvEnd.getText().toString());
                    map.put("plan_desc", edContent.getText().toString().trim());
                    map.put("plan_name", edTitle.getText().toString().trim());
                    map.put("health_record_id", health_record_id);
                    map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
                    map.put("is_send", "0");
                    getPresenter().Addplan(map);
                    LoadingUtil.showLoadingNew(context, "保存中...");
                }

                break;
            case R.id.tv_submit:
                if (checkInputKey()) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("start_time", tvStart.getText().toString());
                    map.put("end_time", tvEnd.getText().toString());
                    map.put("plan_desc", edContent.getText().toString().trim());
                    map.put("plan_name", edTitle.getText().toString().trim());
                    map.put("health_record_id", health_record_id);
                    map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
                    map.put("is_send", "1");
                    getPresenter().Addplan(map);
                    LoadingUtil.showLoadingNew(context, "发布中...");
                }
                break;

            case R.id.tv_start:
                onYearMonthDayTimePicker(tvStart);

                break;
            case R.id.tv_end:
                onYearMonthDayTimePicker(tvEnd);
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
    public AddPlanPresenter createPresenter() {
        return new AddPlanPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void AddPaln(String data) {
        LoadingUtil.hideLoading();
        ToastUtils.showToast(context.getApplicationContext(), data);
        Intent intent = new Intent();
        intent.putExtra("key", "save_success");
        getActivity().setResult(1, intent);
        finish();
    }

    @Override
    public void getHealthPlan(HealthPlanBean data) {
        LoadingUtil.hideLoading();
        if (data != null) {

            edTitle.setText(data.getPlan_name());
            edContent.setText(data.getPlan_desc());
            tvStart.setText(data.getStart_time());
            tvEnd.setText(data.getEnd_time());
        }
    }

    @Override
    public void SendHealthPlan(String data) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        LoadingUtil.hideLoading();
        errorHandle(e);
    }


    public void onYearMonthDayTimePicker(final TextView textView) {
        DatePicker picker = new DatePicker(getActivity());
        picker.setCanLoop(false);//不禁用循环
        picker.setRangeStart(mYear, mMon, mDay);
        picker.setRangeEnd(2030, 12, 31);
        picker.setLineColor(getResources().getColor(R.color.colorAccent));
        picker.setSelectedTextColor(getResources().getColor(R.color.colorAccent));
        picker.setSubmitTextColor(getResources().getColor(R.color.colorAccent));
        picker.setWeightEnable(true);
        picker.setWheelModeEnable(true);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {

                textView.setText(year + "-" + month + "-" + day);

            }
        });
        picker.show();
    }



    private boolean checkInputKey() {
        if (TextUtils.isEmpty(tvStart.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), "请选择开始时段");
            return false;
        }
          if (TextUtils.isEmpty(tvEnd.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), "请选择结束时段");
            return false;
        }

        try {
            if (compareTime(tvStart.getText().toString(), tvEnd.getText().toString())){

                ToastUtils.showToast(context.getApplicationContext(),"结束时段应该大于开始时段");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return true;
    }
    private boolean compareTime(String start_time_str, String end_time_str) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //将字符串形式的时间转化为Date类型的时间

        Date a = sdf.parse(start_time_str);
        Date b = sdf.parse(end_time_str);


        if (b.getTime()-a.getTime()<=0){
            return  true;
        }else {
            return false;
        }

    }
}
