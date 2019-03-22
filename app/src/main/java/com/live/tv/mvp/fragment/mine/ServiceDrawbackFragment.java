package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.ServiceDrawbackPresenter;
import com.live.tv.mvp.view.mine.IServiceDrawbackView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/9/3.
 * 家政服务申请退款
 */

public class ServiceDrawbackFragment extends BaseFragment<IServiceDrawbackView, ServiceDrawbackPresenter> implements IServiceDrawbackView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.iv_heard)
    ImageView ivHeard;
    @BindView(R.id.tv_title)
    TextView mtvTitle;
    @BindView(R.id.tv_drawback)
    TextView tvDrawback;
    @BindView(R.id.ed_drawback)
    EditText edDrawback;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    Unbinder unbinder;

    @Override
    public int getRootViewId() {
        return R.layout.fragment_servicedrawback;
    }

    @Override
    public void initUI() {
tvTitle.setText("申请退款");
    }

    @Override
    public void initData() {

    }

    @Override
    public ServiceDrawbackPresenter createPresenter() {
        return new ServiceDrawbackPresenter(getApp());
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
    @OnClick({R.id.ivLeft})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.ivLeft:

                finish();
                break;
        }
    }
}
