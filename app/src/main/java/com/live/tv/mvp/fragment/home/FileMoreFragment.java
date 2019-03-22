package com.live.tv.mvp.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthFileBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.FileMoreAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.FileMorePresenter;
import com.live.tv.mvp.view.home.IFileMoreView;
import com.live.tv.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;

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

public class FileMoreFragment extends BaseFragment<IFileMoreView, FileMorePresenter> implements IFileMoreView {

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

    public static FileMoreFragment newInstance(String type) {

        Bundle args = new Bundle();

        FileMoreFragment fragment = new FileMoreFragment();
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
        tvTitle.setText(R.string.more_file);

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


        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


                HealthFileBean healthFileBean =    adapter.getAllData().get(position);
             if (tvRight.getText().equals("完成")) {

                 ToastUtils.showToast(context.getApplicationContext(),"编辑状态不可选择");
             } else {

                 EventBus.getDefault().post(healthFileBean.getHealth_record_id());
                 finish();
             }

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
                    tvRight.setText("完成");
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

                if ("确定删除".equals(add.getText().toString())){

                    String num_str="";

                    for (HealthFileBean healthFileBean:adapter.getAllData()){

                        if (healthFileBean.isSelect()){

                            num_str+=healthFileBean.getHealth_record_id()+",";
                        }
                    }

                    if (!num_str.equals("")){

                        num_str=num_str.substring(0,num_str.length()-1);

                        Map<String,String> mMap = new HashMap<>();

                        mMap.put("member_id",userBean.getMember_id());
                        mMap.put("member_token",userBean.getMember_token());
                        mMap.put("health_record_ids",num_str);
                        getPresenter().Delrecord(mMap);


                    }else {

                        ToastUtils.showToast(context.getApplicationContext(),"未选择");

                        return;
                    }





                }else if ("新建档案".equals(add.getText().toString())){
                    startAddFileFragment();

                }



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

        if ("编辑".equals(tvRight.getText().toString())) {
            adapter.clear();
            adapter.setType(-1);
            adapter.addAll(data);
            adapter.notifyDataSetChanged();


        } else {
            adapter.clear();
            adapter.setType(0);
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
        }




    }

    @Override
    public void onDelRecord(String data) {
        ToastUtils.showToast(context.getApplicationContext(),data);
        getPresenter().recordList(map);
    }
}