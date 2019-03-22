package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ServiceCopeBean;
import com.live.tv.mvp.adapter.mine.ServiceScopeAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.ServicecopePresenter;
import com.live.tv.mvp.view.mine.IServicecopeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/8/28.
 * 服务范围
 */

public class ServicecopeFragment extends BaseFragment<IServicecopeView, ServicecopePresenter> implements IServicecopeView {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private ServiceScopeAdapter adapter;
    private List<ServiceCopeBean> mlist = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();

    public static ServicecopeFragment newInstance() {
        ServicecopeFragment fragment = new ServicecopeFragment();

        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_servicescope;
    }

    @Override
    public void initUI() {
        tvTitle.setText("服务范围");
        ivLeft.setVisibility(View.VISIBLE);
        adapter = new ServiceScopeAdapter(getActivity());
        adapter.SetEditOnClickListener(new ServiceScopeAdapter.SetEditView() {
            @Override
            public void setview(ServiceCopeBean data) {

                startEditserviceFragment(data);

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (userBean != null) {
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("house_service_id", userBean.getHouse_service_id());
            getPresenter().getserviceCope(map);
        } else {
            startLogin();
        }

    }

    @Override
    public ServicecopePresenter createPresenter() {
        return new ServicecopePresenter(getApp());
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


    @OnClick({R.id.tv_ok, R.id.ivLeft})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_ok:
                //ToastUtils.showToast(getActivity(), "点击");
                startEditserviceFragment();
                break;
            case R.id.ivLeft:
                finish();
                break;

        }
    }

    @Override
    public void OnServiceCope(List<ServiceCopeBean> list) {
        /**
         * 返回的数据
         */
        mlist = list;
        adapter.clear();
        adapter.addAll(list);
    }
}
