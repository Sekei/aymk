package com.live.tv.mvp.fragment.huanxin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.util.EasyUtils;
import com.live.tv.MainActivity;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.fragment.huanxin.runtimepermissions.PermissionsManager;

import java.util.List;

/**
 * Created by taoh on 2018/3/29.
 */

public class ChatActivity extends EaseBaseActivity {

    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    String toChatUsername;
    String APP_user_name;
    String health_record_id;
    String doctor_id;
    String consult_record_id;
    String mstatus;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.em_activity_chat);
        activityInstance = this;
        //get user id or group id
        toChatUsername = getIntent().getExtras().getString("userId");
        APP_user_name = getIntent().getExtras().getString("APP_user_name");
        health_record_id = getIntent().getExtras().getString("health_record_id");
        doctor_id = getIntent().getExtras().getString("doctor_id");
        consult_record_id = getIntent().getExtras().getString("consult_record_id");
        mstatus=getIntent().getExtras().getString("status");

        //use EaseChatFratFragment
        chatFragment = new ChatFragment();

        //pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
        EMClient.getInstance().chatManager().addMessageListener(messageListener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // make sure only one chat activity is opened
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
        if (EasyUtils.isSingleActivity(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public String getToChatUsername(){
        return toChatUsername;
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    EMMessageListener messageListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            // notify new message

            for (EMMessage message : messages){

                Log.i("dfc", "onMessageReceived: "+message.getMsgId());


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
}
