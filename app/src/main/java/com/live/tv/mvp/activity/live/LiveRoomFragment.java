package com.live.tv.mvp.activity.live;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
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
import com.live.tv.mvp.activity.live.bean.MemberAvoidBean;
import com.live.tv.mvp.activity.live.bean.TCChatEntity;
import com.live.tv.mvp.activity.live.bean.TCSimpleUserInfo;
import com.live.tv.mvp.activity.live.ui.MagicTextView;
import com.live.tv.mvp.activity.live.widget.RoomMessagesView;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.live.LiveRoomPresenter;
import com.live.tv.mvp.view.live.ILiveRoomView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class LiveRoomFragment extends BaseFragment<ILiveRoomView, LiveRoomPresenter> implements ILiveRoomView {

    /**
     * 聊天室布局
     */
//    @BindView(R.id.message_view)
    RoomMessagesView messageView;
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

    long cur_time = 0;
    protected EMChatRoom chatroom;
    protected EMChatRoomChangeListener chatRoomChangeListener;


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
    private String play_type = "";

    private LiveBean liveBean;//直播详情
    private Timer timer111 = new Timer();
    private List<View> giftViewCollection = new ArrayList<View>();
    private TranslateAnimation inAnim;
    private TranslateAnimation outAnim;
    private Handler mHandler = new Handler();
    private ArrayList<TCChatEntity> mArrayListChatEntity = new ArrayList<>();
    private TCSimpleUserInfo simpleUserInfo;
    public LiveRoomFragment.fragmentViewOnclick getFragmentViewOnclick() {
        return fragmentViewOnclick;
    }

    public void setFragmentViewOnclick(LiveRoomFragment.fragmentViewOnclick fragmentViewOnclick) {
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

    @OnClick({R.id.img_close, R.id.img_camera,R.id.img_liwu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                //关闭时退出聊天室
                EMClient.getInstance().chatroomManager().leaveChatRoom(roomId);
                EMMessage message = EMMessage.createTxtSendMessage("退出直播间", roomId);
                message.setChatType(EMMessage.ChatType.ChatRoom);
                message.setAttribute("message_type", "join");
                message.setAttribute("username", userBean.getMember_nick_name());
                EMClient.getInstance().chatManager().sendMessage(message);
                fragmentViewOnclick.viewOnclick(view.getId());
                break;
            case R.id.img_camera:
                fragmentViewOnclick.viewOnclick(view.getId());
                break;
            case R.id.img_liwu:
                showGiftDialog();
                break;
        }
    }


    public static LiveRoomFragment newInstance(String play_type, String roomId, String liveId) {
        Bundle args = new Bundle();
        LiveRoomFragment fragment = new LiveRoomFragment();
        fragment.play_type = play_type;
        fragment.roomId = roomId;
        fragment.liveId = liveId;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_live_room;
    }

    @Override
    public void initUI() {
        if (play_type.equals("push")) {
            imgCamera.setVisibility(View.VISIBLE);
        } else {
            imgCamera.setVisibility(View.GONE);
        }

        messageView = findView(R.id.message_view);

    }

    @Override
    public void initData() {
        joinChatRoom();//加入房间
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        //获取医生信息，即直播详情

        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("live_id", liveId);
        getPresenter().getLiveInfo(map);





        for (int i = 0; i <30 ; i++) {
            GiftBean g=new GiftBean();
            g.setGift_id("1");
            g.setGift_price("2");
            g.setImg("23232");
            g.setIs_running("1");
            giftBeenlist.add(g);
        }


        initSongli();
    }

    @Override
    public void onResume() {
        super.onResume();


        Log.i("dfc", "onResume: " + messageView.toString());
    }


    @Override
    public void onStart() {
        super.onStart();

        Log.i("dfc", "onStart: " + messageView.toString());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("dfc", "onPause: " + messageView.toString());
    }


    /**
     * 初始化消息
     */
    protected void onMessageListInit() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageView.init(roomId);
                messageView.setMessageViewListener(new MessageViewListener() {
                    @Override
                    public void onMessageSend(final String content) {

                        final EMMessage message = EMMessage.createTxtSendMessage(content, roomId);
                        message.setMessageStatusCallback(new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                //刷新消息列表
                                //messageView.refreshSelectLast();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        messageView.addData(message);
                                    }
                                });

                            }

                            @Override
                            public void onError(int i, String s) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtils.showToast(getActivity(), "消息发送失败");
                                    }
                                });

                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });


                        message.setAttribute("barrage", "");
                        message.setChatType(EMMessage.ChatType.ChatRoom);
                        message.setAttribute("username", userBean.getMember_nick_name());
