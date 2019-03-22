package com.live.tv.mvp.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthFileBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.FileMoreAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.FileMorePresenter;
import com.live.tv.mvp.view.home.IFileMoreView;
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

public class FileMoreNewFragment extends BaseFragment<IFileMoreView, FileMorePresenter> implements IFileMoreView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecycleView;
    @BindView(R.id.add)
    TextView add;

    private FileMoreAdapter adapter;
    private List<HealthFileBean> list;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private String type;

    public static FileMoreNewFragment newInstance(String type) {

        Bundle args = new Bundle();

        FileMoreNewFragment fragment = new FileMoreNewFragment();
        fragment.setArguments(args);
        fragment.type = type;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_file_more;
    }

    @Override
    public void initUI() {
        switch (type){
            case "0":
                tvTitle.setText(R.string.choose_test);
                break;
            case "1":
                tvTitle.setText(R.string.more_file);
                break;
            case "2":
                tvTitle.setText(R.string.choose_Consultants);
                break;

        }

        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.edit);

        list = new ArrayList<>();
        adapter = new FileMoreAdapter(context, list);
        easyRecycleView.setLayoutManager(new LinearLayoutManager(context));
        easyRecycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.putExtra(Constants.HEALTH_RECORD_ID,adapter.getItem(position).getHealth_record_id());
                getActivity().setResult(Constants.REQUESTCODE,intent);
                finish();
            }
        });
        adapter.setOnClickListener(new FileMoreAdapter.onClickListener() {
            @Override
            public void onOpen() {

            }
        });
    }


    @Override
    public void initData() {

    }


    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().recordList(map);
    }


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.add})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                if ("编辑".equals(tvRight.getText().toString())) {
                    tvRight.setText(R.string.delete);
                    adapter.setType(0);
                    adapter.notifyDataSetChanged();
                    add.setText(R.string.delete_sure);
                } else {
                    tvRight.setText(R.string.edit);
                    adapter.setType(-1);
                    adapter.notifyDataSetChanged();
                    add.setText(R.string.add_file);
                }
                break;
            case R.id.add:
                startAddFileFragment();
                break;
        }
    }

    @Override
    public FileMorePresenter createPresenter() {
        return new FileMorePresenter(getApp());
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
    public void onRecordList(List<HealthFileBean> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDelRecord(String data) {

    }
}