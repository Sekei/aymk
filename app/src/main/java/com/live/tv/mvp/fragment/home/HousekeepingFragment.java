package com.live.tv.mvp.fragment.home;

import android.graphics.drawable.Drawable;
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
import com.live.tv.bean.HousekeepBean;
import com.live.tv.bean.TypeServiceBean;
import com.live.tv.mvp.adapter.home.HousekeepingListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.ShaixuanPopwindow;
import com.live.tv.mvp.presenter.home.HousekeepingPresenter;
import com.live.tv.mvp.view.home.IHouseKeepingView;
import com.live.tv.util.LoadingUtil;
import com.live.tv.util.city.AddressPopwindow;
import com.live.tv.util.city.CityBean;
import com.live.tv.util.city.CityParseHelper;
import com.live.tv.util.city.DepartmentPopwindow;
import com.live.tv.util.city.DistrictBean;
import com.live.tv.util.city.DoctorAddressPopwindow;
import com.live.tv.util.city.FilterPopwindow;
import com.live.tv.util.city.HotorNewPopwindow;
import com.live.tv.util.city.NewOrHotAdapter;
import com.live.tv.util.city.TypeofServicePopwindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 家政服务
 * Created by sh-lx on 2017/7/12.
 */

public class HousekeepingFragment extends BaseFragment<IHouseKeepingView, HousekeepingPresenter> implements IHouseKeepingView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.bg_one)
    View bg_one;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.choose)
    TextView choose;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecyclerView;
    Unbinder unbinder;
    private HousekeepingListAdapter adapter;
    private List<HousekeepBean> list;
    private DoctorAddressPopwindow addressWindow;
    private int departmentPopState = -1;
    private int addressPopState = -1;
    private int filterPopState = -1;
    private TypeofServicePopwindow departmentPopwindow;
    private HotorNewPopwindow hotPopwindow;
    View loadMore;
    private int page = 1;
    private boolean isMore = true;
    private String province = "";//省
    private String city = "";//市
    private String district = "";//区
    private String service_type = "";//服务类型
    private String is_latest = "desc";
    private CityParseHelper cityParseHelper;
    private String name = "";
    private String ishot = "";
    private List<TypeServiceBean> typelist = new ArrayList<>();
    private boolean ischangetag;

    public static HousekeepingFragment newInstance() {

        Bundle args = new Bundle();

        HousekeepingFragment fragment = new HousekeepingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_housekeeping;
    }

    @Override
    public void initUI() {
        tvTitle.setText("家政服务");
        tvRight.setText("发布");
        tvRight.setVisibility(View.GONE);

        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        list = new ArrayList<>();
        adapter = new HousekeepingListAdapter(context, list);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                HousekeepBean housekeepBean = adapter.getItem(position);
              //  String serviceid=housekeepBean.getHouse_service_id()+"";
                startHouseKeepingDetailFragment(housekeepBean.getHouse_service_id()+"");
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

    @Override
    public void initData() {
        getPresenter().getTypeService(map);
        RequestParameters();
        cityParseHelper = new CityParseHelper();
        cityParseHelper.initData(getContext());
    }


    private void RequestParameters() {

        map.clear();
        if (province!=null&&!province.equals("")) {
            map.put("service_province", province);

        }
        if (city!=null&&!city.equals("")) {
            map.put("service_city", city);

        }
        if (district!=null&&!district.equals("")) {
            map.put("service_country", district);

        }
        if (service_type!=null&&!service_type.equals("")) {
            map.put("service_type", service_type);

        }
        if (is_latest!=null&&!is_latest.equals("")) {
            map.put("is_latest", is_latest);

        }
        if (ishot!=null&&!ishot.equals("")) {
            map.put("count", ishot);

        }
        map.put("page", String.valueOf(page));
        getPresenter().getHouseKeeps(map);


    }//{R.id.ivLeft, R.id.address, R.id.department, R.id.choose}

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.address, R.id.department, R.id.choose})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.tvRight:

                if (userBean != null) {
                    //发布家政信息

                    startReleaseHouseKeepFragment();

                } else {
                    startLogin();
                }
                break;
            case R.id.address:
                setDepartmentG6();
                setFilterG6();
                if (addressPopState == -1) {
                    addressPopState = 0;
                    showAddressPop();
                    address.setTextColor(getResources().getColor(R.color.colorPrimary));
                    address.setCompoundDrawables(null, null, setDrawableUp(), null);
                } else {
                    setAddressG6();

                }

                break;
            case R.id.department:

                setFilterG6();
                setAddressG6();
                if (departmentPopState == -1) {
                    departmentPopState = 0;
                    typeservicePop();
                    department.setTextColor(getResources().getColor(R.color.colorPrimary));
                    department.setCompoundDrawables(null, null, setDrawableUp(), null);
                } else {
                    setDepartmentG6();
                }
                break;
            case R.id.choose:
                setAddressG6();
                setDepartmentG6();
                if (filterPopState == -1) {
                    filterPopState = 0;
                    showhotPop();
                    choose.setTextColor(getResources().getColor(R.color.colorPrimary));
                    choose.setCompoundDrawables(null, null, setDrawableUp(), null);
                } else {
                    setFilterG6();
                }
                break;

        }
    }

    /**
     * 最新最热
     */
    private void showhotPop() {
        if (hotPopwindow == null) {
            hotPopwindow = new HotorNewPopwindow(context).setOutsideTouchable(false);
        }
        if (!hotPopwindow.isShowing()) {
            hotPopwindow.cerate().setOnItemClickListener(new HotorNewPopwindow.onItemClickListener() {
                @Override
                public void onclick(String data) {
                    if (data != null) {
                        if (name.equals("最新")) {
                            ishot = "";
                            is_latest = "desc";
                        } else if (name.equals("最热")) {
                            ishot = "1";
                            is_latest = "";
                        }
                        name = data;
                        choose.setText(name);
                        choose.setTextColor(getResources().getColor(R.color.colorTextG6));
                        choose.setCompoundDrawables(null, null, setDrawableDown(), null);
                        RequestParameters();
                        LoadingUtil.showLoading(getActivity(), "数据加载中...");
                        ischangetag = true;
                    }
                    hotPopwindow.dismiss();
                }
            }).show(bg_one);
        }
    }

    private void setDepartmentG6() {
        if (departmentPopwindow != null) {
            departmentPopwindow.dismiss();
            departmentPopState = -1;
            department.setTextColor(getResources().getColor(R.color.colorTextG6));
            department.setCompoundDrawables(null, null, setDrawableDown(), null);
        }
    }

    private void setFilterG6() {
        if (hotPopwindow != null) {
            hotPopwindow.dismiss();
            filterPopState = -1;
            choose.setTextColor(getResources().getColor(R.color.colorTextG6));
            choose.setCompoundDrawables(null, null, setDrawableDown(), null);
        }
    }

    @Override
    public HousekeepingPresenter createPresenter() {
        return new HousekeepingPresenter(getApp());
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
    public void onHouseKeeps(List<HousekeepBean> data) {
        LoadingUtil.hideLoading();
        if (data.size() == 10) {
            isMore = true;
        } else {
            isMore = false;
            loadMore.setVisibility(View.GONE);
        }
        if (page == 1) {
            adapter.clear();
        }
        if (ischangetag) {
            adapter.clear();
            ischangetag = false;
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void ontypeservice(List<TypeServiceBean> data) {
        typelist = data;
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


    //位置
    private void showAddressPop() {
        if (addressWindow == null) {
            addressWindow = new DoctorAddressPopwindow(context).setOutsideTouchable(false);
        }
        if (!addressWindow.isShowing()) {
            addressWindow.setData(cityParseHelper.getProvinceBeanArrayList()).cerate().setOnItemClickListener(new DoctorAddressPopwindow.onItemClickListener() {
                @Override
                public void onclick(String sheng, String shi, String qu) {

                    province = sheng;
                    city = shi;
                    district = qu;
                    name = district;
                    address.setText(name);
                    address.setTextColor(getResources().getColor(R.color.colorTextG6));
                    address.setCompoundDrawables(null, null, setDrawableDown(), null);
                    RequestParameters();
                    ischangetag = true;

                    LoadingUtil.showLoading(getActivity(), "数据加载中...");
                    addressWindow.dismiss();
                }
            }).show(bg_one);
        }
    }

    //服务类型
    private void typeservicePop() {
        if (departmentPopwindow == null) {
            departmentPopwindow = new TypeofServicePopwindow(context).setOutsideTouchable(false);
        }
        if (!departmentPopwindow.isShowing()) {
            departmentPopwindow.setData(typelist).cerate().setOnItemClickListener(new TypeofServicePopwindow.onItemClickListener() {
                @Override
                public void onclick(TypeServiceBean data) {

                    if (data != null) {
                        service_type = data.getService_type_id() + "";
                        name = data.getService_type();
                        department.setText(name);
                        department.setTextColor(getResources().getColor(R.color.colorTextG6));
                        department.setCompoundDrawables(null, null, setDrawableDown(), null);
                        RequestParameters();
                        ischangetag = true;

                        //getPresenter().getDoctorList(map);
                        LoadingUtil.showLoading(getActivity(), "数据加载中...");
                    }

                    departmentPopwindow.dismiss();
                }
            }).show(bg_one);
        }
    }

    private Drawable setDrawableDown() {
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.book_down);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        return drawable;
    }

    private Drawable setDrawableUp() {
        Drawable drawable = getResources().getDrawable(R.drawable.doctor_choose_up);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        return drawable;
    }


    private void setAddressG6() {
        if (addressWindow != null) {
            addressWindow.dismiss();
            addressPopState = -1;
            address.setTextColor(getResources().getColor(R.color.colorTextG6));
            address.setCompoundDrawables(null, null, setDrawableDown(), null);

        }
    }

}
