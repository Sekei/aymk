package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HousekeepBean;
import com.live.tv.mvp.adapter.home.ServiceAddressMoreAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.MerchantMoreAddressPresenter;
import com.live.tv.mvp.view.home.IMerchantMoreAddressVIew;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/8/30.
 * 商家更多地址
 */

public class MerchantMoreAddressFragment extends BaseFragment<IMerchantMoreAddressVIew, MerchantMoreAddressPresenter> implements IMerchantMoreAddressVIew {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.show_heard)
    ImageView showHeard;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String mServiceID;
    private ServiceAddressMoreAdapter addressMoreAdapter;
    private boolean isShowDelete;
    private List<HousekeepBean.HouseAddressBeansBean> list = new ArrayList<>();
    private List<HousekeepBean.HouseAddressBeansBean> newlist = new ArrayList<>();
    private String servceid;
    private boolean isdelete;
    Unbinder unbinder;


    public static MerchantMoreAddressFragment newInstence(String serviceId) {

        MerchantMoreAddressFragment fragment = new MerchantMoreAddressFragment();

        fragment.mServiceID = serviceId;

        return fragment;


    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_moreaddress;
    }

    @Override
    public void initUI() {

        tvTitle.setText("更多地址");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("编辑");
        addressMoreAdapter = new ServiceAddressMoreAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(addressMoreAdapter);
    }


    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("house_service_id", mServiceID);
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getHousekeepDetail(map);

    }

    @Override
    public MerchantMoreAddressPresenter createPresenter() {
        return new MerchantMoreAddressPresenter(getApp());
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


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.add_address})

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:

                finish();
                break;
            case R.id.tvRight:

                if (!isShowDelete) {
                    isShowDelete = true;
                    tvRight.setText("删除");
                    addressMoreAdapter.setmList(list);
                    addressMoreAdapter.setIsshowCheckBox(true);
                    addressMoreAdapter.notifyDataSetChanged();

                } else {
                    isShowDelete = false;
                    if (addressMoreAdapter.getmList() != null&&addressMoreAdapter.getmList().size()>0) {
                        newlist = addressMoreAdapter.getmList();
                        if (newlist != null && newlist.size() > 0) {
                            StringBuffer stringBuffer = new StringBuffer();

                            for (int i = 0; i < newlist.size(); i++) {
                                if (newlist.get(i).getIs_delete().equals("1")) {
                                    if (i == 0) {
                                        stringBuffer.append(newlist.get(i).getAddress_id());

                                    } else {
                                        stringBuffer.append("," + newlist.get(i).getAddress_id());

                                    }
                                }
                            }
                            servceid = stringBuffer.toString();

                            if (servceid != null && !servceid.equals("")) {
                                map.clear();
                                map.put("member_id", userBean.getMember_id());
                                map.put("member_token", userBean.getMember_token());
                                map.put("address_id", servceid);
                                getPresenter().getdeleteHouseAddress(map);
                            } else {
                                tvRight.setText("编辑");
                                addressMoreAdapter.setmList(list);
                                addressMoreAdapter.setIsshowCheckBox(false);
                                addressMoreAdapter.notifyDataSetChanged();
                            }


                        }

                    } else {
                        tvRight.setText("编辑");
                        addressMoreAdapter.setmList(list);
                        addressMoreAdapter.setIsshowCheckBox(false);
                        addressMoreAdapter.notifyDataSetChanged();
                    }


                }


                break;
            case R.id.add_address:

                ServiceAddAddressFragment(mServiceID);

                break;


        }
    }

    @Override
    public void ondata(HousekeepBean housekeepBean) {
        /***
         * fan回的所有数据
         */
        addressMoreAdapter.clear();
        list.clear();
        tvName.setText(housekeepBean.getHouse_service_name());
        tvContent.setText("主营业务: " + housekeepBean.getService_range());
        if (housekeepBean.getHouseAddressBeans() != null && housekeepBean.getHouseAddressBeans().size() >= 0) {
            list = housekeepBean.getHouseAddressBeans();
            addressMoreAdapter.addAll(housekeepBean.getHouseAddressBeans());
            if (isdelete) {
                isdelete = false;
                tvRight.setText("编辑");
                addressMoreAdapter.setmList(list);
                addressMoreAdapter.setIsshowCheckBox(false);
                addressMoreAdapter.notifyDataSetChanged();
            } else {
                addressMoreAdapter.notifyDataSetChanged();

            }
        }

    }

    @Override
    public void onDeleteAddress(String str) {
        map.clear();
        map.put("house_service_id", mServiceID);
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getHousekeepDetail(map);

        isdelete = true;

    }


}
