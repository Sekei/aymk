package com.live.tv.mvp.fragment.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DepartmentLevelOneBean;
import com.live.tv.bean.DepartmentLevelTwoBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.ShaixuanBean;
import com.live.tv.mvp.adapter.home.DoctorListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.ShaixuanPopwindow;
import com.live.tv.mvp.presenter.home.DoctorListPresenter;
import com.live.tv.mvp.view.home.IDoctorListView;
import com.live.tv.util.LoadingUtil;
import com.live.tv.util.city.AddressPopwindow;
import com.live.tv.util.city.CityBean;
import com.live.tv.util.city.CityParseHelper;
import com.live.tv.util.city.DepartmentPopwindow;
import com.live.tv.util.city.FilterPopwindow;
import com.live.tv.util.city.OfficeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class DoctorListFragment extends BaseFragment<IDoctorListView, DoctorListPresenter> implements IDoctorListView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.easyRecycleView)
    EasyRecyclerView easyRecyclerView;

    @BindView(R.id.bg_one)
    View bg_one;

    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.choose)
    TextView choose;


    Unbinder unbinder;
    private DoctorListAdapter adapter;
    private List<DoctorDetailBean> list;
    private Map<String, String> map = new HashMap<>();
    private int departmentPopState = -1;
    private int addressPopState = -1;
    private int filterPopState = -1;
    private CityParseHelper cityParseHelper;
    //全部弹窗
    private PopupWindow addressPop;
    //附近弹框
    private PopupWindow pw2;
    //只看弹框
    private PopupWindow pw3;
    private String name;
    private int popupHeight;
    private AddressPopwindow addressWindow;
    private DepartmentPopwindow departmentPopwindow;
    private FilterPopwindow filterPopwindow;
    private ShaixuanPopwindow shaixuanPopwindow;
    private ArrayList<DepartmentLevelOneBean> listOne = new ArrayList<>();
    private ArrayList<OfficeBean> listTwo;
    private int  currentPage=1;
    private String job_level="";
    private String hospital_level="";
    private String department_level2="";
    private String doctor_province="";
    private String doctor_city="";
    private String doctor_country="";

    View loadMore;
    private int page = 1;
    private boolean isMore = true;
    public static DoctorListFragment newInstance() {
        Bundle args = new Bundle();
        DoctorListFragment fragment = new DoctorListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_doctor_list;
    }

    @Override
    public void initUI() {
        tvTitle.setText("选择医生");
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        list = new ArrayList<>();
        adapter = new DoctorListAdapter(context, list);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startDoctorDetailFragment(adapter.getItem(position).getDoctor_id()+"");
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

        map.clear();
        if (userBean!=null){
            map.put("member_id",userBean.getMember_id());
        }
        map.put("job_level",job_level);
        map.put("hospital_level",hospital_level);
        map.put("doctor_province",doctor_province);
        map.put("doctor_city",doctor_city);
        map.put("doctor_country",doctor_country);
        map.put("page", String.valueOf(page));
        getPresenter().getDoctorList(map);


    }

    @Override
    public void initData() {
        cityParseHelper = new CityParseHelper();
        cityParseHelper.initData(getContext());
        map.clear();
        getPresenter().getDepartments(map);
        getPresenter().getSelectBeans(map);

        if (userBean!=null){
            map.put("member_id",userBean.getMember_id());
        }

        getPresenter().getDoctorList(map);
        LoadingUtil.showLoading(getActivity(), "数据加载中...");


//        listTwo = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            OfficeBean officeBean = new OfficeBean();
//            ArrayList<HospitalLevelBean> listthree = new ArrayList<>();
//            for (int j = 0; j < 5; j++) {
//                HospitalLevelBean hospitalLevelBean = new HospitalLevelBean();
//                if (j == 1) {
//                    hospitalLevelBean.setHospitalLevel("副主任医师");
//                } else if (j == 2) {
//                    hospitalLevelBean.setHospitalLevel("公立三甲");
//                } else {
//                    hospitalLevelBean.setHospitalLevel("公立三甲");
//                }
//                listthree.add(hospitalLevelBean);
//            }
//            if (i == 0) {
//                officeBean.setOfficeName("职位级别");
//            } else {
//                officeBean.setOfficeName("医院级别");
//            }
//            officeBean.setList(listthree);
//            listTwo.add(officeBean);
//        }

    }


    @Override
    public void onGetDoctorList(List<DoctorDetailBean> data) {

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
        LoadingUtil.hideLoading();
    }

    @Override
    public void onGetDepartments(List<DepartmentLevelOneBean> data) {
        listOne.clear();
        listOne.addAll(data);
    }

    ShaixuanBean  shaixuanBean;
    @Override
    public void onGetSelectBeans(ShaixuanBean data) {
        shaixuanBean=data;
    }



    @Override
    public DoctorListPresenter createPresenter() {
        return new DoctorListPresenter(getApp());
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        LoadingUtil.hideLoading();
        //ToastUtils.showToast(context.getApplicationContext(),e.getMessage());
    }

    //位置
    private void showAddressPop() {
        if (addressWindow == null) {
            addressWindow = new AddressPopwindow(context).setOutsideTouchable(false);
        }
        if (!addressWindow.isShowing()) {
            addressWindow.setData(cityParseHelper.getProvinceBeanArrayList()).cerate().setOnItemClickListener(new AddressPopwindow.onItemClickListener() {
                @Override
                public void onclick(CityBean data) {
                    if (data != null) {


                        name = data.getName();
                        address.setText(name);
                        address.setTextColor(getResources().getColor(R.color.colorTextG6));
                        address.setCompoundDrawables(null, null, setDrawableDown(), null);
                        doctor_city=name;
                        department_level2="";
                        job_level="";
                        hospital_level="";

                        map.clear();
                        map.put("doctor_city", name);
                        getPresenter().getDoctorList(map);
                        LoadingUtil.showLoading(getActivity(), "数据加载中...");
                    }
                    addressWindow.dismiss();
                }
            }).show(bg_one);
        }
    }

    //科室
    private void showDepartmentPop() {
        if (departmentPopwindow == null) {
            departmentPopwindow = new DepartmentPopwindow(context).setOutsideTouchable(false);
        }
        if (!departmentPopwindow.isShowing()) {
            departmentPopwindow.setData(listOne).cerate().setOnItemClickListener(new DepartmentPopwindow.onItemClickListener() {
                @Override
                public void onclick(DepartmentLevelTwoBean data) {
                    if (data != null) {
                        department.setText(data.getDepartment_name());
                        department.setTextColor(getResources().getColor(R.color.colorTextG6));
                        department.setCompoundDrawables(null, null, setDrawableDown(), null);
                        currentPage=1;
                        department_level2=data.getDepartment_name();
                        doctor_city="";
                        job_level="";
                        hospital_level="";

                        map.clear();
                        map.put("page", String.valueOf(currentPage));
                        map.put("department_level2", data.getDepartment_name());
                        getPresenter().getDoctorList(map);
                        LoadingUtil.showLoading(getActivity(), "数据加载中...");
                    }
                    departmentPopwindow.dismiss();
                }
            }).show(address);
        }
    }


