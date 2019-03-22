package com.live.tv.mvp.fragment.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.PlanPriceBean;
import com.live.tv.bean.SimplePlansBean;
import com.live.tv.bean.TestRecordBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.TestResultTabPlanPresenter;
import com.live.tv.mvp.view.home.ITestResultTabPlanView;
import com.live.tv.util.SpSingleInstance;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 调理方案
 * @author Created by stone
 * @since 2018/1/12
 */

public class TestResultTabPlanFragment extends BaseFragment<ITestResultTabPlanView, TestResultTabPlanPresenter> implements ITestResultTabPlanView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.pay)
    TextView pay;
    @BindView(R.id.share)
    TextView share;

    @BindView(R.id.content)
    TextView content;
    Unbinder unbinder;
    private UserBean userBean;
    private String plan_price;//调理方案价格
    private Map<String, String> map = new HashMap<>();
    private String test_id;
    private String health_record_id;
    private String is_buy;//是否购买专业调理方案 0未购买 1已购买

    //分享相关
    private UMImage mImage = null;
    private SHARE_MEDIA mShare_meidia = SHARE_MEDIA.WEIXIN;
    private String mShareUrl = "";
    private String mCoverUrl = "";
    private String mTitle = ""; //标题

    public static TestResultTabPlanFragment newInstance(String test_id) {
        Bundle args = new Bundle();
        TestResultTabPlanFragment fragment = new TestResultTabPlanFragment();
        fragment.test_id = test_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test_result_plan;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.conditioning_program);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();

        map.put("test_id", test_id);
        getPresenter().getSimplePlans(map);
        getPresenter().testRecord(map);
    }

    @Override
    public void onGetSimplePlans(List<SimplePlansBean> data) {
        String s = "";
        for (int i = 0; i < data.size(); i++) {
            s = s + data.get(i).getPlan_desc() + "\n";
        }
        content.setText(s);
    }

    @Override
    public void onGetPlanPrice(PlanPriceBean data) {
        plan_price = data.getPlan_price();
        pay.setText(getString(R.string.txt_buy_plan)+data.getPlan_price()+getResources().getString(R.string.yuan));
//        price.setText(data.getPlan_price()+getResources().getString(R.string.yuan));
    }


    @Override
    public void onTestRecord(TestRecordBean data) {
        health_record_id = data.getHealth_record_id() + "";
        is_buy = data.getIs_buy();
        if ("0".equals(is_buy)){
            getPresenter().getPlanPrice(map);
        }else {
            pay.setText("确认查看");

        }

    }

    @OnClick({R.id.ivLeft, R.id.out,R.id.profession,R.id.pay,R.id.share})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.profession:
            case R.id.out:
                finish();
                //startPayServerFragment("1", plan_price, health_record_id, test_id, "");
                break;
            case R.id.pay:

                if ("0".equals(is_buy)){

                    startBuyPlanFragment(health_record_id,test_id,plan_price);
                }else if ("1".equals(is_buy)){
                    startTestResultTabPlanProfessionFragment(test_id);
                }

                break;



            case R.id.share:
                //分享
                mImage = new UMImage(getContext(), R.drawable.avatar);
                // mShareUrl = "http://liangchequan.com/lcshH5/share.html?member_id=" + userBean.getMember_id();
                mShareUrl = "https://www.baidu.com/";
                mTitle = "爱医美康分享标题";
                showShareDialog();



                break;
        }
    }

    @Override
    public TestResultTabPlanPresenter createPresenter() {
        return new TestResultTabPlanPresenter(getApp());
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode== Constants.INTENT_PAY_SUCCESS){

            String  string =data.getStringExtra("key");
            if (string.equals("success")){

                map.put("test_id", test_id);
                getPresenter().getSimplePlans(map);
                getPresenter().testRecord(map);

            }

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
