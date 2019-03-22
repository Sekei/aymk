package com.live.tv.mvp.fragment.huanxin;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.HealthPlanBean;
import com.live.tv.mvp.adapter.home.HealthStepAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.HealthStepPresenter;
import com.live.tv.mvp.view.home.IHealthStepView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Created by stone
 * @since 2018/1/17
 */

public class HuaxinHealthStepFragment extends BaseFragment<IHealthStepView, HealthStepPresenter> implements IHealthStepView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.img)
    CircleImageView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.mark)
    TextView mark;
    @BindView(R.id.no_mark)
    TextView noMark;
    @BindView(R.id.already_mark)
    TextView alreadyMark;
    private List<HealthPlanBean> homeButtonList;
    private HealthStepAdapter adapter;
    private String health_record_id = "";
    private String doctor_id = "";
    private String health_plan_id = "";


    public static HuaxinHealthStepFragment newInstance(String health_record_id, String doctor_id) {

        Bundle args = new Bundle();
        HuaxinHealthStepFragment fragment = new HuaxinHealthStepFragment();
        fragment.health_record_id = health_record_id;
        fragment.doctor_id = doctor_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_health_step;
    }

    @Override
    public void initUI() {
        tvTitle.setText("健康阶段");
        homeButtonList = new ArrayList<>();

        adapter = new HealthStepAdapter(context, homeButtonList);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                HealthPlanBean healthPlanBean = adapter.getItem(position);
                startHealthStepDetailFragment(healthPlanBean.getHealth_plan_id());
            }
        });

    }

    @Override
    public void initData() {

        Map<String, String> mMap = new HashMap<>();
        mMap.put("health_record_id", health_record_id);
        mMap.put("doctor_id", doctor_id);
        getPresenter().getHealthStage(mMap);
    }

    @OnClick({R.id.ivLeft,R.id.mark})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.mark:

                if (!health_plan_id.equals("")){

                    Map<String,String> mMap = new HashMap<>();
                    mMap.put("member_id",userBean.getMember_id());
                    mMap.put("member_token",userBean.getMember_token());
                    mMap.put("health_record_id", health_record_id);
                    mMap.put("doctor_id", doctor_id);
                    getPresenter().playCard(mMap);
                }

                break;
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
    public HealthStepPresenter createPresenter() {
        return new HealthStepPresenter(getApp());
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

    @Override
    public void onGetHealthStage(DoctorDetailBean data) {

        if (data != null) {
            Glide.with(context).load(Constants.BASE_URL + data.getDoctor_head_image())
                    .placeholder(R.drawable.ava_defaultx)
                    .error(R.drawable.ava_defaultx)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img);

            name.setText(data.getDoctor_name());
            type.setText(data.getDoctor_department());




            if (data.getHealthPlanBeans() != null&&data.getHealthPlanBeans().size()>0) {


                adapter.clear();
                adapter.addAll(data.getHealthPlanBeans());
                adapter.notifyDataSetChanged();
                health_plan_id=data.getHealthPlanBeans().get(0).getHealth_plan_id();

            }


        }
    }

    @Override
    public void onplayCard(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        Map<String, String> mMap = new HashMap<>();
        mMap.put("health_record_id", health_record_id);
        mMap.put("doctor_id", doctor_id);
        getPresenter().getHealthStage(mMap);
    }

}
