package com.live.tv.mvp.fragment.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.live.tv.mvp.activity.ContentActivity;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.BannerBean;
import com.live.tv.bean.ClassBean;
import com.live.tv.bean.GoodsBean;
import com.live.tv.mvp.adapter.shop.HotGoodsAdapter;
import com.live.tv.mvp.adapter.shop.RecommendGoodsAdapter;
import com.live.tv.mvp.adapter.shop.ShopClassAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.ShopPresenter;
import com.live.tv.mvp.view.shop.IShopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class ShopFragment extends BaseFragment<IShopView, ShopPresenter> implements IShopView {


    @BindView(R.id.convenietnBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.class_recycleView)
    EasyRecyclerView classRecycleView;

    @BindView(R.id.hot_recycleView)
    RecyclerView hotRecycleView;

    @BindView(R.id.recommend_recycleView)
    RecyclerView recommendRecycleView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    private Map<String, String> map = new HashMap<>();
    private List<BannerBean> listBanner;
    private ShopClassAdapter shopClassAdapter;
    private HotGoodsAdapter hotGoodsAdapter;
    private RecommendGoodsAdapter recommendGoodsAdapter;
    private List<ClassBean> classBeanList;
    private List<GoodsBean> goodsBeanList;
    private List<GoodsBean> recommendGoodsList;

    public static ShopFragment newInstance() {
        Bundle args = new Bundle();
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initUI() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                getPresenter().getBanner(map);
                getPresenter().getGoodsClass();
                getPresenter().hotGoodsList();
                getPresenter().recommendGoods();

            }
        });

        listBanner = new ArrayList<>();
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                clickBannerItem(listBanner.get(position));
            }
        });

        classBeanList = new ArrayList<>();
        goodsBeanList = new ArrayList<>();
        recommendGoodsList = new ArrayList<>();


        //商品分类
        shopClassAdapter = new ShopClassAdapter(context, classBeanList);
        classRecycleView.setLayoutManager(new GridLayoutManager(context, 5));
        classRecycleView.setAdapter(shopClassAdapter);
        shopClassAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ClassBean classBean = shopClassAdapter.getAllData().get(position);
                //
                if (shopClassAdapter.getAllData().size() == position + 1) {
                    startAllClassFragment();
                } else {
                    startClassGoosListFragment(classBean.getClass_uuid());
                }
            }
        });


        LinearLayoutManager nearbyLinearLayoutManager = new LinearLayoutManager(context);
        nearbyLinearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        hotGoodsAdapter = new HotGoodsAdapter(context, goodsBeanList);
        hotRecycleView.setLayoutManager(nearbyLinearLayoutManager);
        hotRecycleView.setAdapter(hotGoodsAdapter);
        hotGoodsAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                GoodsBean goodsBean = hotGoodsAdapter.getItem(position);

                if ("common_goods".equals(goodsBean.getGoods_type())) {
                    startGoodDetailFragment(goodsBean.getGoods_id());
                } else {

                    startServiceGoodDetailFragment(goodsBean.getGoods_id());
                }
            }
        });


        recommendGoodsAdapter = new RecommendGoodsAdapter(context, recommendGoodsList);
        recommendRecycleView.setLayoutManager(new GridLayoutManager(context, 2));
        recommendRecycleView.setAdapter(recommendGoodsAdapter);
        recommendRecycleView.setNestedScrollingEnabled(false);//防止向上滑动时卡顿


        recommendGoodsAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GoodsBean goodsBean = recommendGoodsAdapter.getItem(position);
                if ("common_goods".equals(goodsBean.getGoods_type())) {
                    startGoodDetailFragment(goodsBean.getGoods_id());
                } else {

                    startServiceGoodDetailFragment(goodsBean.getGoods_id());
                }
            }
        });


    }

    @Override
    public void initData() {
        getPresenter().getBanner(map);
        getPresenter().getGoodsClass();
        getPresenter().hotGoodsList();
        getPresenter().recommendGoods();
    }

    //banner点击返回处理
    private void clickBannerItem(BannerBean banner) {
        //1不跳转；2web链接;3个人中心；4商品
        if (banner != null) {
            BannerBean data = banner;
            //chain:外链 goods:商品 merchants:商家 doctor:医生 house:家政
            if (data.getBanner_type().equals("chain")) {
                startWeb("",banner.getBanner_url());
            } else if (data.getBanner_type().equals("goods")) {
                Intent intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra(Constants.KEY_FRAGMENT, Constants.GOOD_DETAIL_FRAGMENT);
                intent.putExtra("goods_id", data.getGoods_id());
                startActivity(intent);
            } else if (data.getBanner_type().equals("merchants")) {
                Intent intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra(Constants.KEY_FRAGMENT, Constants.Merchants_Detail_Fragment);
                intent.putExtra("merchants_id", data.getMerchants_id());
                startActivity(intent);
            } else if (data.getBanner_type().equals("doctor")) {
                Intent intent = getFragmentIntent(Constants.DOCTOR_DETAIL);
                intent.putExtra(Constants.DOCTOR_ID, data.getDoctor_id());
                startActivity(intent);
            } else if (data.getBanner_type().equals("house")) {
                startHouseKeepingDetailFragment(data.getHouse_service_id());
            }
        }
    }

    @Override
    public void onGetBanner(List<BannerBean> data) {
        if (convenientBanner != null) {
            toSetList(listBanner, data, false);
            convenientBanner.setPages(new CBViewHolderCreator() {
                @Override
                public Holder<BannerBean> createHolder() {
                    return new ImageHolder();
                }
            }, listBanner)
                    .setPageIndicator(new int[]{R.drawable.icon_dot_nor, R.drawable.icon_dot_pre})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

            if (!convenientBanner.isTurning()) {
                convenientBanner.startTurning(4000);
            }
            convenientBanner.notifyDataSetChanged();
        }

    }

    /**
     * 获取商品分类的
     *
     * @param data
     */

    @Override
    public void onGetClass(List<ClassBean> data) {
        shopClassAdapter.clear();
        shopClassAdapter.addAll(data);
        shopClassAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHotGoods(List<GoodsBean> data) {
        hotGoodsAdapter.clear();
        hotGoodsAdapter.addAll(data);
        hotGoodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRecommendGoods(List<GoodsBean> data) {
        recommendGoodsAdapter.clear();
        recommendGoodsAdapter.addAll(data);
        recommendGoodsAdapter.notifyDataSetChanged();

    }

    public class ImageHolder implements Holder<BannerBean> {
        private ImageView iv;

        @Override
        public View createView(Context context) {
            iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            return iv;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerBean data) {
            Glide.with(context).load(Constants.BASE_URL + data.getBanner_img()).error(R.drawable.banner_default).placeholder(R.drawable.banner_default).into(iv);
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
    public ShopPresenter createPresenter() {
        return new ShopPresenter(getApp());
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

    @OnClick({R.id.msg, R.id.more_hot_good, R.id.tv_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.msg:
                startMsgFragment();
                break;
            case R.id.more_hot_good:
                startHotGoodsListFragment();
                break;

            case R.id.tv_search:
                startSearchHistoryFragment();
                break;
            default:
                break;
        }
    }


}
