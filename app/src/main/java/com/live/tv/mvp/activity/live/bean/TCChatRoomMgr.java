package com.live.tv.mvp.activity.live.bean;

import android.text.TextUtils;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.live.tv.Constants;
import com.live.tv.bean.GiftBean;
import com.live.tv.bean.UserBean;
import com.live.tv.util.SpSingleInstance;

import java.util.List;

import static com.live.tv.Constants.IMCMD_DANMU;
import static com.live.tv.Constants.IMCMD_DASHANG;
import static com.live.tv.Constants.IMCMD_ENTER_LIVE;
import static com.live.tv.Constants.IMCMD_EXIT_LIVE;
import static com.live.tv.Constants.IMCMD_GIFT;
import static com.live.tv.Constants.IMCMD_PAILN_TEXT;
import static com.live.tv.Constants.IMCMD_PRAISE;


/**
 * Created by teckjiang on 2016/8/4
 */
public class TCChatRoomMgr implements EMMessageListener {

    /**
     * 环信聊天室id
     */
    protected String roomId = "";
    long cur_time = 0;
    protected EMChatRoom chatroom;
    protected static UserBean userBean;
    protected EMChatRoomChangeListener chatRoomChangeListener;
    public long msgtime;
    private TCChatRoomMgr mTCChatRoomMgr;

    private String action;
    private String tc_username;
    private String tc_id;
    private TCChatRoomListener mTCChatRoomListener;
    private static TCFrequeControl mMsgFreque;

    public void sendGiftMessage(GiftBean giftBeans) {
        sendMessage(Constants.IMCMD_GIFT, giftBeans);
    }

    private TCChatRoomMgr() {

        mMsgFreque = new TCFrequeControl();
        mMsgFreque.init(20, 1);
    }

    private static class TCChatRoomMgrHolder {
        private static TCChatRoomMgr instance = new TCChatRoomMgr();
    }

