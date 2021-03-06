package com.live.tv.mvp.fragment.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.Note;
import com.live.tv.bean.PlateListBean;
import com.live.tv.dao.greendao.NoteDao;
import com.live.tv.mvp.adapter.communicate.MyNoteListAdapter;
import com.live.tv.mvp.adapter.home.NearbyAdapter;
import com.live.tv.mvp.adapter.mine.NewMyPostedAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.PostedListFragmentPresenter;
import com.live.tv.mvp.view.mine.IPostedListView;
import com.live.tv.util.editutils.SpacesItemDecoration;

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

public class NewMyPostedListFragment extends BaseFragment<IPostedListView, PostedListFragmentPresenter> implements IPostedListView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recycler_view)
    EasyRecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
   private NewMyPostedAdapter adapter;
    private View loadMore;
    private int currentPage=1;
    private boolean isMore;
    private Map<String,String> map=new HashMap<>();


    public static NewMyPostedListFragment newInstance() {

        NewMyPostedListFragment fragment = new NewMyPostedListFragment();


        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_postedlist;
    }

    @Override
    public void initUI() {
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);

        tvTitle.setText("我的帖子");
        tvRight.setVisibility(View.GONE);
        adapter=new NewMyPostedAdapter(getActivity());
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
                startPostDetailsFragment(adapter.getAllData().get(position).getPost_id()+"");
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
        map.put("member_id",userBean.getMember_id());
        map.put("member_token",userBean.getMember_token());
        map.put("page",currentPage+"");
        getPresenter().getPostlist(map);
    }

    @Override
    public void initData() {
        showdata();

    }

    @Override
    public PostedListFragmentPresenter createPresenter() {
        return new PostedListFragmentPresenter(getApp());
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

    @OnClick({R.id.ivLeft})

    public void Oncliclk(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }


    }

    /***
     * 获取数据
     * @param plateListBeenlist
     */
    @Override
    public void onPostedList(List<PlateListBean> plateListBeenlist) {

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
}
