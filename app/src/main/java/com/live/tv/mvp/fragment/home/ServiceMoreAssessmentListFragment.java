package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AssessmentBean;
import com.live.tv.bean.ServiceCommentBean;
import com.live.tv.mvp.adapter.communicate.CommunityAppraiseAdapter;
import com.live.tv.mvp.adapter.shop.GoodAssessmentAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.MoreAssessmentPresenter;
import com.live.tv.mvp.view.shop.IMoreAssessmentView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 * 服务详情的更多评价
 */

public class ServiceMoreAssessmentListFragment extends BaseFragment<IMoreAssessmentView, MoreAssessmentPresenter> implements IMoreAssessmentView{
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    Unbinder unbinder;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecycleView;
    private CommunityAppraiseAdapter mAdapter;
    private String order_state = "";
    private Map<String, String> params;
    private List<ServiceCommentBean>  assessmentBeanList;

    View loadMore;
    private int page = 1;
    private boolean isMore = true;
    private String servicid="";

    public static ServiceMoreAssessmentListFragment newInstance(String goods_id) {

        Bundle args = new Bundle();
        ServiceMoreAssessmentListFragment fragment = new ServiceMoreAssessmentListFragment();
        fragment.servicid=goods_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_more_assessment_list;
    }

    @Override
    public void initUI() {
        tvTitle.setText("评价");
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        assessmentBeanList = new ArrayList<>();
        easyRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CommunityAppraiseAdapter(context);
        easyRecycleView.setAdapter(mAdapter);


        //刷新
        easyRecycleView.setRefreshingColorResources(R.color.colorPrimary);
        easyRecycleView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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

    @Override
    public void initData() {
        paramsInfo();

    }

    private void paramsInfo() {

        params = new ArrayMap<>();
        params.put("house_service_id", servicid);
        params.put("page", String.valueOf(page));
        getPresenter().getAssessmentList(params);


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
    public MoreAssessmentPresenter createPresenter() {
        return new MoreAssessmentPresenter(getApp());
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






    @Override
    public void onAssessmentGoods(List<AssessmentBean> data) {

    }

    @Override
    public void onAssessment(List<ServiceCommentBean> data) {
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
}
