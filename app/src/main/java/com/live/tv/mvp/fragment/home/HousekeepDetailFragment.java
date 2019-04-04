package com.live.tv.mvp.fragment.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.iflytek.cloud.thirdparty.V;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HousekeepBean;
import com.live.tv.bean.ServiceCommentBean;
import com.live.tv.bean.ServiceCopeBean;
import com.live.tv.mvp.adapter.communicate.CommunityAppraiseAdapter;
import com.live.tv.mvp.adapter.communicate.CommunityLikeAdapter;
import com.live.tv.mvp.adapter.home.ServiceAddressAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.DialogHouseKeepFragment;
import com.live.tv.mvp.fragment.huanxin.ChatActivity;
import com.live.tv.mvp.presenter.home.HouseKeepDetailPresenter;
import com.live.tv.mvp.view.home.IHousekeepDetailView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.youme.voiceengine.AppPara.getPackageName;

/**
 * Created by mac1010 on 2018/8/27.
 * 家政详情
 */

public class HousekeepDetailFragment extends BaseFragment<IHousekeepDetailView, HouseKeepDetailPresenter> implements IHousekeepDetailView {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_nums)
    TextView tvNums;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.tv_quyu)
    TextView tvQuyu;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.tv_Contacts)
    TextView tvContacts;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.address_more)
    TextView address_more;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_call)
    ImageView ivCall;
    @BindView(R.id.ll_4)
    LinearLayout ll4;
    @BindView(R.id.rl_5)
    RelativeLayout rl5;
    @BindView(R.id.recycler_address)
    RecyclerView recycleraddress;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.textView82)
    TextView textView82;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_click)
    TextView tvClick;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.tv_zixun)
    TextView tvZiXun;
    @BindView(R.id.ct_miaoshu)
    ConstraintLayout ctMiaoshu;
    @BindView(R.id.tv_fw_content)
    TextView tvFwContent;
    @BindView(R.id.mFlexboxLayout)
    FlexboxLayout mFlexboxLayout;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.ct_image)
    ConstraintLayout ctImage;
    @BindView(R.id.tv_appraise)
    TextView tvAppraise;
    @BindView(R.id.recycler_appraise)
    RecyclerView recyclerAppraise;
    @BindView(R.id.ct_pinglun)
    ConstraintLayout ctPinglun;
    @BindView(R.id.tv_like)
    TextView tvLike;
    @BindView(R.id.tv_moreaddress)
    TextView tvMoreAddress;
    @BindView(R.id.recycle_like)
    RecyclerView recycleLike;
    @BindView(R.id.view8)
    View view8;
    @BindView(R.id.tv_warning)
    TextView tvWarning;
    @BindView(R.id.ct_likelist)
    ConstraintLayout ctLikelist;
    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;
    @BindView(R.id.ll_call)
    LinearLayout llCall;
    @BindView(R.id.ll_bottom_content)
    LinearLayout llBottomContent;
    @BindView(R.id.show_ll)
    RelativeLayout showLl;
    @BindView(R.id.tv_jing)
    TextView tvjing;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.tv_pinglun)
    TextView tvPingLun;

    private CommunityAppraiseAdapter appraiseAdapter;//评论的
    private CommunityLikeAdapter likeAdapter;//猜你喜欢
    private List<ServiceCommentBean> list = new ArrayList<>();
    private List<HousekeepBean> likelist = new ArrayList<>();
    Unbinder unbinder;
    private String housekeep_id;
    private boolean isclick;
    private ServiceAddressAdapter addressAdapter;
    private Map<String, String> map = new HashMap<>();
    private DialogHouseKeepFragment dialogHouseKeepFragment;
    private List<ServiceCopeBean> serviceCopeBeenList;//点击服务传值
    private HousekeepBean bean;

    //private List<>
    public static HousekeepDetailFragment newInstance(String housekeep_id) {
        Bundle args = new Bundle();
        HousekeepDetailFragment fragment = new HousekeepDetailFragment();
        fragment.housekeep_id = housekeep_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showToast(getActivity(), e.getMessage());
    }

    @Override
    public int getRootViewId() {
        return R.layout.housekeeping_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("详情");

        recyclerAppraise.setNestedScrollingEnabled(false);
        recycleLike.setNestedScrollingEnabled(false);
        appraiseAdapter = new CommunityAppraiseAdapter(getActivity());
        appraiseAdapter.addAll(list);
        recyclerAppraise.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerAppraise.setAdapter(appraiseAdapter);
        likeAdapter = new CommunityLikeAdapter(getActivity());
        // likeAdapter.addAll(list);
        recycleLike.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleLike.setAdapter(likeAdapter);
        tvWarning.setText(Html.fromHtml("<font size=\"5\" color=\"black\">今办理服务前请勿付</font><font size=\"5\" color=\"red\">定金`押金</font><font size=\"5\" color=\"black\">等费用!</font>"));
        dialogHouseKeepFragment = new DialogHouseKeepFragment();
        dialogHouseKeepFragment.setOnSelectListener(new DialogHouseKeepFragment.SelectListener() {
            @Override
            public void OnSelectListener(String id, String prace) {
                //  startBuyGoodFragment(id, prace,"1");
                sq();
               /* if (dialogHouseKeepFragment.getShowsDialog()) {
                    dialogHouseKeepFragment.dismiss();
                }*/
            }
        });

        addressAdapter = new ServiceAddressAdapter(getActivity());
        recycleraddress.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleraddress.setAdapter(addressAdapter);

    }

    @Override
    public void initData() {
        if (housekeep_id.equals(userBean.getHouse_service_id())) {
            tvMoreAddress.setVisibility(View.VISIBLE);
        } else {
            tvMoreAddress.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("house_service_id", housekeep_id);
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getHousekeepDetail(map);//服务详情
        getPresenter().getserviceCope(map);//服务范围
        getPresenter().getAssessmentList(map);//评论

    }

    @Override
    public HouseKeepDetailPresenter createPresenter() {
        return new HouseKeepDetailPresenter(getApp());
    }

    @Override
    public void onGetHousekeepDetail(HousekeepBean data) {
        /**
         * 详情返回的值
         */
        bean = data;


        if (data.getIs_collection() != null && data.getIs_collection().equals("1")) {
            Drawable drawable = getActivity().getResources().getDrawable(R.drawable.doctor_consult_followed);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvCollect.setCompoundDrawables(null, drawable, null, null);
        } else {

            Drawable drawable = getActivity().getResources().getDrawable(R.drawable.doctor_consult_follow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvCollect.setCompoundDrawables(null, drawable, null, null);
        }
        setdatalikelist();
        Glide.with(getContext()).load(Constants.BASE_URL + data.getHouse_service_image())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(img);
        tvDesc.setText(data.getHouse_service_name());
        tvTime.setText("发布时间: " + data.getCreate_time());
        tvNums.setText(data.getCount() + "人浏览");
        tvType.setText(data.getService_type());
        tvQuyu.setText(data.getHouse_address());
        tvContacts.setText(data.getContact_name());
        tvContent.setText(data.getHouse_service_desc());
        tvContent.post(new Runnable() {
            @Override
            public void run() {
                if (tvContent.getLineCount() >= 2) {
                    tvClick.setVisibility(View.VISIBLE);
                } else {
                    tvClick.setVisibility(View.GONE);
                }

            }
        });

        String mobile = data.getContact_phone();
        if (null != mobile && !"".equals(mobile)) {
            mobile = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
        }
        tvPhone.setText(mobile);
        addressAdapter.clear();
        addressAdapter.addAll(data.getHouseAddressBeans());
        addressAdapter.notifyDataSetChanged();

        String[] tag = null;
        try {
            tag = data.getService_range().split(",");

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tag != null && tag.length > 0) {
            mFlexboxLayout.removeAllViews();
            for (int i = 0; i < tag.length; i++) {

                final TextView word = new TextView(getContext());
                word.setTextColor(getContext().getResources().getColor(R.color.tx_l));
                word.setText(tag[i]);
                word.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelSize(R.dimen.y26));
                word.setBackgroundResource(R.drawable.newshape_line);
                FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(12, 5, 12, 5);

                word.setLayoutParams(lp);
                mFlexboxLayout.addView(word);

            }
        }


    }

    private void setdatalikelist() {
        /*map.put("house_province",bean.getHouse_province());
        map.put("house_city",bean.getHouse_city());
        map.put("house_country",bean.getHouse_country());*/
        map.put("service_type_id", bean.getService_type());
        map.put("is_hot", "1");
        getPresenter().getHouseKeeps(map);
    }

    @Override
    public void onHouseKeeps(List<HousekeepBean> list) {
        if (list != null && list.size() > 0) {
            tvLike.setVisibility(View.VISIBLE);
        } else {
            tvLike.setVisibility(View.GONE);

        }
        //热门推荐的接口
        likeAdapter.clear();
        likeAdapter.addAll(list);
        likeAdapter.notifyDataSetChanged();

    }

    @Override
    public void OnServiceCope(List<ServiceCopeBean> data) {
        serviceCopeBeenList = data;
    }

    @Override
    public void OnserviceComment(List<ServiceCommentBean> data) {
        /***
         * 评论列表
         */
        tvAppraise.setText("评论(" + data.size() + ")");
        appraiseAdapter.clear();
        appraiseAdapter.addAll(data);
        appraiseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCollectiohouse(String data) {
        ToastUtils.showToast(getActivity(), data);
        ToastUtils.showToast(context.getApplicationContext(), "收藏成功");
        Drawable drawable = getActivity().getResources().getDrawable(R.drawable.doctor_consult_followed);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvCollect.setCompoundDrawables(null, drawable, null, null);
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


    @OnClick({R.id.ivLeft, R.id.tv_click, R.id.iv_call, R.id.tv_service, R.id.tv_jing, R.id.tv_moreaddress, R.id.address_more,
            R.id.tv_collect, R.id.tv_call, R.id.tv_zixun})
    public void Onclicksenter(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_click:
                if (!isclick) {
                    tvContent.setEllipsize(null);
                    isclick = true;
                    tvContent.setSingleLine(false);

                    Drawable drawable = getResources().getDrawable(R.mipmap.icon_up);
                    //一定要加这行！！！！！！！！！！！
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvClick.setCompoundDrawables(null, null, drawable, null);
                    tvClick.setText("点击收起");


                } else {
                    isclick = false;
                    tvContent.setLines(2);
                    tvContent.setEllipsize(TextUtils.TruncateAt.END);
                    Drawable drawable = getResources().getDrawable(R.mipmap.icon_down);
                    //一定要加这行！！！！！！！！！！！
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvClick.setCompoundDrawables(null, null, drawable, null);
                    tvClick.setText("展开更多");
                }

                break;
            case R.id.iv_call:

                sq();
                break;
            case R.id.tv_service:
                if (serviceCopeBeenList != null && serviceCopeBeenList.size() > 0) {
                    showhousekeepDialog(serviceCopeBeenList);

                } else {
                    ToastUtils.showToast(getActivity(), "此商家暂无服务稍后再试");
                }
                break;

            case R.id.tv_jing:

                startComplantFragment(housekeep_id);//投诉界面
                break;
            case R.id.tv_moreaddress:

                merchantamoreaddreFragment(housekeep_id);//商家更多信息
                break;
            case R.id.address_more:

                startServiceMoreFragment(bean.getHouse_service_id() + "");
                break;
            case R.id.tv_collect:
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("collection_type", "house");
                map.put("house_service_id", bean.getHouse_service_id() + "");
                getPresenter().getinsertCollection(map);

                break;
            case R.id.tv_call:
                sq();
                break;
            case R.id.tv_pinglun:

                break;
            case R.id.tv_zixun:

                /***
                 * 联系客服
                 */
                if (EMClient.getInstance().isLoggedInBefore()) {
                    //如果登陆过，判断是否连接
                    if (EMClient.getInstance().isConnected()) {
                        //链接
                        if (bean != null && bean.getHx_account() != null) {
                            startActivity(new Intent(getActivity(), ChatActivity.class)
                                    .putExtra("userId", bean.getHx_account())
                                    .putExtra("APP_user_name", userBean.getHx_nick_name())
                                    .putExtra("health_record_id", "")
                                    .putExtra("doctor_id", "")
                                    .putExtra("consult_record_id", "")
                                    .putExtra("to_head_image", userBean.getLegal_hand_img())
                                    .putExtra("androidtype", "1")
                                    .putExtra("to_username", "客服"));
                        } else {
                            ToastUtils.showToast(getActivity(), "暂时不能咨询");
                        }


                    } else {
                        Log.d("dfc", "登录过，已短开！");
                        EMClient.getInstance().logout(true);
                        loginHx();
                    }
                } else {
                    //开始登陆
                    loginHx();
                }

                break;

            default:


                break;
        }

    }

    //登陆环信
    private void loginHx() {

        EMClient.getInstance().login(userBean.getHx_account(), userBean.getHx_password(), new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                if (bean != null && bean.getHx_account() != null) {

                    startActivity(new Intent(getActivity(), ChatActivity.class)
                            .putExtra("userId", bean.getHx_account())
                            .putExtra("APP_user_name", userBean.getHx_nick_name())
                            .putExtra("health_record_id", "")
                            .putExtra("doctor_id", "")
                            .putExtra("consult_record_id", "")
                            .putExtra("to_head_image", userBean.getLegal_hand_img())
                            .putExtra("to_username", "客服"));
                } else {
                    ToastUtils.showToast(getActivity(), "暂时不能咨询");
                }


            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(int code, String message) {
                Log.d("dfc", "登录聊天服务器失败！" + code + message);
            }
        });
    }

    private void showhousekeepDialog(List<ServiceCopeBean> data) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("ServiceCopeBean", (ArrayList<? extends Parcelable>) data);
        if (bean != null) {
            args.putString("ShowImage", bean.getHouse_service_image());
            args.putString("phone", bean.getContact_phone());
        }
        dialogHouseKeepFragment.setArguments(args);
        if (dialogHouseKeepFragment.isAdded()) {
            dialogHouseKeepFragment.dismiss();
        } else {
            dialogHouseKeepFragment.show(getFragmentManager(), "DialogFragment");
        }
    }

    private void sq() {
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
            // 已经获得授权， 可以打电话
            if (bean != null && bean.getContact_phone() != null && !bean.getContact_phone().equals("")) {
                CallPhone(bean.getContact_phone());

            } else {
                ToastUtils.showToast(getActivity(), "暂未获取到此商家号码");
            }
        }
    }

    private void CallPhone(String phonenumber) {
        Intent intent = new Intent(); // 意图对象：动作 + 数据
        intent.setAction(Intent.ACTION_CALL); // 设置动作
        Uri data = Uri.parse("tel:" + phonenumber); // 设置数据
        intent.setData(data);
        startActivity(intent); // 激活Activity组件
    }
}
