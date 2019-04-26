package com.live.tv.mvp.fragment.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.iflytek.cloud.thirdparty.V;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.live.tv.MainActivity;
import com.live.tv.bean.BannerBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.HomeButtonBean;
import com.live.tv.bean.HomeHealthDetail;
import com.live.tv.bean.HomeImgsBean;
import com.live.tv.bean.HomeMessageBean;
import com.live.tv.bean.LiveBean;
import com.live.tv.bean.MerchantHotBean;
import com.live.tv.bean.NewsBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.activity.ContentActivity;
import com.live.tv.mvp.activity.live.LivePlaybackActivity;
import com.live.tv.mvp.activity.live.PlaybackActivity;
import com.live.tv.mvp.adapter.home.DoctorAdapter;
import com.live.tv.mvp.adapter.home.HealthAdapter;
import com.live.tv.mvp.adapter.home.HomeButtonAdapter;
import com.live.tv.mvp.adapter.home.LiveAdapter;
import com.live.tv.mvp.adapter.home.NearbyAdapter;
import com.live.tv.mvp.adapter.home.NearbyRecommendAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.HomePresenter;
import com.live.tv.mvp.view.home.IHomeView;
import com.live.tv.util.HorizontalScrollTextView;
import com.live.tv.util.SpSingleInstance;
import com.live.tv.util.Utils;
import com.ysjk.health.iemk.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.TELEPHONY_SERVICE;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class HomeFragment extends BaseFragment<IHomeView, HomePresenter> implements IHomeView, PoiSearch.OnPoiSearchListener {


    @BindView(R.id.convenietnBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.homeButton_recycleView)
    RecyclerView homeButtonRecycleView;
    @BindView(R.id.live_recycleView)
    RecyclerView liveRecycleView;
    @BindView(R.id.doctor_recommend_recycleView)
    RecyclerView doctorRecommendRecycleView;
    @BindView(R.id.health_recycleView)
    RecyclerView healthRecycleView;
    @BindView(R.id.nearby_recommend_recycleView)
    RecyclerView nearbyRecommendRecycleView;
    @BindView(R.id.nearby_recycleView)
    RecyclerView nearbyRecycleView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.mLocation)
    TextView mLocation;
    @BindView(R.id.sw_message)
    HorizontalScrollTextView sw_message;
    @BindView(R.id.home_one_picture)
    ImageView homeOnePicture;
    @BindView(R.id.home_two_picture)
    ImageView homeTwoPicture;
    @BindView(R.id.home_three_picture)
    ImageView homeThreePicture;
    @BindView(R.id.health_tittle)
    TextView healthTittle;
    @BindView(R.id.health_more)
    TextView healthMore;
    @BindView(R.id.host_tittle)
    TextView hostTittle;
    @BindView(R.id.host_more)
    TextView hostMore;
    private List<MerchantHotBean> merchantHotlist;
    private HomeButtonAdapter homeButtonAdapter;
    List<HomeButtonBean> homeButtonList;
    private LiveAdapter liveAdapter;
    private DoctorAdapter doctorAdapter;
    private HealthAdapter healthAdapter;
    private NearbyRecommendAdapter nearbyRecommendAdapter;
    private NearbyAdapter nearbyAdapter;

    private List<BannerBean> listBanner;
    private List<LiveBean> liveBeanList;

    //private List<MerchantsBean> merchantsBeanList;
    private String plateId;


    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private PoiSearch.Query query;

    private boolean NoCity = true;
    //定位
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    public static String lag = "";
    public static String lat = "";
    private double dlat = 0;
    private double dlag = 0;
    public static String Detail_address = "";
    final int REQUEST_LOCATION = 102;
    private List<DoctorDetailBean> doctorDetailBeanList;

    private List<String> home_information = new ArrayList();
    private static final int MESSAGE_DELAYED = 1;
    private int information_index = 0;
    private String city = "上海";
    private List<String> mlist = new ArrayList<>();
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (mlist.size() > 0) {

                        sw_message.setText(mlist.get(information_index % mlist.size()));
                        sw_message.init(getActivity().getWindowManager());
                        sw_message.startScroll();
                        information_index++;
                        Message zskgg = mHandler.obtainMessage();
                        zskgg.what = MESSAGE_DELAYED;
                        zskgg.obj = information_index;
                        // mHandler.sendEmptyMessageDelayed(MESSAGE_DELAYED, 10000);
                    }
                    break;
            }
        }
    };
    private PoiSearch poiSearch;
    private ArrayList<PoiItem> list;
    private List<HomeHealthDetail.PostBeansBean> listdata;
    private String mtitle;


    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mHandler != null && mlist.size() > 0) {
            mHandler.sendEmptyMessage(MESSAGE_DELAYED);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (convenientBanner != null) {
            convenientBanner.stopTurning();
        }
        if (mHandler != null) {
            mHandler.removeMessages(MESSAGE_DELAYED);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (sw_message != null) {
            sw_message.stopScroll();

        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }

    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI() {
        sw_message.setOnScrollCompleteListener(new HorizontalScrollTextView.OnScrollCompleteListener() {
            @Override
            public void onScrollComplete() {
                mHandler.sendEmptyMessage(MESSAGE_DELAYED);

            }
        });
        //初始化定位
        initLocation();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        doctorDetailBeanList = new ArrayList<>();

        homeButtonList = new ArrayList();
        homeButtonList.add(new HomeButtonBean("快速咨询", R.drawable.home_icon_consul));
        homeButtonList.add(new HomeButtonBean("健康测试", R.drawable.home_icon_test));
        homeButtonList.add(new HomeButtonBean("健康档案", R.drawable.home_icon_file));
        homeButtonList.add(new HomeButtonBean("电子病历", R.drawable.home_icon_case));
        homeButtonList.add(new HomeButtonBean("健康数据", R.drawable.home_icon_data));
        homeButtonList.add(new HomeButtonBean("家政服务", R.drawable.home_icon_house));
        homeButtonAdapter = new HomeButtonAdapter(context, homeButtonList);


        homeButtonRecycleView.setLayoutManager(new GridLayoutManager(context, 3));
        homeButtonRecycleView.setAdapter(homeButtonAdapter);

        try {

            homeButtonRecycleView.setNestedScrollingEnabled(false);
            liveRecycleView.setNestedScrollingEnabled(false);
            doctorRecommendRecycleView.setNestedScrollingEnabled(false);
            healthRecycleView.setNestedScrollingEnabled(false);
            nearbyRecycleView.setNestedScrollingEnabled(false);
            nearbyRecommendRecycleView.setNestedScrollingEnabled(false);

        } catch (Exception e) {

            Log.e("dfc", "initUI: " + e.getMessage());
        }


        homeButtonAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
                if (userBean != null) {

                    switch (position) {
                        case 0:
                            startDoctorListFragment();
                            break;
                        case 1:
                            startTestFragment();
                            break;
                        case 2:
                            if (userBean != null) {
                                startHeartFileFragment();
                            } else {
                                startLogin();
                            }
                            break;
                        case 3:
                            if (userBean != null) {
                                startMedicalRecordsFragment();
                            } else {
                                startLogin();
                            }

                            break;
                        case 4:

                            // startHealthDataFragment();
                            ToastUtils.showToast(getActivity(), "数据收集中");
                            break;
                        case 5:
                            startHouseKeepingFragment();
                            break;
                    }


                } else {

                    startLogin();
                }
            }
        });

        List<HomeButtonBean> homeButtonList1 = new ArrayList();
        for (int i = 0; i < 6; i++) {
            homeButtonList1.add(new HomeButtonBean());
        }

        //名医直播
        liveBeanList = new ArrayList<>();
        LinearLayoutManager liveLinearLayoutManager = new LinearLayoutManager(context);
        liveLinearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        liveAdapter = new LiveAdapter(context, liveBeanList);
        liveRecycleView.setLayoutManager(liveLinearLayoutManager);
        liveRecycleView.setAdapter(liveAdapter);
        liveAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LiveBean liveBean = liveAdapter.getItem(position);
                if (userBean != null) {
                    if (liveBean.getLive_state().equals("1")) {
                        Intent intent = new Intent(getActivity(), PlaybackActivity.class);
                        intent.putExtra("videoPath", liveBean.getLive_play_rtmp_url());
                        intent.putExtra("roomId", liveBean.getRoom_id());
                        intent.putExtra("stream_id", liveBean.getStream_id());
                        intent.putExtra("liveId", liveBean.getLive_id());
                        startActivity(intent);

                    } else {
                        Intent intent = new Intent(getActivity(), LivePlaybackActivity.class);
                        intent.putExtra("videoPath", liveBean.getLive_playback_url());
                        startActivity(intent);
                    }
                } else {
                    startLogin();
                }
            }
        });


        //热门商家
        LinearLayoutManager nearbyLinearLayoutManager = new LinearLayoutManager(context);
        nearbyLinearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        nearbyRecommendAdapter = new NearbyRecommendAdapter(context);
        nearbyRecommendRecycleView.setLayoutManager(nearbyLinearLayoutManager);
        nearbyRecommendRecycleView.setAdapter(nearbyRecommendAdapter);
        nearbyRecommendAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if (merchantHotlist != null) {

                    startMerchantsDetailFragment(merchantHotlist.get(position).getMerchants_id() + "");

                }
            }
        });

        doctorAdapter = new DoctorAdapter(context, doctorDetailBeanList);
        doctorRecommendRecycleView.setLayoutManager(new LinearLayoutManager(context));
        doctorRecommendRecycleView.setAdapter(doctorAdapter);
        doctorAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                DoctorDetailBean doctorDetailBean = doctorAdapter.getItem(position);

                if (userBean != null) {
                    startDoctorDetailFragment(doctorDetailBean.getDoctor_id());
                } else {
                    startLogin();
                }

            }
        });


        healthAdapter = new HealthAdapter(context);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(context));
        healthRecycleView.setAdapter(healthAdapter);

        healthAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if (listdata != null) {
                    if (listdata.get(position).getPostBean() != null && listdata.get(position).getPostBean().getForward_num() != null) {

                        startPostDetailsFragment(listdata.get(position).getPostBean().getPost_id() + "");

                    } else {
                        //  startPlateDetailFragment(listdata.get(position).getPost_id()+"");
                        try {
                            startPostDetailsFragment(listdata.get(position).getPost_id() + "");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });

        nearbyAdapter = new NearbyAdapter(context);
        nearbyRecycleView.setLayoutManager(new LinearLayoutManager(context));
        nearbyRecycleView.setAdapter(nearbyAdapter);
        nearbyAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


                startMerchantsMapFragment(list.get(position));

                //startActivity(new Intent(getActivity(), SingleRouteCalculateActivity.class));
            }
        });


        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                //整体刷新数据
                getPresenter().getmessagelist(map);
                getPresenter().getBanner(map);
                getPresenter().getHomeButton(map);
                doctorListParamsInfo();
                getPresenter().getHomeImgs();//三张图片
                LiveparamsInfo();//名医直播参数请求
            }
        });

        listBanner = new ArrayList<>();



        /*sw_message.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(getContext());
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelOffset(R.dimen.y26));
                tv.setTextColor(getContext().getResources().getColor(R.color.colorTextG6));
                tv.setLines(1);
                tv.setLayoutParams(lp);
                tv.setGravity(Gravity.CENTER_VERTICAL);
                tv.setEllipsize(TextUtils.TruncateAt.END);
                return tv;
            }
        });
        sw_message.setInAnimation(getContext(), R.anim.in_animation);
        sw_message.setOutAnimation(getContext(), R.anim.out_animation);*/

        query = new PoiSearch.Query("健康|养生|药店", "", city);
