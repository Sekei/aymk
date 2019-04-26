package com.live.tv.mvp.fragment.mine;

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
import com.live.tv.bean.MemberMsgsBean;
import com.live.tv.bean.PlateListBean;
import com.live.tv.mvp.adapter.home.LiveMessageAdapter;
import com.live.tv.mvp.adapter.home.OrderMessageAdapter;
import com.live.tv.mvp.adapter.mine.NewMyPostedAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.MsgListPresenter;
import com.live.tv.mvp.presenter.mine.PostedListFragmentPresenter;
import com.live.tv.mvp.view.home.IMsgListView;
import com.live.tv.mvp.view.mine.IPostedListView;
import com.ysjk.health.iemk.R;

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
 * @since 2018/8/1
 */

public class MessageListFragment extends BaseFragment<IMsgListView, MsgListPresenter> implements IMsgListView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recycler_view)
    EasyRecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    private LiveMessageAdapter adapter;
    private View loadMore;
    private int currentPage = 1;
    private boolean isMore;
    private Map<String, String> map = new HashMap<>();
    private List<MemberMsgsBean> plateListBeenlist = new ArrayList<>();

    public static MessageListFragment newInstance() {
        MessageListFragment fragment = new MessageListFragment();
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_postedlist;
    }

    @Override
    public void initUI() {
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        tvTitle.setText("消息列表");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("新增");
        adapter = new LiveMessageAdapter(getActivity(), plateListBeenlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        //刷新
        recyclerView.setRefreshingColorResources(R.color.colorPrimary);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                showdata();
            }
        });

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //startPostDetailsFragment(adapter.getAllData().get(position).getPost_id() + "");
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
                    currentPage++;
                    showdata();

                }
            }


            @Override
            public void onMoreClick() {

            }
        });
    }

    private void showdata() {
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("page", currentPage + "");
        map.put("msg_type", "live");
        getPresenter().getMemberMsgs(map);
    }

    @Override
    public void initData() {
        showdata();

    }

    @Override
    public MsgListPresenter createPresenter() {
        return new MsgListPresenter(getApp());
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


    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onCliclk(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startMessageSendFragment();
                break;
        }
    }

    /***
     * 获取数据
     * @param plateListBeenlist
     */
    @Override
    public void onGetMemberMsgs(List<MemberMsgsBean> plateListBeenlist) {
        if (plateListBeenlist != null) {
            if (plateListBeenlist.size() == 10) {
                isMore = true;
            } else {
                isMore = false;
                loadMore.setVisibility(View.GONE);
            }
            if (currentPage == 1) {
                adapter.clear();
            }
            adapter.addAll(plateListBeenlist);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onSendMsg(String data) {

    }
}
