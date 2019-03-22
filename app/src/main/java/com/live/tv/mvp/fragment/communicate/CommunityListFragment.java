package com.live.tv.mvp.fragment.communicate;

import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.PlateListBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.communicate.CommunityListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.communicate.CommunityListFragmentPresenter;
import com.live.tv.mvp.view.communicate.ICommunityListFragmentView;
import com.live.tv.util.SpSingleInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/7/18
 * 健康养生界面
 */

public class CommunityListFragment extends BaseFragment<ICommunityListFragmentView, CommunityListFragmentPresenter> implements ICommunityListFragmentView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecycleView;
    Unbinder unbinder;
    private CommunityListAdapter adapter;
   // private List<DoctorDetailBean> list;
    private List<PlateListBean> mlist=new ArrayList<>();
    private int mpage=1;
    View loadMore;
    private UserBean userBean;
    private String mPlateid;
    private Map<String, String> map = new HashMap<>();
    private boolean isMore;
    private String mTitle;
    public static CommunityListFragment newInstance(String plateid,String title) {
        CommunityListFragment fragment = new CommunityListFragment();
        fragment.mPlateid = plateid;
        fragment.mTitle=title;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_common_test;
    }

    @Override
    public void initUI() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);

        if (mTitle!=null&&!mTitle.equals("")){
            tvTitle.setText(mTitle);

        }else {
            tvTitle.setText("健康养生");

        }
        if (userBean!=null){
            if ("1".equals(userBean.getDoctor_type())){
                tvRight.setVisibility(View.VISIBLE);

            }else {
                tvRight.setVisibility(View.GONE);

            }
        }
        tvRight.setText("发帖");
        tvRight.setTextColor(getResources().getColor(R.color.tx_4));
        adapter = new CommunityListAdapter(context);
        easyRecycleView.setLayoutManager(new LinearLayoutManager(context));
        easyRecycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
               try {
                   startPostDetailsFragment(mlist.get(position).getPost_id()+"");

               }catch (Exception e){
                   e.printStackTrace();
               }
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
                    mpage++;
                    map.put("plate_id",mPlateid);
                    try {
                        map.put("member_id",userBean.getMember_id());

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    map.put("page",mpage+"");
                    getPresenter().gethomeHealthMore(map);

                }else {

                   // ToastUtils.showToast(getActivity(),"我是有底限的～～");
                }
            }

            @Override
            public void onMoreClick() {

            }
        });
    }

    @Override
    public void initData() {

        map.put("plate_id",mPlateid);
        if (userBean!=null){
            map.put("member_id",userBean.getMember_id());

        }
        map.put("page",mpage+"");
        getPresenter().gethomeHealthMore(map);
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startPostedFragment(mPlateid);
                break;
        }
    }

    @Override
    public CommunityListFragmentPresenter createPresenter() {
        return new CommunityListFragmentPresenter(getApp());
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
     * 健康养生界面的数据获取
     * @param homeHealthDetails
     */
    @Override
    public void onHomeHealthMore(List<PlateListBean> homeHealthDetails) {
        if (homeHealthDetails.size() == 10) {
            isMore = true;
        } else {
            isMore = false;
            loadMore.setVisibility(View.GONE);
        }
        if (mpage == 1) {
            adapter.clear();
            mlist.clear();
        }
        mlist.addAll(homeHealthDetails);
        adapter.addAll(homeHealthDetails);
        adapter.notifyDataSetChanged();
    }


}
