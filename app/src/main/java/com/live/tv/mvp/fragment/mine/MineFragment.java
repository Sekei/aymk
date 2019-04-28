package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.MinePresenter;
import com.live.tv.mvp.view.mine.IMineView;
import com.live.tv.util.SpSingleInstance;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ysjk.health.iemk.R.id.my_live;
import static com.ysjk.health.iemk.R.id.yisheng_dangan;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class MineFragment extends BaseFragment<IMineView, MinePresenter> implements IMineView {


    @BindView(R.id.myshouyi)
    TextView myshouyi;
    @BindView(yisheng_dangan)
    TextView yishengDangan;
    @BindView(R.id.zixun_list)
    TextView zixunList;
    @BindView(R.id.healthy_user)
    TextView healthyUser;
    @BindView(my_live)
    TextView myLive;
    Unbinder unbinder;
    @BindView(R.id.tittle)
    TextView tittle;
    @BindView(R.id.message)
    ImageView message;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.integral)
    TextView integral;
    @BindView(R.id.vip)
    TextView vip;
    @BindView(R.id.home_page)
    TextView homePage;
    @BindView(R.id.focusNum)
    TextView focusNum;
    @BindView(R.id.focus)
    TextView focus;
    @BindView(R.id.fansNum)
    TextView fansNum;
    @BindView(R.id.fans)
    TextView fans;
    @BindView(R.id.collectionNum)
    TextView collectionNum;
    @BindView(R.id.collection)
    TextView collection;
    @BindView(R.id.carNum)
    TextView carNum;
    @BindView(R.id.car)
    TextView car;
    @BindView(R.id.healthFile)
    TextView healthFile;
    @BindView(R.id.records)
    TextView records;
    @BindView(R.id.signNum)
    TextView signNum;
    @BindView(R.id.sign)
    TextView sign;
    @BindView(R.id.bg_five)
    TextView bgFive;
    @BindView(R.id.noPay)
    TextView noPay;
    @BindView(R.id.noUse)
    TextView noUse;
    @BindView(R.id.noComment)
    TextView noComment;
    @BindView(R.id.afterSale)
    TextView afterSale;
    @BindView(R.id.myService)
    TextView myService;
    @BindView(R.id.merchantform)
    TextView merchantform;
    @BindView(R.id.merchantmessage)
    TextView merchantmessage;
    @BindView(R.id.my_posted)
    TextView myposted;
    @BindView(R.id.message_list)
    TextView messageList;

    private UserBean userBean;
    private UserBean userBeanNet;
    private Map<String, String> map;

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initUI() {
        map = new HashMap<>();
        myshouyi.setVisibility(View.GONE);
        yishengDangan.setVisibility(View.GONE);
        zixunList.setVisibility(View.GONE);
        healthyUser.setVisibility(View.GONE);
        myLive.setVisibility(View.GONE);
        merchantmessage.setVisibility(View.GONE);
        myposted.setVisibility(View.GONE);
        messageList.setVisibility(View.GONE);
    }

    @Override
    public void initData() {


    }


    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if (userBean != null && !TextUtils.isEmpty(userBean.getMember_id()) && !TextUtils.isEmpty(userBean.getMember_token())) {
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            getPresenter().getUserDetail(map);
        }
    }

    @OnClick({R.id.message1, R.id.name, R.id.avatar, R.id.healthFile,
            R.id.records, R.id.focusNum, R.id.focus, R.id.fansNum, R.id.fans, R.id.collectionNum,
            R.id.myAdvisory, R.id.collection, R.id.carNum, R.id.car, R.id.message,
            R.id.home_page, R.id.bg_two, R.id.join, R.id.sign
            , R.id.myshouyi, yisheng_dangan, R.id.zixun_list, R.id.healthy_user,
            my_live, R.id.myAccount, R.id.allOrder, R.id.myDoctors, R.id.myVip, R.id.noUse
            , R.id.noPay, R.id.noComment, R.id.afterSale, R.id.my_posted, R.id.merchantform, R.id.myService,
            R.id.merchantmessage, R.id.message_list
    })
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.noPay://待使用
                startMyOrderFragment("service", "2");
                break;

            case R.id.noUse://待收货
                startMyOrderFragment("good", "3");
                break;

            case R.id.noComment://待评价
                startMyOrderFragment("good", "4");
                break;

            case R.id.afterSale://售后
                startAfterSaleOrderListFragment();
                break;

            case R.id.name:
            case R.id.avatar:
                //个人资料
                startPersonalnfoFragment();
                break;
            case R.id.myVip:

                startMyVipragment();
                break;

            case R.id.allOrder:
                //所有订单
                startMyOrderFragment("", "");
                break;
            case R.id.myAccount:
                //我的账户
                startMyAccountFragment();
                break;
            case R.id.myDoctors:
                //我的医生
                startMyDoctorFragment();
                break;
            case R.id.join:
                //合作加盟
                startJoinFragment();
                break;
            case R.id.message:
                startMsgFragment();
                break;
            case R.id.bg_two:
            case R.id.home_page:
                // startPersonalHomePageFragment();
                break;
            case R.id.healthFile:
                //健康档案
                startHeartFileFragment();
                break;
            case R.id.records:
                //电子病历
                startMedicalRecordsHxFragment();
                break;
            case R.id.message1:
                //设置
                startSettingFragment();
                break;
            case R.id.focusNum:
            case R.id.focus:
                //关注
                startFocusFragment();
                break;
            case R.id.fans:
            case R.id.fansNum:
                //粉丝
                startFansFragment();
                break;
            case R.id.collectionNum:
            case R.id.collection:
                //收藏
                startMyCollectionFragment();
                break;
            case R.id.carNum:
            case R.id.car:
                startCartFragment();
                break;
            case R.id.myAdvisory:
                //我的咨询
                startMyAdvisoryFragment();
                break;

            case R.id.myshouyi://我的收益

                startMyProfitFragment();
                break;
            case yisheng_dangan://医生档案

                startDoctorFileFragment();
                break;
            case R.id.zixun_list://咨询列表
                startDoctorConsultationFragment();
                break;
            case R.id.healthy_user://健康用户
                startHealthUserFragment();
                break;
            case my_live://我的直播
                startMyLiveListFragment();
                break;

            case R.id.sign://签到
                Map<String, String> mMap = new HashMap<>();
                mMap.put("member_id", userBean.getMember_id());
                mMap.put("member_token", userBean.getMember_token());
                getPresenter().getSign(mMap);
                break;
            case R.id.my_posted:
                startPostedListFragment();
                break;
            case R.id.merchantform:
                startformtFragment("1");//商家订单
                break;
            case R.id.myService:

                startformtFragment("2");//我的服务

                break;
            case R.id.merchantmessage:
                //商家消息
                startMerchantmessagefragment();
                break;

            case R.id.message_list:
                startMessageListFragment();
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
    public MinePresenter createPresenter() {
        return new MinePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onGetUserDetail(UserBean data) {

        if (data != null) {
            userBeanNet = data;
            bindData();
            SpSingleInstance.getSpSingleInstance().setUserBean(data);
            saveUser(data);
        }

    }

    @Override
    public void onSign(String data) {

        ToastUtils.showToast(context.getApplicationContext(), data);
        sign.setText("今日已签到");
        sign.setTextColor(getResources().getColor(R.color.colorAccent));
        if (userBean != null) {
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            getPresenter().getUserDetail(map);
        }

    }


    private void bindData() {

        if (userBean.getHouseService_type().equals("1")) {
            merchantmessage.setVisibility(View.VISIBLE);
        } else {

            merchantmessage.setVisibility(View.GONE);

        }


        if ("1".equals(userBean.getDoctor_type())) {
            myshouyi.setVisibility(View.VISIBLE);
            yishengDangan.setVisibility(View.VISIBLE);
            zixunList.setVisibility(View.VISIBLE);
            healthyUser.setVisibility(View.VISIBLE);
            myLive.setVisibility(View.VISIBLE);
            myposted.setVisibility(View.VISIBLE);
            //messageList.setVisibility(View.VISIBLE);
        } else {
            myshouyi.setVisibility(View.GONE);
            yishengDangan.setVisibility(View.GONE);
            zixunList.setVisibility(View.GONE);
            healthyUser.setVisibility(View.GONE);
            myLive.setVisibility(View.GONE);
            myposted.setVisibility(View.GONE);
            messageList.setVisibility(View.GONE);
        }


        vip.setText(userBean.getAssociator_type());


        Glide.with(context).load(Constants.BASE_URL + userBeanNet.getMember_head_image())
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(avatar);

        name.setText(userBeanNet.getMember_nick_name());
        integral.setText(getString(R.string.txt_integral) + userBeanNet.getIntegral_value());

        if ("1".equals(userBeanNet.getIs_sign())) {

            sign.setText("今日已签到");
            sign.setTextColor(getResources().getColor(R.color.colorAccent));
            signNum.setText(userBeanNet.getSignNum());
        } else {

            sign.setText("未签到");
            signNum.setText(userBeanNet.getSignNum());
        }


        focusNum.setText(userBeanNet.getFollow_num());
        fansNum.setText(userBeanNet.getFans_num());
        collectionNum.setText(userBeanNet.getCollection_num());
        carNum.setText(userBeanNet.getShop_car_num());
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
}
