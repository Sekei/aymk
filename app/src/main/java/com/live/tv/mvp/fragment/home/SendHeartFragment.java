package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.HeartBean;
import com.live.tv.bean.HeartMoneyBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.HeartListAdapter;
import com.live.tv.mvp.adapter.home.HeartMoneyAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.SendHeartPresenter;
import com.live.tv.mvp.view.home.ISendHeartView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ysjk.health.iemk.R.id.easyRecyclerView;


/**
 * @author Created by stone
 * @since 2018/1/11
 */

public class SendHeartFragment extends BaseFragment<ISendHeartView, SendHeartPresenter> implements ISendHeartView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecycleView;
    Unbinder unbinder;
    private HeartListAdapter adapter;
    private List<HeartBean> list;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    private HeartMoneyAdapter heartMoneyAdapter;
    private List<HeartMoneyBean> moneyList;
    private String doctor_id;
    private String doctorName;
    private String img_str;

    private int mPosition = 0;
    View loadMore;
    private int page = 1;
    private boolean isMore = true;
    public static SendHeartFragment newInstance(String doctor_id, String sendDoctorName,String img_str) {
        Bundle args = new Bundle();
        SendHeartFragment fragment = new SendHeartFragment();
        fragment.doctor_id = doctor_id;
        fragment.doctorName = sendDoctorName;
        fragment.img_str = img_str;
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("send_success")) {
            page=1;
            RequestParameters();//刷新数据

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_common_test;
    }
    ImageView avatar;
    @Override
    public void initUI() {
        tvTitle.setText(R.string.send_heart);
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        list = new ArrayList<>();
        adapter = new HeartListAdapter(context, list);
        easyRecycleView.setLayoutManager(new LinearLayoutManager(context));
        easyRecycleView.setAdapter(adapter);
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                final View view = LayoutInflater.from(context).inflate(R.layout.top_send_heart, null);
                TextView name = (TextView) view.findViewById(R.id.name);
                 avatar = (ImageView) view.findViewById(R.id.avatar);
                Glide.with(context).load(Constants.BASE_URL + img_str)
                        .error(R.drawable.ava_defaultx)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(avatar);

                name.setText(doctorName);
                EasyRecyclerView moneyEasyRecyclerView = (EasyRecyclerView) view.findViewById(easyRecyclerView);
                moneyList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    HeartMoneyBean heartMoneyBean = new HeartMoneyBean();
                    switch (i) {
                        case 0:
                            heartMoneyBean.setMoney("2");
                            heartMoneyBean.setUnit(getResources().getString(R.string.yuan));
                            heartMoneyBean.setContent(getResources().getString(R.string.little_mind));
                            break;
                        case 1:
                            heartMoneyBean.setMoney("8");
                            heartMoneyBean.setUnit(getResources().getString(R.string.yuan));
                            heartMoneyBean.setContent(getResources().getString(R.string.white_angel));
                            break;
                        case 2:
                            heartMoneyBean.setMoney("12");
                            heartMoneyBean.setUnit(getResources().getString(R.string.yuan));
                            heartMoneyBean.setContent(getResources().getString(R.string.medical_skills));
                            break;
                        case 3:
                            heartMoneyBean.setMoney("36");
                            heartMoneyBean.setUnit(getResources().getString(R.string.yuan));
                            heartMoneyBean.setContent(getResources().getString(R.string.german_xin));
                            break;
                        case 4:
                            heartMoneyBean.setMoney("");
                            heartMoneyBean.setUnit(getResources().getString(R.string.yuan));
                            heartMoneyBean.setContent(getResources().getString(R.string.crafting_heart));
                            break;
                    }
                    moneyList.add(heartMoneyBean);
                }
                heartMoneyAdapter = new HeartMoneyAdapter(context, moneyList);
                moneyEasyRecyclerView.setLayoutManager(new GridLayoutManager(context, 5));
                moneyEasyRecyclerView.setAdapter(heartMoneyAdapter);
                heartMoneyAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        mPosition = position;
                        if (position == 4) {
                            startEditMoneyFragmet("");
                        }
                        heartMoneyAdapter.setPosition(position);
                        heartMoneyAdapter.notifyDataSetChanged();
                    }
                });

                final TextView comment = (TextView) view.findViewById(R.id.comment);
                TextView commit = (TextView) view.findViewById(R.id.commit);
                commit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       // startPayServerFragment("2", heartMoneyAdapter.getItem(mPosition).getMoney(), doctor_id, comment.getText().toString(), doctorName);

                        startGiveHeartFragment(doctorName,doctor_id,comment.getText().toString(),heartMoneyAdapter.getItem(mPosition).getMoney());
                        comment.setText("");
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
            }
        });





        //刷新
        easyRecycleView.setRefreshingColorResources(R.color.colorPrimary);
        easyRecycleView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        map.clear();
        map.put("page", String.valueOf(page));
        map.put("doctor_id", doctor_id);
        getPresenter().getHeartList(map);
    }



    @Override
    public void initData() {
        RequestParameters();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onGetHeartList(List<HeartBean> data) {
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

    public void startEditMoneyFragmet(String s) {
        EditMoneyFragmet editMoneyFragmet = EditMoneyFragmet.newInstance(s);
        editMoneyFragmet.setOkClickListener(new EditMoneyFragmet.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                heartMoneyAdapter.getItem(4).setMoney(state);
                heartMoneyAdapter.notifyItemChanged(4);
            }
        });
        editMoneyFragmet.show(getFragmentManager(), SendHeartFragment.class.getSimpleName());
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
    public SendHeartPresenter createPresenter() {
        return new SendHeartPresenter(getApp());
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


}