package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.TimeSetting2Presenter;
import com.live.tv.mvp.view.mine.ITimeSetting2View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.addapp.pickers.picker.DateTimePicker;

/**
 * @author Created by stone
 * @since 2018/2/1
 */

public class TimeSettingFragment2 extends BaseFragment<ITimeSetting2View, TimeSetting2Presenter> implements ITimeSetting2View {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_cancel)
    TextView tvNum;
    Unbinder unbinder1;
    int newYear;
    int newMon;
    int newDay;

    int mYear;
    int mMon;
    int mDay;
    int mHour;
    int mMinute;
    String start_time_str = "";
    String end_time_str = "";
    String date_str = "";
    String type = "";
    Date date = null;
    private String current_time_str = "";

    public static TimeSettingFragment2 newInstance(String date_str, String type) {

        Bundle args = new Bundle();
        TimeSettingFragment2 fragment = new TimeSettingFragment2();
        fragment.date_str = date_str;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_time_setting2;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.time_setting);
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);//年
        mMon = c.get(Calendar.MONTH) + 1;//月
        mDay = c.get(Calendar.DATE);//日
        mHour = c.get(Calendar.HOUR_OF_DAY);//时
        mMinute = c.get(Calendar.MINUTE);//分

        if (mMon < 10) {

            if (mDay < 10) {

                current_time_str = mYear + "-" + "0" + mMon + "-" + "0" + mDay;
            } else {
                current_time_str = mYear + "-" + "0" + mMon + "-" + mDay;
            }

        } else {

            if (mDay < 10) {

                current_time_str = mYear + "-" + mMon + "-" + "0" + mDay;
            } else {
                current_time_str = mYear + "-" + mMon + "-" + mDay;
            }


        }


    }

    String[] strs;

    @Override
    public void initData() {

        if (!date_str.equals(current_time_str)) {
            mHour = 0;
            mMinute = 0;
        }


        strs = date_str.split("-");
        newYear = Integer.parseInt(strs[0]);
        newMon = Integer.parseInt(strs[1]);
        newDay = Integer.parseInt(strs[2]);
        Log.i("dfc", "initData: " + strs[0] + strs[1] + strs[2]);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public TimeSetting2Presenter createPresenter() {
        return new TimeSetting2Presenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @OnClick({R.id.tv_start, R.id.tv_end, R.id.tv_cancel, R.id.tv_submit, R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_start:
                onYearMonthDayTimePicker(tvStart);
                break;
            case R.id.tv_end:
                onYearMonthDayTimePicker(tvEnd);
                break;
            case R.id.tv_cancel:
                break;
            case R.id.tv_submit:


                if (checkInputKey()) {

                    try {

                        if (compareTime(tvStart.getText().toString(), tvEnd.getText().toString())){

                            ToastUtils.showToast(context.getApplicationContext(),"结束时间应该大于开始时间");
                            return;
                        }




                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Map<String, String> map = new HashMap<>();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
                    map.put("start_time", start_time_str);
                    map.put("end_time", end_time_str);
                    map.put("consult_type", type);
                    map.put("consult_set_day", date_str);
                    map.put("member_num", tvNum.getText().toString().trim());

                    getPresenter().setConsultTime(map);
                }


                break;


        }
    }

    private boolean compareTime(String start_time_str, String end_time_str) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //将字符串形式的时间转化为Date类型的时间

        Date a = sdf.parse(start_time_str);
        Date b = sdf.parse(end_time_str);


        if (b.getTime()-a.getTime()<=0){
            return  true;
        }else {
            return false;
        }

    }

    @Override
    public void onConsultTime(String data) {

        if (data != null) {

            ToastUtils.showToast(context.getApplicationContext(), data);
            finish();
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

        errorHandle(e);
    }

    private boolean checkInputKey() {

        if (StringUtils.isBlank(start_time_str)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.input_start_time));
            return false;
        }
        if (StringUtils.isBlank(end_time_str)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.input_end_time));
            return false;
        }


        if (StringUtils.isBlank(tvNum.getText().toString().trim())) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.input_service_num));
            return false;
        }


        return true;
    }


    public void onYearMonthDayTimePicker(final TextView textView) {
        DateTimePicker picker = new DateTimePicker(getActivity(), DateTimePicker.HOUR_24);
        picker.setCanLoop(false);//不禁用循环
        picker.setDateRangeStart(newYear, newMon, newDay);
        picker.setDateRangeEnd(newYear, newMon, newDay);
        picker.setTimeRangeStart(mHour, mMinute);

        picker.setLineColor(getResources().getColor(R.color.colorAccent));
        picker.setSelectedTextColor(getResources().getColor(R.color.colorAccent));
        picker.setSubmitTextColor(getResources().getColor(R.color.colorAccent));
        picker.setWeightEnable(true);
        picker.setWheelModeEnable(true);
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                //showToast(year + "-" + month + "-" + day + " " + hour + ":" + minute);

                textView.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute);

                switch (textView.getId()) {

                    case R.id.tv_start:
                        start_time_str = hour + ":" + minute + ":" + "00";
                        break;

                    case R.id.tv_end:
                        end_time_str = hour + ":" + minute + ":" + "00";
                        break;
                }


            }
        });

        picker.show();
    }
}
