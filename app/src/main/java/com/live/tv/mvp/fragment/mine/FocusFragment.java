package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CollectionBean;
import com.live.tv.mvp.adapter.mine.FocusAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.FocusListPresenter;
import com.live.tv.mvp.view.mine.IFocusView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *
 * 关注的人
 * @author Created by stone
 * @since 2018/1/16
 */

public class FocusFragment extends BaseFragment<IFocusView,FocusListPresenter>implements IFocusView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecycleView;
    Unbinder unbinder;
    private FocusAdapter mAdapter;
    private List<CollectionBean> list;

    View loadMore;
    private int page = 1;
    private boolean isMore = true;

    public static FocusFragment newInstance() {
        Bundle args = new Bundle();
        FocusFragment fragment = new FocusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_common_test;
    }

    @Override
    public void initUI() {
        tvTitle.setText("关注的人");
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        list = new ArrayList<>();

        mAdapter = new FocusAdapter(context, list);
        easyRecycleView.setLayoutManager(new LinearLayoutManager(context));
        easyRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
               // startTestHomePageFragment();
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


        mAdapter.setOnclick(new FocusAdapter.onclick() {
            @Override
            public void onItemChildClick(CollectionBean collectionBean) {
                Map<String ,String> mMap = new HashMap<>();
                mMap.put("member_id",userBean.getMember_id());
                mMap.put("member_token",userBean.getMember_token());
                mMap.put("collection_id",collectionBean.getCollection_id()+"");
                getPresenter().getCancelCollection(mMap);
            }
        });


    }

    private void paramsInfo() {

        map.clear();
        map.put("member_id",userBean.getMember_id());
        map.put("member_token",userBean.getMember_token());
        map.put("collection_type","doctor");
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
    public FocusListPresenter createPresenter() {
        return new FocusListPresenter(getApp());
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
    public void onFocusList(List<CollectionBean> data) {
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
    public void onCancelCollection(String data) {
        ToastUtils.showToast(context.getApplicationContext(),"取消成功");
        page = 1;
        paramsInfo();
    }
}
