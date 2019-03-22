package com.live.tv.mvp.fragment.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.PoiItem;
import com.iflytek.cloud.thirdparty.V;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.MerchantsBean;
import com.live.tv.mvp.base.SimpleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * Created by sh-lx on 2017/7/12.
 * <p>
 * 门店详情
 */

public class MerchantsMapFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    MapView mapView;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    /*  @BindView(R.id.top_toolbar)
      LinearLayout topToolbar;*/
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_lastaddress)
    TextView tvLastaddress;
    @BindView(R.id.tv_call)
    TextView tvCall;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private AMap aMap;
    Unbinder unbinder;
    private MerchantsBean merchantsBean;
    private PoiItem mPoiItem;
    private LatLng latlng;
    private MarkerOptions markerOption;
    private Marker marker;

    public static MerchantsMapFragment newInstance(PoiItem poiItem) {

        Bundle args = new Bundle();
        MerchantsMapFragment fragment = new MerchantsMapFragment();
        fragment.setArguments(args);
        fragment.mPoiItem = poiItem;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        mapView = findView(R.id.mapView);
        mapView.onCreate(savedInstanceState); // 此方法必须重写
        if (aMap == null) {
            aMap = mapView.getMap();

        }
        return rootView;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_merchants_map;
    }

    @Override
    public void initUI() {
        tvTitle.setText("门店详情");
    }

    @Override
    public void initData() {
        if (mPoiItem != null) {
            double lag = mPoiItem.getLatLonPoint().getLongitude();
            double lat = mPoiItem.getLatLonPoint().getLatitude();
            latlng = new LatLng(lat, lag);
            markerOption = new MarkerOptions()
                    // .icon(BitmapDescriptorFactory.fromResource(R.drawable.qishou))
                    .position(latlng)
                    .title(mPoiItem.getTitle())
                    //.snippet("88888")
                    .draggable(true);
            marker = aMap.addMarker(markerOption);
            marker.showInfoWindow();
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13f));
            tvName.setText(mPoiItem.getTitle());
            tvAddress.setText(mPoiItem.getSnippet());
            tvLastaddress.setText(mPoiItem.getSnippet());
            if (mPoiItem.getTel()!=null&&!mPoiItem.getTel().equals("")){
                tvCall.setText(mPoiItem.getTel());

            }else{
                tvCall.setText("暂无号码");
            }

        }


    }
    @OnClick({R.id.ivLeft, R.id.tv_call})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_call:

                if (mPoiItem != null) {
                   if (!tvCall.getText().toString().equals("暂无号码")){
                       sq(mPoiItem.getTel());
                   }


                }

                break;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        mapView.onDestroy();
    }


    private void sq(String num) {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                // 弹窗需要解释为何需要该权限，再次请求授权
                ToastUtils.showToast(context.getApplicationContext(), "请授权！");
                // 帮跳转到该应用的设置界面，让用户手动授权
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                // 不需要解释为何需要该权限，直接请求授权
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            // 已经获得授权，可以打电话
            CallPhone(num);
        }
    }

    private void CallPhone(String number) {
        Intent intent = new Intent(); // 意图对象：动作 + 数据
        intent.setAction(Intent.ACTION_CALL); // 设置动作
        Uri data = Uri.parse("tel:" + number); // 设置数据
        intent.setData(data);
        startActivity(intent); // 激活Activity组件
    }
}
