package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iflytek.cloud.thirdparty.V;
import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.OrderBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.MerchantFormPersenter;
import com.live.tv.mvp.view.mine.IMerchantFormView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/8/24.
 * 家政服务和商家订单
 */

public class MerchantFormFragment extends BaseFragment<IMerchantFormView, MerchantFormPersenter> implements IMerchantFormView {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    private String mType;
    private List<CharSequence> titlelist;
    private List<Fragment> listData;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;


    public static MerchantFormFragment newInstance(String str) {
        Bundle args = new Bundle();
        MerchantFormFragment fragment = new MerchantFormFragment();
        fragment.setArguments(args);
        fragment.mType=str;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_merchant_form;
    }

    @Override
    public void initUI() {
        if (mType!=null&&mType.equals("2")){
            tvTitle.setText("家政服务");

        }else {
            tvTitle.setText("商家订单");

        }
        ivLeft.setVisibility(View.VISIBLE);
        titlelist = new ArrayList<>();
        listData = new ArrayList<>();
        titlelist.add("全部服务");
        titlelist.add("待服务");
        titlelist.add("待确认");
        titlelist.add("已完成");
        titlelist.add("待评价");


        listData.add(MerchantFormItemFragment.newInstance("",mType));
        listData.add(MerchantFormItemFragment.newInstance("wait_service",mType));
        listData.add(MerchantFormItemFragment.newInstance("wait_service",mType));
        listData.add(MerchantFormItemFragment.newInstance("end",mType));
        listData.add(MerchantFormItemFragment.newInstance("wait_assessment",mType));
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, titlelist);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initData() {

    }

    @Override
    public MerchantFormPersenter createPresenter() {
        return new MerchantFormPersenter(getApp());
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
    public void OnClickLisenter(View view){
        switch (view.getId()){
            case R.id.ivLeft:

                finish();
                break;


        }
    }

    /**
     * 获取列表的数据
     * @param beanList
     */
    @Override
    public void onformList(List<OrderBean> beanList) {

    }
}
