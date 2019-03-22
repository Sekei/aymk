package com.live.tv.mvp.fragment.mine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DepartmentLevelOneBean;
import com.live.tv.bean.DepartmentLevelTwoBean;
import com.live.tv.bean.LiveBean;
import com.live.tv.mvp.activity.live.LivePlaybackActivity;
import com.live.tv.mvp.activity.live.PlaybackActivity;
import com.live.tv.mvp.adapter.mine.FamousDoctorLiveListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.shop.popwindow.LiveStatePopwindow;
import com.live.tv.mvp.fragment.shop.popwindow.newPopwindow;
import com.live.tv.mvp.presenter.live.FamousDoctorLiveListPresenter;
import com.live.tv.mvp.view.live.IFamousdoctorLiveListView;
import com.live.tv.util.city.DepartmentPopwindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 名医直播列表
 * Created by sh-lx on 2017/7/12.
 */

public class FamousDoctorLiveListFragment extends BaseFragment<IFamousdoctorLiveListView, FamousDoctorLiveListPresenter> implements IFamousdoctorLiveListView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_live_state)
    TextView tvLiveState;
    @BindView(R.id.tv_new)
    TextView tvNew;
    private FamousDoctorLiveListAdapter mAdapter;
    private List<LiveBean> liveBeanList;
    View loadMore;
    private int page = 1;
    private boolean isMore = true;

    private int departmentPopState = -1;
    private int livePopState = -1;
    private int newPopState = -1;
    private DepartmentPopwindow departmentPopwindow;
    private newPopwindow newPopwindow;
    private LiveStatePopwindow liveStatePopwindow;
    private ArrayList<DepartmentLevelOneBean> listOne = new ArrayList<>();

    private String department_level2 = "";
    private String live_type = "1";
    private String hotOrNew = "1";

    public static FamousDoctorLiveListFragment newInstance() {

        Bundle args = new Bundle();

        FamousDoctorLiveListFragment fragment = new FamousDoctorLiveListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_famous_doctor_live_list;
    }

    @Override
    public void initUI() {
        tvTitle.setText("名医直播");
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        liveBeanList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new FamousDoctorLiveListAdapter(getContext(), liveBeanList);
        recyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                LiveBean liveBean = mAdapter.getItem(position);

                if (liveBean.getLive_state().equals("1")) {
                    Intent intent = new Intent(getActivity(), PlaybackActivity.class);
                    intent.putExtra("videoPath", liveBean.getLive_play_rtmp_url());
                    intent.putExtra("roomId", liveBean.getRoom_id());
                    intent.putExtra("stream_id", liveBean.getStream_id());
                    intent.putExtra("liveId", liveBean.getLive_id());
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getActivity(), LivePlaybackActivity.class);
                    intent.putExtra("videoPath", liveBean.getLive_playback_url());
                    startActivity(intent);
                }
            }
        });


        //刷新
        recyclerView.setRefreshingColorResources(R.color.colorPrimary);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                paramsInfo();
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
                    paramsInfo();

                }
            }

            @Override
            public void onMoreClick() {

            }
        });
    }

    private void paramsInfo() {
        if (userBean != null) {
            map.clear();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("page", String.valueOf(page));
            map.put("hotOrNew", hotOrNew);
            map.put("live_type", live_type);
            map.put("department_level2", department_level2);
            getPresenter().getLiveList(map);
        } else {
            startLogin();
        }

    }

    @Override
    public void initData() {
        paramsInfo();
        getPresenter().getDepartments(map);
    }

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
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
    public FamousDoctorLiveListPresenter createPresenter() {
        return new FamousDoctorLiveListPresenter(getApp());
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

    }

    @Override
    public void onLiveList(List<LiveBean> data) {
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

    @Override
    public void onGetDepartments(List<DepartmentLevelOneBean> data) {
        listOne.clear();
        listOne.addAll(data);
    }


    //科室
    private void showDepartmentPop() {
        if (departmentPopwindow == null) {
            departmentPopwindow = new DepartmentPopwindow(context).setOutsideTouchable(false);
        }
        if (!departmentPopwindow.isShowing()) {
            departmentPopwindow.setData(listOne).cerate().setOnItemClickListener(new DepartmentPopwindow.onItemClickListener() {
                @Override
                public void onclick(DepartmentLevelTwoBean data) {
                    if (data != null) {
                        departmentPopState = -1;
                        tvDepartment.setText(data.getDepartment_name());
                        tvDepartment.setTextColor(getResources().getColor(R.color.colorTextG6));
                        tvDepartment.setCompoundDrawables(null, null, setDrawableDown(), null);
                        page = 1;
                        department_level2 = data.getDepartment_name();
                        paramsInfo();

                    }
                    departmentPopwindow.dismiss();
                }
            }).show(tvDepartment);
        }
    }

    //直播状态
    private void showLiveStatePop() {
        if (liveStatePopwindow == null) {
            liveStatePopwindow = new LiveStatePopwindow(context).setOutsideTouchable(false);
        }
        if (!liveStatePopwindow.isShowing()) {


            liveStatePopwindow.cerate().setOnItemClickListener(new LiveStatePopwindow.onItemClickListener() {
                @Override
                public void onclick(String type, String name) {
                    livePopState = -1;
                    page = 1;
                    live_type = type;

                    paramsInfo();

                    tvLiveState.setText(name);
                    tvLiveState.setTextColor(getResources().getColor(R.color.colorTextG6));
                    tvLiveState.setCompoundDrawables(null, null, setDrawableDown(), null);
                }
            }).show(tvLiveState);


        }
    }


    //最新 最热
    private void showNewPop() {
        if (newPopwindow == null) {
            newPopwindow = new newPopwindow(context).setOutsideTouchable(false);
        }
        if (!newPopwindow.isShowing()) {
            newPopwindow.cerate().setOnItemClickListener(new newPopwindow.onItemClickListener() {
                @Override
                public void onclick(String type, String name) {
                    newPopState = -1;
                    hotOrNew = type;
                    page = 1;
                    paramsInfo();
                    tvNew.setText(name);
                    tvNew.setTextColor(getResources().getColor(R.color.colorTextG6));
                    tvNew.setCompoundDrawables(null, null, setDrawableDown(), null);
                }
            }).show(tvLiveState);


        }
    }


    private Drawable setDrawableUp() {
        Drawable drawable = getResources().getDrawable(R.drawable.doctor_choose_up);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        return drawable;
    }

    private Drawable setDrawableDown() {
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.book_down);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        return drawable;
    }

    @OnClick({R.id.tv_department, R.id.tv_live_state, R.id.tv_new})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_department:
                setLiveStateG6();
                setnewStateG6();
                if (departmentPopState == -1) {
                    departmentPopState = 0;
                    showDepartmentPop();
                    tvDepartment.setTextColor(getResources().getColor(R.color.colorPrimary));
                    tvDepartment.setCompoundDrawables(null, null, setDrawableUp(), null);
                } else {
                    setDepartmentG6();
                }


                break;
            case R.id.tv_live_state:

                setDepartmentG6();
                setnewStateG6();
                if (livePopState == -1) {
                    livePopState = 0;
                    showLiveStatePop();
                    tvLiveState.setTextColor(getResources().getColor(R.color.colorPrimary));
                    tvLiveState.setCompoundDrawables(null, null, setDrawableUp(), null);
                } else {
                    setLiveStateG6();
                }


                break;
            case R.id.tv_new:

                setDepartmentG6();
                setLiveStateG6();
                if (newPopState == -1) {
                    newPopState = 0;
                    showNewPop();
                    tvNew.setTextColor(getResources().getColor(R.color.colorPrimary));
                    tvNew.setCompoundDrawables(null, null, setDrawableUp(), null);
                } else {
                    setnewStateG6();
                }


                break;
        }
    }


    private void setLiveStateG6() {
        if (liveStatePopwindow != null) {
            liveStatePopwindow.dismiss();
            livePopState = -1;
            tvLiveState.setTextColor(getResources().getColor(R.color.colorTextG6));
            tvLiveState.setCompoundDrawables(null, null, setDrawableDown(), null);

        }
    }

    private void setnewStateG6() {
        if (newPopwindow != null) {
            newPopwindow.dismiss();
            newPopState = -1;
            tvNew.setTextColor(getResources().getColor(R.color.colorTextG6));
            tvNew.setCompoundDrawables(null, null, setDrawableDown(), null);

        }
    }


    private void setDepartmentG6() {
        if (departmentPopwindow != null) {
            departmentPopwindow.dismiss();
            departmentPopState = -1;
            tvDepartment.setTextColor(getResources().getColor(R.color.colorTextG6));
            tvDepartment.setCompoundDrawables(null, null, setDrawableDown(), null);
        }
    }
}
