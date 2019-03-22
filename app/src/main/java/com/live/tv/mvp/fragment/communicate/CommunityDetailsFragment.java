package com.live.tv.mvp.fragment.communicate;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.PlateListBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.communicate.CommunityAllMessageAdapter;
import com.live.tv.mvp.adapter.communicate.CommunityShowimgAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.communicate.CommunityDetailsPresenter;
import com.live.tv.mvp.view.communicate.ICommunityDetailsView;
import com.live.tv.util.SpSingleInstance;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/7/24
 * 首页帖子的详情
 */

public class CommunityDetailsFragment extends BaseFragment<ICommunityDetailsView, CommunityDetailsPresenter> implements ICommunityDetailsView {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tv_Title;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.iv_heard)
    ImageView ivHeard;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.recycle_image)
    RecyclerView recycleImage;
    @BindView(R.id.recycle_all)
    RecyclerView recycleAll;

    Unbinder unbinder;
    private String mplateId;
    private UserBean userBean;
    private CommunityAllMessageAdapter messageAdapter;
    private CommunityShowimgAdapter showimgAdapter;
    private Map<String, String> map = new HashMap<>();


    public static CommunityDetailsFragment newInstance(String plateid) {

        CommunityDetailsFragment fragment = new CommunityDetailsFragment();

        fragment.mplateId = plateid;

        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_community_detail;
    }

    @Override
    public void initUI() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();

        recycleImage.setNestedScrollingEnabled(false);//禁止滑动
        recycleAll.setNestedScrollingEnabled(false);
        messageAdapter = new CommunityAllMessageAdapter(getActivity());
        showimgAdapter = new CommunityShowimgAdapter(getActivity());
        recycleImage.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recycleImage.setAdapter(showimgAdapter);
        recycleAll.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleAll.setAdapter(messageAdapter);

    }

    @Override
    public void initData() {

        if (mplateId != null && !mplateId.equals("")) {
            map.put("post_id", mplateId);
            map.put("member_id", userBean.getMember_id());
            getPresenter().getPlateDetails(map);
        }

    }

    @Override
    public CommunityDetailsPresenter createPresenter() {
        return new CommunityDetailsPresenter(getApp());
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

    /***
     * 返回的数据
     * @param plateListBean
     */
    @Override
    public void onPlateDetails(PlateListBean plateListBean) {

        messageAdapter.addAll(plateListBean.getCommentPostBeans());
        messageAdapter.notifyDataSetChanged();
        showimgAdapter.addAll(plateListBean.getPostImageBeans());
        showimgAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ivLeft})
    public void Onclick(View view){

    switch (view.getId()){
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
