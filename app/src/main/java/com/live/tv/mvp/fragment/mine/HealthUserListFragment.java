package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.zhouwei.library.CustomPopWindow;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.mvp.adapter.mine.HealthUserAdapter;
import com.live.tv.mvp.adapter.mine.PopServiceTypeAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.HealthUserPresenter;
import com.live.tv.mvp.view.mine.IHealthUserView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class HealthUserListFragment extends BaseFragment<IHealthUserView, HealthUserPresenter> implements IHealthUserView {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.choose)
    TextView choose;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecycleView;
    Unbinder unbinder;
    private List<HealthRecordDetailBean> list;
    private HealthUserAdapter mAdapter;

    private List<String> ServiceTypeList;
    private List<String> PlanList;
    private List<String> NewList;

    private String service_type_show = "";
    private String plan_state = "";
    private String latest = "";
    View loadMore;
    private int page = 1;
    private boolean isMore = true;

    public static HealthUserListFragment newInstance() {
        Bundle args = new Bundle();
        HealthUserListFragment fragment = new HealthUserListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_health_user_list;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.health_user);
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        list = new ArrayList<>();
        mAdapter = new HealthUserAdapter(context, list);
        easyRecycleView.setLayoutManager(new LinearLayoutManager(context));
        easyRecycleView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HealthRecordDetailBean healthRecordDetailBean = mAdapter.getItem(position);
                startHealthUserDetailFragment(healthRecordDetailBean.getHealth_record_id());

            }
        });


        //刷新
        easyRecycleView.setRefreshingColorResources(R.color.colorPrimary);
        easyRecycleView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                RequestParameters();
            }
        });
        //下拉加载
        mAdapter.setMore(loadMore, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                if (isMore) {
                    if (loadMore != null) {
                        loadMore.setVisibility(View.VISIBLE);
                    }
                    page++;
                    RequestParameters();

                }
            }

            @Override
            public void onMoreClick() {

            }
        });


    }

    @Override
    public void initData() {
        RequestParameters();
        ServiceTypeList = new ArrayList<>();
        ServiceTypeList.add("体验用户");
        ServiceTypeList.add("季度用户");
        ServiceTypeList.add("年度用户");
        ServiceTypeList.add("VIP用户");


        PlanList = new ArrayList<>();

        PlanList.add("待建立");
        PlanList.add("已建立");
        PlanList.add("正在进行");
        PlanList.add("已完成");
        NewList = new ArrayList<>();

        NewList.add("最新用户");
        NewList.add("最近建立");

    }

    private void RequestParameters() {

        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
        map.put("service_type_show", service_type_show);
        map.put("plan_state", plan_state);
        map.put("latest", latest);
        map.put("page", page + "");
        getPresenter().getRecordsByDoctor(map);
    }

    @Override
    public HealthUserPresenter createPresenter() {
        return new HealthUserPresenter(getApp());
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

    @OnClick({R.id.ivLeft, R.id.address, R.id.department, R.id.choose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.address:
                showPopListView();
                break;
            case R.id.department:
                showPopListView2();
                break;
            case R.id.choose:
                showPopListView3();
                break;
        }
    }

    @Override
    public void onHealthUser(List<HealthRecordDetailBean> data) {
        if (data.size() == 10) {
            isMore = true;
        } else {
            isMore = false;
            loadMore.setVisibility(View.GONE);
        }
        if (page == 1) {
            mAdapter.clear();
        }
        mAdapter.addAll(data);
        mAdapter.notifyDataSetChanged();
    }


    CustomPopWindow mListPopWindow;

    private void showPopListView() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_layout, null);
        //处理popWindow 显示内容
        handleListView(contentView);
        //创建并显示popWindow
        mListPopWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.9f) // 控制亮度
                .size(300, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()
                .showAsDropDown(address, 0, 0);
    }

    private void showPopListView2() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_layout, null);
        //处理popWindow 显示内容
        handleListView2(contentView);
        //创建并显示popWindow
        mListPopWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.9f) // 控制亮度
                .size(300, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()
                .showAsDropDown(department, 0, 0);
    }

    private void showPopListView3() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_layout, null);
        //处理popWindow 显示内容
        handleListView3(contentView);
        //创建并显示popWindow
        mListPopWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.9f) // 控制亮度
                .size(300, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()
                .showAsDropDown(choose, 0, 0);
    }

    private void handleListView(View contentView) {
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PopServiceTypeAdapter adapter = new PopServiceTypeAdapter(ServiceTypeList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                String str = (String) adapter.getData().get(position);

                address.setText(str);
                service_type_show = str;
                page=1;
                RequestParameters();
                mListPopWindow.dissmiss();

            }
        });

    }

    private void handleListView2(View contentView) {
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PopServiceTypeAdapter adapter = new PopServiceTypeAdapter(PlanList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                String str = (String) adapter.getData().get(position);

                department.setText(str);
                plan_state = str;
                page=1;
                RequestParameters();

                mListPopWindow.dissmiss();
            }
        });

    }

    private void handleListView3(View contentView) {
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PopServiceTypeAdapter adapter = new PopServiceTypeAdapter(NewList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                String str = (String) adapter.getData().get(position);
                choose.setText(str);
                latest = str;
                page=1;
                RequestParameters();
                mListPopWindow.dissmiss();
            }
        });
    }


}
