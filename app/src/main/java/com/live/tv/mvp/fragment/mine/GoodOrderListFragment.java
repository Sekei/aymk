package com.live.tv.mvp.fragment.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.OrderBean;
import com.live.tv.mvp.adapter.mine.GoodOrderListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.GoodOrderListPresenter;
import com.live.tv.mvp.view.shop.IGoodOrderListView;
import com.live.tv.util.CustomDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 商品订单列表
 */

public class GoodOrderListFragment extends BaseFragment<IGoodOrderListView, GoodOrderListPresenter> implements IGoodOrderListView , SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;
    private String order_state = "";
    private Map<String, String> params;
    private int currentPage = 1;

    private GoodOrderListAdapter mAdapter;

    public static GoodOrderListFragment newInstance(String order_state) {
        Bundle args = new Bundle();
        GoodOrderListFragment fragment = new GoodOrderListFragment();
        fragment.order_state = order_state;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("order_success")) {
            onRefresh();//刷新数据
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_good_order_list;
    }

    @Override
    public void initUI() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new GoodOrderListAdapter(new ArrayList<OrderBean>());
        recyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrderBean orderBean = (OrderBean) adapter.getData().get(position);

                startGoodOrderDetailFragment(orderBean.getOrder_id());
            }
        });


        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderBean orderBean = (OrderBean) adapter.getData().get(position);

                switch (view.getId()) {
                    case R.id.tv_cancels:
                        cancelDialog(orderBean.getOrder_id());
                        break;
                    case R.id.tv_pay:
                        startBuyGoodFragment(orderBean.getOrder_id(), orderBean.getOrder_actual_price());
                        break;
                    case R.id.tv_delete:
                        deleteDialog(orderBean.getOrder_id());

                        break;
                    case R.id.tv_confirm:
                        //确认收货
                        receiveOrdera(orderBean.getOrder_id(),position);
                        break;
                    case R.id.tv_assessment:
                        startAssessmentOrderFragment(orderBean.getOrderGoodsBeans(),orderBean.getOrder_id());
                        break;
                    case R.id.tv_logistics:

                        //物流
                        break;


                }

            }
        });

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                currentPage++;
                paramsInfo();//设置参数加载数据
                getPresenter().getMoreGoodOrderList(params);

            }
        }, recyclerView);


    }

    /**
     * 确认收货
     * @param order_id
     */
    private void receiveOrdera(String order_id,int position) {
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("order_id", order_id);
        getPresenter().getreceiveOrder(map,position);
    }


    private void cancelDialog(final String order_id) {

        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("是否取消订单？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("order_id", order_id);
                getPresenter().getCancelGoodOrder(map);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();

    }

    private void deleteDialog(final String order_id) {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("是否删除订单？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("order_id", order_id);
                getPresenter().getDelGoodOrder(map);

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }


    @Override
    public void initData() {
        paramsInfo();
        getPresenter().getGoodOrderList(params);

    }

    private void paramsInfo() {

        params = new ArrayMap<>();
        params.put("member_id", userBean.getMember_id());
        params.put("member_token", userBean.getMember_token());
        params.put("order_state", order_state);
        params.put("order_type", "common_goods");
        params.put("page", String.valueOf(currentPage));


    }

    @Override
    public GoodOrderListPresenter createPresenter() {
        return new GoodOrderListPresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        mSwipeRefreshLayout.setRefreshing(false);
        errorHandle(e);
    }

    @Override
    public void onGoodOrderList(List<OrderBean> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(data);
        if (data != null && data.size() > 0) {
            if (data.size() == 0) {
                //mAdapter.setEmptyView(getActivity().getLayoutInflater().inflate(R.layout.empty_address, (ViewGroup) mRecyclerView.getParent(), false));
            }
            if (data.size() < 10) {
                mAdapter.loadMoreEnd();
            }

        }
    }

    @Override
    public void onMoreGoodOrderList(List<OrderBean> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.addData(data);
        mAdapter.loadMoreComplete();
        if (data.size() < 10) {
            mAdapter.loadMoreEnd();

        }
    }

    @Override
    public void CancelOrder(String data) {

        ToastUtils.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("order_success"));
    }

    @Override
    public void DelOrder(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("order_success"));
    }

    @Override
    public void onreceiveOrder(String data,int position) {
        //确认收货
        ToastUtils.showToast(getActivity(),data);
        mAdapter.remove(position);
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

    //刷新数据
    @Override
    public void onRefresh() {
        currentPage = 1;
        if (userBean != null) {
            paramsInfo();//设置参数加载数据
            getPresenter().getGoodOrderList(params);
        }
    }
}
