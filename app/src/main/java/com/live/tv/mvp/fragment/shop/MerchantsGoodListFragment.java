package com.live.tv.mvp.fragment.shop;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ClassBean;
import com.live.tv.bean.GoodsBean;
import com.live.tv.mvp.adapter.shop.ClassGoodsListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.shop.popwindow.ClassPopwindow;
import com.live.tv.mvp.fragment.shop.popwindow.SalePopwindow;
import com.live.tv.mvp.presenter.shop.MerchantsGoodsListPresenter;
import com.live.tv.mvp.view.shop.IMerchantsGoosListView;

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

public class MerchantsGoodListFragment extends BaseFragment<IMerchantsGoosListView, MerchantsGoodsListPresenter> implements IMerchantsGoosListView {

    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.choose)
    TextView choose;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecycleView;
    Unbinder unbinder;
    private String merchants_id = "";

    private ClassGoodsListAdapter mAdapter;
    private List<GoodsBean> GoodsList;
    View loadMore;
    private int page = 1;
    private boolean isMore = true;
    private String goods_id = "";

    private ClassPopwindow classPopwindow;
    private SalePopwindow salePopwindow;
    private SalePopwindow pricePopwindow;
    private String total_sales = "desc";
    private String min_price = "desc";
    private List<ClassBean> classOneList = new ArrayList<>();
    private String class_uuid = "";

    public static MerchantsGoodListFragment newInstance(String merchants_id) {
        Bundle args = new Bundle();
        MerchantsGoodListFragment fragment = new MerchantsGoodListFragment();
        fragment.merchants_id = merchants_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_merchants_good_list;
    }

    @Override
    public void initUI() {
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        GoodsList = new ArrayList<>();
        mAdapter = new ClassGoodsListAdapter(context, GoodsList);
        easyRecycleView.setLayoutManager(new GridLayoutManager(context, 2));
        easyRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                GoodsBean goodsBean = mAdapter.getItem(position);

                if ("common_goods".equals(goodsBean.getGoods_type())) {
                    startGoodDetailFragment(goodsBean.getGoods_id());
                } else {

                    startServiceGoodDetailFragment(goodsBean.getGoods_id());
                }
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
        map.put("merchants_id", merchants_id);
        map.put("page", String.valueOf(page));
        map.put("total_sales", total_sales);
        map.put("min_price", min_price);
        map.put("class_uuid", class_uuid);
        getPresenter().getClassGoodsList(map);
    }

    //分类的参数
    private void paramsClassInfo() {

        Map<String, String> params = new HashMap<>();
        params.put("parent_id", "-1");
        params.put("level", "2");
        getPresenter().getFirstClass(params);
    }

    @Override
    public void initData() {
        paramsInfo();
        paramsClassInfo();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public MerchantsGoodsListPresenter createPresenter() {
        return new MerchantsGoodsListPresenter(getApp());
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
    public void onClassGoodsList(List<GoodsBean> data) {
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
    public void onClass(List<ClassBean> data) {

        classOneList = data;
    }


    //分类
    private void showClassPop() {
        if (classPopwindow == null) {
            classPopwindow = new ClassPopwindow(context).setOutsideTouchable(false);
        }
        if (!classPopwindow.isShowing()) {


            classPopwindow.setData(classOneList).cerate().setOnItemClickListener(new ClassPopwindow.onItemClickListener() {
                @Override
                public void onclick(ClassBean data) {

                    class_uuid = data.getClass_uuid();
                    page = 1;
                    paramsInfo();
                    // address.setText(name);
                    address.setTextColor(getResources().getColor(R.color.colorTextG6));
                    address.setCompoundDrawables(null, null, setDrawableDown(), null);
                    classPopwindow.dismiss();
                }
            }).show(address);


        }
    }

    //销量
    private void showSalePop() {
        if (salePopwindow == null) {
            salePopwindow = new SalePopwindow(context).setOutsideTouchable(false);
        }
        if (!salePopwindow.isShowing()) {


            salePopwindow.cerate().setOnItemClickListener(new SalePopwindow.onItemClickListener() {
                @Override
                public void onclick(String type) {
                    total_sales = type;
                    min_price = "";
                    page = 1;
                    paramsInfo();
                    department.setTextColor(getResources().getColor(R.color.colorTextG6));
                    department.setCompoundDrawables(null, null, setDrawableDown(), null);
                }
            }).show(address);


        }
    }

    //销量
    private void showPricePop() {
        if (pricePopwindow == null) {
            pricePopwindow = new SalePopwindow(context).setOutsideTouchable(false);
        }
        if (!pricePopwindow.isShowing()) {


            pricePopwindow.cerate().setOnItemClickListener(new SalePopwindow.onItemClickListener() {
                @Override
                public void onclick(String type) {
                    min_price = type;
                    total_sales = "";
                    page = 1;
                    paramsInfo();
                    choose.setTextColor(getResources().getColor(R.color.colorTextG6));
                    choose.setCompoundDrawables(null, null, setDrawableDown(), null);
                }
            }).show(address);


        }
    }

    private int pricePopState = -1;
    private int classPopState = -1;
    private int salePopState = -1;


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


    private void setClassG6() {
        if (classPopwindow != null) {
            classPopwindow.dismiss();
            classPopState = -1;
            address.setTextColor(getResources().getColor(R.color.colorTextG6));
            address.setCompoundDrawables(null, null, setDrawableDown(), null);

        }
    }


    private void setSaleG6() {
        if (salePopwindow != null) {
            salePopwindow.dismiss();
            salePopState = -1;
            choose.setTextColor(getResources().getColor(R.color.colorTextG6));
            choose.setCompoundDrawables(null, null, setDrawableDown(), null);
        }
    }

    private void setPriceG6() {
        if (pricePopwindow != null) {
            pricePopwindow.dismiss();
            pricePopState = -1;
            department.setTextColor(getResources().getColor(R.color.colorTextG6));
            department.setCompoundDrawables(null, null, setDrawableDown(), null);
        }
    }

    @OnClick({R.id.address, R.id.department, R.id.choose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address:


                if (classOneList != null && classOneList.size() > 0) {
                    setSaleG6();
                    setPriceG6();
                    if (classPopState == -1) {
                        classPopState = 0;
                        showClassPop();
                        address.setTextColor(getResources().getColor(R.color.colorPrimary));
                        address.setCompoundDrawables(null, null, setDrawableUp(), null);
                    } else {
                        setClassG6();

                    }
                }

                break;
            case R.id.department:


                setClassG6();
                setPriceG6();
                if (salePopState == -1) {
                    salePopState = 0;
                    showSalePop();
                    department.setTextColor(getResources().getColor(R.color.colorPrimary));
                    department.setCompoundDrawables(null, null, setDrawableUp(), null);
                } else {
                    setSaleG6();

                }
                break;
            case R.id.choose:


                setClassG6();
                setSaleG6();
                if (pricePopState == -1) {
                    pricePopState = 0;
                    showPricePop();
                    choose.setTextColor(getResources().getColor(R.color.colorPrimary));
                    choose.setCompoundDrawables(null, null, setDrawableUp(), null);
                } else {
                    setPriceG6();

                }
                break;
        }
    }



public  void dismissPopWindow(){
    if (classPopwindow != null) {
        classPopwindow.dismiss();
    }
    if (salePopwindow != null) {
        salePopwindow.dismiss();
    }
    if (pricePopwindow != null) {
        pricePopwindow.dismiss();
    }


}

}
