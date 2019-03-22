package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.mvp.adapter.home.ServiceTypeAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.ServiceTypePresenter;
import com.live.tv.mvp.view.mine.IServiceTypeView;
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

public class ServiceTypeFragment extends BaseFragment<IServiceTypeView, ServiceTypePresenter> implements IServiceTypeView {
    String date_str;
    String type;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private Map<String,String> map=new HashMap<>();
    private ServiceTypeAdapter mAdapter;

    public static ServiceTypeFragment newInstance(String date_str, String type) {

        Bundle args = new Bundle();

        ServiceTypeFragment fragment = new ServiceTypeFragment();
        fragment.date_str = date_str;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
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
    public int getRootViewId() {
        return R.layout.fragment_service_type;
    }

    @Override
    public void initUI() {
        tvTitle.setText(getResources().getString(R.string.service_setting));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ServiceTypeAdapter(getActivity());
        mAdapter.SetOnClickLisenter(new ServiceTypeAdapter.OnEditChangelisenter() {
            @Override
            public void setData(HealthManagerBeans beans) {
                addhealthmanagefragment(beans);

            }
        });
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void initData() {



    }


    @Override
    public void onResume() {
        super.onResume();
        map.put("doctor_id",userBean.getDoctorBean().getDoctor_id());
        getPresenter().getservicetypeList(map);

    }

    @Override
    public ServiceTypePresenter createPresenter() {
        return new ServiceTypePresenter(getApp());
    }

    @Override
    public void onHealthManager(List<HealthManagerBeans> data) {

        if (data != null) {

          //  mAdapter.setNewData(data);
        }
    }

    @Override
    public void onservicetypeList(List<HealthManagerBeans> data) {

        mAdapter.clear();
        mAdapter.addAll(data);
        mAdapter.notifyDataSetChanged();

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

    @OnClick({R.id.ivLeft,R.id.addsetvice})
    public void onViewClicked(View view) {
       switch (view.getId()){
           case R.id.ivLeft:

               finish();
               break;
           case R.id.addsetvice:

               addhealthmanagefragment("");
               break;
       }
    }
}