//    //筛选
//    private void showFiltertPop() {
//        if (filterPopwindow == null) {
//            filterPopwindow = new FilterPopwindow(context).setOutsideTouchable(false);
//        }
//        if (!filterPopwindow.isShowing()) {
//            filterPopwindow.setData(sha).cerate().setOnItemClickListener(new FilterPopwindow.onItemClickListener() {
//                @Override
//                public void onclick(HospitalLevelBean data) {
//                    if (data != null) {
//                        choose.setText(data.getHospitalLevel());
//                        choose.setTextColor(getResources().getColor(R.color.colorTextG6));
//                        choose.setCompoundDrawables(null, null, setDrawableDown(), null);
//                        map.put("doctor_city", name);
//                        getPresenter().getDoctorList(map);
//                        LoadingUtil.showLoading(getActivity(), "数据加载中...");
//                    }
//                    filterPopwindow.dismiss();
//                }
//            }).show(bg_one);
//        }
//    }


    //新的筛选
    private void showShaixuanPop() {
        if (shaixuanPopwindow == null) {
            shaixuanPopwindow = new ShaixuanPopwindow(context).setOutsideTouchable(false);
        }

        if (!shaixuanPopwindow.isShowing()) {
            shaixuanPopwindow.setData(shaixuanBean).cerate().setOnItemClickListener(new ShaixuanPopwindow.onItemClickListener() {
                @Override
                public void onclick(ShaixuanBean data) {
                    if (data != null) {

                    }
                    shaixuanPopwindow.dismiss();
                }

                @Override
                public void onShaixuanStr(String doc_str, String hos_str) {

                    job_level=doc_str;
                    hospital_level=hos_str;
                    doctor_city="";
                    department_level2="";
                    page=1;
                    map.clear();
                    map.put("job_level",doc_str);
                    map.put("hospital_level",hos_str);
                    map.put("page", String.valueOf(page));
                    if (userBean!=null){
                        map.put("member_id",userBean.getMember_id());
                    }
                    getPresenter().getDoctorList(map);
                    shaixuanPopwindow.dismiss();
                }
            }).show(bg_one);
        }

    }




    @OnClick({R.id.ivLeft, R.id.address, R.id.department, R.id.choose})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.address://切换地址
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
            case R.id.department://科室
                setFilterG6();
                setAddressG6();
                if (departmentPopState == -1) {
                    departmentPopState = 0;
                    showDepartmentPop();
                    department.setTextColor(getResources().getColor(R.color.colorPrimary));
                    department.setCompoundDrawables(null, null, setDrawableUp(), null);
                } else {
                    setDepartmentG6();
                }
                break;
            case R.id.choose://筛选
                setAddressG6();
                setDepartmentG6();
                if (filterPopState == -1) {
                    filterPopState = 0;
                    //showFiltertPop();
                    showShaixuanPop();
                    choose.setTextColor(getResources().getColor(R.color.colorPrimary));
                    choose.setCompoundDrawables(null, null, setDrawableUp(), null);
                } else {
                    setFilterG6();
                }

                break;
        }
    }



    private Drawable setDrawableUp() {
        Drawable drawable = getResources().getDrawable(R.drawable.doctor_choose_up);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        return drawable;
    }

    private Drawable setDrawableDown() {
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.book_down);
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

//    private void setFilterG6() {
//        if (filterPopwindow != null) {
//            filterPopwindow.dismiss();
//            filterPopState = -1;
//            choose.setTextColor(getResources().getColor(R.color.colorTextG6));
//            choose.setCompoundDrawables(null, null, setDrawableDown(), null);
//        }
//    }

    private void setFilterG6() {
        if (shaixuanPopwindow != null) {
            shaixuanPopwindow.dismiss();
            filterPopState = -1;
            choose.setTextColor(getResources().getColor(R.color.colorTextG6));
            choose.setCompoundDrawables(null, null, setDrawableDown(), null);
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

}
