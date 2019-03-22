package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.TuijianDoctorAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.TestResultTabPlanProfessionPresenter;
import com.live.tv.mvp.view.home.ITestResultTabPlanProfessionView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 调理方案
 * @author Created by stone
 * @since 2018/1/12
 */

public class TestResultTabPlanProfessionFragment extends BaseFragment<ITestResultTabPlanProfessionView, TestResultTabPlanProfessionPresenter> implements ITestResultTabPlanProfessionView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    Unbinder unbinder;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecycleView;


    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    private TuijianDoctorAdapter adapter;
    private List<DoctorDetailBean> list;
    private String test_id;

    public static TestResultTabPlanProfessionFragment newInstance(String test_id) {
        Bundle args = new Bundle();
        TestResultTabPlanProfessionFragment fragment = new TestResultTabPlanProfessionFragment();
        fragment.test_id = test_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test_result_plan_profession;
    }

    @Override
    public void initUI() {

        tvTitle.setText(R.string.conditioning_program);
        list = new ArrayList<>();
        adapter = new TuijianDoctorAdapter(context, list);
        easyRecycleView.setLayoutManager(new LinearLayoutManager(context));
        easyRecycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startDoctorDetailFragment(adapter.getItem(position).getDoctor_id() + "");
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.fragment_test_profession_top, null);
                TextView one = (TextView) view.findViewById(R.id.one);
                TextView two = (TextView) view.findViewById(R.id.two);
                TextView three = (TextView) view.findViewById(R.id.three);
                TextView four = (TextView) view.findViewById(R.id.four);
                TextView five = (TextView) view.findViewById(R.id.five);
                TextView six = (TextView) view.findViewById(R.id.six);
                TextView seven = (TextView) view.findViewById(R.id.seven);
                TextView eight = (TextView) view.findViewById(R.id.eight);
                one.setOnClickListener(onClickListener);
                two.setOnClickListener(onClickListener);
                three.setOnClickListener(onClickListener);
                four.setOnClickListener(onClickListener);
                five.setOnClickListener(onClickListener);
                six.setOnClickListener(onClickListener);
                seven.setOnClickListener(onClickListener);
                eight.setOnClickListener(onClickListener);
                return view;
            }
            @Override
            public void onBindView(View headerView) {

            }
        });
    }

    @Override
    public void initData() {

        Map<String,String> mMap = new HashMap<>();
        mMap.put("is_recommend","1");
        getPresenter().getDoctorList(mMap);
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.one:
                    startTestPlanProfessionFragment(test_id, "环境调养");
                    break;
                case R.id.two:
                    startTestPlanProfessionFragment(test_id, "运动调养");
                    break;
                case R.id.three:
                    startTestPlanProfessionFragment(test_id, "饮食调养");
                    break;
                case R.id.four:
                    startTestPlanProfessionFragment(test_id, "生活习惯调养");
                    break;
                case R.id.five:
                    startTestPlanProfessionFragment(test_id, "娱乐调养");
                    break;
                case R.id.six:
                    startTestPlanProfessionFragment(test_id, "按摩调养");
                    break;
                case R.id.seven:
                    startTestPlanProfessionFragment(test_id, "保健品调养");
                    break;
                case R.id.eight:
                    startTestPlanProfessionFragment(test_id, "药物调养");
                    break;
            }
        }
    };

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

        }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public TestResultTabPlanProfessionPresenter createPresenter() {
        return new TestResultTabPlanProfessionPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onGetDoctorList(List<DoctorDetailBean> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

    }
}
