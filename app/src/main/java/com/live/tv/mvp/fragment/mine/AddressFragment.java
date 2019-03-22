package com.live.tv.mvp.fragment.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AddressBean;
import com.live.tv.mvp.adapter.mine.AddressAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.AddressPresenter;
import com.live.tv.mvp.view.mine.IAddressView;
import com.live.tv.util.CustomDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class AddressFragment extends BaseFragment<IAddressView, AddressPresenter> implements IAddressView, SwipeRefreshLayout.OnRefreshListener  {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private AddressAdapter mAdapter;
    private int currentPage = 1;
    Map<String, String> params;
    private String  type="";

    public static AddressFragment newInstance(String type) {
        Bundle args = new Bundle();
        AddressFragment fragment = new AddressFragment();
        fragment.type=type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_address;
    }

    @Override
    public void initUI() {
        tvTitle.setText("地址管理");
        tvRight.setText("添加");
        tvRight.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new AddressAdapter(new ArrayList<AddressBean>());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                final AddressBean addressBean = (AddressBean) adapter.getData().get(position);

                switch (view.getId()) {
                    case R.id.cb_default:
                        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_default);
                        if (checkBox.isChecked()) {
                            checkBox.setClickable(false);
                            ToastUtils.showToast(context.getApplicationContext(), "已是默认地址");
                        } else {
                            params = new ArrayMap<String, String>();
                            params.put("member_id", userBean.getMember_id());
                            params.put("member_token", userBean.getMember_token());
                            params.put("address_id", addressBean.getAddress_id());
                            getPresenter().SetDefaultAddress(params);
                        }

                        break;
                    case R.id.tv_edit:

                        startAddAddressFragment("update", addressBean);

                        break;
                    case R.id.tv_del:

                        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                        builder.setMessage("是否确认删除？");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                params = new ArrayMap<>();
                                params.put("member_id", userBean.getMember_id());
                                params.put("member_token", userBean.getMember_token());
                                params.put("address_id", addressBean.getAddress_id());
                                getPresenter().getDelAddress(params);
                                dialog.dismiss();
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.onCreate().show();

                        break;


                }

            }
        });


        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                AddressBean  addressBean = (AddressBean) adapter.getData().get(position);
                if ("choose".equals(type)){
                    Intent intent = new Intent();
                    intent.putExtra("addressBean",addressBean);
                    getActivity().setResult(1000,intent);
                    finish();
                }

            }
        });

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                currentPage++;
                paramsInfo();//设置参数加载数据
                getPresenter().getMoreAddressList(map);

            }
        }, recyclerView);


    }

    @Override
    public void initData() {

        paramsInfo();
        getPresenter().getAddressList(map);

        //com.ysjk.health.iemk:id/load_more_load_end_view



    }


    //设置获取收地址的参数
    private void paramsInfo() {

        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("page", currentPage + "");
        map.put("member_token", userBean.getMember_token());

    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startAddAddressFragment("add", null);
                break;

        }
    }

    @Override
    public AddressPresenter createPresenter() {
        return new AddressPresenter(getApp());
    }

    @Override
    public void AddressList(List<AddressBean> addressBeanList) {

        mSwipeRefreshLayout.setRefreshing(false);
        if (addressBeanList != null && addressBeanList.size() > 0) {

            mAdapter.setNewData(addressBeanList);
            if (addressBeanList.size() == 0) {
                //mAdapter.setEmptyView(getActivity().getLayoutInflater().inflate(R.layout.empty_address, (ViewGroup) mRecyclerView.getParent(), false));
            }
            if (addressBeanList.size() < 10) {
                mAdapter.loadMoreEnd();
            }

        }
    }

    @Override
    public void MoreAddressList(List<AddressBean> addressBeanList) {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.addData(addressBeanList);
        mAdapter.loadMoreComplete();
        if (addressBeanList.size() < 10) {
            mAdapter.loadMoreEnd();

        }
    }


    @Override
    public void DelAddress(String data) {
        onRefresh();
        ToastUtils.showToast(context.getApplicationContext(), data);
    }

    @Override
    public void SetDefaultAddress(String data) {


        onRefresh();
        ToastUtils.showToast(context.getApplicationContext(), data);
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
    public void onRefresh() {
        currentPage = 1;
        if (userBean != null) {
            paramsInfo();//设置参数加载数据
            getPresenter().getAddressList(map);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== Constants.REQUESTCODE){
            onRefresh();
        }
    }
}
