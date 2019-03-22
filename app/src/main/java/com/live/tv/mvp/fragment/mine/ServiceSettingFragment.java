package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.FetureDateBeans;
import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.mvp.adapter.mine.TimeSetting2Adapter;
import com.live.tv.mvp.adapter.mine.TimeSettingAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.ServiceTypeSettingPresenter;
import com.live.tv.mvp.view.mine.IServiceTypeSettingView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/2/1
 */

public class ServiceSettingFragment extends BaseFragment<IServiceTypeSettingView, ServiceTypeSettingPresenter> implements IServiceTypeSettingView {


    @BindView(R.id.tv_edit_1)
    TextView tvEdit1;
    @BindView(R.id.tv_price_1)
    TextView tvPrice1;
    @BindView(R.id.tv_edit_2)
    TextView tvEdit2;
    @BindView(R.id.tv_price_2)
    TextView tvPrice2;
    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    @BindView(R.id.tv_edit_3)
    TextView tvEdit3;
    @BindView(R.id.tv_price_3)
    TextView tvPrice3;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.tv_edit_4)
    TextView tvEdit4;
    @BindView(R.id.recyclerView3)
    RecyclerView recyclerView3;
    Unbinder unbinder;

    private TimeSettingAdapter mAdapter;
    private TimeSettingAdapter mAdapter2;
    private TimeSetting2Adapter mAdapter3;
private DoctorDetailBean doctorUserbean;
    public static ServiceSettingFragment newInstance() {

        Bundle args = new Bundle();

        ServiceSettingFragment fragment = new ServiceSettingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.service_setting;
    }

    @Override
    public void initUI() {
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mAdapter = new TimeSettingAdapter(new ArrayList<FetureDateBeans>());
        recyclerView1.setAdapter(mAdapter);


        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mAdapter2 = new TimeSettingAdapter(new ArrayList<FetureDateBeans>());
        recyclerView2.setAdapter(mAdapter2);


        recyclerView3.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mAdapter3 = new TimeSetting2Adapter(new ArrayList<HealthManagerBeans>());
        recyclerView3.setAdapter(mAdapter3);






    }

    @Override
    public void initData() {

    }


    public void SetServiceInfo(DoctorDetailBean userBean) {
        doctorUserbean=userBean;
        mAdapter.setNewData(userBean.getFetureDateBeans());
        mAdapter2.setNewData(userBean.getFetureDateBeans());

        mAdapter3.setNewData(userBean.getHealthManagerBeans());
        tvPrice1.setText(userBean.getGraphic_price());
        tvPrice2.setText(userBean.getPhone_price());
        tvPrice3.setText(userBean.getVideo_price());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public ServiceTypeSettingPresenter createPresenter() {
        return new ServiceTypeSettingPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_edit_1, R.id.tv_edit_2, R.id.tv_edit_3, R.id.tv_edit_4})
    public void onViewClicked(View view) {


        switch (view.getId()) {
            case R.id.tv_edit_1:
                startImageEditFragment(doctorUserbean.getGraphic_price(),doctorUserbean.getDoctor_id());
                break;
            case R.id.tv_edit_2:
                startPhoneEditFragment(doctorUserbean.getPhone_price(),doctorUserbean.getDoctor_id());

                break;
            case R.id.tv_edit_3:
                startVideoEditFragment(doctorUserbean.getVideo_price(),doctorUserbean.getDoctor_id());

                break;
            case R.id.tv_edit_4:

                startServiceTypeFragment("","");
                break;
        }
    }


    @Override
    public void onUpdateHealthManager(String data) {

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
}
