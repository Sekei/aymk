package com.live.tv;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.bilibili.boxing.BoxingCrop;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseNotifier;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.king.base.util.LogUtils;
import com.king.base.util.ToastUtils;
import com.live.tv.bean.UserBean;
import com.live.tv.dao.greendao.DaoMaster;
import com.live.tv.dao.greendao.DaoSession;
import com.live.tv.di.component.AppComponent;
import com.live.tv.di.component.DaggerAppComponent;
import com.live.tv.di.module.AppModule;
import com.live.tv.http.HttpResult;
import com.live.tv.impl.BoxingFrescoLoader;
import com.live.tv.impl.BoxingUcrop;
import com.live.tv.mvp.fragment.huanxin.CallReceiver;
import com.live.tv.mvp.fragment.huanxin.ChatActivity;
import com.live.tv.mvp.fragment.huanxin.Constant;
import com.live.tv.mvp.fragment.huanxin.VideoCallActivity;
import com.live.tv.mvp.fragment.huanxin.VoiceCallActivity;
import com.live.tv.util.SPUtils;
import com.live.tv.util.SpSingleInstance;
import com.qiniu.pili.droid.rtcstreaming.RTCMediaStreamingManager;
import com.qiniu.pili.droid.rtcstreaming.RTCServerRegion;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.hyphenate.easeui.utils.EaseUserUtils.getUserInfo;
import static com.live.tv.mvp.fragment.LoginFragment.saveLoginInfo;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/13
 */

public class App extends Application {
    public static final String UPDATE_STATUS_ACTION = "com.umeng.message.example.action.UPDATE_STATUS";

    private DaoMaster.DevOpenHelper mHelper;

    private DaoSession mDaoSession;
    /**
     * 应用实例
     */
    private static App instance;
    private AppComponent mAppComponent;

    private static Context mContext;

    private CallReceiver callReceiver;
    private String deviceTokens;
    private UserBean userBean;
    private HashMap<String, String> params;

