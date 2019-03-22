package com.live.tv.mvp.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.EventBusMessage;
import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.HealthUserDetailPresenter;
import com.live.tv.mvp.view.mine.IHealthUserDetailView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 健康用户详情
 *
 * @author Created by stone
 * @since 2018/2/1
 */

public class HealthUserDetailFragment extends BaseFragment<IHealthUserDetailView, HealthUserDetailPresenter> implements IHealthUserDetailView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;

    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.verified)
    TextView verified;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.tabLayout2)
    TabLayout tabLayout2;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    Unbinder unbinder;
    private List<CharSequence> listTitle;
    private List<Fragment> listData;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private String health_record_id = "";

    public static HealthUserDetailFragment newInstance(String health_record_id) {

        Bundle args = new Bundle();
        HealthUserDetailFragment fragment = new HealthUserDetailFragment();
        fragment.health_record_id = health_record_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusMessage Event) {
        if ("setting_success".equals(Event.getMessage())) {
            Requestparameters();
        }


    }

    /**
     * 请求网络参数
     */
    private void Requestparameters() {

        HashMap<String, String> map = new HashMap<>();
//        map.put("member_id", userBean.getMember_id());
//        map.put("member_token", userBean.getMember_token());
        map.put("health_record_id", health_record_id);
        getPresenter().getHealthUserDetail(map);

    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_health_user_detail;
    }

    HealthUserDetailBaseInfoFragment baseinfo;
    HealthUserDetailSchemeFragment serviceSetting;

    @Override
    public void initUI() {
        tvTitle.setText(R.string.doctor_file);
        tvRight.setText("添加方案");
        tvRight.setVisibility(View.VISIBLE);

        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
        listTitle.add(getString(R.string.base_info));
        listTitle.add(getString(R.string.txt_plan));

        baseinfo = HealthUserDetailBaseInfoFragment.newInstance();
        serviceSetting = HealthUserDetailSchemeFragment.newInstance();
        listData.add(baseinfo);
        listData.add(serviceSetting);
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        mViewPager.setAdapter(viewPagerFragmentAdapter);
        tabLayout2.setupWithViewPager(mViewPager);
    }

    @Override
    public void initData() {
        Requestparameters();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public HealthUserDetailPresenter createPresenter() {
        return new HealthUserDetailPresenter(getApp());
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


    private void setDataInfo(HealthRecordDetailBean data) {
        Glide.with(context).load(Constants.BASE_URL + data.getHead_image())
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(avatar);

        age.setText(data.getAge() + "岁");
        sex.setText(data.getSex());
        name.setText(data.getReal_name());

        baseinfo.SetDoctorInfo(data);
        serviceSetting.SetDoctorInfo(data);
    }


    @OnClick({R.id.ivLeft,R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                //添加方案

                startAddPlanFragment(health_record_id);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==1){
            Requestparameters();

        }
    }

    @Override
    public void onHealthUserDetail(HealthRecordDetailBean data) {
        if (data != null) {

            setDataInfo(data);

        }
    }
}
