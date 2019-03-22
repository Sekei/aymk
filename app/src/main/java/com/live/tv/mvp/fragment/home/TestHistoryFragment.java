package com.live.tv.mvp.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ReportHistoryBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.TestHitoryListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.TestHistoryPresenter;
import com.live.tv.mvp.view.home.ITestHistoryView;
import com.live.tv.util.SpSingleInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 2018/1/12
 */

public class TestHistoryFragment extends BaseFragment<ITestHistoryView, TestHistoryPresenter> implements ITestHistoryView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecycleView;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    private TestHitoryListAdapter adapter;
    private List<ReportHistoryBean> list;
    private  String click_type;//0 进入测试数据详情，1  聊天时发送测试数据

    public static TestHistoryFragment newInstance( String click_type) {

        Bundle args = new Bundle();
        TestHistoryFragment fragment = new TestHistoryFragment();
        fragment.click_type =click_type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test_history;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.test_report);
        list = new ArrayList<>();
        adapter = new TestHitoryListAdapter(context, list);
        easyRecycleView.setLayoutManager(new GridLayoutManager(context,2));
        easyRecycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if ("1".equals(click_type)){
                    // 聊天是发送测试数据  把测试test_id 发给对方
                    String  test_id=adapter.getItem(position).getTest_id()+"";
                    Intent intent = new Intent();
                    intent.putExtra("test_id",test_id);
                    getActivity().setResult(Constants.INTENT_TEST_DATA,intent);

                    finish();

                }else {
                    startTestResultTabFragment(adapter.getItem(position).getTest_id()+"");

                }

            }
        });
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        getPresenter().getReportList(map);
    }


    @Override
    public void onGetReportList(List<ReportHistoryBean> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
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
    public TestHistoryPresenter createPresenter() {
        return new TestHistoryPresenter(getApp());
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


}