package com.live.tv.mvp.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ConsultBean;
import com.live.tv.bean.FirstEvent;
import com.live.tv.mvp.adapter.home.MyAdvisoryAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.huanxin.ChatActivity;
import com.live.tv.mvp.presenter.mine.MyAdvisoryPresenter;
import com.live.tv.mvp.view.home.IMyAdvisoryView;

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
 * @since 2018/1/18
 */

public class MyAdvisoryFragment extends BaseFragment<IMyAdvisoryView,MyAdvisoryPresenter>implements IMyAdvisoryView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;
    Unbinder unbinder;
    private MyAdvisoryAdapter adapter;
    private List<ConsultBean> list;
    private Map<String, String> map = new HashMap<>();

    View loadMore;
    private int page = 1;
    private boolean isMore = true;

    public static MyAdvisoryFragment newInstance() {
        Bundle args = new Bundle();
        MyAdvisoryFragment fragment = new MyAdvisoryFragment();
        fragment.setArguments(args);
        return fragment;
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

    @Subscribe(threadMode = ThreadMode.MAIN)

    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("close_advisory")) {
            Map<String,String> mMap= new HashMap<>();
            mMap.put("member_id",userBean.getMember_id());
            mMap.put("is_done","0");
            mMap.put("page","1");
            getPresenter().getConsultList(mMap);

        }
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_my_advisory;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.advisory);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.advisory_history);
        tvRight.setTextColor(getResources().getColor(R.color.tx_4));
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        list = new ArrayList<>();
        adapter = new MyAdvisoryAdapter(context, list);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ConsultBean consultBean =  adapter.getItem(position);
//


                if ("text".equals(consultBean.getConsult_type())){
                    startActivity(new Intent(getActivity(), ChatActivity.class)
                            .putExtra("userId",consultBean.getHx_account())
                            .putExtra("APP_user_name",consultBean.getDoctor_name())
                            .putExtra("health_record_id",consultBean.getHealth_record_id())
                            .putExtra("doctor_id",consultBean.getDoctor_id())
                            .putExtra("consult_record_id",consultBean.getConsult_record_id())
                            .putExtra("to_head_image",consultBean.getMember_head_image())
                            .putExtra("to_username",consultBean.getMember_nick_name())
                    );

                }else {

                    startVoiceVideoCallFragment(consultBean,"user");
                }

            }
        });



        //刷新
        easyRecyclerView.setRefreshingColorResources(R.color.colorPrimary);
        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                RequestParameters();
            }
        });


        //下拉加载
        adapter.setMore(loadMore, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                if (isMore) {
                    if (loadMore != null) {
                        loadMore.setVisibility(View.VISIBLE);
                    }
                    page++;
                    RequestParameters();

                }
            }

            @Override
            public void onMoreClick() {

            }
        });
    }

    private void RequestParameters() {
        Map<String,String> mMap= new HashMap<>();
        mMap.put("member_id",userBean.getMember_id());
        mMap.put("is_done","0");
        mMap.put("page", String.valueOf(page));
        getPresenter().getConsultList(mMap);

    }

    @Override
    public void initData() {
        RequestParameters();

    }

    @OnClick({R.id.ivLeft,R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.tvRight:
                startHistoryAdvisoryFragment();
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
    public MyAdvisoryPresenter createPresenter() {
        return new MyAdvisoryPresenter(getApp());
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
    public void onGetConsultList(List<ConsultBean> data) {
        if (data.size() == 10) {
            isMore = true;
        } else {
            isMore = false;
            loadMore.setVisibility(View.GONE);
        }
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
