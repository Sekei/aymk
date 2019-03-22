package com.live.tv.mvp.fragment.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.bean.StartTestBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.TestDescriptionPresenter;
import com.live.tv.mvp.view.home.ITestDescriptionView;
import com.live.tv.util.LoadingUtil;
import com.live.tv.util.SpSingleInstance;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/12
 */

public class TestDescriptionFragment extends BaseFragment<ITestDescriptionView, TestDescriptionPresenter> implements ITestDescriptionView {
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.textDescripe)
    TextView textDescripe;
    Unbinder unbinder;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    private String health_record_id;


    //分享相关
    private UMImage mImage = null;
    private SHARE_MEDIA mShare_meidia = SHARE_MEDIA.WEIXIN;
    private String mShareUrl = "";
    private String mCoverUrl = "";
    private String mTitle = ""; //标题


    public static TestDescriptionFragment newInstance() {
        Bundle args = new Bundle();
        TestDescriptionFragment fragment = new TestDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test_descripe;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.test_description);
        ivRight.setVisibility(View.INVISIBLE);
        ivRight.setImageResource(R.drawable.doctor_share);
        textDescripe.setText(R.string.test_descripe);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        health_record_id = userBean.getHealthRecordBean().getHealth_record_id();
        name.setText(userBean.getHealthRecordBean().getRecord_name() + "(" + userBean.getHealthRecordBean().getRelation() + ")");
        map.put("health_record_id", health_record_id);
        getPresenter().healthRecordDetail(map);
    }

    @OnClick({R.id.ivLeft, R.id.start, R.id.tittle, R.id.ivRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.start:
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("health_record_id", health_record_id);
                getPresenter().startTest(map);
                break;
            case R.id.tittle:
                startMoreConsultantFragment();
                break;


            case R.id.ivRight://分享
                mImage = new UMImage(getContext(), R.drawable.avatar);
                // mShareUrl = "http://liangchequan.com/lcshH5/share.html?member_id=" + userBean.getMember_id();
                mShareUrl = "https://www.baidu.com/";
                mTitle = "爱医美康分享标题";
                showShareDialog();
                break;
        }
    }

    @Override
    public void onHealthRecordDetail(HealthRecordDetailBean data) {
        health_record_id = data.getHealth_record_id();
        name.setText(data.getRecord_name() + "(" + data.getRelation() + ")");
    }

    @Override
    public void onStartTest(StartTestBean data) {
        // startTestPhysiqueFragment(health_record_id);
        if ("0".equals(data.getIs_sure_ques())) {
            startTestPhysiqueFragment(health_record_id, "0", "0");
        } else {

            if (data.getTest_result()!=null&&!"".equals(data.getTest_result())){
                startTestPhysiqueFragment(health_record_id, "1", data.getTest_result());
            }else {
                startTestPhysiqueFragment(health_record_id, "0", "0");
            }



/*            if ("0".equals(data.getIs_done_factors())) {
                startTestPhysiqueFragment(health_record_id, "1", data.getTest_result());
            } else {
                startTestDiagnoseReportFragment(health_record_id);
            }*/
        }
    }

    @Override
    public void onGetMemberDetail(UserBean data) {

    }

    @Override
    public TestDescriptionPresenter createPresenter() {
        return new TestDescriptionPresenter(getApp());
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
        ToastUtils.showToast(context.getApplicationContext(), e.getMessage());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.REQUESTCODE:
                if (data != null) {
                    String stringExtra = data.getStringExtra(Constants.HEALTH_RECORD_ID);
                    map.clear();
                    map.put("health_record_id", stringExtra);
                    getPresenter().healthRecordDetail(map);
                }
                break;
        }
    }


    //===============================分享=================================

    private void showShareDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.share_dialog, null);
        final AlertDialog mDialog = new AlertDialog.Builder(context).create();
        mDialog.show();// 显示创建的AlertDialog，并显示，必须放在Window设置属性之前
        Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(view);//这一步必须指定，否则不出现弹窗
            WindowManager.LayoutParams mParams = window.getAttributes();
            mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
            window.setBackgroundDrawableResource(android.R.color.white);
            window.setWindowAnimations(R.style.AnimBottom);
            window.setAttributes(mParams);
        }

        TextView btn_wx = (TextView) view.findViewById(R.id.btn_share_wx);
        TextView btn_circle = (TextView) view.findViewById(R.id.btn_share_circle);
        TextView btn_qq = (TextView) view.findViewById(R.id.btn_share_qq);
        TextView btn_qzone = (TextView) view.findViewById(R.id.btn_share_qqzone);
        TextView btn_cancle = (TextView) view.findViewById(R.id.tv_cancel);


        btn_wx.setOnClickListener(mShareBtnClickListen);
        btn_circle.setOnClickListener(mShareBtnClickListen);
        btn_qq.setOnClickListener(mShareBtnClickListen);
        btn_qzone.setOnClickListener(mShareBtnClickListen);


        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
    }

    private View.OnClickListener mShareBtnClickListen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_share_wx:
                    mShare_meidia = SHARE_MEDIA.WEIXIN;
                    break;
                case R.id.btn_share_circle:
                    mShare_meidia = SHARE_MEDIA.WEIXIN_CIRCLE;
                    break;
                case R.id.btn_share_qq:
                    mShare_meidia = SHARE_MEDIA.QQ;
                    break;
                case R.id.btn_share_qqzone:
                    mShare_meidia = SHARE_MEDIA.QZONE;
                    break;

                default:
                    break;
            }

            ShareAction shareAction = new ShareAction(getActivity());

            UMWeb web = new UMWeb(mShareUrl);
            web.setThumb(mImage);
            web.setTitle(mTitle);
            web.setDescription("来自《爱医美康》APP的分享！");//描述

            shareAction.withMedia(web);
            shareAction.setCallback(umShareListener);
            shareAction.setPlatform(mShare_meidia).share();
        }
    };


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(getActivity(), "分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), "分享失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


}