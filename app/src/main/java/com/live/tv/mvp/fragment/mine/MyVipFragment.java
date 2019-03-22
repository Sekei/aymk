package com.live.tv.mvp.fragment.mine;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AssociatorBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.mine.MyVipAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.MyVipPresenter;
import com.live.tv.mvp.view.mine.IMyVipView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class MyVipFragment extends BaseFragment<IMyVipView, MyVipPresenter> implements IMyVipView {

    @BindView(R.id.tittle)
    TextView tittle;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    private MyVipAdapter mAdapter;

    public static MyVipFragment newInstance() {

        Bundle args = new Bundle();

        MyVipFragment fragment = new MyVipFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_my_vip;
    }

    @Override
    public void initUI() {


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new MyVipAdapter(new ArrayList<AssociatorBean>());

        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AssociatorBean associatorBean = (AssociatorBean) adapter.getData().get(position);
                startVipDatailFragment(associatorBean);


            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintColor(R.color.colorPrimary);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);//状态栏所需颜色
            // tintManager.setTintColor(R.color.pure_white);//文字颜色
        }
    }

    @Override
    public void initData() {

        map.clear();
        getPresenter().getAssociators(map);

        if (userBean != null) {
            map.clear();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            getPresenter().getUserDetail(map);
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
    public MyVipPresenter createPresenter() {
        return new MyVipPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAssociators(List<AssociatorBean> data) {

        if (data != null) {

            mAdapter.setNewData(data);
        }
    }

    @Override
    public void onGetUserDetail(UserBean data) {
        Glide.with(context).load(Constants.BASE_URL + data.getMember_head_image())
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(avatar);
        name.setText(data.getMember_nick_name()+"  "+data.getAssociator_type());
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

    @OnClick(R.id.imageView4)
    public void onViewClicked() {

        finish();
    }
}
