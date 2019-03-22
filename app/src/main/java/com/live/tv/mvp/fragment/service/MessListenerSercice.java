package com.live.tv.mvp.fragment.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.live.tv.MainActivity;
import com.ysjk.health.iemk.R;

import java.util.List;

/**
 * Created by mac1010 on 2018/3/27.
 */
public class MessListenerSercice extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //通知
    private NotificationManager notificationManager;
    private int notificationManagerId=1;
    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationCall();
        EMClient.getInstance().chatManager().addMessageListener(msgListener);

    }

    NotificationCompat.Builder builder;
    private void notificationCall(){

        builder=new NotificationCompat.Builder(this);
        builder.setOngoing(false);//点击清除 true不会清除
        builder.setContentTitle(getResources().getString(R.string.app_name));
        //系统状态栏显示的小图标
//        builder.setSmallIcon(R.mipmap.ic_launcher_small);
        builder.setSmallIcon(R.drawable.ava_defaultx);
        builder.setAutoCancel(true);
        //下拉显示的大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ava_defaultx));
        builder.setWhen(System.currentTimeMillis());

        builder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE);


        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("message_type",MESSAGE);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        //点击跳转的intent
        builder.setContentIntent(pIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setVisibility(Notification.VISIBILITY_PUBLIC);
            // 关联PendingIntent
            builder.setFullScreenIntent(pIntent, true);
        }
    }

    public static final String MESSAGE = "message";

    private EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            //收到消息
//            if(isBackground(MessListenerSercice.this)){
                builder.setContentText("您有新消息，请注意查收！");
                notificationManager.notify(notificationManagerId, builder.build());
//            }

        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
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
    };


    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    Log.i("", "后台"+appProcess.processName);
                    return true;
                }else{
                    Log.i("","前台"+appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }


}
