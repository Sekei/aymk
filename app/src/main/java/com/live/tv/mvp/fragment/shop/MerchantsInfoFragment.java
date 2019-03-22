package com.live.tv.mvp.fragment.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.MerchantsBean;
import com.live.tv.mvp.base.SimpleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.youme.voiceengine.mgr.YouMeManager.mContext;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class MerchantsInfoFragment extends SimpleFragment {

    @BindView(R.id.img_merchants)
    ImageView imgMerchants;
    @BindView(R.id.tv_merchants_name)
    TextView tvMerchantsName;
    @BindView(R.id.tv_merchants_desc)
    TextView tvMerchantsDesc;
    @BindView(R.id.tv_merchants_sales)
    TextView tvMerchantsSales;
    @BindView(R.id.tv_range)
    TextView tvRange;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_synopsis)
    TextView tvSynopsis;
    Unbinder unbinder;
    private String merchants_id = "";

    public static MerchantsInfoFragment newInstance(String merchants_id) {

        Bundle args = new Bundle();
        MerchantsInfoFragment fragment = new MerchantsInfoFragment();
        fragment.merchants_id = merchants_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_merchants_info;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

    }


    public void SetMerchantsInfo(MerchantsBean merchantsBean) {
        Glide.with(mContext).
                load(Constants.BASE_URL + merchantsBean.getMerchants_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .into(imgMerchants);

        tvMerchantsName.setText(merchantsBean.getMerchants_name());
        tvMerchantsDesc.setText(merchantsBean.getManage_range());
        tvMerchantsSales.setText("日销量："+merchantsBean.getDay_sales());
        tvRange.setText(merchantsBean.getManage_range());
        tvAddress.setText(merchantsBean.getMerchants_province()+merchantsBean.getMerchants_city()+merchantsBean.getMerchants_country()+merchantsBean.getMerchants_address());
        tvSynopsis.setText(merchantsBean.getMerchants_introduction());
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
}
