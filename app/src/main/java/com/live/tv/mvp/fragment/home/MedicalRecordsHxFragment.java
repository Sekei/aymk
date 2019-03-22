package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.MedicalClassBean;
import com.live.tv.bean.MedicalListBean;
import com.live.tv.mvp.adapter.home.MedicalClassdAdapter;
import com.live.tv.mvp.adapter.home.MedicalMonthListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.MedicalRecordsPresenter;
import com.live.tv.mvp.view.home.IMedicalRecordsView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/15
 */

public class MedicalRecordsHxFragment extends BaseFragment<IMedicalRecordsView, MedicalRecordsPresenter> implements IMedicalRecordsView {
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.all)
    TextView all;
    private Map<String, String> map = new HashMap<>();

    private MedicalClassdAdapter medicalClassdAdapter;
    private List<MedicalClassBean> medicalClassList;

 /*   private MedicalListAdapter adapter;
    private List<MedicalListBean> medicalList;*/

    private MedicalMonthListAdapter adapter;
    private List<MedicalListBean> medicalList;
    private String medical_class_id = "";
    private String  click_type;

    public static MedicalRecordsHxFragment newInstance() {
        Bundle args = new Bundle();
        MedicalRecordsHxFragment fragment = new MedicalRecordsHxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_medical_record;
    }

    @Override
    public void initUI() {
        tvTitle.setText("电子病历");
        medicalClassList = new ArrayList<>();
        LinearLayoutManager nearbyLinearLayoutManager = new LinearLayoutManager(context);
        nearbyLinearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        medicalClassdAdapter = new MedicalClassdAdapter(context, medicalClassList);
        recyclerView.setLayoutManager(nearbyLinearLayoutManager);
        recyclerView.setAdapter(medicalClassdAdapter);
        medicalClassdAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                all.setTextColor(getResources().getColor(R.color.colorTextG6));
                all.setBackgroundResource(R.color.pure_white);
                medicalClassdAdapter.setPosition(position);
                medicalClassdAdapter.notifyDataSetChanged();
                medical_class_id=medicalClassdAdapter.getItem(position).getMedical_class_id();
                map.clear();

                if ("1".equals(userBean.getDoctor_type())){

                    map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
                }

                map.put("medical_class_id", medical_class_id);
                map.put("member_id",userBean.getMember_id());
                map.put("member_token",userBean.getMember_token());
                getPresenter().medicalList(map);

            }
        });


      /*  medicalList = new ArrayList<>();
        adapter = new MedicalListAdapter(context, medicalList);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new MedicalListAdapter.OnClickListener() {
            @Override
            public void onOpen() {

            }
        });
*/
        medicalList = new ArrayList<>();
        adapter = new MedicalMonthListAdapter(context, medicalList);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new MedicalMonthListAdapter.OnClickListener() {
            @Override
            public void onOpen(MedicalListBean medicalListBean, int pos) {
                startMedicalRecordsDetailFragment(medicalListBean, pos);

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void initData() {
        getPresenter().getMedicalClassBeans(map);

        map.clear();
        if ("1".equals(userBean.getDoctor_type())){

            map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
        }
        map.put("member_id",userBean.getMember_id());
        map.put("member_token",userBean.getMember_token());
        getPresenter().medicalList(map);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("bingli_success")) {
            map.clear();
            if ("1".equals(userBean.getDoctor_type())){

                map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
            }
            map.put("medical_class_id",medical_class_id);
            map.put("member_id",userBean.getMember_id());
            map.put("member_token",userBean.getMember_token());
            getPresenter().medicalList(map);
        }
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public MedicalRecordsPresenter createPresenter() {
        return new MedicalRecordsPresenter(getApp());
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
    public void onGetMedicalClassBeans(List<MedicalClassBean> data) {
        medicalClassdAdapter.clear();
        medicalClassdAdapter.addAll(data);
        medicalClassdAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMedicalList(List<MedicalListBean> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ivLeft, R.id.all})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.all:
                medicalClassdAdapter.setPosition(-1);
                medicalClassdAdapter.notifyDataSetChanged();
                all.setTextColor(getResources().getColor(R.color.pure_white));
                all.setBackgroundResource(R.color.colorPrimary);
                map.clear();
                if ("1".equals(userBean.getDoctor_type())){
                    map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
                }
                map.put("member_id",userBean.getMember_id());
                map.put("member_token",userBean.getMember_token());
                getPresenter().medicalList(map);
                break;

        }
    }
}