    //记录是否需要回拨
    public  boolean mCallback = true;
    private EaseUI easeUI;
    public boolean isVoiceCalling;
    public boolean isVideoCalling;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);


        MultiDex.install(base);
        //Beta.installTinker();
    }


    //获得实例
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initDatabase();
        // setStrictMode();

        instance = this;

        //初始化保存用户信息
        UserBean userBean = SPUtils.getObj1(getApplicationContext(), Constants.USER_BEAN);
        SpSingleInstance.getSpSingleInstance().setUserBean(userBean);


        //配置分享第三方平台的appkey
        PlatformConfig.setWeixin(Constants.WEIXIN_SHARE_ID, Constants.WEIXIN_SHARE_SECRECT);
        PlatformConfig.setSinaWeibo(Constants.SINA_WEIBO_SHARE_ID, Constants.SINA_WEIBO_SHARE_SECRECT, Constants.SINA_WEIBO_SHARE_REDIRECT_URL);
        PlatformConfig.setQQZone(Constants.QQZONE_SHARE_ID, Constants.QQZONE_SHARE_SECRECT);

        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);
        if (EaseUI.getInstance().init(this, options)) {
            //get easeui instance
            easeUI = EaseUI.getInstance();
        }
        IBoxingMediaLoader loader = new BoxingFrescoLoader(this);
        BoxingMediaLoader.getInstance().init(loader);
        BoxingCrop.getInstance().init(new BoxingUcrop());


        //异常处理
        /*NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {

            }
        });*/

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this, Constants.BASE_URL)).build();

        //初始化Leak内存泄露检测工具
       /* LeakCanary.install(this);*/

        try {
            IntentFilter callFilter = new IntentFilter(EMClient.getInstance().callManager().getIncomingCallBroadcastAction());
            if (callReceiver == null) {
                callReceiver = new CallReceiver();
            }
            registerReceiver(callReceiver, callFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RTCMediaStreamingManager.init(getApplicationContext(), RTCServerRegion.RTC_CN_SERVER);

        umengPush();//友盟推送
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
//initUpush();
    }

    private void umengPush() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        UMConfigure.init(this, "5a658f628f4a9d0c85000078", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "da5672d67368952f3d8ab8abd7e2bb27");
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.setResourcePackageName("com.ysjk.health.iemk");
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(final String deviceToken) {
                //注册成功会返回device token
                deviceTokens = deviceToken;
                saveLoginInfo(mContext, deviceToken);
                Log.e("deviceToken", "onSuccess: " + deviceToken);

                new Thread() {
                    @Override
                    public void run() {

                        if (userBean != null) {
                            params = new HashMap<>();
                            params.put("member_id", userBean.getMember_id());
                            params.put("device_token", deviceToken);


                            getAppCommponent().getAPIService()
                                    .getDeviceTokens(params)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<HttpResult<String>>() {
                                        @Override
                                        public void onError(Throwable e) {
                                            //ToastUtils.showToast(getApplicationContext(), e.getMessage());

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
//                                                ToastUtils.showToast(getApplicationContext(), "发送成功");
                                            }
                                        }
                                    });

                        }


                    }
                }.start();

            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("deviceToken", "onFailure: " + s + "=====" + s1);

            }
        });
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE); //声音
        mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);//呼吸灯
        mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);//振动
        mPushAgent.setNoDisturbMode(0, 0, 0, 0);
        mPushAgent.setMuteDurationSeconds(0);
        mPushAgent.setDisplayNotificationNumber(10);
    }

    public void initDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "tv-db", null);

        DaoMaster daoMaster = new DaoMaster(mHelper.getWritableDatabase());

        mDaoSession = daoMaster.newSession();
    }

    public AppComponent getAppCommponent() {
        return mAppComponent;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    @TargetApi(9)
    protected void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }

    public static Context getGlobalContext() {
        return mContext;
    }

    public static void saveLoginInfo(Context context, String deviceToken) {
        //获取SharedPreferences对象
        SharedPreferences sharedPre = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor = sharedPre.edit();
        //设置参数
        editor.putString("deviceToken", deviceToken);
        //提交
        editor.commit();
    }


    EMMessageListener messageListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            // notify new message

            for (EMMessage message : messages){
                // 应用在后台，不需要刷新UI,通知栏提示新消息
                if (!easeUI.hasForegroundActivies()) {
                    easeUI.getNotifier().onNewMsg(message);
                    easeUI.getNotifier().setNotificationInfoProvider(provider);
                    Log.i("dfc", "看看消息么？通知栏");
                }
                Log.i("dfc", "看看消息么？"+message);
            }

        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {

        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {

        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {}
    };


    EaseNotifier.EaseNotificationInfoProvider provider=new EaseNotifier.EaseNotificationInfoProvider() {

        @Override
        public String getTitle(EMMessage message) {
            //修改标题,这里使用默认
            return null;
        }

        @Override
        public int getSmallIcon(EMMessage message) {
            //设置小图标，这里为默认
            return 0;
        }

        @Override
        public String getDisplayedText(EMMessage message) {
            // 设置状态栏的消息提示，可以根据message的类型做相应提示
            String ticker = EaseCommonUtils.getMessageDigest(message, getApplicationContext());
            if(message.getType() == EMMessage.Type.TXT){
                ticker = ticker.replaceAll("\\[.{2,3}\\]", "[表情]");
            }
            EaseUser user = getUserInfo(message.getFrom());
            if(user != null){
                return getUserInfo(message.getFrom()).getNick() + ": " + ticker;
            }else{
                return message.getFrom() + ": " + ticker;
            }
        }

        @Override
        public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
            return null;
            // return fromUsersNum + "个基友，发来了" + messageNum + "条消息";
        }

        @Override
        public Intent getLaunchIntent(EMMessage message) {
            //设置点击通知栏跳转事件
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            //有电话时优先跳转到通话页面
            if(isVideoCalling){
                intent = new Intent(getApplicationContext(), VideoCallActivity.class);
            }else if(isVoiceCalling){
                intent = new Intent(getApplicationContext(), VoiceCallActivity.class);
            }else{
                EMMessage.ChatType chatType = message.getChatType();
                if (chatType == EMMessage.ChatType.Chat) { // 单聊信息
                    intent.putExtra("userId", message.getFrom());
                    intent.putExtra("chatType", Constant.CHATTYPE_SINGLE);
                } else { // 群聊信息
                    // message.getTo()为群聊id
                    intent.putExtra("userId", message.getTo());
                    if(chatType == EMMessage.ChatType.GroupChat){
                        intent.putExtra("chatType", Constant.CHATTYPE_GROUP);
                    }else{
                        intent.putExtra("chatType", Constant.CHATTYPE_CHATROOM);
                    }

                }
            }
            return intent;
        }
    };
}



