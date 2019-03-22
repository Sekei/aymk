package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.mine.FansAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.FansListPresenter;
import com.live.tv.mvp.view.mine.IFansListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/16
 */

public class FansFragment extends BaseFragment<IFansListView, FansListPresenter> implements IFansListView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecycleView;
    Unbinder unbinder;
    private FansAdapter mAdapter;
    private List<UserBean> list;

    View loadMore;
    private int page = 1;
    private boolean isMore = true;


    public static FansFragment newInstance() {

        Bundle args = new Bundle();

        FansFragment fragment = new FansFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_common_test;
    }

    @Override
    public void initUI() {
        tvTitle.setText("关注我的人");
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        list = new ArrayList<>();
        mAdapter = new FansAdapter(context, list);
        easyRecycleView.setLayoutManager(new LinearLayoutManager(context));
        easyRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });

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


    private void paramsInfo() {

        map.clear();
        map.put("member_id",userBean.getMember_id());
        map.put("member_token",userBean.getMember_token());
        map.put("page", String.valueOf(page));
        getPresenter().getFocusList(map);

    }

    @Override
    public void initData() {
        paramsInfo();

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
    public FansListPresenter createPresenter() {
        return new FansListPresenter(getApp());
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
    public void onFansList(List<UserBean> data) {
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
