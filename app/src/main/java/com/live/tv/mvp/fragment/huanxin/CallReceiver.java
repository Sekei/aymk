package com.live.tv.mvp.fragment.huanxin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.util.EMLog;

/**
 * Created by taoh on 2018/3/30.
 */

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


//        if(EMClient.getInstance().isLoggedInBefore())
//            return;
        //username

        String callExt = EMClient.getInstance().callManager().getCurrentCallSession().getExt();

        String[] ext=null;
        Log.i("dfc", "onReceive: " + callExt);
        try {
             ext = callExt.split(",");
            if (ext.length<2){
                ext=new String[] {"",""};

            }

        }catch (Exception e){
            e.printStackTrace();
            ext=new String[] {"",""};
        }

        String username = intent.getStringExtra("username");
        String doctor_name = intent.getStringExtra("doctor_name");
        String  consult_record_id=intent.getStringExtra("consult_record_id");

        EMLog.i("dfc", "接到视频=====" + username);
        String from = intent.getStringExtra("from");
        //call type
        String type = intent.getStringExtra("type");
        if ("video".equals(type)) { //video call
            context.startActivity(new Intent(context, VideoCallActivity.class)
                    .putExtra("username", from)
                    .putExtra("doctor_name", ext[1])
                    .putExtra("doctor_img", ext[0])
                    .putExtra("isComingCall", true)
                    .putExtra("consult_record_id","")
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } else { //voice call
            context.startActivity(new Intent(context, VoiceCallActivity.class)
                    .putExtra("doctor_name", ext[1])
                    .putExtra("doctor_img", ext[0])
                    .putExtra("username", from)
                    .putExtra("isComingCall", true)
                    .putExtra("consult_record_id","")
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        EMLog.d("CallReceiver", "app received a incoming call");
    }

}