package com.live.tv.mvp.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ConsultBean;
import com.live.tv.bean.FetureDateBeans;
import com.live.tv.bean.FirstEvent;
import com.live.tv.mvp.adapter.mine.ConsultListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.huanxin.ChatActivity;
import com.live.tv.mvp.presenter.mine.DoctorConsultVideoPresenter;
import com.live.tv.mvp.view.mine.IDoctorConsultVideoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 医生  图文咨询列表
 * @author Created by stone
 * @since 2018/2/1
 */

public class DoctorConsultImageFragment  extends BaseFragment<IDoctorConsultVideoView, DoctorConsultVideoPresenter> implements IDoctorConsultVideoView , SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    private ConsultListAdapter consultListAdapter;
    private int currentPage = 1;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static DoctorConsultImageFragment newInstance() {

        Bundle args = new Bundle();

        DoctorConsultImageFragment fragment = new DoctorConsultImageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.doctor_consult_image;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)

    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("close_advisory")) {
            currentPage=1;
            RequestParameters();
            getPresenter().getDoctorConsultList(map);

        }
    }

    @Override
    public void initUI() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        consultListAdapter = new ConsultListAdapter(new ArrayList<ConsultBean>());
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setAdapter(consultListAdapter);

        recyclerView2.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                ConsultBean consultBean = (ConsultBean) adapter.getData().get(position);
//                startVoiceVideoCallFragment(consultBean);


               // startActivity(new Intent(getActivity(), ChatActivity.class).putExtra("userId",consultBean.getHx_account()).putExtra("APP_user_name",consultBean.getMember_nick_name()));

                startActivity(new Intent(getActivity(), ChatActivity.class)
                        .putExtra("userId",consultBean.getHx_account())
                        .putExtra("APP_user_name",consultBean.getDoctor_name())
                        .putExtra("health_record_id",consultBean.getHealth_record_id())
                        .putExtra("doctor_id",consultBean.getDoctor_id())
                        .putExtra("consult_record_id",consultBean.getConsult_record_id())
                        .putExtra("to_head_image",consultBean.getMember_head_image())
                        .putExtra("to_username",consultBean.getMember_nick_name())
                        .putExtra("consultation_type","1")
                        .putExtra("status",consultBean.getState_show()));


            }
        });


        consultListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                currentPage++;
                RequestParameters();
                getPresenter().getMoreDoctorConsultList(map);

            }
        }, recyclerView2);
    }

    @Override
    public void initData() {


    }

    @Override
    public void onResume() {
        super.onResume();
        RequestParameters();
        getPresenter().getDoctorConsultList(map);
    }

    private String time_str="";
    private void RequestParameters() {

         map.clear();
        map.put("doctor_id",userBean.getDoctorBean().getDoctor_id());
        map.put("is_done","0");
        map.put("consult_time",time_str);
        map.put("consult_type","text");
        map.put("page", String.valueOf(currentPage));
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
        mSwipeRefreshLayout.setRefreshing(false);
        errorHandle(e);
    }

    @Override
    public void onGetFetureDay(List<FetureDateBeans> data) {

    }

    @Override
    public void ongetDoctorConsultList(List<ConsultBean> data) {

        mSwipeRefreshLayout.setRefreshing(false);
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
        mSwipeRefreshLayout.setRefreshing(false);
        if (data != null) {
            consultListAdapter.addData(data);
            consultListAdapter.loadMoreComplete();
            if (data.size() < 10) {
                consultListAdapter.loadMoreEnd();
            }

        }
    }

    @Override
    public void onRefresh() {
        currentPage=1;
        RequestParameters();
        getPresenter().getDoctorConsultList(map);
    }
}
