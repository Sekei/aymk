package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.MemberMsgsBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.OrderMessageAdapter;
import com.live.tv.mvp.adapter.home.SystemMessageAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.MsgListPresenter;
import com.live.tv.mvp.view.home.IMsgListView;
import com.live.tv.util.SpSingleInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class MsgListFragment extends BaseFragment<IMsgListView, MsgListPresenter> implements IMsgListView {


    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;
    private List<MemberMsgsBean> recommendList;
    private SystemMessageAdapter adapter;
    private OrderMessageAdapter orderAdapter;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String state;


    public static MsgListFragment newInstance(String state) {
        Bundle args = new Bundle();
        MsgListFragment fragment = new MsgListFragment();
        fragment.state=state;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_all_msg;
    }

    @Override
    public void initUI() {

        switch (state) {
            case "0":
                recommendList = new ArrayList<>();
                orderAdapter = new OrderMessageAdapter(context, recommendList);
                easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                easyRecyclerView.setAdapter(orderAdapter);
                break;
            case "1":
                recommendList = new ArrayList<>();
                adapter = new SystemMessageAdapter(context, recommendList);
                easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                easyRecyclerView.setAdapter(adapter);
                break;
        }

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if (userBean != null) {
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
        }
        if (state.equals("0")) {
            map.put("msg_type", "order");
        } else {
            map.put("msg_type", "system");
        }
        getPresenter().getMemberMsgs(map);

    }

    @Override
    public void onGetMemberMsgs(List<MemberMsgsBean> data) {
        if (state.equals("0")) {
            orderAdapter.clear();
            orderAdapter.addAll(data);
            orderAdapter.notifyDataSetChanged();
        } else {
            adapter.clear();
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
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

    }



    @Override
    public MsgListPresenter createPresenter() {
        return new MsgListPresenter(getApp());
    }


}