    public static TCChatRoomMgr getInstance() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        return TCChatRoomMgrHolder.instance;
    }

    public void sendTextMessage(String msg) {
        sendMessage(IMCMD_PAILN_TEXT, msg);
    }


    public void sendDashangMessage(String content,String price) {
        sendDashangMessages(content, price);
    }

    public void sendDanmuMessage(String msg) {
        sendMessage(IMCMD_DANMU, msg);
    }

    public void sendPraiseMessage() {
        sendMessage(IMCMD_PRAISE, "点赞");
    }

    /*public void sendGiftMessage(GiftBeans giftBeans) {
        sendMessage(IMCMD_GIFT, giftBeans);
    }*/


    /**
     * 加入聊天室
     */
    public void joinGroup(final String mRoomId) {

        roomId = mRoomId;
        EMClient.getInstance().chatroomManager().joinChatRoom(roomId, new EMValueCallBack<EMChatRoom>() {
            @Override
            public void onSuccess(EMChatRoom emChatRoom) {
                Log.i("dfc", "onSuccess: "+"加入聊天室成功");
                cur_time = System.currentTimeMillis();
                chatroom = emChatRoom;
                addChatRoomChangeListenr();

                if (null != mTCChatRoomListener)
                    mTCChatRoomListener.onJoinGroupCallback(0, roomId);

                EMMessage message = EMMessage.createTxtSendMessage("进入了直播间", roomId);
                message.setFrom(userBean.getHx_account());
                message.setChatType(EMMessage.ChatType.ChatRoom);
                message.setAttribute("username", userBean.getMember_nick_name());
                message.setAttribute("sex", userBean.getMember_sex());
                message.setAttribute("barrage", "");
                message.setAttribute("user_id", userBean.getMember_id());
                message.setAttribute("userimg", userBean.getMember_head_image());
                message.setAttribute("intoroom", "1");
                EMClient.getInstance().chatManager().sendMessage(message);

               /* mTCChatRoomListener.onReceiveMsg(IMCMD_PAILN_TEXT, new TCSimpleUserInfo(userBean.getUser_id(),
                        userBean.getUsername(), userBean.getImg()), "进入了直播间");*/

            }

            @Override
            public void onError(int i, String s) {

                Log.i("dfc", "onError: "+s);
            }
        });
    }

    public void quitGroup(final String mRoomId) {
        roomId = mRoomId;

        EMMessage message = EMMessage.createTxtSendMessage("离开了直播间", roomId);
        message.setFrom(userBean.getHx_account());
        message.setChatType(EMMessage.ChatType.ChatRoom);
        message.setAttribute("username", userBean.getMember_nick_name());
        message.setAttribute("sex", userBean.getMember_sex());
        message.setAttribute("barrage", "");
        message.setAttribute("user_id", userBean.getMember_id());
        message.setAttribute("userimg", userBean.getMember_head_image());
        message.setAttribute("intoroom", "0");
        message.setAttribute("leaveChatroom", "1");
        EMClient.getInstance().chatManager().sendMessage(message);

        EMClient.getInstance().chatroomManager().leaveChatRoom(roomId);
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
            public void onChatRoomDestroyed(String roomId, String roomName) {

            }

            /**
             * 聊天室加入新成员事件
             *
             * @param roomId
             *          聊天室id
             * @param participant
             *          新成员username  环信账号
             */
            @Override
            public void onMemberJoined(String roomId, String participant) {

            }

            /**
             * 聊天室成员主动退出事件
             *
             * @param roomId
             *          聊天室id
             * @param roomName
             *          聊天室名字
             * @param participant
             *          退出的成员的username
             */
            @Override
            public void onMemberExited(String roomId, String roomName, String participant) {

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


    /**
     * 发送消息
     *
     * @param cmd      控制符（代表不同的消息类型）
     * @param giftBean 参数
     *                 礼物
     * @param
     */
    private void sendMessage(int cmd, GiftBean giftBean) {

        final EMMessage message = EMMessage.createTxtSendMessage("送了主播一个" + giftBean.getGift_name(), roomId);
        message.setChatType(EMMessage.ChatType.ChatRoom);
        message.setAttribute("username", userBean.getMember_nick_name());
        message.setAttribute("user_id", userBean.getMember_id());
        message.setAttribute("userimg", Constants.BASE_URL+userBean.getMember_head_image());
        message.setAttribute("sex", userBean.getMember_sex());
        message.setAttribute("giftimg", giftBean.getGift_img());
        message.setAttribute("barrage", "");
        message.setAttribute("giftprice", giftBean.getGift_price());
        message.setAttribute("gift_id", giftBean.getGift_id());
        message.setAttribute("giftname", giftBean.getGift_name());
        EMClient.getInstance().chatManager().saveMessage(message);
        EMClient.getInstance().chatManager().sendMessage(message);
        message.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                if (null != mTCChatRoomListener)
                    mTCChatRoomListener.onSendMsgCallback(0, message);
            }

            @Override
            public void onError(int i, String s) {
                if (null != mTCChatRoomListener)
                    mTCChatRoomListener.onSendMsgCallback(-1, null);
            }

            @Override
            public void onProgress(int i, String s) {
            }
        });
    }

    /**
     * 发送消息
     *
     * @param cmd     控制符（代表不同的消息类型）
     * @param content 参数
     * @param
     */
    private void sendMessage(int cmd, String content) {

        final EMMessage message = EMMessage.createTxtSendMessage(content, roomId);
        message.setChatType(EMMessage.ChatType.ChatRoom);
        message.setAttribute("username", userBean.getMember_nick_name());
        message.setAttribute("user_id", userBean.getMember_id());
        message.setAttribute("userimg", userBean.getMember_head_image());
        message.setAttribute("sex", userBean.getMember_sex());
        switch (cmd) {
            case IMCMD_PRAISE:
                message.setAttribute("praise", "1");
                break;
            case IMCMD_DANMU:
                message.setAttribute("barrage", "1");
        }
        EMClient.getInstance().chatManager().sendMessage(message);
        message.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                if (null != mTCChatRoomListener)
                    mTCChatRoomListener.onSendMsgCallback(0, message);
            }

            @Override
            public void onError(int i, String s) {
                if (null != mTCChatRoomListener)
                    mTCChatRoomListener.onSendMsgCallback(-1, null);
            }

            @Override
            public void onProgress(int i, String s) {
            }
        });
    }


 /**
     * 发送消息
     *
     * @param      （代表不同的消息类型）
     * @param content 参数
     * @param
     */
    private void sendDashangMessages(String content,String price) {

        final EMMessage message = EMMessage.createTxtSendMessage(content, roomId);
        message.setChatType(EMMessage.ChatType.ChatRoom);
        message.setAttribute("username", userBean.getMember_nick_name());
        message.setAttribute("user_id", userBean.getMember_id());
        message.setAttribute("userimg", userBean.getMember_head_image());
        message.setAttribute("sex", userBean.getMember_sex());
        message.setAttribute("dashang", "1");
        message.setAttribute("dashang_price", price);
        EMClient.getInstance().chatManager().sendMessage(message);
        message.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                if (null != mTCChatRoomListener)
                    mTCChatRoomListener.onSendMsgCallback(0, message);
            }

            @Override
            public void onError(int i, String s) {
                if (null != mTCChatRoomListener)
                    mTCChatRoomListener.onSendMsgCallback(-1, null);
            }

            @Override
            public void onProgress(int i, String s) {
            }
        });
    }

    public void sendEMMessage(String type) {
        EMMessage message = EMMessage.createSendMessage(EMMessage.Type.CMD);
        message.setTo(roomId);
        message.addBody(new EMCmdMessageBody(type));
        message.setChatType(EMMessage.ChatType.ChatRoom);
        EMClient.getInstance().chatManager().sendMessage(message);
    }





    /**
     * 发送透传消息
     *
     * @param action
     * @param user_id
     */
    public void sendcmdMessage(String action, String user_id, String user_name) {
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD); // 消息的类型 透传消息
        // 发送的类型  聊天室 群聊 单聊  拉黑不能发送单聊
        cmdMsg.setChatType(EMMessage.ChatType.ChatRoom);  // 聊天室
        EMCmdMessageBody cmdBody = new EMCmdMessageBody(action);
        // 发送给谁
        cmdMsg.setTo(roomId);
        // 扩展字段
        cmdMsg.setAttribute("userid", user_id);
        cmdMsg.setAttribute("username", user_name);
        cmdMsg.addBody(cmdBody);
        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
    }


    /**
     * 注入消息回调监听类
     * 需要实现并注入TCChatRoomListener才能获取相应消息回调
     *
     * @param listener
     */
    public void setMessageListener(TCChatRoomListener listener) {
        mTCChatRoomListener = listener;
        EMClient.getInstance().chatManager().addMessageListener(this);
    }

    /**
     * 退出房间
     */
    public void removeMsgListener() {
        //sendEMMessage(Constants.anchorStopLive);
        mTCChatRoomListener = null;
        EMClient.getInstance().chatManager().removeMessageListener(this);
        EMClient.getInstance().chatroomManager().leaveChatRoom(roomId);
    }


    /**
     * 消息循环监听类
     */
    public interface TCChatRoomListener {

        /**
         * 加入群组回调
         *
         * @param code 错误码，成功时返回0，失败时返回相应错误码
         * @param msg  返回信息，成功时返回群组Id，失败时返回相应错误信息
         */
        void onJoinGroupCallback(int code, String msg);


        /**
         * 发送消息结果回调
         *
         * @param code       错误码，成功时返回0，失败时返回相应错误码
         * @param timMessage 发送的TIM消息
         */
        void onSendMsgCallback(int code, EMMessage timMessage);

        /**
         * 接受消息监听接口
         * 文本消息回调
         *
         * @param type     消息类型
         * @param userInfo 发送者信息
         * @param content  内容
         */
        void onReceiveMsg(int type, TCSimpleUserInfo userInfo, String content);


    }

    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        //收到普通消息
        for (int i = messages.size() - 1; i >= 0; i--) {
            EMMessage message = messages.get(i);
            String user = "";
            String username = "";
            String userimg = "";
            String body = "";
            //消息超过限制，丢弃
            /*if (!mMsgFreque.canTrigger()) {
                break;
            }*/
            if (message.getChatType() == EMMessage.ChatType.GroupChat
                    || message.getChatType() == EMMessage.ChatType.ChatRoom) {
                user = message.getTo();
                if (!user.equals(roomId)) {
                    break;
                }
                body = ((EMTextMessageBody) message.getBody()).getMessage();
                String forced_off = message.getStringAttribute("forced_off", "");
                username = message.getStringAttribute("username", "");
                userimg = message.getStringAttribute("userimg", "");
                String is_danmu = message.getStringAttribute("barrage", "");
                String user_id=message.getStringAttribute("user_id","");
                if (is_danmu.equals("1")) {
                    mTCChatRoomListener.onReceiveMsg(IMCMD_DANMU, new TCSimpleUserInfo("",
                            username, userimg), body);
                    return;
                }
                String praise = message.getStringAttribute("praise", "");
                if (praise.equals("1")) {
                    mTCChatRoomListener.onReceiveMsg(IMCMD_PRAISE, new TCSimpleUserInfo("",
                            username, userimg), null);
                    return;
                }
                String intoroom = message.getStringAttribute("intoroom", "");
                if (intoroom.equals("1")) {
                    mTCChatRoomListener.onReceiveMsg(IMCMD_ENTER_LIVE, new TCSimpleUserInfo("",
                            username, userimg), null);
                    return;
                }
                String leaveChatroom = message.getStringAttribute("leaveChatroom", "");
                if (leaveChatroom.equals("1")) {
                    mTCChatRoomListener.onReceiveMsg(IMCMD_EXIT_LIVE, new TCSimpleUserInfo("",
                            username, userimg), null);
                    return;
                }
                //String userId, String nickname, String headpic,  String dashang_price, String gift_num
                String dashang = message.getStringAttribute("dashang", "");
                String dashang_price = message.getStringAttribute("dashang_price", "");
                if (dashang.equals("1")) {
                    mTCChatRoomListener.onReceiveMsg(IMCMD_DASHANG, new TCSimpleUserInfo("",
                            username, userimg,dashang_price,"1"), null);
                    return;
                }



                String gift = message.getStringAttribute("giftimg", "");
                if (!TextUtils.isEmpty(gift)) {
                    String Gift_IMG = message.getStringAttribute("giftimg", "");
                    String userid = message.getStringAttribute("user_id", "");
                    String giftname = message.getStringAttribute("giftname", "");
                    String gift_id = message.getStringAttribute("gift_id", "");
                    String giftprice = message.getStringAttribute("giftprice", "");
                    String gift_num;
                    try {
                        gift_num = message.getStringAttribute("gift_num", "1");
                    } catch (Exception e) {
                        gift_num = "1";
                    }
                    EMTextMessageBody textMessageBody = new EMTextMessageBody("送了主播" + gift_num + "个" + giftname);
                    message.addBody(textMessageBody);
                    body = textMessageBody.getMessage();

                    if (message.getMsgTime() >= cur_time) {
                        mTCChatRoomListener.onReceiveMsg(IMCMD_GIFT, new TCSimpleUserInfo(userid,
                                username, userimg, gift_id, giftname, Gift_IMG, gift_num), textMessageBody.getMessage());
                    }
                }

            } else {
                // 单聊消息
                user = message.getFrom();
            }
            // 如果是当前会话的消息，刷新聊天页面
            mTCChatRoomListener.onReceiveMsg(IMCMD_PAILN_TEXT, new TCSimpleUserInfo("",
                    username, userimg), body);

        }


    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {


        //收到透传消息
        EMMessage message = messages.get(messages.size() - 1);
        String id = message.getTo();
        if (!id.equals(roomId)) {
            return;
        }
        try {
            long a = message.localTime();
            long b = message.getMsgTime();
            msgtime = (a - b) / 1000;
            msgtime = b;
            action = ((EMCmdMessageBody) message.getBody()).action();
            tc_username = message.getStringAttribute("username", "");
            tc_id = message.getStringAttribute("userid", "");



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {
        //收到已读回执
    }

    @Override
    public void onMessageDelivered(List<EMMessage> message) {
        //收到已送达回执
    }

    @Override
    public void onMessageRecalled(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {
        //消息状态变动
    }

}
