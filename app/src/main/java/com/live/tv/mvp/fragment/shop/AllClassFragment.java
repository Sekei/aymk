package com.live.tv.mvp.fragment.shop;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ClassBean;
import com.live.tv.mvp.adapter.shop.ClassFirstAdapter;
import com.live.tv.mvp.adapter.shop.ClassTwoAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.AllClassPresenter;
import com.live.tv.mvp.view.shop.IAllClassView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class AllClassFragment extends BaseFragment<IAllClassView,AllClassPresenter>implements IAllClassView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.mRecyclerView2)
    RecyclerView mRecyclerView2;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    LinearLayoutManager layoutManager;
    private ClassFirstAdapter classFirstAdapter;
    private ClassTwoAdapter classTwoAdapter;

    private  String class_name="";
    public static AllClassFragment newInstance() {

        Bundle args = new Bundle();

        AllClassFragment fragment = new AllClassFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_all_class;
    }

    @Override
    public void initUI() {
        tvTitle.setText("全部分类");

        layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        classFirstAdapter = new ClassFirstAdapter(new ArrayList<ClassBean>());
        mRecyclerView.setAdapter(classFirstAdapter);


        mRecyclerView2.setLayoutManager(new GridLayoutManager(getActivity(),3));
        classTwoAdapter = new ClassTwoAdapter(new ArrayList<ClassBean>());
        mRecyclerView2.setAdapter(classTwoAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                ClassBean goodsClass= (ClassBean) adapter.getData().get(position);
                classTwoAdapter.setNewData(goodsClass.getGoodsClassBeans());

            }
        });
        mRecyclerView2.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                ClassBean goodsClass= (ClassBean) adapter.getData().get(position);

//                startMerchantsClass(par_id,class_name,position);
                startClassGoosListFragment(goodsClass.getClass_uuid());

            }
        });
    }

    @Override
    public void initData() {
        paramsClassInfo();


    }


    //分类的参数
    private void paramsClassInfo() {

        Map<String, String> params = new HashMap<>();
        params.put("parent_id", "-1");
        params.put("level", "2");
        getPresenter().getFirstClass(params);
    }


    //分类的参数
    private void paramsClassTwoInfo(String  class_id) {

        Map<String, String> params = new HashMap<>();
        params.put("parent_id", class_id);
        getPresenter().getTwoClass(params);
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
    public AllClassPresenter createPresenter() {
        return new AllClassPresenter(getApp());
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
    public void onClass(List<ClassBean> data) {
        classFirstAdapter.setNewData(data);
        classTwoAdapter.setNewData(data.get(0).getGoodsClassBeans());
    }

    @Override
    public void onClassTwo(List<ClassBean> data) {

    }
}
