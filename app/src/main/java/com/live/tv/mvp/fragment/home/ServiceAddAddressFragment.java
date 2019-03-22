package com.live.tv.mvp.fragment.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.AddAddressPresenter;
import com.live.tv.mvp.view.home.IAddAddressView;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/8/30.
 * 家政详情添加地址
 */

public class ServiceAddAddressFragment extends BaseFragment<IAddAddressView, AddAddressPresenter> implements IAddAddressView {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.showaddress)
    TextView showaddress;
    @BindView(R.id.editaddress)
    EditText editaddress;
    Unbinder unbinder;
    private String serviceId;
    private String province = "";
    private String city = "";
    private String country = "";
    private Map<String, String> map = new HashMap<>();

    public static   ServiceAddAddressFragment newInstence(String serviceId) {

        ServiceAddAddressFragment fragment = new ServiceAddAddressFragment();

        fragment.serviceId = serviceId;

        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_service_add_address;
    }

    @Override
    public void initUI() {
        tvTitle.setText("添加地址");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("添加");
    }

    @Override
    public void initData() {

    }

    @Override
    public AddAddressPresenter createPresenter() {
        return new AddAddressPresenter(getApp());
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


    @OnClick({R.id.ivLeft, R.id.tv1, R.id.tvRight})

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv1:

                cityPicker();
                break;
            case R.id.tvRight:
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("house_province", province);
                map.put("house_city", city);
                map.put("house_country", country);
                map.put("house_address", editaddress.getText().toString());
                map.put("house_service_id", serviceId);
                getPresenter().getaddaddress(map);

                break;
        }
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
                showaddress.setText(province + " " + city + " " + country);


            }

            @Override
            public void onCancel() {


            }
        });
    }

    @Override
    public void onAddAddress(String str) {
        ToastUtils.showToast(getActivity(), str);
        finish();
    }
}
