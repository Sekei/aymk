package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CollectionBean;
import com.live.tv.mvp.adapter.mine.CollectionMerchantsAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.CollectionListPresenter;
import com.live.tv.mvp.view.mine.ICollectionView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 收藏医生
 * @author Created by stone
 * @since 2018/1/16
 */

public class CollectionMerchantsFragment extends BaseFragment<ICollectionView,CollectionListPresenter>implements ICollectionView {
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecycleView;
    View loadMore;
    private int page = 1;
    private boolean isMore = true;
    private List<CollectionBean> list;
    private CollectionMerchantsAdapter  mAdapter;

    public static CollectionMerchantsFragment newInstance() {
        Bundle args = new Bundle();
        CollectionMerchantsFragment fragment = new CollectionMerchantsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_all_collection;
    }

    @Override
    public void initUI() {
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        list = new ArrayList<>();

        mAdapter = new CollectionMerchantsAdapter(context, list);
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


        mAdapter.setOnclick(new CollectionMerchantsAdapter.onclick() {
            @Override
            public void onItemChildClick(CollectionBean collectionBean) {
                //取消收藏
                Map<String ,String> mMap = new HashMap<>();
                mMap.put("member_id",userBean.getMember_id());
                mMap.put("member_token",userBean.getMember_token());
                mMap.put("collection_id",collectionBean.getCollection_id()+"");
                getPresenter().getCancelCollection(mMap);


            }
        });

        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                CollectionBean merchantsBean =mAdapter.getItem(position);

                startMerchantsDetailFragment(merchantsBean.getMerchants_id());


            }
        });

    }

    private void paramsInfo() {

        map.clear();
        map.put("member_id",userBean.getMember_id());
        map.put("member_token",userBean.getMember_token());
        map.put("collection_type","merchants");
        map.put("page", String.valueOf(page));
        getPresenter().getCollectionList(map);

    }

    @Override
    public void initData() {
        paramsInfo();
    }


    @Override
    public CollectionListPresenter createPresenter() {
        return new CollectionListPresenter(getApp());
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
    public void onCollectionList(List<CollectionBean> data) {
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
        //刷新数据
        page = 1;
        paramsInfo();
    }
}