//keyWord表示搜索字符串，
//第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
//cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(5);// 设置每页最多返回多少条poiitem
        poiSearch = new PoiSearch(getActivity(), query);
        poiSearch.setOnPoiSearchListener(this);


    }


    //名医直播参数
    private void LiveparamsInfo() {

        Map<String, String> LiveMap = new HashMap<>();
//        LiveMap.put("member_id", userBean.getMember_id());
//        LiveMap.put("member_token", userBean.getMember_token());
        LiveMap.put("page", String.valueOf(1));
        getPresenter().getLiveList(LiveMap);


    }

    //医生列表参数
    private void doctorListParamsInfo() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        Map<String, String> mMap = new HashMap<>();
        if (userBean != null) {
            mMap.put("member_id", userBean.getMember_id());
        }
        mMap.put("is_recommend", "1");
        getPresenter().getDoctorList(mMap);
    }


    @Override
    public void initData() {
        getPresenter().getmessagelist(map);
        getPresenter().getBanner(map);
        getPresenter().getHomeButton(map);
        doctorListParamsInfo();
        getPresenter().getHomeImgs();//三张图片
        LiveparamsInfo();//名医直播参数请求
        getPresenter().getHomeHealth(map);//获取养生知识
        getPresenter().getMerchantHot(map);
        //
        TelephonyManager tm=(TelephonyManager)getActivity().getSystemService(TELEPHONY_SERVICE);
        Map<String,String> deviceMap=new HashMap<>();
        deviceMap.put("member_id",userBean.getMember_id());
        deviceMap.put("member_token",userBean.getMember_token());
        deviceMap.put("device_tokens","device_tokens");
        //getPresenter().getDevice_tokens(deviceMap);
        ////////////
        //申请权限
        List<String> permissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 23) {
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {


                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
            } else {
                startLocation();
            }


        } else {
            startLocation();
        }

