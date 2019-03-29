package com.live.tv.mvp.fragment.huanxin;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.hyphenate.easeui.widget.presenter.EaseChatRowPresenter;
import com.hyphenate.util.EasyUtils;
import com.hyphenate.util.PathUtil;
import com.king.base.util.LogUtils;
import com.king.base.util.ToastUtils;
import com.live.tv.App;
import com.live.tv.Constants;
import com.live.tv.MainActivity;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.UserBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.activity.ContentActivity;
import com.live.tv.util.CustomDialog;
import com.live.tv.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by taoh on 2018/3/29.
 */

public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {

    // constant start from 11 to avoid conflict with constant in base class
    private static final int ITEM_VIDEO = 11;
    private static final int ITEM_FILE = 12;
    private static final int ITEM_VOICE_CALL = 13;
    private static final int ITEM_VIDEO_CALL = 14;
    private static final int ITEM_TEST = 15;//测试数据
    private static final int ITEM_MEDICAL_RECORD = 16;//电子病历
    private static final int ITEM_HEALTH_FILE = 17;//健康档案


    private static final int REQUEST_CODE_SELECT_VIDEO = 11;
    private static final int REQUEST_CODE_SELECT_FILE = 12;
    private static final int REQUEST_CODE_GROUP_DETAIL = 13;
    private static final int REQUEST_CODE_CONTEXT_MENU = 14;
    private static final int REQUEST_CODE_SELECT_AT_USER = 15;
    private static final int MESSAGE_TYPE_SENT_VOICE_CALL = 1;
    private static final int MESSAGE_TYPE_RECV_VOICE_CALL = 2;
    private static final int MESSAGE_TYPE_SENT_VIDEO_CALL = 3;
    private static final int MESSAGE_TYPE_RECV_VIDEO_CALL = 4;
    private static final int MESSAGE_TYPE_RECALL = 9;


    /**
     * if it is chatBot
     */
    private boolean isRobot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, true);
    }

    @Override
    protected void setUpView() {
        setChatFragmentHelper(this);
        if (chatType == Constant.CHATTYPE_SINGLE) {
            isRobot = true;
        }
        super.setUpView();

        titleBar.setLeftImageResource(R.drawable.back);
        titleBar.setRightLayoutVisibility(View.GONE);
        titleBar.setRight_textClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结束咨询
                closeAdvisory();
            }
        });

        // set click listener
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (EasyUtils.isSingleActivity(getActivity())) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                onBackPressed();
            }
        });


        // ((EaseEmojiconMenu)inputMenu.getEmojiconMenu()).addEmojiconGroup(EmojiconExampleGroupData.getData());
        if (chatType == EaseConstant.CHATTYPE_GROUP) {
            inputMenu.getPrimaryMenu().getEditText().addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count == 1 && "@".equals(String.valueOf(s.charAt(start)))) {

                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }


    private void closeAdvisory() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(getContext());
        builder.setMessage("是否确认结束咨询？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //执行结束咨询的网络请求
                UserBean userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
                Map<String, String> mMap1 = new HashMap<>();
                mMap1.put("member_id", userBean.getMember_id());
                mMap1.put("member_token", userBean.getMember_token());
                mMap1.put("consult_record_id", consult_record_id);
                mMap1.put("state_show", "已结束");
                mMap1.put("is_done", "1");
                Networkequest(mMap1, "1");
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

    private void Networkequest(Map<String, String> parmer, final String str) {

        ((App) getActivity().getApplication()).getAppCommponent().getAPIService()
                .updateConsultState(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(HttpResult<String> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (!TextUtils.isEmpty(str) && str.equals("1")) {
                                ToastUtils.showToast(getActivity(), "结束咨询");
                                EventBus.getDefault().post(new FirstEvent("close_advisory"));
                                //结束咨询 删除聊天记录
                                // EMClient.getInstance().chatManager().deleteConversation(toChatUsername, true);
                                getActivity().finish();
                            }


                        }
                    }
                });


    }

    /**
     * 发送电子病历
     *
     * @param parmer
     */

    private void SendMedicalRecord(Map<String, String> parmer) {

        ((App) getActivity().getApplication()).getAppCommponent().getAPIService()
                .sendMedical(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(HttpResult<String> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {

                            ToastUtils.showToast(getActivity(), "发送成功");
                        }
                    }
                });


    }


    /**
     * 发送健康档案
     *
     * @param parmer
     */

    private void SendHealthPlan(Map<String, String> parmer) {

        ((App) getActivity().getApplication()).getAppCommponent().getAPIService()
                .sendHealthPlan(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(HttpResult<String> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {

                        }
                    }
                });


    }


    /**
     * 发送测试数据
     *
     * @param parmer
     */

    private void SendTestRecord(Map<String, String> parmer) {

        ((App) getActivity().getApplication()).getAppCommponent().getAPIService()
                .sendTestRecord(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(HttpResult<String> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {

                        }
                    }
                });


    }


    @Override
    protected void registerExtendMenuItem() {
        //use the menu in base class
        super.registerExtendMenuItem();
        //extend menu items
        //inputMenu.registerExtendMenuItem("测试数据", R.drawable.message_giftx, ITEM_TEST, extendMenuItemClickListener);
        inputMenu.registerExtendMenuItem("电子病历", R.drawable.message_bingli, ITEM_MEDICAL_RECORD, extendMenuItemClickListener);
        //inputMenu.registerExtendMenuItem("健康档案", R.drawable.message_picx, ITEM_HEALTH_FILE, extendMenuItemClickListener);

    }

    private UserBean userBean;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        //发送测试数据
        if (requestCode == Constants.INTENT_TEST_DATA) {

            if (data != null) {
                String test_id = data.getStringExtra("test_id");
                EMMessage message = EMMessage.createTxtSendMessage("1", toChatUsername);
                // 增加自己特定的属性
                message.setAttribute("is_test_data", true);
                message.setAttribute("test_id", test_id);

                message.setAttribute("from_user_id", userBean.getMember_id());
                message.setAttribute("from_head_image", EaseConstant.BASE_URL + userBean.getMember_head_image());
                message.setAttribute("from_username", userBean.getMember_nick_name());

                EMClient.getInstance().chatManager().sendMessage(message);
                //inputMenu.hideExtendMenuContainer();

                Map<String, String> mMap = new HashMap<>();
                mMap.put("member_id", userBean.getMember_id());
                mMap.put("test_id", test_id);
                mMap.put("doctor_id", doctor_id);
                SendTestRecord(mMap);//发送测试数据
            }

        }
        //发送健康档案
        if (requestCode == Constants.INTENT_HEALTH_FILE) {

            if (data != null) {

                String health_record_id = data.getStringExtra("health_record_id");
                String health_plan_id = data.getStringExtra("health_plan_id");
                // String doctor_id = data.getStringExtra("doctor_id");
                EMMessage message = EMMessage.createTxtSendMessage("1", toChatUsername);
                // 增加自己特定的属性
                message.setAttribute("is_health_file", true);
                message.setAttribute("health_plan_id", health_plan_id);
                message.setAttribute("doctor_id", doctor_id);
                message.setAttribute("health_record_id", health_record_id);

                message.setAttribute("from_user_id", userBean.getMember_id());
                message.setAttribute("from_head_image", EaseConstant.BASE_URL + userBean.getMember_head_image());
                message.setAttribute("from_username", userBean.getMember_nick_name());

                EMClient.getInstance().chatManager().sendMessage(message);
                //inputMenu.hideExtendMenuContainer();
                Map<String, String> mMap = new HashMap<>();
                mMap.put("member_id", userBean.getMember_id());
                mMap.put("member_token", userBean.getMember_token());
                mMap.put("health_plan_id", health_plan_id);
                mMap.put("doctor_id", doctor_id);
                mMap.put("health_record_id", health_record_id);
                SendHealthPlan(mMap);//发送健康档案


            }

        }


        if (requestCode == Constants.INTENT_MEDICAL_RECORD) {
            //发送电子病历
            if (data != null) {
                String medical_id = data.getStringExtra("medical_id");
//                String doctor_id = data.getStringExtra("doctor_id");
                EMMessage message = EMMessage.createTxtSendMessage("1", toChatUsername);
                // 增加自己特定的属性
                message.setAttribute("is_medical_record", true);
                message.setAttribute("medical_id", medical_id);
                message.setAttribute("doctor_id", doctor_id);
                message.setAttribute("from_user_id", userBean.getMember_id());
                message.setAttribute("from_head_image", EaseConstant.BASE_URL + userBean.getMember_head_image());
                message.setAttribute("from_username", userBean.getMember_nick_name());
                EMClient.getInstance().chatManager().sendMessage(message);

                Map<String, String> mMap = new HashMap<>();
                mMap.put("member_id", userBean.getMember_id());
                mMap.put("member_token", userBean.getMember_token());
                mMap.put("doctor_id", doctor_id);
                mMap.put("medical_id", medical_id);
                SendMedicalRecord(mMap);

            }

        }


        if (requestCode == REQUEST_CODE_CONTEXT_MENU) {
            switch (resultCode) {


                default:
                    break;
            }
        }
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SELECT_VIDEO: //send the video
                    if (data != null) {
                        int duration = data.getIntExtra("dur", 0);
                        String videoPath = data.getStringExtra("path");
                        File file = new File(PathUtil.getInstance().getImagePath(), "thvideo" + System.currentTimeMillis());
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            Bitmap ThumbBitmap = ThumbnailUtils.createVideoThumbnail(videoPath, 3);
                            ThumbBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            fos.close();
                            sendVideoMessage(videoPath, file.getAbsolutePath(), duration);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case REQUEST_CODE_SELECT_FILE: //send the file
                    if (data != null) {
                        Uri uri = data.getData();
                        if (uri != null) {
                            sendFileByUri(uri);
                        }
                    }
                    break;
                case REQUEST_CODE_SELECT_AT_USER:
                    if (data != null) {
                        String username = data.getStringExtra("username");
                        inputAtUsername(username, false);
                    }
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void onSetMessageAttributes(EMMessage message) {
        if (isRobot) {
            //set message extension
            message.setAttribute("em_robot_message", isRobot);
        }
        UserBean userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        message.setAttribute("from_user_id", userBean.getMember_id());
        message.setAttribute("from_head_image", EaseConstant.BASE_URL + userBean.getMember_head_image());
        message.setAttribute("from_username", userBean.getMember_nick_name());
        if ("1".equals(userBean.getDoctor_type())) {
            if (!TextUtils.isEmpty(mstatus) && mstatus.equals("未回复")) {
                //执行结束咨询的网络请求
                UserBean userBean1 = SpSingleInstance.getSpSingleInstance().getUserBean();
                Map<String, String> mMap = new HashMap<>();
                mMap.put("member_id", userBean1.getMember_id());
                mMap.put("member_token", userBean1.getMember_token());
                mMap.put("consult_record_id", consult_record_id);
                Log.e("ceshi", ">>>>" + consult_record_id);
                mMap.put("state_show", "已回复");
                mMap.put("is_done", "0");
                Networkequest(mMap, "");

            }
        }

    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return new CustomChatRowProvider();
    }


    @Override
    public void onEnterToChatDetails() {
        if (chatType == Constant.CHATTYPE_GROUP) {
            EMGroup group = EMClient.getInstance().groupManager().getGroup(toChatUsername);
            if (group == null) {
                Toast.makeText(getActivity(), R.string.gorup_not_found, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (chatType == Constant.CHATTYPE_CHATROOM) {
        }
    }

    @Override
    public void onAvatarClick(String username) {
        //handling when user click avatar
//        Intent intent = new Intent(getActivity(), UserProfileActivity.class);
//        intent.putExtra("username", username);
//        startActivity(intent);
    }


    @Override
    public void onAvatarLongClick(String username) {
        inputAtUsername(username);
    }


    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        // 消息框点击事件，demo这里不做覆盖，如需覆盖，return true

        if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_TEST_DATA, false)) {


            String test_id = message.getStringAttribute("test_id", "0");

            Log.i("dfc", "onBubbleClick: " + test_id);

            Intent intent = new Intent(getActivity(), ContentActivity.class);
            intent.putExtra(Constants.KEY_FRAGMENT, Constants.TESTDATA_HUANXIN_FRAGMENT);
            intent.putExtra("test_id", test_id);
            startActivity(intent);


        }
        if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_HEALTH_FILE, false)) {


            String health_plan_id = message.getStringAttribute("health_plan_id", "0");
            String doctor_id = message.getStringAttribute("doctor_id", "0");
            String health_record_id = message.getStringAttribute("health_record_id", "0");

            Log.i("dfc", "onBubbleClick: " + health_plan_id);

            Intent intent = new Intent(getActivity(), ContentActivity.class);
            intent.putExtra(Constants.KEY_FRAGMENT, Constants.HUANXIN_HEALTH_STEP_FRAGMENT);
            intent.putExtra("health_plan_id", health_plan_id);
            intent.putExtra("health_record_id", health_record_id);
            intent.putExtra("doctor_id", doctor_id);
            startActivity(intent);


        }
        if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_MEDICAL_RECORD, false)) {


            String medical_id = message.getStringAttribute("medical_id", "0");
            String doctor_id = message.getStringAttribute("doctor_id", "0");

            Intent intent = new Intent(getActivity(), ContentActivity.class);
            intent.putExtra(Constants.KEY_FRAGMENT, Constants.MEDICAL_RECORDS_DETAIL_HX_FRAGMENT);
            intent.putExtra("medical_id", medical_id);
            intent.putExtra("doctor_id", doctor_id);
            startActivity(intent);


        }
