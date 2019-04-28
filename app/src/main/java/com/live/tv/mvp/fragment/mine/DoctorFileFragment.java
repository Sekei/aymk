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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hyphenate.easeui.utils.GlideCircleTransform;
import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.EventBusMessage;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.DoctorFilePresenter;
import com.live.tv.mvp.view.mine.IDoctorFileView;

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

/**
 * 医生档案
 *
 * @author Created by stone
 * @since 2018/2/1
 */

public class DoctorFileFragment extends BaseFragment<IDoctorFileView, DoctorFilePresenter> implements IDoctorFileView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.avatar)
    ImageView avatar;
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


    public static DoctorFileFragment newInstance() {

        Bundle args = new Bundle();

        DoctorFileFragment fragment = new DoctorFileFragment();
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
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
        getPresenter().getDoctorDetail(map);

    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_doctor_file;
    }

    BaseInfoFragment baseinfo;
    ServiceSettingFragment serviceSetting;

    @Override
    public void initUI() {
        tvTitle.setText(R.string.doctor_file);
        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
        listTitle.add(getString(R.string.base_info));
        listTitle.add(getString(R.string.service_setting));

        baseinfo = BaseInfoFragment.newInstance();
        serviceSetting = ServiceSettingFragment.newInstance();
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
    public DoctorFilePresenter createPresenter() {
        return new DoctorFilePresenter(getApp());
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

    }

    @Override
    public void DoctorDetail(DoctorDetailBean data) {

        if (data != null) {

            setDataInfo(data);

        }

    }

    private void setDataInfo(DoctorDetailBean data) {
        Glide.with(context).load(Constants.BASE_URL + userBean.getMember_head_image())
                .transform(new GlideCircleTransform(context))
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(avatar);

        age.setText(data.getDoctor_age() + "岁");
        sex.setText("m".equals(data.getDoctor_sex()) ? "男" : "女");
        name.setText(userBean.getMember_nick_name());

        baseinfo.SetDoctorInfo(data);
        serviceSetting.SetServiceInfo(data);
    }


    @OnClick({R.id.ivLeft, R.id.tv_person_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_person_info:
                startDoctorInfoFragment();
                break;


        }
    }
}
