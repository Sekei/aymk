package com.live.tv.mvp.fragment.mine;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.SystemHtmlBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.JoinPresenter;
import com.live.tv.mvp.view.mine.IJoinView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.live.tv.mvp.fragment.mine.JoinDoctorFragment.READ_CONTACTS_REQUEST_CODE;

/**
 * @author Created by stone
 * @since 2018/2/1
 * 合作加盟
 */

public class JoinFragment extends BaseFragment<IJoinView,JoinPresenter> implements IJoinView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private String houseService_state="";
    private String merchants_state="";
    private String doctor_state="";
    private List<SystemHtmlBean> mlist;
    private String htmltype;

    public static JoinFragment newInstance() {

        Bundle args = new Bundle();

        JoinFragment fragment = new JoinFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_join;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.join);
    }

    @Override
    public void initData() {


    }

    @Override
    public void onResume() {
        super.onResume();
        Map<String,String> mMap =  new HashMap<>();
        mMap.put("member_id",userBean.getMember_id());
        mMap.put("member_token",userBean.getMember_token());
        getPresenter().getUserDetail(mMap);
        getPresenter().gethtmlList();

    }

    @OnClick({R.id.ivLeft,R.id.shop,R.id.doctor,R.id.house})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.shop:

                if ("3".equals(merchants_state)){
                    if (Build.VERSION.SDK_INT >= 23) {
                        // 如果是大于6.0
                        // 如果权限没有被授予
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) !=
                                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
                                PackageManager.PERMISSION_GRANTED) {
                            // 申请权限
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, READ_CONTACTS_REQUEST_CODE);
                        } else {
                            // TODO 权限已经被授予
                            htmltype= getHtmltype("商家");
                                startJoinShopFragment(htmltype);

                        }
                    } else {
                        // 如果小于6.0
                        htmltype= getHtmltype("商家");
                        startJoinShopFragment(htmltype);
                    }

                }else {

                    startJoindoctorSuccessFragment("1");//商家入驻
                }



                break;
            case R.id.doctor:


                if ("3".equals(doctor_state)){
                    if (Build.VERSION.SDK_INT >= 23) {
                        // 如果是大于6.0
                        // 如果权限没有被授予
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) !=
                                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
                                PackageManager.PERMISSION_GRANTED) {
                            // 申请权限
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, READ_CONTACTS_REQUEST_CODE);
                        } else {
                            // TODO 权限已经被授予
                            htmltype= getHtmltype("医生");
                            startJoinDoctorFragment(htmltype);
                        }
                    } else {
                        // 如果小于6.0
                        htmltype= getHtmltype("医生");
                        startJoinDoctorFragment(htmltype);
                    }

                }else {

                    startJoindoctorSuccessFragment("2");//商家入驻
                }



                break;
            case R.id.house:


                if ("3".equals(houseService_state)){
                    if (Build.VERSION.SDK_INT >= 23) {
                        // 如果是大于6.0
                        // 如果权限没有被授予
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) !=
                                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
                                PackageManager.PERMISSION_GRANTED) {
                            // 申请权限
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, READ_CONTACTS_REQUEST_CODE);
                        } else {
                            // TODO 权限已经被授予
                            htmltype=getHtmltype("家政");
                            startJoinHouseFragment(htmltype);
                        }
                    } else {
                        // 如果小于6.0
                        htmltype=getHtmltype("家政");
                        startJoinHouseFragment(htmltype);
                    }

                }else {

                    startJoindoctorSuccessFragment("3");//家政申请
                }


                break;
        }
    }

    private String getHtmltype(String s) {

        if (mlist!=null&&mlist.size()>0){

          for (int i=0;i<mlist.size();i++){

       if (mlist.get(i).getHtml_type().equals(s));

          return mlist.get(i).getHtml_url();

          }
        }

            return null;
    }


    @Override
    public JoinPresenter createPresenter() {
        return new JoinPresenter(getApp());
    }

    @Override
    public void onGetUserDetail(UserBean data) {
        userBean=data;
        merchants_state=data.getMerchants_state();
        doctor_state=data.getDoctor_state();
        houseService_state=data.getHouseService_state();
    }

    @Override
    public void onhtmllist(List<SystemHtmlBean> data) {
        //获取协议
        mlist=data;
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
}
