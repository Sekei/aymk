package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.OrderBean;
import com.live.tv.mvp.adapter.mine.NewMerchantFormAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.MerchantFormItemPresenter;
import com.live.tv.mvp.view.mine.IMerchantFormItemView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/8/24.
 * 商家订单界面
 */

public class MerchantFormItemFragment extends BaseFragment<IMerchantFormItemView, MerchantFormItemPresenter> implements IMerchantFormItemView , SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private String mType;
    private String morderstate;
    private NewMerchantFormAdapter mAdapter;
    private List<String> list = new ArrayList<>();
    private int currentPage=1;
    private Map<String,String> params;
    private View loadMore;
    private boolean isMore;
    public static MerchantFormItemFragment newInstance(String orderstate, String type) {
        Bundle args = new Bundle();
        MerchantFormItemFragment fragment = new MerchantFormItemFragment();
        fragment.mType = type;
        fragment.morderstate = orderstate;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_merchantform_item;
    }

    @Override
    public void initUI() {
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NewMerchantFormAdapter(getActivity());
        mAdapter.SetOnButtonLisenter(new NewMerchantFormAdapter.OnClickLisenter() {
            @Override
            public void onleftLisenter(OrderBean lorderBean) {

                ToastUtils.showToast(getActivity(),"点击左边的按钮");
            }

            @Override
            public void OnRightLisenter(OrderBean rorderBean) {

                ToastUtils.showToast(getActivity(),"点击右边的按钮");

            }
        });
        recyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startGoodOrderDetailFragment(mAdapter.getAllData().get(position).getOrder_id());
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
                    paramsInfo();
                    getPresenter().getFormList(params);

                }
            }


            @Override
            public void onMoreClick() {

            }
        });

    }
    private void paramsInfo() {

        params = new ArrayMap<>();
        params.put("member_id", userBean.getMember_id());
        params.put("member_token", userBean.getMember_token());
        params.put("order_state", morderstate);
        params.put("order_type", "houseService");
        if (mType!=null&&mType.equals("2")){

        }else {
            params.put("house_service_id",userBean.getHouse_service_id());
        }
        params.put("page", String.valueOf(currentPage));


    }
    @Override
    public void initData() {
        paramsInfo();
        getPresenter().getFormList(params);
    }

    @Override
    public MerchantFormItemPresenter createPresenter() {
        return new MerchantFormItemPresenter(getApp());
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onformList(List<OrderBean> beanList) {
        /***
         * 获取数据
         */
        mSwipeRefreshLayout.setRefreshing(false);
        if (beanList != null) {
            if (beanList.size() == 10) {
                isMore = true;
            } else {
                isMore = false;
                loadMore.setVisibility(View.GONE);
            }
            if (currentPage == 1) {
                mAdapter.clear();
            }
            mAdapter.addAll(beanList);
            mAdapter.notifyDataSetChanged();

        }
    }
    @Override
    public void onRefresh() {
           currentPage = 1;
        if (userBean != null) {
            paramsInfo();//设置参数加载数据
            getPresenter().getFormList(params);
        }
    }
}
