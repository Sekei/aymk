package com.live.tv.mvp.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.LiveBean;
import com.live.tv.mvp.activity.live.LivePlaybackActivity;
import com.live.tv.mvp.activity.live.PushFlowActivity;
import com.live.tv.mvp.activity.live.StartLiveActivity;
import com.live.tv.mvp.adapter.mine.LiveListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.live.MyLiveListPresenter;
import com.live.tv.mvp.view.live.IMyLiveListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class MyLiveListFragment extends BaseFragment<IMyLiveListView,MyLiveListPresenter>implements IMyLiveListView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    Unbinder unbinder;
    private LiveListAdapter mAdapter;
    private List<LiveBean> liveBeanList ;
    View loadMore;
    private int page = 1;
    private boolean isMore = true;

    public static MyLiveListFragment newInstance() {
        Bundle args = new Bundle();
        MyLiveListFragment fragment = new MyLiveListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_live_list;
    }

    @Override
    public void initUI() {
        tvTitle.setText("我的直播");
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        liveBeanList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mAdapter= new LiveListAdapter(getContext(),liveBeanList);
        recyclerView.setAdapter(mAdapter);
        //刷新
        recyclerView.setRefreshingColorResources(R.color.colorPrimary);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                paramsInfo();
            }
        });
        //下拉加载
        mAdapter.setMore(loadMore, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                if (isMore) {
                    if (loadMore != null) {
                        loadMore.setVisibility(View.VISIBLE);
                    }
                    page++;
                    paramsInfo();

                }
            }

            @Override
            public void onMoreClick() {

            }
        });

        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                LiveBean liveBean = mAdapter.getItem(position);

                if (liveBean.getLive_state().equals("1")) {
                    Intent intent = new Intent(getActivity(), PushFlowActivity.class);
                    intent.putExtra("live_push_url",liveBean.getLive_push_url());
                    intent.putExtra("roomId",liveBean.getRoom_id());
                    intent.putExtra("stream_id",liveBean.getStream_id());
                    intent.putExtra("liveId",liveBean.getLive_id());
                    startActivity(intent);

                }else {
                    Intent intent = new Intent(getActivity(), LivePlaybackActivity.class);
                    intent.putExtra("videoPath", liveBean.getLive_playback_url());
                    startActivity(intent);
                }
            }
        });

    }


    private void paramsInfo() {

        map.clear();
        map.put("member_id",userBean.getMember_id());
        map.put("member_token",userBean.getMember_token());
        map.put("isMe","1");
        map.put("live_type","1");
        map.put("hotOrNew","1");
        map.put("page", String.valueOf(page));
        getPresenter().getLiveList(map);
    }


    @Override
    public void initData() {

        paramsInfo();
    }

    @OnClick({R.id.ivLeft,R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.tvRight:

               // startStartLiveFragment();
                startActivity(new Intent(getActivity(), StartLiveActivity.class));

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
    public MyLiveListPresenter createPresenter() {
        return new MyLiveListPresenter(getApp());
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

    }

    @Override
    public void onLiveList(List<LiveBean> data) {
        if (data.size() == 10) {
            isMore = true;
        } else {
            isMore = false;
            loadMore.setVisibility(View.GONE);
        }
        if (page == 1) {
            mAdapter.clear();
        }
        mAdapter.addAll(data);
        mAdapter.notifyDataSetChanged();

    }
}
