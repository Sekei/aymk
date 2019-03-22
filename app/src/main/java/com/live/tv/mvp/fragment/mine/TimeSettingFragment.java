package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.ConsultTimesBean;
import com.live.tv.mvp.adapter.mine.ConsultTimeAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.TimeSettingPresenter;
import com.live.tv.mvp.view.mine.ITimeSettingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/2/1
 */

public class TimeSettingFragment extends BaseFragment<ITimeSettingView,TimeSettingPresenter> implements ITimeSettingView  {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.sw_on)
    Switch swOn;
    @BindView(R.id.recyclerView4)
    RecyclerView recyclerView4;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    Unbinder unbinder;
    private String date_str;
    private String type;

    private ConsultTimeAdapter mAdapter;
    public static TimeSettingFragment newInstance(String date_str,String type) {

        Bundle args = new Bundle();

        TimeSettingFragment fragment = new TimeSettingFragment();
        fragment.date_str=date_str;
        fragment.type=type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_time_setting;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.time_setting);

        swOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //控制开关字体颜色
                if (isChecked) {
                    swOn.setSwitchTextAppearance(getActivity(),R.style.s_true);
                }else {
                    swOn.setSwitchTextAppearance(getActivity(),R.style.s_false);
                }



            }
        });

        swOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isopen=swOn.isChecked()==true?"0":"1";
                Map<String,String> map = new HashMap<>();
                map.put("member_id",userBean.getMember_id());
                map.put("member_token",userBean.getMember_token());
                map.put("doctor_id",userBean.getDoctorBean().getDoctor_id());
                map.put("consult_type",type);
                map.put("consult_set_day",date_str);
                map.put("is_open",isopen);
                getPresenter().setOpenTime(map);

            }
        });

        recyclerView4.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mAdapter = new ConsultTimeAdapter(new ArrayList<ConsultTimesBean>());
        recyclerView4.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Map<String,String> map = new HashMap<>();
        map.put("doctor_id",userBean.getDoctorBean().getDoctor_id());
        map.put("consult_type",type);
        map.put("consult_set_day",date_str);
        getPresenter().setConsultTime(map);
    }

    @Override
    public void initData() {

        if (swOn.isChecked()){
            recyclerView4.setVisibility(View.VISIBLE);

        }else {
            recyclerView4.setVisibility(View.GONE);
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
    public TimeSettingPresenter createPresenter() {
        return new TimeSettingPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({ R.id.tv_submit,R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_submit:

                startTimeSetting2Fragment(date_str,type);

                break;

            case R.id.ivLeft:
                finish();
                break;
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

    @Override
    public void onConsultTimeList(List<ConsultTimesBean> data) {

        if (data!=null&&data.size()>0){
            mAdapter.setNewData(data);

            if (data.get(0).getIs_open().equals("0")){
                swOn.setChecked(true);
                swOn.setSwitchTextAppearance(getActivity(),R.style.s_true);
                recyclerView4.setVisibility(View.VISIBLE);
            }else {
                swOn.setChecked(false);
                swOn.setSwitchTextAppearance(getActivity(),R.style.s_false);
                recyclerView4.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onsetOpenTime(String data) {
        initData();
       // ToastUtils.showToast(context.getApplicationContext(),data);
    }
}
