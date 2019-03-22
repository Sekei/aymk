package com.live.tv.mvp.fragment.home;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.GoodsBean;
import com.live.tv.bean.MerchantsBean;
import com.live.tv.bean.SearchBean;
import com.live.tv.mvp.adapter.home.SearchDoctorAdapter;
import com.live.tv.mvp.adapter.home.SearchGoodAdapter;
import com.live.tv.mvp.adapter.home.SearchMerchantsAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.SearchPresenter;
import com.live.tv.mvp.view.home.ISearchView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class SearchFragment extends BaseFragment<ISearchView, SearchPresenter> implements ISearchView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.btn_search)
    TextView btnSearch;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.doctor_recycleview)
    RecyclerView doctorRecycleview;
    @BindView(R.id.more_doctor)
    TextView moreDoctor;
    @BindView(R.id.ll_doctor)
    LinearLayout llDoctor;
    @BindView(R.id.merchants_recycleview)
    RecyclerView merchantsRecycleview;
    @BindView(R.id.more_merchants)
    TextView moreMerchants;
    @BindView(R.id.ll_merchants)
    LinearLayout llMerchants;
    @BindView(R.id.good_recycleview)
    RecyclerView goodRecycleview;
    @BindView(R.id.more_good)
    TextView moreGood;
    @BindView(R.id.ll_good)
    LinearLayout llGood;
    Unbinder unbinder;
    private String search_content = "";
    private SearchDoctorAdapter searchDoctorAdapter;
    private SearchGoodAdapter searchGoodAdapter;
    private SearchMerchantsAdapter searchMerchantsAdapter;
    private List<DoctorDetailBean> doctorDetailBeanList;
    private List<MerchantsBean> merchantsBeanList;
    private List<GoodsBean> goodsBeanList;

    public static SearchFragment newInstance(String search_content) {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.search_content = search_content;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintColor(R.color.colorPrimary);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);//状态栏所需颜色
            // tintManager.setTintColor(R.color.pure_white);//文字颜色
        }
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_search;
    }

    @Override
    public void initUI() {
        doctorDetailBeanList = new ArrayList<>();
        merchantsBeanList = new ArrayList<>();
        goodsBeanList = new ArrayList<>();
        searchDoctorAdapter = new SearchDoctorAdapter(context, doctorDetailBeanList);
        doctorRecycleview.setLayoutManager(new LinearLayoutManager(context));
        doctorRecycleview.setAdapter(searchDoctorAdapter);
        searchDoctorAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DoctorDetailBean doctorDetailBean =searchDoctorAdapter.getItem(position);
                startDoctorDetailFragment(doctorDetailBean.getDoctor_id());

            }
        });

        searchMerchantsAdapter = new SearchMerchantsAdapter(context, merchantsBeanList);
        merchantsRecycleview.setLayoutManager(new LinearLayoutManager(context));
        merchantsRecycleview.setAdapter(searchMerchantsAdapter);
        searchMerchantsAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MerchantsBean merchantsBean =searchMerchantsAdapter.getItem(position);
                startMerchantsDetailFragment(merchantsBean.getMerchants_id()+"");

            }
        });



        searchGoodAdapter = new SearchGoodAdapter(context, goodsBeanList);
        goodRecycleview.setLayoutManager(new LinearLayoutManager(context));
        goodRecycleview.setAdapter(searchGoodAdapter);


        searchGoodAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                GoodsBean goodsBean = searchGoodAdapter.getItem(position);

                if ("common_goods".equals(goodsBean.getGoods_type())){
                    startGoodDetailFragment(goodsBean.getGoods_id());
                }else {

                    startServiceGoodDetailFragment(goodsBean.getGoods_id());
                }
            }
        });

    }

    @Override
    public void initData() {
        editSearch.setText(search_content);
        map.clear();
        map.put("keyWord", search_content);
        getPresenter().gethomeSearch(map);

    }

    @OnClick({R.id.ivLeft,R.id.btn_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.btn_search:
                if (checkInputKey()) {
                    //getPresenter().insert(editSearch.getText().toString());
                    map.clear();
                    map.put("keyWord", editSearch.getText().toString());
                    getPresenter().gethomeSearch(map);
                }




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
    public SearchPresenter createPresenter() {
        return new SearchPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.more_doctor, R.id.more_merchants, R.id.more_good})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more_doctor:
                startMoreSearchDoctorFragment(editSearch.getText().toString());
                break;
            case R.id.more_merchants:
                startMoreSearchMerchantsFragment(editSearch.getText().toString());
                break;
            case R.id.more_good:
                startMoreSearchGoodFragment(editSearch.getText().toString());
                break;
        }
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
    private boolean checkInputKey() {
        if (StringUtils.isBlank(editSearch.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入关键搜索");
            return false;
        }

        return true;
    }
    @Override
    public void onhomeSearch(SearchBean data) {

        if (data != null) {

            if (data.getDoctor() != null&&data.getDoctor().size()>0) {
                searchDoctorAdapter.clear();
                searchDoctorAdapter.addAll(data.getDoctor());
                searchDoctorAdapter.notifyDataSetChanged();
                llDoctor.setVisibility(View.VISIBLE);
            } else {
                llDoctor.setVisibility(View.GONE);
            }
            if (data.getMerchants() != null&&data.getMerchants().size()>0) {
                searchMerchantsAdapter.clear();
                searchMerchantsAdapter.addAll(data.getMerchants());
                searchMerchantsAdapter.notifyDataSetChanged();
                llMerchants.setVisibility(View.VISIBLE);
            } else {
                llMerchants.setVisibility(View.GONE);
            }

            if (data.getGoods() != null&&data.getGoods().size()>0) {
                searchGoodAdapter.clear();
                searchGoodAdapter.addAll(data.getGoods());
                searchGoodAdapter.notifyDataSetChanged();
                llGood.setVisibility(View.VISIBLE);
            } else {
                llGood.setVisibility(View.GONE);
            }


        }

    }
}
