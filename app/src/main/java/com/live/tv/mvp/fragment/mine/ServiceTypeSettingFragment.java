package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.ServiceTypeSettingPresenter;
import com.live.tv.mvp.view.mine.IServiceTypeSettingView;
import com.live.tv.util.LoadingUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 体验类型设置
 * 编辑内容
 *
 * @author Created by stone
 * @since 17/8/22
 */

public class ServiceTypeSettingFragment extends BaseFragment<IServiceTypeSettingView, ServiceTypeSettingPresenter> implements IServiceTypeSettingView {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ed_price)
    EditText edPrice;
    @BindView(R.id.textView23)
    View textView23;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    Unbinder unbinder;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private String state = "";
    private String state2 = "";

    private HealthManagerBeans healthManagerBeans;

    public static ServiceTypeSettingFragment newInstance(HealthManagerBeans healthManagerBeans, String state2) {
        Bundle args = new Bundle();
        ServiceTypeSettingFragment fragment = new ServiceTypeSettingFragment();
        fragment.healthManagerBeans = healthManagerBeans;
        fragment.state2 = state2;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_service_type_setting;
    }

    @Override
    public void initUI() {
        tvTitle.setText(getResources().getString(R.string.service_setting));

    }

    @Override
    public void initData() {

        if (healthManagerBeans != null) {

            edPrice.setText(healthManagerBeans.getService_price());
            edContent.setText(healthManagerBeans.getService_desc());
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
    public ServiceTypeSettingPresenter createPresenter() {
        return new ServiceTypeSettingPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ivLeft, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_submit:

                Map<String, String> map = new HashMap<>();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("service_price", edPrice.getText().toString().trim());
                map.put("service_desc", edContent.getText().toString());
                map.put("health_manager_id", healthManagerBeans.getHealth_manager_id());
                getPresenter().updateHealthManager(map);
                LoadingUtil.showLoading(context, "保存中...");


                break;
        }
    }

    @Override
    public void onUpdateHealthManager(String data) {
        LoadingUtil.hideLoading();
        ToastUtils.showToast(context, data);
        finish();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        LoadingUtil.hideLoading();
        errorHandle(e);
    }
}