//                        message.setAttribute("userimg", userBean.getImg());
//                        message.setAttribute("sex", userBean.getSex());
//                        message.setAttribute("user_id", userBean.getUser_id());
//                        message.setAttribute("usergrade", userBean.getGrade());

                        EMClient.getInstance().chatManager().sendMessage(message);

                    }

                    @Override
                    public void onHiderBottomBar() {

                    }

                    @Override
                    public void onItemClickListener(int id, EMMessage message) {

                    }

                    @Override
                    public void onLoadMore() {

                    }
                });

            }
        });
    }

    /**
     * 加入聊天室
     */
    public void joinChatRoom() {

        EMClient.getInstance().chatroomManager().joinChatRoom(roomId, new EMValueCallBack<EMChatRoom>() {
            @Override
            public void onSuccess(EMChatRoom emChatRoom) {

                Log.i("dfc", "加入房间成功");
//
//
//                cur_time = System.currentTimeMillis();
//                chatroom = emChatRoom;
                addChatRoomChangeListenr();
                onMessageListInit();
////

                EMMessage message = EMMessage.createTxtSendMessage("进入了直播间", roomId);
                message.setChatType(EMMessage.ChatType.ChatRoom);
                message.setAttribute("message_type", "join");
                message.setAttribute("username", userBean.getMember_nick_name());
//                if (userBean.getHx_account() != null) {
//                    message.setFrom(userBean.getHx_account());
//                }
//                message.setAttribute("Hx_account", userBean.getHx_account());
                EMClient.getInstance().chatManager().sendMessage(message);


                try {
                    per_nums = EMClient.getInstance().chatroomManager().fetchChatRoomFromServer(roomId).getMemberCount();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }

                tv_nums.setText("观看人数：" + per_nums + "");

                //messageView.notifyDataSetChanged();

            }

            @Override
            public void onError(int i, String s) {

                Log.i("dfc", "加入房间错误信息onError: " + s);
            }
        });

    }


    /**
     * 创建聊天室监听事件并监听
     * <p>
     * 包括：
     * 新成员加入、退出，
     * 聊天室的解散、
     * 成员被移除直播间
     */
    protected void addChatRoomChangeListenr() {
        chatRoomChangeListener = new EMChatRoomChangeListener() {
            @Override
            public void onChatRoomDestroyed(String s, String s1) {

            }

            @Override
            public void onMemberJoined(String s, String s1) {

                joinAndOutRoom();

                Log.i("dfc", "onMemberJoined: " + "s=====" + s + "s1======" + s1);

                getPresenter().inTheLiveRoom(map);

                try {
                    per_nums = EMClient.getInstance().chatroomManager().fetchChatRoomFromServer(roomId).getMemberCount();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }

                tv_nums.setText("观看人数：" + per_nums + "");

            }

            @Override
            public void onMemberExited(String s, String s1, String s2) {
                Log.i("dfc", "onMemberExited: " + "s=====" + s + "s1======" + s1 + "s2====" + s2);
                joinAndOutRoom();
                getPresenter().outTheLiveRoom(map);


                try {
                    per_nums = EMClient.getInstance().chatroomManager().fetchChatRoomFromServer(roomId).getMemberCount();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }

                tv_nums.setText("观看人数：" + per_nums + "");
            }

            @Override
            public void onRemovedFromChatRoom(String s, String s1, String s2) {

            }

            @Override
            public void onMuteListAdded(String s, List<String> list, long l) {

            }

            @Override
            public void onMuteListRemoved(String s, List<String> list) {

            }

            @Override
            public void onAdminAdded(String s, String s1) {

            }

            @Override
            public void onAdminRemoved(String s, String s1) {

            }

            @Override
            public void onOwnerChanged(String s, String s1, String s2) {

            }

            @Override
            public void onAnnouncementChanged(String s, String s1) {

            }
        };
        EMClient.getInstance().chatroomManager().addChatRoomChangeListener(chatRoomChangeListener);
    }


    ///加入直播间和退出直播的使用的参数
    private void joinAndOutRoom() {

        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("live_id", liveId);
    }


    /**
     * 消息接收监听
     * 普通消息 扩展消息
     */
    EMMessageListener msgListener = new EMMessageListener() {
        @Override
        public void onMessageReceived(final List<EMMessage> list) {

            for (EMMessage message : list) {
                messageView.addData(message);
            }


        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        @Override
        public void onMessageRecalled(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {
            messageView.addData(emMessage);
        }
    };

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

    /**
     *礼物列表
     * @param data
     */
    @Override
    public void getLiveGift(List<GiftBean> data) {

        if (data!=null){

            giftBeenlist=data;


        }

    }

    @Override
    public void getAvoidPassword(MemberAvoidBean data) {

    }

    @Override
    public void sendGift(String data) {

    }

    @Override
    public void getReward(String data) {

    }

    @Override
    public void onGetUserDetail(UserBean data) {

    }

    /**
     * 设置医生即主播基本信息
     */
    private void setLiveInfo() {
        Glide.with(getContext()).load(Constants.BASE_URL+liveBean.getDoctorBean().getDoctor_head_image())
                .placeholder(R.drawable.ava_defaultx)
                .error(R.drawable.ava_defaultx)
                .transform(new GlideCircleTransform(getContext()))
                .into(imgDoctor);
        tvDoctorName.setText(liveBean.getDoctorBean().getDoctor_name()+"  "+liveBean.getDoctorBean().getDoctor_department());
        tvHospital.setText(liveBean.getDoctorBean().getDoctor_hospital());
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


    public interface fragmentViewOnclick {

        void viewOnclick(int id);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        EMClient.getInstance().chatroomManager().leaveChatRoom(roomId);
    }


    private GiftListDialogFragment giftListDialogFragment;
    private List<GiftBean> giftBeenlist = new ArrayList<>();
    private GiftBean giftBean;




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
                giftBean = giftBeans;

                map.put("gift_id", giftBean.getGift_id());
                map.put("live_id", liveId);
                map.put("member_id", userBean.getMember_id());
               // getPresenter().songli(map);


                simpleUserInfo.setGift_id(giftBean.getGift_id());
                simpleUserInfo.setGiftimg(giftBean.getImg());
                simpleUserInfo.setGiftname(giftBean.getGift_name());
               getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showGift(giftBean.getGift_id(), simpleUserInfo);
                    }
                });



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
                TCChatEntity entity = new TCChatEntity();
                entity.setSenderName("我");
                entity.setContext("送了一个" + bean.giftname + "");
                entity.setType(Constants.TEXT_TYPE);
               // notifyMsg(entity);
                CircleImageView crvheadimage = (CircleImageView) giftView.findViewById(R.id.crvheadimage);
                crvheadimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                ImageView crvheadimage_bz = (ImageView) giftView.findViewById(R.id.crvheadimage_bz);
                ImageView ivgift = (ImageView) giftView.findViewById(R.id.ivgift);
                Glide.with(this).load(bean.giftimg).into(ivgift);
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
     * 送礼物
     */


    private NumAnim giftNumAnim;
    private void initSongli() {
        simpleUserInfo = new TCSimpleUserInfo(userBean.getMember_id(),
                userBean.getMember_nick_name(), userBean.getMember_head_image());
        inAnim = (TranslateAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.gift_in);
        outAnim = (TranslateAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.gift_out);
        giftNumAnim = new NumAnim();
        clearTiming();
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





//    private void notifyMsg(final TCChatEntity entity) {
//
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (mArrayListChatEntity.size() > 1000) {
//                    while (mArrayListChatEntity.size() > 900) {
//                        mArrayListChatEntity.remove(0);
//                    }
//                }
//                mArrayListChatEntity.add(entity);
//                mChatMsgListAdapter.notifyDataSetChanged();
//            }
//        });
//    }


}
