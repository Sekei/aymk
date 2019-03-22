package com.live.tv.mvp.activity.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.utils.GlideCircleTransform;
import com.hyphenate.exceptions.HyphenateException;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.GiftBean;
import com.live.tv.bean.LiveBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.activity.live.GiftListDialogFragment;
import com.live.tv.mvp.activity.live.adapter.TCChatMsgListAdapter;
import com.live.tv.mvp.activity.live.bean.MemberAvoidBean;
import com.live.tv.mvp.activity.live.bean.TCChatEntity;
import com.live.tv.mvp.activity.live.bean.TCChatRoomMgr;
import com.live.tv.mvp.activity.live.bean.TCInputTextMsgDialog;
import com.live.tv.mvp.activity.live.bean.TCSimpleUserInfo;
import com.live.tv.mvp.activity.live.ui.DaShangFragmet;
import com.live.tv.mvp.activity.live.ui.DialogDashangPayFragment;
import com.live.tv.mvp.activity.live.ui.DialogGiftPayFragment;
import com.live.tv.mvp.activity.live.ui.MagicTextView;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.home.OrderPayFragment;
import com.live.tv.mvp.fragment.pay.GiveHeartFragment;
import com.live.tv.mvp.presenter.live.LiveRoomPresenter;
import com.live.tv.mvp.view.live.ILiveRoomView;
import com.live.tv.util.CustomDialog;
import com.live.tv.util.payutils.PayHelper;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ysjk.health.iemk.R.id.bt_gift;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class NewLiveRoomFragment extends BaseFragment<ILiveRoomView, LiveRoomPresenter> implements ILiveRoomView, TCChatRoomMgr.TCChatRoomListener, TCInputTextMsgDialog.OnTextSendListener {

    /**
     * 主播id
     */
    protected String anchorId;
    /**
     * 环信聊天室id
     * 50063348924418
     */
    protected String roomId = "";
    /**
     * ucloud直播id
     */
    protected String liveId = "";
    Unbinder unbinder;
    @BindView(R.id.img_camera)
    ImageView imgCamera;
    @BindView(R.id.tv_nums)
    TextView tv_nums;
    int per_nums = 0;
    @BindView(R.id.img_doctor)
    ImageView imgDoctor;
    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.llgiftcontent)
    LinearLayout llgiftcontent;

    @BindView(R.id.ll_dashang)
    LinearLayout ll_dashang;
    @BindView(R.id.bt_send_mess)
    ImageView btSendMess;
    @BindView(bt_gift)
    ImageView btGift;
    @BindView(R.id.bt_bef)
    ImageView btBef;
    @BindView(R.id.tv_shang)
    TextView tvShang;
    @BindView(R.id.im_msg_listview)
    ListView imMsgListview;
    private String play_type = "";

    private LiveBean liveBean;//直播详情
    private Timer timer111 = new Timer();
    private Timer timerDashang = new Timer();
    private List<View> giftViewCollection = new ArrayList<View>();
    private List<View> daShangViewCollection = new ArrayList<View>();
    private TranslateAnimation inAnim;
    private TranslateAnimation outAnim;
    private Handler mHandler = new Handler();
    private ArrayList<TCChatEntity> mArrayListChatEntity = new ArrayList<>();
    private TCSimpleUserInfo simpleUserInfo;
    private TCSimpleUserInfo simpleUserInfoDashang;
    private String pay_type = "";


    private TCChatRoomMgr mTCChatRoomMgr;
    private TCChatMsgListAdapter mChatMsgListAdapter;

    private String dashang_paice_str = "";
    private DialogDashangPayFragment dialogDashangPayFragment;

    public NewLiveRoomFragment.fragmentViewOnclick getFragmentViewOnclick() {
        return fragmentViewOnclick;
    }

    public void setFragmentViewOnclick(NewLiveRoomFragment.fragmentViewOnclick fragmentViewOnclick) {
        this.fragmentViewOnclick = fragmentViewOnclick;
    }

    private fragmentViewOnclick fragmentViewOnclick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public LiveRoomPresenter createPresenter() {
        return new LiveRoomPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @OnClick({R.id.img_close, R.id.img_camera, R.id.bt_send_mess, bt_gift, R.id.bt_bef, R.id.tv_shang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                //关闭时退出聊天室
                cancelDialog(view.getId());
                break;
            case R.id.img_camera:
                fragmentViewOnclick.viewOnclick(view.getId());
                break;

            case R.id.bt_send_mess:
                showInputMsgDialog();
                break;
            case bt_gift:

                if (giftBeenlist!=null&&giftBeenlist.size()>0){
                    showGiftDialog();
                }

                break;
            case R.id.bt_bef://美颜
                fragmentViewOnclick.viewOnclick(view.getId());
                break;
            case R.id.tv_shang:
                startEditMoneyFragmet("");
                break;

        }
    }


    public static NewLiveRoomFragment newInstance(String play_type, String roomId, String liveId) {
        Bundle args = new Bundle();
        NewLiveRoomFragment fragment = new NewLiveRoomFragment();
        fragment.play_type = play_type;
        fragment.roomId = roomId;
        fragment.liveId = liveId;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_new_live_room;
    }

    @Override
    public void initUI() {
        if (play_type.equals("push")) {
            imgCamera.setVisibility(View.VISIBLE);
            btGift.setVisibility(View.GONE);
            btBef.setVisibility(View.VISIBLE);
            tvShang.setVisibility(View.GONE);
        } else {
            imgCamera.setVisibility(View.GONE);
            btGift.setVisibility(View.VISIBLE);
            btBef.setVisibility(View.GONE);
            tvShang.setVisibility(View.VISIBLE);
        }

        //初始化消息回调
        mTCChatRoomMgr = TCChatRoomMgr.getInstance();
        mTCChatRoomMgr.setMessageListener(this);
        mTCChatRoomMgr.joinGroup(roomId);


        mInputTextMsgDialog = new TCInputTextMsgDialog(getActivity(), R.style.InputDialog);
        mInputTextMsgDialog.setmOnTextSendListener(this);

        //打赏回调
        dialogDashangPayFragment = new DialogDashangPayFragment();
        dialogDashangPayFragment.setOnSelectListener(new DialogDashangPayFragment.SelectListener() {
            @Override
            public void OnPay(String price, String pay_types) {
                pay_type = pay_types;
                dashang_paice_str = price;
                //执行支付代码

                if ("alipay".equals(pay_type)) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("order_actual_price", price);
                    map.put("channel", pay_type);
                    map.put("doctor_id", liveBean.getDoctorBean().getDoctor_id());
                    map.put("type", "android");
                    getPresenter().getReward(map);


                } else if ("wx".equals(pay_type)) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("order_actual_price", price);
                    map.put("channel", pay_type);
                    map.put("doctor_id", liveBean.getDoctorBean().getDoctor_id());
                    map.put("type", "android");
                    getPresenter().getReward(map);

                } else if ("balance_pay".equals(pay_type)) {
                    //余额支付  密码
                    startOrderPayFragmentFragment(price);

                }


                dialogDashangPayFragment.dismiss();

            }
        });
    }

    @Override
    public void initData() {

        //获取医生信息，即直播详情
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("live_id", liveId);
        getPresenter().getLiveInfo(map);
        getPresenter().getLiveGift(map);
        getPresenter().getUserDetail(map);


        mChatMsgListAdapter = new TCChatMsgListAdapter(getActivity(), imMsgListview, mArrayListChatEntity);
        imMsgListview.setAdapter(mChatMsgListAdapter);

        initSongli();
    }

    @Override
    public void onResume() {
        super.onResume();


    }


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onPause() {
        super.onPause();

    }


    ///加入直播间和退出直播的使用的参数
    private void joinAndOutRoom() {

        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("live_id", liveId);
    }


    @Override
    public void inTheLiveRoom(String data) {

    }

    @Override
    public void outTheLiveRoom(String data) {

    }

    /**
     * 获取直播详情
     *
     * @param data
     */
    @Override
    public void getLiveInfo(LiveBean data) {

        if (data != null) {
            liveBean = data;
            setLiveInfo();

        }
    }

    @Override
    public void getLiveGift(List<GiftBean> data) {

        if (data != null) {

            giftBeenlist = data;
        }
    }

    /**
     * 打开页面第一次送礼物的时，需要验证支付密码
     *
     * @param data
     */
    @Override
    public void getAvoidPassword(MemberAvoidBean data) {
        member_avoid_password = data.getMember_avoid_password();
        dialogGiftPayFragment.dismiss();
    }

    /**
     * 送礼物返回成功数据
     *
     * @param data
     */
    @Override
    public void sendGift(String data) {

        if (data != null) {

            mTCChatRoomMgr.sendGiftMessage(giftBean);
            simpleUserInfo.setGift_id(giftBean.getGift_id());
            simpleUserInfo.setGiftimg(giftBean.getGift_img());
            simpleUserInfo.setGiftname(giftBean.getGift_name());
            simpleUserInfo.setHeadpic(Constants.BASE_URL + userBean.getMember_head_image());

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showGift(giftBean.getGift_id(), simpleUserInfo);
                    TCChatEntity entity = new TCChatEntity();
                    entity.setSenderName("我");
                    entity.setContext("送了一个" + giftBean.getGift_name() + "");
                    entity.setType(Constants.TEXT_TYPE);
                    notifyMsg(entity);

                }
            });
        }

    }

    /**
     * 打赏支付
     *
     * @param data
     */
    @Override
    public void getReward(String data) {

        if (pay_type.equals("balance_pay")) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTCChatRoomMgr.sendDashangMessage("打赏主播", "¥" + dashang_paice_str);
                    simpleUserInfoDashang.setDashang_price("¥" + dashang_paice_str);
                    showDashang(userBean.getMember_id(), simpleUserInfoDashang);
                    TCChatEntity entity = new TCChatEntity();
                    entity.setSenderName("我");
                    entity.setContext("打赏主播" + "¥" + dashang_paice_str);
                    entity.setType(Constants.TEXT_TYPE);
                    notifyMsg(entity);
                }
            });


        } else {

            PayHelper.getInstance(context).alipayAndWxPay(getActivity(), pay_type, data)
                    .setIPayListener(new PayHelper.IPayListener() {
                        @Override
                        public void onSuccess() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTCChatRoomMgr.sendDashangMessage("打赏主播", "¥" + dashang_paice_str);
                                    simpleUserInfoDashang.setDashang_price("¥" + dashang_paice_str);
                                    showDashang(userBean.getMember_id(), simpleUserInfoDashang);
                                    TCChatEntity entity = new TCChatEntity();
                                    entity.setSenderName("我");
                                    entity.setContext("打赏主播" + "¥" + dashang_paice_str);
                                    entity.setType(Constants.TEXT_TYPE);
                                    notifyMsg(entity);
                                }
                            });
                        }

                        @Override
                        public void onFail() {

                        }

                        @Override
                        public void onCancel() {

                        }
                    });
        }


    }

    private UserBean newUserBean;

    @Override
    public void onGetUserDetail(UserBean data) {

        if (data != null) {

            newUserBean = data;
        }
    }

    /**
     * 设置医生即主播基本信息
     */
    private void setLiveInfo() {
        Glide.with(getContext()).load(Constants.BASE_URL + liveBean.getDoctorBean().getDoctor_head_image())
                .placeholder(R.drawable.ava_defaultx)
                .error(R.drawable.ava_defaultx)
                .transform(new GlideCircleTransform(getContext()))
                .into(imgDoctor);
        tvDoctorName.setText(liveBean.getDoctorBean().getDoctor_name() + "  " + liveBean.getDoctorBean().getDoctor_department());
        tvHospital.setText(liveBean.getDoctorBean().getDoctor_hospital());
    }


    //输入打赏金额
    public void startEditMoneyFragmet(String s) {
        DaShangFragmet editMoneyFragmet = DaShangFragmet.newInstance(s);
        editMoneyFragmet.setOkClickListener(new DaShangFragmet.OKOnclickListener() {
            @Override
            public void onOk(String state) {

                showDashangPayDialog(state);
            }
        });
        editMoneyFragmet.show(getChildFragmentManager(), NewLiveRoomFragment.class.getSimpleName());
    }


    /**
     * 显示打赏支付dialog
     *
     * @param type
     */
    private void showDashangPayDialog(String type) {
        Bundle args = new Bundle();
        args.putString("price", type);
        dialogDashangPayFragment.setArguments(args);
        if (dialogDashangPayFragment.isAdded()) {
            dialogDashangPayFragment.dismiss();
        } else {
            dialogDashangPayFragment.show(getFragmentManager(), "dialogDashangPayFragment");
        }
    }

    public void startOrderPayFragmentFragment(final String price) {
        OrderPayFragment orderPayFragment = OrderPayFragment.newInstance(price);
        orderPayFragment.setOkClickListener(new OrderPayFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("order_actual_price", price);
                map.put("channel", "balance_pay");
                map.put("doctor_id", liveBean.getDoctorBean().getDoctor_id());
                map.put("type", "android");
                map.put("member_pay_password", state);
                getPresenter().getReward(map);

            }

            @Override
            public void cancel() {
                ToastUtils.showToast(context.getApplicationContext(), "支付取消");
            }
        });
        orderPayFragment.show(getChildFragmentManager(), GiveHeartFragment.class.getSimpleName());

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        if (e.getMessage().equals("余额不足")) {

            ToastUtils.showToast(context.getApplicationContext(), "余额不足");
        } else {
            errorHandle(e);
        }
    }

    /**
     * 加入房间
     *
     * @param code 错误码，成功时返回0，失败时返回相应错误码
     * @param msg  返回信息，成功时返回群组Id，失败时返回相应错误信息
     */

    @Override
    public void onJoinGroupCallback(int code, String msg) {


        if (code==0){

            try {
                per_nums = EMClient.getInstance().chatroomManager().fetchChatRoomFromServer(roomId).getMemberCount();
            } catch (HyphenateException e) {
                e.printStackTrace();
            }

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_nums.setText("观看人数：" + per_nums + "");

                }
            });
        }
    }

    /**
     * 发送消息是否成功
     *
     * @param code       错误码，成功时返回0，失败时返回相应错误码
     * @param timMessage 发送的TIM消息
     */
    @Override
    public void onSendMsgCallback(int code, EMMessage timMessage) {

        Log.i("dfc", "onSendMsgCallback: " + code);
    }

    /**
     * 接收消息
     *
     * @param type     消息类型
     * @param userInfo 发送者信息
     * @param content  内容
     */
    @Override
    public void onReceiveMsg(int type, TCSimpleUserInfo userInfo, String content) {
        switch (type) {
            case Constants.IMCMD_ENTER_LIVE:
                handleMemberJoinMsg(userInfo);//进入房间
                break;
            case Constants.IMCMD_EXIT_LIVE:
                handleMemberQuitMsg(userInfo);//退出房间
                break;
            case Constants.IMCMD_EXIT_ZHUBO:
                // handleZBQuitMsg(userInfo);
                break;
            case Constants.IMCMD_PRAISE:
                //  handlePraiseMsg(userInfo);
                break;
            case Constants.IMCMD_PAILN_TEXT:
                handleTextMsg(userInfo, content);
                break;
            case Constants.IMCMD_DANMU:
                // handleDanmuMsg(userInfo, content);
                break;
            case Constants.IMCMD_GIFT:
                //handleDanmuMsg(userInfo, content);
                handleGiFtMsg(userInfo, content);
                break;
            case Constants.IMCMD_DASHANG:
                handleDashangMsg(userInfo, content);
                break;


            default:
                break;
        }


    }

    /**
     * 发送文字消息
     *
     * @param msg
     * @param tanmuOpen
     */
    @Override
    public void onTextSend(String msg, boolean tanmuOpen) {

        if (msg.length() == 0)
            return;
        try {
            byte[] byte_num = msg.getBytes("utf8");
            if (byte_num.length > 160) {
                ToastUtils.showToast(getActivity(), "请输入内容");
                return;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }

        TCChatEntity entity = new TCChatEntity();
        entity.setSenderName("我");
        entity.setContext(msg);
        entity.setType(Constants.TEXT_TYPE);
        notifyMsg(entity);
        mTCChatRoomMgr.sendTextMessage(msg);


    }


    /**
     * 文字
     *
     * @param userInfo
     * @param text
     */
    public void handleTextMsg(TCSimpleUserInfo userInfo, String text) {
        TCChatEntity entity = new TCChatEntity();
        entity.setSenderName(userInfo.nickname);
        entity.setContext(text);
        entity.setType(Constants.TEXT_TYPE);
        notifyMsg(entity);


    }

    public void handleMemberJoinMsg(TCSimpleUserInfo userInfo) {

        TCChatEntity entity = new TCChatEntity();
        entity.setSenderName("通知");
        if (userInfo.nickname.equals("")) {
            entity.setContext(userInfo.userid + " 进入了直播间");
        } else {
            entity.setContext(userInfo.nickname + " 进入了直播间");
            entity.setType(Constants.MEMBER_ENTER);
            notifyMsg(entity);
        }
        try {
            per_nums = EMClient.getInstance().chatroomManager().fetchChatRoomFromServer(roomId).getMemberCount();
        } catch (HyphenateException e) {
            e.printStackTrace();
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_nums.setText("观看人数：" + per_nums + "");
                joinAndOutRoom();
                getPresenter().inTheLiveRoom(map);

            }
        });


        Log.i("dfc", "进入直播间人数: " + per_nums);

    }

    public void handleMemberQuitMsg(TCSimpleUserInfo userInfo) {

        TCChatEntity entity = new TCChatEntity();
        entity.setSenderName("通知");
        if (userInfo.nickname.equals("")) {
            entity.setContext(userInfo.userid + " 离开了直播");
        } else {
            entity.setContext(userInfo.nickname + " 离开了直播");
            entity.setType(Constants.MEMBER_EXIT);
            notifyMsg(entity);

        }


        try {
            per_nums = EMClient.getInstance().chatroomManager().fetchChatRoomFromServer(roomId).getMemberCount();
        } catch (HyphenateException e) {
            e.printStackTrace();
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_nums.setText("观看人数：" + per_nums + "");
                joinAndOutRoom();
                getPresenter().inTheLiveRoom(map);
            }
        });

        Log.i("dfc", "退出直播间人数: " + per_nums);
    }

    public void handleGiFtMsg(final TCSimpleUserInfo userInfo, String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showGift(userInfo.gift_id, userInfo);
            }
        });

    }

    public void handleDashangMsg(final TCSimpleUserInfo userInfo, String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showDashang(userInfo.getUserid(), userInfo);
            }
        });

    }


    public interface fragmentViewOnclick {

        void viewOnclick(int id);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        timer111.cancel();
        timerDashang.cancel();
        EMClient.getInstance().chatroomManager().leaveChatRoom(roomId);
    }


    private GiftListDialogFragment giftListDialogFragment;
    private List<GiftBean> giftBeenlist = new ArrayList<>();
    private GiftBean giftBean;
    private TCInputTextMsgDialog mInputTextMsgDialog;

    /**
     * 发消息弹出框
     */
    private void showInputMsgDialog() {
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mInputTextMsgDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        mInputTextMsgDialog.getWindow().setAttributes(lp);
        mInputTextMsgDialog.setCancelable(true);
        mInputTextMsgDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mInputTextMsgDialog.show();
    }

    private String member_avoid_password = "";

    /**
     * 显示礼物dialog
     */
    private void showGiftDialog() {
        giftListDialogFragment = GiftListDialogFragment.newInstances(giftBeenlist);

        giftListDialogFragment.setOnclickListener(new GiftListDialogFragment.onclickListener() {
            @Override
            public void addAmount() {


            }

            @Override
            public void send(GiftBean giftBeans) {
                if (member_avoid_password.equals("")) {

                    startGiftPayFragment("0");//打开验证支付密码页面

                } else {

                    double yue = Double.parseDouble(newUserBean.getMember_amount());
                    double gift_price = Double.parseDouble(giftBeans.getGift_price());

                    if (gift_price > yue) {
                        ToastUtils.showToast(context.getApplicationContext(), "余额不足");
                        return;
                    }


                    giftBean = giftBeans;
                    map.clear();
                    map.put("gift_id", giftBean.getGift_id());
                    map.put("live_id", liveId);
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("member_avoid_password", member_avoid_password);
                    map.put("get_id", liveBean.getMember_id());
                    map.put("send_num", "1");
                    map.put("gift_price", giftBean.getGift_price());
                    getPresenter().sendGift(map);

                }


            }
        }).show(this.getChildFragmentManager(), this.getClass().getName());
    }

    /**
     * 定时清除礼物
     */
    private void clearTiming() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (llgiftcontent != null) {
                            int count = llgiftcontent.getChildCount();
                            for (int i = 0; i < count; i++) {
                                View view = llgiftcontent.getChildAt(i);
                                CircleImageView crvheadimage = (CircleImageView) view.findViewById(R.id.crvheadimage);
                                long nowtime = System.currentTimeMillis();
                                long upTime = (Long) crvheadimage.getTag();
                                if ((nowtime - upTime) >= 3000) {
                                    removeGiftView(i);
                                    return;
                                }
                            }
                        }
                    }
                });

            }
        };
        timer111.schedule(task, 0, 3000);
    }


    /**
     * 定时清除打赏
     */
    private void clearTimingDashang() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ll_dashang != null) {
                            int count = ll_dashang.getChildCount();
                            for (int i = 0; i < count; i++) {
                                View view = ll_dashang.getChildAt(i);
                                CircleImageView crvheadimage = (CircleImageView) view.findViewById(R.id.crvheadimage);
                                long nowtime = System.currentTimeMillis();
                                long upTime = (Long) crvheadimage.getTag();
                                if ((nowtime - upTime) >= 3000) {
                                    removeGiftViewDashang(i);
                                    return;
                                }
                            }
                        }
                    }
                });

            }
        };
        timerDashang.schedule(task, 0, 3000);
    }


    /**
     * 添加礼物view,(考虑垃圾回收)
     */
    private View addGiftView() {
        View view = null;
        if (giftViewCollection.size() <= 0) {
            /*如果垃圾回收中没有view,则生成一个*/
            view = LayoutInflater.from(getActivity()).inflate(R.layout.item_gift1, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.topMargin = 10;
            view.setLayoutParams(lp);
            llgiftcontent.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View view) {
                }

                @Override
                public void onViewDetachedFromWindow(View view) {
                    giftViewCollection.add(view);
                }
            });
        } else {
            view = giftViewCollection.get(0);
            giftViewCollection.remove(view);
        }
        return view;
    }

    /**
     * 添加打赏view,(考虑垃圾回收)
     */
    private View addGiftViewDashang() {
        View view = null;
        if (daShangViewCollection.size() <= 0) {
            /*如果垃圾回收中没有view,则生成一个*/
            view = LayoutInflater.from(getActivity()).inflate(R.layout.item_gift2, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.topMargin = 10;
            view.setLayoutParams(lp);
            ll_dashang.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View view) {
                }

                @Override
                public void onViewDetachedFromWindow(View view) {
                    daShangViewCollection.add(view);
                }
            });
        } else {
            view = daShangViewCollection.get(0);
            daShangViewCollection.remove(view);
        }
        return view;
    }

    /**
     * 删除礼物view
     */
    private void removeGiftView(final int index) {
        final View removeView = llgiftcontent.getChildAt(index);
        outAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llgiftcontent.removeViewAt(index);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                removeView.startAnimation(outAnim);
            }
        });
    }

    /**
     * 删除礼物view
     */
    private void removeGiftViewDashang(final int index) {
        final View removeView = ll_dashang.getChildAt(index);
        outAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ll_dashang.removeViewAt(index);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                removeView.startAnimation(outAnim);
            }
        });
    }

    /**
     * 显示礼物的方法
     */
    private void showGift(String tag, final TCSimpleUserInfo bean) {
        try {
            View giftView = llgiftcontent.findViewWithTag(tag);
            if (giftView == null) {/*该用户不在礼物显示列表*/
                if (llgiftcontent.getChildCount() > 2) {/*如果正在显示的礼物的个数超过两个，那么就移除最后一次更新时间比较长的*/
                    View giftView1 = llgiftcontent.getChildAt(0);
                    CircleImageView picTv1 = (CircleImageView) giftView1.findViewById(R.id.crvheadimage);
                    long lastTime1 = (Long) picTv1.getTag();
                    View giftView2 = llgiftcontent.getChildAt(1);
                    CircleImageView picTv2 = (CircleImageView) giftView2.findViewById(R.id.crvheadimage);
                    long lastTime2 = (Long) picTv2.getTag();
                    if (lastTime1 > lastTime2) {/*如果第二个View显示的时间比较长*/
                        removeGiftView(1);
                    } else {/*如果第一个View显示的时间长*/
                        removeGiftView(0);
                    }
                }

                giftView = addGiftView();/*获取礼物的View的布局*/
                giftView.setTag(tag);/*设置view标识*/
                TextView textView = (TextView) giftView.findViewById(R.id.username);
                TextView gift_name = (TextView) giftView.findViewById(R.id.gift_name);
                textView.setText(bean.nickname);
                gift_name.setText("送了一个" + bean.giftname + "");
//                TCChatEntity entity = new TCChatEntity();
//                entity.setSenderName("我");
//                entity.setContext("送了一个" + bean.giftname + "");
//                entity.setType(Constants.TEXT_TYPE);
//                notifyMsg(entity);
                CircleImageView crvheadimage = (CircleImageView) giftView.findViewById(R.id.crvheadimage);
                crvheadimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                ImageView crvheadimage_bz = (ImageView) giftView.findViewById(R.id.crvheadimage_bz);
                ImageView ivgift = (ImageView) giftView.findViewById(R.id.ivgift);
                Glide.with(this).load(Constants.BASE_URL + bean.giftimg).into(ivgift);
                Glide.with(this).load(bean.headpic).into(crvheadimage);
                final MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.giftNum);/*找到数量控件*/
                giftNum.setText("x1");/*设置礼物数量*/
                crvheadimage.setTag(System.currentTimeMillis());/*设置时间标记*/
                giftNum.setTag(1);/*给数量控件设置标记*/
                try {

                    llgiftcontent.addView(giftView);/*将礼物的View添加到礼物的ViewGroup中*/
                    llgiftcontent.invalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
              /*刷新该view*/
                giftView.startAnimation(inAnim);/*开始执行显示礼物的动画*/
                inAnim.setAnimationListener(new Animation.AnimationListener() {/*显示动画的监听*/
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        giftNumAnim.start(giftNum);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            } else {/*该用户在礼物显示列表*/
                CircleImageView crvheadimage = (CircleImageView) giftView.findViewById(R.id.crvheadimage);/*找到头像控件*/
                MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.giftNum);/*找到数量控件*/
                int showNum = (Integer) giftNum.getTag() + 1;
                giftNum.setText("x" + showNum);
                giftNum.setTag(showNum);
                crvheadimage.setTag(System.currentTimeMillis());
                giftNumAnim.start(giftNum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示打赏的方法
     */
    private void showDashang(String tag, final TCSimpleUserInfo bean) {
        try {
            View giftView = ll_dashang.findViewWithTag(tag);
            if (giftView == null) {/*该用户不在礼物显示列表*/
                if (ll_dashang.getChildCount() > 2) {/*如果正在显示的礼物的个数超过两个，那么就移除最后一次更新时间比较长的*/
                    View giftView1 = ll_dashang.getChildAt(0);
                    CircleImageView picTv1 = (CircleImageView) giftView1.findViewById(R.id.crvheadimage);
                    long lastTime1 = (Long) picTv1.getTag();
                    View giftView2 = ll_dashang.getChildAt(1);
                    CircleImageView picTv2 = (CircleImageView) giftView2.findViewById(R.id.crvheadimage);
                    long lastTime2 = (Long) picTv2.getTag();
                    if (lastTime1 > lastTime2) {/*如果第二个View显示的时间比较长*/
                        removeGiftView(1);
                    } else {/*如果第一个View显示的时间长*/
                        removeGiftView(0);
                    }
                }

                giftView = addGiftViewDashang();/*获取礼物的View的布局*/
                giftView.setTag(tag);/*设置view标识*/
                TextView textView = (TextView) giftView.findViewById(R.id.username);
                TextView gift_name = (TextView) giftView.findViewById(R.id.gift_name);
                textView.setText(bean.nickname);
                gift_name.setText("打赏主播");
                CircleImageView crvheadimage = (CircleImageView) giftView.findViewById(R.id.crvheadimage);
                crvheadimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                TextView ivgift = (TextView) giftView.findViewById(R.id.ivgift);
                Glide.with(this).load( Constants.BASE_URL+bean.headpic).into(crvheadimage);
                ivgift.setText(bean.getDashang_price());

                final MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.giftNum);/*找到数量控件*/
                giftNum.setText("x1");/*设置礼物数量*/
                crvheadimage.setTag(System.currentTimeMillis());/*设置时间标记*/
                giftNum.setTag(1);/*给数量控件设置标记*/
                try {

                    ll_dashang.addView(giftView);/*将礼物的View添加到礼物的ViewGroup中*/
                    ll_dashang.invalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
              /*刷新该view*/
                giftView.startAnimation(inAnim);/*开始执行显示礼物的动画*/
                inAnim.setAnimationListener(new Animation.AnimationListener() {/*显示动画的监听*/
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        giftNumAnim.start(giftNum);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            } else {/*该用户在礼物显示列表*/
                CircleImageView crvheadimage = (CircleImageView) giftView.findViewById(R.id.crvheadimage);/*找到头像控件*/
                MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.giftNum);/*找到数量控件*/
                int showNum = (Integer) giftNum.getTag() + 1;
                giftNum.setText("x" + showNum);
                giftNum.setTag(showNum);
                crvheadimage.setTag(System.currentTimeMillis());
                giftNumAnim.start(giftNum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 送礼物
     */


    private NumAnim giftNumAnim;

    private void initSongli() {
        simpleUserInfo = new TCSimpleUserInfo(userBean.getMember_id(),
                userBean.getMember_nick_name(),  Constants.BASE_URL+userBean.getMember_head_image());

        simpleUserInfoDashang = new TCSimpleUserInfo(userBean.getMember_id(),
                userBean.getMember_nick_name(),userBean.getMember_head_image());


        inAnim = (TranslateAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.gift_in);
        outAnim = (TranslateAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.gift_out);
        giftNumAnim = new NumAnim();
        clearTiming();
        clearTimingDashang();
    }


    DialogGiftPayFragment dialogGiftPayFragment;

    public void startGiftPayFragment(String s) {
        dialogGiftPayFragment = DialogGiftPayFragment.newInstance(s);
        dialogGiftPayFragment.setOkClickListener(new DialogGiftPayFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("member_pay_password", state);
                getPresenter().getAvoidPassword(map);


            }

            @Override
            public void cancel() {
                ToastUtils.showToast(context.getApplicationContext(), "支付取消");
            }
        });
        dialogGiftPayFragment.show(getFragmentManager(), GiveHeartFragment.class.getSimpleName());

    }

    public class NumAnim {
        private Animator lastAnimator = null;

        public void start(View view) {
            if (lastAnimator != null) {
                lastAnimator.removeAllListeners();
                lastAnimator.end();
                lastAnimator.cancel();
            }
            ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", 1.5f, 1.0f);
            ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY", 1.5f, 1.0f);
            AnimatorSet animSet = new AnimatorSet();
            lastAnimator = animSet;
            animSet.setDuration(200);
            animSet.setInterpolator(new OvershootInterpolator());
            animSet.playTogether(anim1, anim2);
            animSet.start();
        }
    }

    private void notifyMsg(final TCChatEntity entity) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mArrayListChatEntity.size() > 1000) {
                    while (mArrayListChatEntity.size() > 900) {
                        mArrayListChatEntity.remove(0);
                    }
                }
                mArrayListChatEntity.add(entity);
                mChatMsgListAdapter.notifyDataSetChanged();
            }
        });
    }


    /**
     * 关闭页面时，提示信息
     * @param view_id
     */
    public void cancelDialog(final int view_id) {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("正在直播，确认退出？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mTCChatRoomMgr.quitGroup(roomId);
                fragmentViewOnclick.viewOnclick(view_id);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();

    }

}
