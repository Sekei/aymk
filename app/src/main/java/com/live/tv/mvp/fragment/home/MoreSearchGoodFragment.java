package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.GoodsBean;
import com.live.tv.bean.MerchantsBean;
import com.live.tv.mvp.adapter.shop.ClassGoodsListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.MoreSearchDoctorPresenter;
import com.live.tv.mvp.view.home.IMoreSearchDoctorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class MoreSearchGoodFragment extends BaseFragment<IMoreSearchDoctorView,MoreSearchDoctorPresenter>implements IMoreSearchDoctorView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecyclerView;
    private ClassGoodsListAdapter adapter;
    private List<GoodsBean> list;
    View loadMore;
    private int page = 1;
    private boolean isMore = true;
    private String keyWord="";
    public static MoreSearchGoodFragment newInstance(String keyWord) {
        Bundle args = new Bundle();
        MoreSearchGoodFragment fragment = new MoreSearchGoodFragment();
        fragment.keyWord=keyWord;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_more_search_doctor;
    }

    @Override
    public void initUI() {
        tvTitle.setText("商品");
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        list = new ArrayList<>();
        adapter = new ClassGoodsListAdapter(context, list);
        easyRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GoodsBean goodsBean = adapter.getItem(position);
                if ("common_goods".equals(goodsBean.getGoods_type())){
                    startGoodDetailFragment(goodsBean.getGoods_id());
                }else {

                    startServiceGoodDetailFragment(goodsBean.getGoods_id());
                }
            }
        });
        //刷新
        easyRecyclerView.setRefreshingColorResources(R.color.colorPrimary);
        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getDoctorList(map);
            }
        });


        //下拉加载
        adapter.setMore(loadMore, new RecyclerArrayAdapter.OnMoreListener() {
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

    private void RequestParameters() {
        map.clear();
        map.put("page", String.valueOf(page));
        map.put("keyWord",keyWord);
        map.put("type","goods");
        getPresenter().getGoodList(map);
    }

    @Override
    public void initData() {
        RequestParameters();
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
    public MoreSearchDoctorPresenter createPresenter() {
        return new MoreSearchDoctorPresenter(getApp());
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
    public void onGetDoctorList(List<DoctorDetailBean> data) {

    }

    @Override
    public void onGetMerchantsList(List<MerchantsBean> data) {

    }

    @Override
    public void onGetGoodList(List<GoodsBean> data) {
        if (data.size() == 10) {
            isMore = true;
        } else {
            isMore = false;
            loadMore.setVisibility(View.GONE);
        }
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
