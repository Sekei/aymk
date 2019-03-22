package com.live.tv.mvp.fragment.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AddressBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.AddAddressPresenter;
import com.live.tv.mvp.view.mine.IAddAddressView;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class AddAddressFragment extends BaseFragment<IAddAddressView,AddAddressPresenter>implements IAddAddressView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_choose_city)
    TextView edChooseCity;
    @BindView(R.id.ed_detail_address)
    EditText edDetailAddress;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;
    private String type = "";//add  update   添加 修改
    private AddressBean addressBean;


    public static AddAddressFragment newInstance(String type, AddressBean addressBean) {
        Bundle args = new Bundle();
        AddAddressFragment fragment = new AddAddressFragment();
        fragment.type = type;
        fragment.addressBean = addressBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_address;
    }

    @Override
    public void initUI() {

        if ("update".equals(type)) {
            tvTitle.setText("修改地址");
            ok.setText("确认修改");
        } else {
            tvTitle.setText("添加地址");
            ok.setText("确认添加");
        }

    }

    @Override
    public void initData() {

        if ("update".equals(type)) {
            edName.setText(addressBean.getAddress_name());
            edPhone.setText(addressBean.getAddress_mobile());
            edChooseCity.setText(addressBean.getAddress_province()+addressBean.getAddress_city()+addressBean.getAddress_country());
            edDetailAddress.setText(addressBean.getAddress_detailed());
            province=addressBean.getAddress_province();
            city=addressBean.getAddress_city();
            country=addressBean.getAddress_country();

        } else {

        }

    }

    public  String province = "";
    public String city = "";
    public  String country = "";


    @OnClick({R.id.ivLeft, R.id.ok,R.id.ed_choose_city})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:


                if (checkInputKey()) {

                    map = new HashMap<>();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("address_mobile", edPhone.getText().toString().trim());
                    map.put("address_name", edName.getText().toString().trim());
                    map.put("address_province", province);
                    map.put("address_city", city);
                    map.put("address_country", country);
                    map.put("address_detailed", edDetailAddress.getText().toString().trim());
                  if ("update".equals(type)){
                        map.put("address_id",addressBean.getAddress_id());
                        getPresenter().getUpdateAddress(map);
                    }else {

                          getPresenter().getAddAddress(map);

                  }


                }





                break;

            case R.id.ed_choose_city:
                cityPicker();
                break;
        }
    }



    private boolean checkInputKey() {

        if (StringUtils.isBlank(edName.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入收货人姓名");
            return false;
        }
        if (StringUtils.isBlank(edPhone.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入联系方式");
            return false;
        }

        if (StringUtils.isBlank(edChooseCity.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), "请选择地区");
            return false;
        }
        if (StringUtils.isBlank(edDetailAddress.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入详细地址");
            return false;
        }


        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public AddAddressPresenter createPresenter() {
        return new AddAddressPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void UpdateAddress(String data) {

        ToastUtils.showToast(context.getApplicationContext(),"修改成功");
        Intent intent = new Intent();
        getActivity().setResult(1000,intent);
        finish();
    }

    @Override
    public void AddAddress(String data) {
        ToastUtils.showToast(context.getApplicationContext(),"添加成功");
        Intent intent = new Intent();
        getActivity().setResult(1000,intent);
        finish();
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


    /**
     * 显示城市列表
     */
    private void cityPicker() {
        CityPicker cityPicker = new CityPicker.Builder(context)
                .textSize(16)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#ffffff")
                .titleTextColor("#666666")
                .confirTextColor("#6EB92B")
                .cancelTextColor("#999999")
                .province("上海")
                .city("上海市")
                .district("虹口区")
                .textColor(Color.parseColor("#6EB92B"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(10)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                province = citySelected[0];
                //城市
                city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                country = citySelected[2];

                //邮编
                String code = citySelected[3];
                edChooseCity.setText(province+" "+city+" "+country);


            }

            @Override
            public void onCancel() {


            }
        });
    }





}
