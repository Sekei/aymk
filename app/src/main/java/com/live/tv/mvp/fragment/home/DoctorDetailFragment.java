package com.live.tv.mvp.fragment.home;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hyphenate.easeui.utils.GlideCircleTransform;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AllDoctorAssessesBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.DoctorCommentAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.DoctorDetailPresenter;
import com.live.tv.mvp.view.home.IDoctorDetailView;
import com.live.tv.util.MyTextViewUitl;
import com.live.tv.util.SpSingleInstance;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public class DoctorDetailFragment extends BaseFragment<IDoctorDetailView, DoctorDetailPresenter> implements IDoctorDetailView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.focus)
    TextView focus;
    Unbinder unbinder;

    private TextView doctorName;//医生姓名
    private TextView commentTittle;//评价数量


    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private String doctor_id;//医生id
    private int requestType = 1;//咨询类型 默认 1图文资讯
    private int focusState = -1;
    private String sendDoctorName;

    private DoctorCommentAdapter adapter;
    List<AllDoctorAssessesBean> list;

    private TextView type;//职称
    private TextView askNum;//咨询人数
    private TextView praiseNum;//好评率
    private TextView focusNum;//关注人数
    private TextView department;//科室
    private TextView hospital;//医院
    private TextView Indications;//主治
    private ImageView img;

    private String img_price = "";
    private String phone_price = "";
    private String video_price = "";
    private String health_price = "";
    TextView onePrice;
    TextView twoPrice;
    TextView threePrice;
    TextView fourPrice;


    //分享相关
    private UMImage mImage = null;
    private SHARE_MEDIA mShare_meidia = SHARE_MEDIA.WEIXIN;
    private String mShareUrl = "";
    private String mCoverUrl = "";
    private String mTitle = ""; //标题

    private DoctorDetailBean doctorDetailBean;
    private String collection_id;


    public static DoctorDetailFragment newInstance(String doctor_id) {
        Bundle args = new Bundle();
        DoctorDetailFragment fragment = new DoctorDetailFragment();
        fragment.doctor_id = doctor_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_doctor_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("医生详情");
        ivRight.setVisibility(View.INVISIBLE);
        ivRight.setImageResource(R.drawable.doctor_share);
        list = new ArrayList();
        adapter = new DoctorCommentAdapter(context, list);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                final View view = LayoutInflater.from(context).inflate(R.layout.doctor_detail_top, null);

                doctorName = (TextView) view.findViewById(R.id.name);
                img = (ImageView) view.findViewById(R.id.img);
                commentTittle = (TextView) view.findViewById(R.id.commentTittle);
                type = (TextView) view.findViewById(R.id.type);
                askNum = (TextView) view.findViewById(R.id.askNum);
                praiseNum = (TextView) view.findViewById(R.id.praiseNum);
                focusNum = (TextView) view.findViewById(R.id.focusNum);
                department = (TextView) view.findViewById(R.id.department);
                hospital = (TextView) view.findViewById(R.id.hospital);
                Indications = (TextView) view.findViewById(R.id.Indications);

                onePrice = (TextView) view.findViewById(R.id.onePrice);
                twoPrice = (TextView) view.findViewById(R.id.twoPrice);
                threePrice = (TextView) view.findViewById(R.id.threePrice);
                fourPrice = (TextView) view.findViewById(R.id.fourPrice);

                MyTextViewUitl.setText(onePrice, new String[]{img_price, "元/次"}, new String[]{"#6EB92B", "#6EB92B"});
                MyTextViewUitl.setText(twoPrice, new String[]{phone_price, "元/次"}, new String[]{"#E5192C", "#999999"});
                MyTextViewUitl.setText(threePrice, new String[]{video_price, "元/次"}, new String[]{"#E5192C", "#999999"});
                MyTextViewUitl.setText(fourPrice, new String[]{health_price, "元/月"}, new String[]{"#E5192C", "#999999"});

                final TextView requestOne = (TextView) view.findViewById(R.id.requestOne);
                requestOne.setTextColor(getResources().getColor(R.color.colorPrimary));
                final TextView requestTwo = (TextView) view.findViewById(R.id.requestTwo);
                final TextView requestThree = (TextView) view.findViewById(R.id.requestThree);
                final TextView requestFour = (TextView) view.findViewById(R.id.requestFour);

                final TextView requestOneBg = (TextView) view.findViewById(R.id.requestOneBg);
                final TextView requestTwoBg = (TextView) view.findViewById(R.id.requestTwoBg);
                final TextView requestThreeBg = (TextView) view.findViewById(R.id.requestThreeBg);
                final TextView requestFourBg = (TextView) view.findViewById(R.id.requestFourBg);
                View bg_one = view.findViewById(R.id.bg_one);

                bg_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (doctorDetailBean != null) {
                            startDoctorPersonalInfoFragment(doctorDetailBean);
                        }


                    }
                });

                requestOneBg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestType = 1;
                        ok.setText(requestOne.getText().toString() + onePrice.getText().toString());

                        requestOneBg.setBackgroundColor(getResources().getColor(R.color.bg_1));
                        requestTwoBg.setBackgroundColor(getResources().getColor(R.color.pure_white));
                        requestThreeBg.setBackgroundColor(getResources().getColor(R.color.pure_white));
                        requestFourBg.setBackgroundColor(getResources().getColor(R.color.pure_white));

                        requestOne.setTextColor(getResources().getColor(R.color.colorPrimary));
                        requestTwo.setTextColor(getResources().getColor(R.color.colorTextG3));
                        requestThree.setTextColor(getResources().getColor(R.color.colorTextG3));
                        requestFour.setTextColor(getResources().getColor(R.color.colorTextG3));
                        MyTextViewUitl.setText(onePrice, new String[]{img_price, "元/次"}, new String[]{"#6EB92B", "#6EB92B"});
                        MyTextViewUitl.setText(twoPrice, new String[]{phone_price, "元/次"}, new String[]{"#E5192C", "#999999"});
                        MyTextViewUitl.setText(threePrice, new String[]{video_price, "元/次"}, new String[]{"#E5192C", "#999999"});
                        MyTextViewUitl.setText(fourPrice, new String[]{health_price, "元/月"}, new String[]{"#E5192C", "#999999"});
                    }
                });
                requestTwoBg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestType = 2;
                        ok.setText(requestTwo.getText().toString() + twoPrice.getText().toString());

                        requestOneBg.setBackgroundColor(getResources().getColor(R.color.pure_white));
                        requestTwoBg.setBackgroundColor(getResources().getColor(R.color.bg_1));
                        requestThreeBg.setBackgroundColor(getResources().getColor(R.color.pure_white));
                        requestFourBg.setBackgroundColor(getResources().getColor(R.color.pure_white));

                        requestOne.setTextColor(getResources().getColor(R.color.colorTextG3));
                        requestTwo.setTextColor(getResources().getColor(R.color.colorPrimary));
                        requestThree.setTextColor(getResources().getColor(R.color.colorTextG3));
                        requestFour.setTextColor(getResources().getColor(R.color.colorTextG3));


                        MyTextViewUitl.setText(onePrice, new String[]{img_price, "元/次"}, new String[]{"#E5192C", "#999999"});
                        MyTextViewUitl.setText(twoPrice, new String[]{phone_price, "元/次"}, new String[]{"#6EB92B", "#6EB92B"});
                        MyTextViewUitl.setText(threePrice, new String[]{video_price, "元/次"}, new String[]{"#E5192C", "#999999"});
                        MyTextViewUitl.setText(fourPrice, new String[]{health_price, "元/月"}, new String[]{"#E5192C", "#999999"});
                    }
                });
                requestThreeBg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestType = 3;
                        ok.setText(requestThree.getText().toString() + threePrice.getText().toString());
                        requestOneBg.setBackgroundColor(getResources().getColor(R.color.pure_white));
                        requestTwoBg.setBackgroundColor(getResources().getColor(R.color.pure_white));
                        requestThreeBg.setBackgroundColor(getResources().getColor(R.color.bg_1));
                        requestFourBg.setBackgroundColor(getResources().getColor(R.color.pure_white));

                        requestOne.setTextColor(getResources().getColor(R.color.colorTextG3));
                        requestTwo.setTextColor(getResources().getColor(R.color.colorTextG3));
                        requestThree.setTextColor(getResources().getColor(R.color.colorPrimary));
                        requestFour.setTextColor(getResources().getColor(R.color.colorTextG3));

                        MyTextViewUitl.setText(onePrice, new String[]{img_price, "元/次"}, new String[]{"#E5192C", "#999999"});
                        MyTextViewUitl.setText(twoPrice, new String[]{phone_price, "元/次"}, new String[]{"#E5192C", "#999999"});
                        MyTextViewUitl.setText(threePrice, new String[]{video_price, "元/次"}, new String[]{"#6EB92B", "#6EB92B"});
                        MyTextViewUitl.setText(fourPrice, new String[]{health_price, "元/月"}, new String[]{"#E5192C", "#999999"});
                    }
                });
                requestFourBg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestType = 4;
                        ok.setText(requestFour.getText().toString() + fourPrice.getText().toString());

                        requestOneBg.setBackgroundColor(getResources().getColor(R.color.pure_white));
                        requestTwoBg.setBackgroundColor(getResources().getColor(R.color.pure_white));
                        requestThreeBg.setBackgroundColor(getResources().getColor(R.color.pure_white));
                        requestFourBg.setBackgroundColor(getResources().getColor(R.color.bg_1));

                        requestOne.setTextColor(getResources().getColor(R.color.colorTextG3));
                        requestTwo.setTextColor(getResources().getColor(R.color.colorTextG3));
                        requestThree.setTextColor(getResources().getColor(R.color.colorTextG3));
                        requestFour.setTextColor(getResources().getColor(R.color.colorPrimary));

                        MyTextViewUitl.setText(onePrice, new String[]{img_price, "元/次"}, new String[]{"#E5192C", "#999999"});
                        MyTextViewUitl.setText(twoPrice, new String[]{phone_price, "元/次"}, new String[]{"#E5192C", "#999999"});
                        MyTextViewUitl.setText(threePrice, new String[]{video_price, "元/次"}, new String[]{"#E5192C", "#999999"});
                        MyTextViewUitl.setText(fourPrice, new String[]{health_price, "元/月"}, new String[]{"#6EB92B", "#6EB92B"});
                    }
                });


                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("doctor_id", doctor_id);
        getPresenter().getDoctorDetail(map);
        getPresenter().getAllDoctorAssesses(map);
    }


    @Override
    public void onGetDoctorDetail(DoctorDetailBean data) {
        if (data != null) {


            doctorDetailBean = data;

            sendDoctorName = data.getDoctor_name();
            doctorName.setText(data.getDoctor_name());
            doctorName.setText(data.getDoctor_name());
            type.setText(data.getDoctor_title());
            askNum.setText(data.getConsulting_count());
            focusNum.setText(data.getAttention_count());
            praiseNum.setText(data.getRate());
            department.setText(data.getDepartment_level2());
            hospital.setText(data.getDoctor_hospital());
            Indications.setText("主治：" + data.getDoctor_attendingtype());

            Glide.with(getActivity()).load(Constants.BASE_URL + data.getDoctor_head_image())
                    .transform(new GlideCircleTransform(getContext()))
                    .placeholder(R.drawable.ava_defaultx)
                    .error(R.drawable.ava_defaultx)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img);
            img_price = data.getGraphic_price() != null ? data.getGraphic_price() : "0";
            phone_price = data.getPhone_price() != null ? data.getPhone_price() : "0";
            video_price = data.getVideo_price() != null ? data.getVideo_price() : "0";
            health_price = data.getHealth_management_price() != null ? data.getHealth_management_price() : "0";
            MyTextViewUitl.setText(onePrice, new String[]{img_price, "元/次"}, new String[]{"#6EB92B", "#6EB92B"});
            MyTextViewUitl.setText(twoPrice, new String[]{phone_price, "元/次"}, new String[]{"#E5192C", "#999999"});
            MyTextViewUitl.setText(threePrice, new String[]{video_price, "元/次"}, new String[]{"#E5192C", "#999999"});
            MyTextViewUitl.setText(fourPrice, new String[]{health_price, "元/月"}, new String[]{"#E5192C", "#999999"});
            ok.setText("图文咨询" + img_price + "元/次");
            collection_id = data.getCollection_id();
            if (data != null && data.getIs_collection() != null) {
                if (data.getIs_collection().equals("1")) {
                    focus.setText("取消关注");
                    Drawable drawable = getContext().getResources().getDrawable(R.drawable.doctor_consult_followed);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                    focus.setCompoundDrawables(null, drawable, null, null);
                } else {
                    focus.setText("关注");
                    Drawable drawable = getContext().getResources().getDrawable(R.drawable.doctor_consult_follow);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                    focus.setCompoundDrawables(null, drawable, null, null);
                }
            }
        }
    }

    @Override
    public void onGetAllDoctorAssesses(List<AllDoctorAssessesBean> data) {
        commentTittle.setText(getResources().getString(R.string.assess) + "（" + data.size() + "）");
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onCollectionDoctor(String data) {

        ToastUtils.showToast(context.getApplicationContext(), "关注成功");
        focus.setText("取消关注");
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.doctor_consult_followed);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        focus.setCompoundDrawables(null, drawable, null, null);

        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("doctor_id", doctor_id);
        getPresenter().getDoctorDetail(map);
    }

    @Override
    public void onCancelCollection(String data) {
        ToastUtils.showToast(context.getApplicationContext(), "取消关注");
        focus.setText("关注");
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.doctor_consult_follow);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        focus.setCompoundDrawables(null, drawable, null, null);
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("doctor_id", doctor_id);
        getPresenter().getDoctorDetail(map);
    }

    @Override
    public DoctorDetailPresenter createPresenter() {
        return new DoctorDetailPresenter(getApp());
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

    @OnClick({R.id.ivLeft, R.id.ok, R.id.pay, R.id.focus, R.id.ivRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:
                switch (requestType) {
                    case 1://图文咨询
                        startRequestOneFragment(doctor_id, doctorName.getText().toString(), img_price, doctorDetailBean.getHx_account());
                        break;
                    case 2://电话咨询
                        startChooseTimeFragment(doctor_id, "phone", phone_price);
                        break;
                    case 3://视频咨询
                        startChooseTimeFragment(doctor_id, "video", video_price);
                        break;
                    case 4://健康管理
                        startHealthManageFragment(doctor_id, doctorName.getText().toString());

                        break;
                }
                break;
            case R.id.pay://送心意
                startSendHeartFragment(doctor_id, sendDoctorName, doctorDetailBean.getDoctor_head_image());
                break;
            case R.id.focus://关注

                if (userBean != null) {


                    if (doctorDetailBean.getIs_collection().equals("1")) {
                        focus.setText("关注");
                        Drawable drawable = getContext().getResources().getDrawable(R.drawable.doctor_consult_follow);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        focus.setCompoundDrawables(null, drawable, null, null);


                        Map<String, String> mMap = new HashMap<>();
                        mMap.put("member_id", userBean.getMember_id());
                        mMap.put("member_token", userBean.getMember_token());
                        mMap.put("collection_id", collection_id);
                        getPresenter().getCancelCollection(mMap);
                    } else {
                        focus.setText("取消关注");
                        Drawable drawable = getContext().getResources().getDrawable(R.drawable.doctor_consult_followed);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        focus.setCompoundDrawables(null, drawable, null, null);
                        map.clear();
                        map.put("member_id", userBean.getMember_id());
                        map.put("member_token", userBean.getMember_token());
                        map.put("collection_type", "doctor");
                        map.put("doctor_id", doctor_id);
                        getPresenter().getinsertCollection(map);


                    }


                } else {
                    startLogin();
                }


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
