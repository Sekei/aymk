package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.City;
import com.live.tv.bean.MerchantsBean;
import com.live.tv.mvp.adapter.home.NearbyAdapter;
import com.live.tv.mvp.base.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 更多附近门店
 * Created by sh-lx on 2017/7/12.
 */

public class MoreNearbyShopListFragment extends SimpleFragment implements PoiSearch.OnPoiSearchListener {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView recyclerView;
    Unbinder unbinder;
    private NearbyAdapter mAdapter;
    private List<MerchantsBean> liveBeanList;
    private String mcity;
    private double mlat;
    private double mlag;
    private PoiSearch.Query query;
    private int currentPage = 1;
    private PoiSearch poiSearch;
    private List<PoiItem> list=new ArrayList<>();
    private View loadMore;
    private boolean isMore;


    public static MoreNearbyShopListFragment newInstance(String city, double lat, double lag) {
        Bundle args = new Bundle();
        MoreNearbyShopListFragment fragment = new MoreNearbyShopListFragment();
        fragment.mcity = city;
        fragment.mlat = lat;
        fragment.mlag = lag;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.more_nearby_shop_list;
    }

    @Override
    public void initUI() {
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);

        tvTitle.setText("附近门店");
        liveBeanList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NearbyAdapter(getContext());
        recyclerView.setAdapter(mAdapter);

        //刷新
        recyclerView.setRefreshingColorResources(R.color.colorPrimary);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                showGaode();
            }
        });
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startMerchantsMapFragment(list.get(position));

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
                    currentPage++;
                    showGaode();

                }
            }


            @Override
            public void onMoreClick() {

            }
        });

        showGaode();
    }

    private void showGaode() {
        query = new PoiSearch.Query("健康|养生|药店", "",mcity);
//keyWord表示搜索字符串，
//第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
//cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);//设置查询页码
        poiSearch = new PoiSearch(getActivity(), query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(
                mlat, mlag), 50000));//设置周边搜索的中心点以及半径
        poiSearch.searchPOIAsyn();

    }


    @Override
    public void initData() {

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (poiResult != null) {
            if (poiResult.getPois().size() == 10) {
                isMore = true;
            } else {
                isMore = false;
                loadMore.setVisibility(View.GONE);
            }
            if (currentPage == 1) {
                mAdapter.clear();
                list.clear();
            }
            list.addAll(poiResult.getPois()) ;
            mAdapter.addAll(poiResult.getPois());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
