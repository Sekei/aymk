package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ConsultBean;
import com.live.tv.bean.FetureDateBeans;
import com.live.tv.mvp.adapter.mine.ConsultListAdapter;
import com.live.tv.mvp.adapter.mine.SevendaysAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.DoctorConsultVideoPresenter;
import com.live.tv.mvp.view.mine.IDoctorConsultVideoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 医生  图文咨询列表
 *
 * @author Created by stone
 * @since 2018/2/1
 */

public class DoctorConsultVideoFragment extends BaseFragment<IDoctorConsultVideoView, DoctorConsultVideoPresenter> implements IDoctorConsultVideoView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    Unbinder unbinder;
    private SevendaysAdapter mAdapter;
    private ConsultListAdapter   consultListAdapter;
    private int  currentPage=1;

    public static DoctorConsultVideoFragment newInstance() {

        Bundle args = new Bundle();

        DoctorConsultVideoFragment fragment = new DoctorConsultVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.doctor_consult_video;
    }

    @Override
    public void initUI() {

        mAdapter = new SevendaysAdapter(new ArrayList<FetureDateBeans>());
        recyclerView.setLayoutManager(new GridLayoutManager(context, 7));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                FetureDateBeans feture= (FetureDateBeans) adapter.getData().get(position);


                mAdapter.setPosition(position);
                mAdapter.notifyDataSetChanged();
                time_str=feture.getConsult_set_day();
                currentPage=1;
                RequestParameters();
            }
        });

        consultListAdapter = new ConsultListAdapter(new ArrayList<ConsultBean>());
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setAdapter(consultListAdapter);

        recyclerView2.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                ConsultBean consultBean = (ConsultBean) adapter.getData().get(position);
                startVoiceVideoCallFragment(consultBean,"doctor");

            }
        });


        consultListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                currentPage++;
                MoreRequestParameters();
            }
        }, recyclerView2);


    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
    // getPresenter().getFetureDay();
        currentPage=1;
        RequestParameters();

    }

    private String time_str="";
    private void RequestParameters() {

        Map<String,String> map = new HashMap<>();
        map.put("doctor_id",userBean.getDoctorBean().getDoctor_id());
        map.put("is_done","0");
        map.put("consult_time",time_str);
        map.put("consult_type","video");
        map.put("page", String.valueOf(currentPage));
        getPresenter().getDoctorConsultList(map);

    }
    private void MoreRequestParameters() {

        Map<String,String> map = new HashMap<>();
        map.put("doctor_id",userBean.getDoctorBean().getDoctor_id());
        map.put("is_done","0");
        map.put("consult_time",time_str);
        map.put("consult_type","video");
        map.put("page", String.valueOf(currentPage));
        getPresenter().getMoreDoctorConsultList(map);

    }


    @Override
    public DoctorConsultVideoPresenter createPresenter() {
        return new DoctorConsultVideoPresenter(getApp());
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

    @Override
    public void onGetFetureDay(List<FetureDateBeans> data) {

        if (data!=null){

            mAdapter.setNewData(data);

            if (data.size()>0){

                FetureDateBeans fetureDateBeans =data.get(0);
                time_str=fetureDateBeans.getConsult_set_day();
            }

            RequestParameters();
        }
    }

    @Override
    public void ongetDoctorConsultList(List<ConsultBean> data) {


        if (data != null) {
            consultListAdapter.setNewData(data);

            if (data.size() == 0) {
                // consultListAdapter.setEmptyView(getActivity().getLayoutInflater().inflate(R.layout.empty_on_order, (ViewGroup) mRecyclerView.getParent(), false));
            }
            if (data.size() < 10) {
                consultListAdapter.loadMoreEnd();
            }
        }
    }

    @Override
    public void ongetMoreDoctorConsultList(List<ConsultBean> data) {

        if (data != null) {
            consultListAdapter.addData(data);
            consultListAdapter.loadMoreComplete();
            if (data.size() < 10) {
                consultListAdapter.loadMoreEnd();
            }

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