//


        return false;
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        super.onCmdMessageReceived(messages);
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        switch (itemId) {
            case ITEM_TEST://测试数据


                Intent intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra(Constants.KEY_FRAGMENT, Constants.TEST_HISTORY);
                intent.putExtra("click_type", "1");
                startActivityForResult(intent, Constants.INTENT_TEST_DATA);

                break;

            case ITEM_MEDICAL_RECORD://电子病历
                //判断是否是历史咨询记录
                if ("0".equals(consultation_type)) {
                    Toast.makeText(getActivity(), "请到医生主页进行二次预约", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent2 = new Intent(getActivity(), ContentActivity.class);
                    intent2.putExtra(Constants.KEY_FRAGMENT, Constants.MEDICAL_RECORD);
                    intent2.putExtra("click_type", "1");
                    startActivityForResult(intent2, Constants.INTENT_MEDICAL_RECORD);
                }
                break;

            case ITEM_HEALTH_FILE://健康档案

                Intent intent1 = new Intent(getActivity(), ContentActivity.class);
                intent1.putExtra("health_record_id", health_record_id);
                intent1.putExtra(Constants.KEY_FRAGMENT, Constants.SEND_HEALTH_FILE_FRAGMENT);
                startActivityForResult(intent1, Constants.INTENT_HEALTH_FILE);
                // inputMenu.hideExtendMenuContainer();

                break;


            default:
                break;
        }
        //keep exist extend menu
        return false;
    }

    /**
     * select file
     */
    protected void selectFileFromLocal() {
        Intent intent = null;
        if (Build.VERSION.SDK_INT < 19) { //api 19 and later, we can't use this way, demo just select from images
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);

        } else {
            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent, REQUEST_CODE_SELECT_FILE);
    }


    /**
     * chat row provider
     */
    private final class CustomChatRowProvider implements EaseCustomChatRowProvider {
        @Override
        public int getCustomChatRowTypeCount() {
            //here the number is the message type in EMMessage::Type
            //which is used to count the number of different chat row
            return 11;
        }

        @Override
        public int getCustomChatRowType(EMMessage message) {
            if (message.getType() == EMMessage.Type.TXT) {
                //voice call
                if (message.getBooleanAttribute(Constant.MESSAGE_ATTR_IS_VOICE_CALL, false)) {
                    return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VOICE_CALL : MESSAGE_TYPE_SENT_VOICE_CALL;
                } else if (message.getBooleanAttribute(Constant.MESSAGE_ATTR_IS_VIDEO_CALL, false)) {
                    //video call
                    return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VIDEO_CALL : MESSAGE_TYPE_SENT_VIDEO_CALL;
                }
                //messagee recall
                else if (message.getBooleanAttribute(Constant.MESSAGE_TYPE_RECALL, false)) {
                    return MESSAGE_TYPE_RECALL;
                }
            }
            return 0;
        }

        @Override
        public EaseChatRowPresenter getCustomChatRow(EMMessage message, int position, BaseAdapter adapter) {
            if (message.getType() == EMMessage.Type.TXT) {
                // voice call or video call
                if (message.getBooleanAttribute(Constant.MESSAGE_ATTR_IS_VOICE_CALL, false) ||
                        message.getBooleanAttribute(Constant.MESSAGE_ATTR_IS_VIDEO_CALL, false)) {
                    EaseChatRowPresenter presenter = new EaseChatVoiceCallPresenter();
                    return presenter;
                }
                //recall message
                else if (message.getBooleanAttribute(Constant.MESSAGE_TYPE_RECALL, false)) {
                    EaseChatRowPresenter presenter = new EaseChatRecallPresenter();
                    return presenter;
                }
            }
            return null;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
