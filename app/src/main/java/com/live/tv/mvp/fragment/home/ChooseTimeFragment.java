package com.live.tv.mvp.fragment.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ConsultTimesBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.WeekAdapter;
import com.live.tv.mvp.adapter.home.WeekListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.ChooseTimePresenter;
import com.live.tv.mvp.view.home.IChooseTimeView;
import com.live.tv.util.SpSingleInstance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public class ChooseTimeFragment extends BaseFragment<IChooseTimeView, ChooseTimePresenter> implements IChooseTimeView {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.easyRecyclerView)
    RecyclerView easyRecyclerView;

    @BindView(R.id.easyRecyclerViewTwo)
    EasyRecyclerView easyRecyclerViewTwo;
    Unbinder unbinder;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.open)
    TextView open;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private WeekAdapter adapter;
    private WeekListAdapter adapterTwo;
    private String doctor_id;
    private String type;
    List<String> getdata = new ArrayList<>();
    List<ConsultTimesBean> list;
    private String today;//当前日期
    private String week;//当前周几

    private String service_price="";

    public static ChooseTimeFragment newInstance(String doctor_id, String type,String service_price) {
        Bundle args = new Bundle();
        ChooseTimeFragment fragment = new ChooseTimeFragment();
        fragment.type = type;
        fragment.doctor_id = doctor_id;
        fragment.service_price = service_price;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_time;
    }

    @Override
    public void initUI() {
        tvTitle.setText("预约时间");
        adapter = new WeekAdapter(context, getdata());
        easyRecyclerView.setLayoutManager(new GridLayoutManager(context, 7));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                String data= setDate(position);

                map.clear();
                map.put("doctor_id", doctor_id);
                map.put("consult_type", type);
                map.put("consult_set_day", data);
                getPresenter().getConsultTimes(map);
                adapter.setPosition(position);
                adapter.notifyDataSetChanged();

//
//                if (position == 0) {
//                    map.clear();
//                    map.put("doctor_id", doctor_id);
//                    map.put("consult_type", "phone");
//                    map.put("consult_set_day", "2018-12-26");
//                    getPresenter().getConsultTimes(map);
//                    adapter.setPosition(position);
//                    adapter.notifyDataSetChanged();
//                } else {
//
//                    String data= setDate(position);
//
//                    map.clear();
//                    map.put("doctor_id", doctor_id);
//                    map.put("consult_type", "phone");
//                    map.put("consult_set_day", data);
//                    getPresenter().getConsultTimes(map);
//                    adapter.setPosition(position);
//                    adapter.notifyDataSetChanged();
//                }


            }
        });
        adapter.setPosition(0);
        adapter.notifyDataSetChanged();
        list = new ArrayList<>();

        adapterTwo = new WeekListAdapter(context, list);
        easyRecyclerViewTwo.setLayoutManager(new GridLayoutManager(context, 2));
        easyRecyclerViewTwo.setAdapter(adapterTwo);
        adapterTwo.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String start_time = adapterTwo.getItem(position).getStart_time();
                String end_time = adapterTwo.getItem(position).getEnd_time();


                ConsultTimesBean consultTimesBean= adapterTwo.getItem(position);


                if (consultTimesBean.getFlag().equals("1")){

                    ToastUtils.showToast(context.getApplicationContext(),"不可预约");
                    return;
                }

                if ("phone".equals(type)) {
                    startBuyConsultVideoFragment("phone", service_price,consultTimesBean.getDoctor_name(),consultTimesBean);


                } else {
                    startBuyConsultVideoFragment("video", service_price,consultTimesBean.getDoctor_name(),consultTimesBean);
                }
            }
        });
        adapterTwo.setOnClickListener(new WeekListAdapter.onClickListener() {
            @Override
            public void onOpen() {



            }
        });

    }



    @Override
    public void initData() {
        getToday();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();


        String str=setDate(0);
        map.put("doctor_id", doctor_id);
        map.put("consult_type", type);
        map.put("consult_set_day", str);
        getPresenter().getConsultTimes(map);

    }


    private String setDate(int position) {

        Calendar c = Calendar.getInstance();
        long millis = System.currentTimeMillis();
        if(position>0){
            millis = millis + (1000 * position * 60 * 60 * 24);
        }
        c.setTime(new Date(millis));
        int year = c.get(Calendar.YEAR);
        int months = c.get(Calendar.MONTH) + 1;
        String month = months + "";
        if (months < 10) {
            month = "0" + months;
        }
        int day = c.get(Calendar.DAY_OF_MONTH);
        String dayStr="";
        if (day < 10) {
            dayStr = "0" + day;
        }else {
            dayStr =  day+"";
        }

        int week = c.get(Calendar.DAY_OF_WEEK);
        String  str=year+"-"+month+"-"+dayStr;

        switch (week) {
            case 1:
                time.setText(str+"  周日");
                break;
            case 2:
                time.setText(str+"  周一");
                break;
            case 3:
                time.setText(str+"  周二");
                break;
            case 4:
                time.setText(str+"  周三");
                break;
            case 5:
                time.setText(str+"  周四");
                break;
            case 6:
                time.setText(str+"  周五");
                break;
            case 7:
                time.setText(str+"  周六");
                break;
        }
        return str;

    }

    @Override
    public void onGetConsultTimes(List<ConsultTimesBean> data) {
        if (data!=null&&data.size() > 0) {
            today = data.get(0).getConsult_set_day();
            week = data.get(0).getDate();
            time.setText(today + " " + week);
            open.setText("可预约");
            open.setTextColor(getResources().getColor(R.color.colorAccent));
            setDrawableRight(open,R.drawable.consult_time_spread);


        }else {

            open.setText("不可预约");
            open.setTextColor(getResources().getColor(R.color.colorTextG6));
            setDrawableRight(open,R.drawable.me_right);
        }
        adapterTwo.clear();
        adapterTwo.addAll(data);
        adapterTwo.notifyDataSetChanged();
    }

    private void setDrawableRight(TextView attention, int drawableId) {
        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        attention.setCompoundDrawables(null, null, drawable, null);
    }
    private List<String> getdata() {
        List<String> date = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        long millis = System.currentTimeMillis();

        for (int i = 0; i < 7; i++) {
            c.setTime(new Date(millis));
            millis = millis + (1000 * 1 * 60 * 60 * 24);
            int months = c.get(Calendar.MONTH) + 1;
            String month = months + "";
            if (months < 10) {
                month = "0" + months;
            }
            int day = c.get(Calendar.DAY_OF_MONTH);
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (i == 0) {
                switch (week) {
                    case 1:
                        date.add("日\n今");
                        break;
                    case 2:
                        date.add("一\n今");
                        break;
                    case 3:
                        date.add("二\n今");
                        break;
                    case 4:
                        date.add("三\n今");
                        break;
                    case 5:
                        date.add("四\n今");
                        break;
                    case 6:
                        date.add("五\n今");
                        break;
                    case 7:
                        date.add("六\n今");
                        break;
                }


            } else {
                switch (week) {
                    case 1:
                        date.add("日\n" + (day));
                        break;
                    case 2:
                        date.add("一\n" + (day));
                        break;
                    case 3:
                        date.add("二\n" + (day));
                        break;
                    case 4:
                        date.add("三\n" + (day));
                        break;
                    case 5:
                        date.add("四\n" + (day));
                        break;
                    case 6:
                        date.add("五\n" + (day));
                        break;
                    case 7:
                        date.add("六\n" + (day));
                        break;
                }
            }
        }

        return date;
    }

    @OnClick({R.id.ivLeft,R.id.open})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.open:
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
    public ChooseTimePresenter createPresenter() {
        return new ChooseTimePresenter(getApp());
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

    /*获取今天往后一周的日期（年-月-日） */
    public static List<String> get7date() {
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        String date = sim.format(c.getTime());
        dates.add(date);
        for (int i = 0; i < 6; i++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            date = sim.format(c.getTime());
            dates.add(date);
        }
        return dates;
    }

    //获取系统当前时间
    public void getToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        today = formatter.format(curDate);
    }
}