//        sw_message.setText("广告消息：");
//        sw_message.setCurrentText("药品知识");


        home_information.add("广告消息1");
        home_information.add("广告消息2");
        home_information.add("广告消息3");
        home_information.add("广告消息4");
        home_information.add("广告消息5");

    }

    //banner点击返回处理
    private void clickBannerItem(BannerBean banner) {
        //1不跳转；2web链接;3个人中心；4商品
        if (banner != null) {
            BannerBean data = banner;
            //chain:外链 goods:商品 merchants:商家 doctor:医生 house:家政
            if (data.getBanner_type().equals("chain")) {
                startWeb("爱医美康", banner.getBanner_url(), "2");
            } else if (data.getBanner_type().equals("goods")) {
                Intent intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra(Constants.KEY_FRAGMENT, Constants.GOOD_DETAIL_FRAGMENT);
                intent.putExtra("goods_id", data.getGoods_id());
                startActivity(intent);
            } else if (data.getBanner_type().equals("merchants")) {
                Intent intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra(Constants.KEY_FRAGMENT, Constants.Merchants_Detail_Fragment);
                intent.putExtra("merchants_id", data.getMerchants_id());
                startActivity(intent);
            } else if (data.getBanner_type().equals("doctor")) {
                Intent intent = getFragmentIntent(Constants.DOCTOR_DETAIL);
                intent.putExtra(Constants.DOCTOR_ID, data.getDoctor_id());
                startActivity(intent);
            } else if (data.getBanner_type().equals("house")) {
                startHouseKeepingDetailFragment(data.getHouse_service_id());
            }

//            if (banner.getSort().equals("2")) {
//
//                startWeb(banner.getBanner_title(), banner.getBanner_url(), "2");
//            } else {
//
//                // ToastUtils.showToast(getActivity(), "状态值不确定");
//            }

        }
    }

    /**
     * 首页轮播图
     *
     * @param data
     */
    @Override
    public void onGetBanner(List<BannerBean> data) {
        if (convenientBanner != null) {
            toSetList(listBanner, data, false);
            convenientBanner.setPages(new CBViewHolderCreator() {
                @Override
                public Holder<BannerBean> createHolder() {
                    return new ImageHolder();
                }
            }, listBanner)
                    .setPageIndicator(new int[]{R.drawable.icon_dot_nor, R.drawable.icon_dot_pre})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

            if (!convenientBanner.isTurning()) {
                convenientBanner.startTurning(4000);
            }
            convenientBanner.notifyDataSetChanged();
            convenientBanner.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    clickBannerItem(listBanner.get(position));
                }
            });
        }

    }

    @Override
    public void onmessageList(List<HomeMessageBean> data) {

        if (data != null) {
            mHandler.removeCallbacksAndMessages(null);
            if (data.size() > 0) {
                information_index = 0;
                mlist.clear();
                Message msg = mHandler.obtainMessage();
                msg.what = MESSAGE_DELAYED;
                msg.obj = information_index;
                mHandler.sendMessage(msg);
                for (int i = 0; i < data.size(); i++) {
                    mlist.add(data.get(i).getMessage_desc());
                    // mlist.add("临时添加测试数据");
                }
            }
        }
    }

    @Override
    public void onGetHomeButton(List<HomeButtonBean> data) {
//        homeButtonAdapter.clear();
//        homeButtonAdapter.addAll(data);
//        homeButtonAdapter.notifyDataSetChanged();


    }

    @Override
    public void onGetDoctorList(List<DoctorDetailBean> data) {

        doctorAdapter.clear();
        doctorAdapter.addAll(data);
        doctorAdapter.notifyDataSetChanged();


    }

    /**
     * 首页轮播消息
     *
     * @param data
     */

    @Override
    public void onMessageList(List<NewsBean> data) {

        if (data != null) {
            mHandler.removeCallbacksAndMessages(null);
            if (data.size() > 0) {
                information_index = 0;
                home_information.clear();
                Message msg = mHandler.obtainMessage();
                msg.what = MESSAGE_DELAYED;
                msg.obj = information_index;
                mHandler.sendMessage(msg);
                for (int i = 0; i < data.size(); i++) {
                    home_information.add(data.get(i).getTitle());
                }
            }
        }


    }


    /**
     * 首页三张图片
     *
     * @param data
     */
    @Override
    public void onHomeImgs(List<HomeImgsBean> data) {

        if (data != null && data.size() >= 3) {

            Glide.with(getContext()).load(Constants.BASE_URL + data.get(0).getHome_img())
                    .placeholder(R.drawable.home_pic_act1)
                    .error(R.drawable.home_pic_act1)
                    .into(homeOnePicture);

            Glide.with(getContext()).load(Constants.BASE_URL + data.get(1).getHome_img())
                    .placeholder(R.drawable.home_pic_act2)
                    .error(R.drawable.home_pic_act2)
                    .into(homeTwoPicture);

            Glide.with(getContext()).load(Constants.BASE_URL + data.get(2).getHome_img())
                    .placeholder(R.drawable.home_pic_act3)
                    .error(R.drawable.home_pic_act3)
                    .into(homeThreePicture);

        }


    }

    /**
     * 名医直播
     *
     * @param data
     */
    @Override
    public void onLiveList(List<LiveBean> data) {
        liveBeanList.clear();
        liveBeanList = data;
        liveAdapter.clear();
        liveAdapter.addAll(data);
        liveAdapter.notifyDataSetChanged();


    }

    /***
     * 获取首页养生知识
     * @param homeHealthDetail
     */
    @Override
    public void onHealthDetail(HomeHealthDetail homeHealthDetail) {

        if (homeHealthDetail.getPostBeans() != null && homeHealthDetail.getPostBeans().size() > 0) {
            healthTittle.setVisibility(View.VISIBLE);
            healthMore.setVisibility(View.VISIBLE);

        } else {
            healthTittle.setVisibility(View.GONE);
            healthMore.setVisibility(View.GONE);
        }
        mtitle = homeHealthDetail.getPlate_name();
        // healthTittle.setText(homeHealthDetail.getPlate_name());
        plateId = homeHealthDetail.getPlate_id() + "";
        listdata = homeHealthDetail.getPostBeans();
        healthAdapter.clear();
        healthAdapter.addAll(listdata);
        healthAdapter.notifyDataSetChanged();


    }

    /**
     * 获取热门商家的数据
     *
     * @param merchantHotBeanList
     */
    @Override
    public void onMerchantHot(List<MerchantHotBean> merchantHotBeanList) {

        if (merchantHotBeanList != null && merchantHotBeanList.size() > 0) {
            merchantHotlist = merchantHotBeanList;
            nearbyRecommendAdapter.clear();
            nearbyRecommendAdapter.addAll(merchantHotBeanList);
            nearbyRecommendAdapter.notifyDataSetChanged();
            nearbyRecommendRecycleView.setVisibility(View.VISIBLE);
            hostTittle.setVisibility(View.VISIBLE);
            hostMore.setVisibility(View.VISIBLE);
        } else {
            nearbyRecommendRecycleView.setVisibility(View.GONE);
            hostTittle.setVisibility(View.GONE);
            hostMore.setVisibility(View.GONE);
        }


    }

    /***
     * 高德地图
     * @param poiResult
     * @param i
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {

        if (poiResult != null) {
            list = poiResult.getPois();
            nearbyAdapter.addAll(list);
            nearbyAdapter.notifyDataSetChanged();
        }

        Log.e("gaode", ">>>" + poiResult.getPageCount());
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
        Log.e("gaode", ">>>" + poiItem.getAdName());

    }

    public class ImageHolder implements Holder<BannerBean> {
        private ImageView iv;

        @Override
        public View createView(Context context) {
            iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            return iv;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerBean data) {
            Glide.with(context).load(Constants.BASE_URL + data.getBanner_img()).error(R.drawable.banner_default).placeholder(R.drawable.banner_default).into(iv);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getApp());
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Constants.SEARCH_CITY:
                if (resultCode == RESULT_OK) {
                    city = data.getStringExtra(Constants.CITY);
                    mLocation.setText(city);
                    NoCity = false;
                }
                break;
        }
    }

    @OnClick({R.id.mLocation, R.id.msg, R.id.home_one_picture, R.id.home_two_picture
            , R.id.home_three_picture, R.id.live_more, R.id.nearby_more, R.id.tv_search,
            R.id.health_more, R.id.host_more})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLocation:
                startSearchCityFragment();
                break;
            case R.id.msg:

                userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
                if (userBean != null) {
                    startMsgFragment();

                } else {
                    startLogin();
                }


                break;
            case R.id.home_one_picture:
                break;


            case R.id.home_two_picture:
                break;


            case R.id.home_three_picture:
                break;

            case R.id.live_more:
                if (liveBeanList != null && liveBeanList.size() > 0) {
                    if (userBean != null) {
                        startFamousDoctorLiveListFragment();

                    } else {
                        startLogin();
                    }
                } else {
                    ToastUtils.showToast(getActivity(), "暂无更多数据");

                }

                break;

            case R.id.nearby_more:

                if (city != null && dlat != 0 && dlat != 0) {
                    startMoreNearbyShopListFragment(city, dlat, dlag);

                } else {
                    ToastUtils.showToast(getActivity(), "暂无数据");
                }
                break;

            case R.id.tv_search:
                startSearchHistoryFragment();
                break;
            case R.id.health_more:
                /*if (plateId!=null&&!plateId.equals("")){

                    if (mtitle!=null){
                        startCommunityListFragment(plateId,mtitle);

                    }else {
                        startCommunityListFragment(plateId,"健康");

                    }
                }else {

                    ToastUtils.showToast(context,"没有更是内容");
                }*/
                startCommunicateFragment("1");
                break;
            case R.id.host_more:
                if (MainActivity.newInstance != null) {
                    MainActivity.newInstance.showShopFragment("1");
                    return;
                }
                startshopfragment();
                break;


        }
    }


    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(context.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }


    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(20000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {

                    poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(
                            location.getLatitude(), location.getLongitude()), 7000));//设置周边搜索的中心点以及半径
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    sb.append("定位时间: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                //定位之后的回调时间
                sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");
                Log.i("dfc", "onLocationChanged: " + sb.toString());
                //解析定位结果，
                poiSearch.searchPOIAsyn();
                String result = sb.toString();
                city = location.getCity();
                mLocation.setText(location.getCity());
                lag = location.getLongitude() + "";
                lat = location.getLatitude() + "";
                dlat = location.getLatitude();
                dlag = location.getLongitude();
                Detail_address = location.getAddress();

                stopLocation();

            } else {
                mLocation.setText("定位失败，loc is null");
            }


        }
    };


    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        //根据控件的选择，重新设置定位参数

        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }


}
