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
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.mvp.adapter.mine.MyDoctorAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.MyDoctorPresenter;
import com.live.tv.mvp.view.mine.IMyDoctorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的医生
 * Created by sh-lx on 2017/7/12.
 */

public class MydoctorFragment extends BaseFragment<IMyDoctorView,MyDoctorPresenter>implements IMyDoctorView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private MyDoctorAdapter mAdapter;

    public static MydoctorFragment newInstance() {

        Bundle args = new Bundle();

        MydoctorFragment fragment = new MydoctorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_my_doctor;
    }

    @Override
    public void initUI() {
        tvTitle.setText("我的医生");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter= new MyDoctorAdapter(new ArrayList<DoctorDetailBean>());
        recyclerView.setAdapter(mAdapter);



        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                DoctorDetailBean doctorDetailBean = (DoctorDetailBean) adapter.getData().get(position);

                startDoctorDetailFragment(doctorDetailBean.getDoctor_id());

            }
        });
    }

    @Override
    public void initData() {




    }

    @Override
    public void onResume() {
        super.onResume();
        Map<String,String>  mMap=new HashMap<>();
        mMap.put("member_id",userBean.getMember_id());
        mMap.put("member_token",userBean.getMember_token());
        getPresenter().myDoctorBeans(mMap);
    }

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
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
    public MyDoctorPresenter createPresenter() {
        return new MyDoctorPresenter(getApp());
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
    public void onMyDoctorBeans(List<DoctorDetailBean> data) {

        if (data!=null){

            mAdapter.setNewData(data);
        }
    }
}
