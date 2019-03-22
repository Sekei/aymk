package com.live.tv.mvp.fragment.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.OrderBean;
import com.live.tv.mvp.adapter.shop.ServiceGoodOrderListAdapter;
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
import butterknife.Unbinder;


/**
 * Created by sh-lx on 2017/7/12.
 */

public class ServiceOrderListFragment extends BaseFragment<IGoodOrderListView, GoodOrderListPresenter> implements IGoodOrderListView , SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;
    private String order_state = "";
    private Map<String, String> params;
    private int currentPage = 1;

    private ServiceGoodOrderListAdapter mAdapter;

    public static ServiceOrderListFragment newInstance(String order_state) {

        Bundle args = new Bundle();
        ServiceOrderListFragment fragment = new ServiceOrderListFragment();
        fragment.order_state = order_state;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_good_order_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("service_order_success")) {
            onRefresh();//刷新数据
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ServiceGoodOrderListAdapter(new ArrayList<OrderBean>());
        recyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrderBean orderBean = (OrderBean) adapter.getData().get(position);

                startServiceGoodOrderDetailFragment(orderBean.getOrder_id());
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
                    case R.id.tv_one_list:

                        if (orderBean.getOrderGoodsBeans()!=null) {
                            startServiceGoodDetailFragment(orderBean.getOrderGoodsBeans().get(0).getGoods_id());
                        }
                        break;
                    case R.id.tv_see:
                        startServiceGoodOrderDetailFragment(orderBean.getOrder_id());
                        break;
                    case R.id.tv_buy_again:
                        if (orderBean.getOrderGoodsBeans()!=null) {
                            startServiceGoodDetailFragment(orderBean.getOrderGoodsBeans().get(0).getGoods_id());
                        }

                        break;
                    case R.id.tv_assessment:
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
        params.put("order_type", "service_goods");
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

    }

    @Override
    public void onGoodOrderList(List<OrderBean> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (data != null ) {

            mAdapter.setNewData(data);
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
        EventBus.getDefault().post(new FirstEvent("service_order_success"));
    }

    @Override
    public void DelOrder(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("service_order_success"));
    }

    @Override
    public void onreceiveOrder(String data, int position) {

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

    @Override
    public void onRefresh() {
        currentPage = 1;
        if (userBean != null) {
            paramsInfo();//设置参数加载数据
            getPresenter().getGoodOrderList(params);
        }
    }
}
