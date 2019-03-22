package com.live.tv.mvp.fragment.huanxin;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.GlideCircleTransform;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.king.base.util.LogUtils;
import com.king.base.util.ToastUtils;
import com.live.tv.App;
import com.live.tv.Constants;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.UserBean;
import com.live.tv.http.HttpResult;
import com.live.tv.util.CustomDialog;
import com.live.tv.util.SpSingleInstance;
import com.ysjk.health.iemk.R;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by taoh on 2018/3/29.
 */

public class VoiceCallActivity extends CallActivity implements View.OnClickListener {
    private LinearLayout comingBtnContainer;
    private Button hangupBtn;
    private Button refuseBtn;
    private Button answerBtn;
    private ImageView muteImage;
    private ImageView handsFreeImage;

    private boolean isMuteState;
    private boolean isHandsfreeState;

    private TextView callStateTextView;
    private boolean endCallTriggerByMe = false;
    private Chronometer chronometer;
    String st1;
    private LinearLayout voiceContronlLayout;
    private TextView netwrokStatusVeiw;
    private boolean monitor = false;
    private String consultrecordid;
    private boolean isgoning;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            finish();
            return;
        }
        setContentView(R.layout.em_activity_voice_call);
        callType = 0;
        comingBtnContainer = (LinearLayout) findViewById(R.id.ll_coming_call);
        refuseBtn = (Button) findViewById(R.id.btn_refuse_call);
        answerBtn = (Button) findViewById(R.id.btn_answer_call);
        hangupBtn = (Button) findViewById(R.id.btn_hangup_call);
        muteImage = (ImageView) findViewById(R.id.iv_mute);
        handsFreeImage = (ImageView) findViewById(R.id.iv_handsfree);
        callStateTextView = (TextView) findViewById(R.id.tv_call_state);
        TextView nickTextView = (TextView) findViewById(R.id.tv_nick);
        ImageView swing_card = (ImageView) findViewById(R.id.swing_card);
        TextView durationTextView = (TextView) findViewById(R.id.tv_calling_duration);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        voiceContronlLayout = (LinearLayout) findViewById(R.id.ll_voice_control);
        netwrokStatusVeiw = (TextView) findViewById(R.id.tv_network_status);

        refuseBtn.setOnClickListener(this);
        answerBtn.setOnClickListener(this);
        hangupBtn.setOnClickListener(this);
        muteImage.setOnClickListener(this);
        handsFreeImage.setOnClickListener(this);

        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        addCallStateListener();
        msgid = UUID.randomUUID().toString();

        username = getIntent().getStringExtra("username");
        doctor_name = getIntent().getStringExtra("doctor_name");
        doctor_img = getIntent().getStringExtra("doctor_img");
        ext_content = getIntent().getStringExtra("ext_content");
        consultrecordid=getIntent().getStringExtra("consult_record_id");
        isInComingCall = getIntent().getBooleanExtra("isComingCall", false);
        nickTextView.setText(doctor_name);
        Glide.with(this).load(Constants.BASE_URL + doctor_img)
                .transform(new GlideCircleTransform(this))
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(swing_card);
        if (!isInComingCall) {// outgoing call
            soundPool = new SoundPool(1, AudioManager.STREAM_RING, 0);
            outgoing = soundPool.load(this, R.raw.em_outgoing, 1);

            comingBtnContainer.setVisibility(View.INVISIBLE);
            hangupBtn.setVisibility(View.VISIBLE);
            st1 = getResources().getString(R.string.Are_connected_to_each_other);
            callStateTextView.setText(st1);
            handler.sendEmptyMessage(MSG_CALL_MAKE_VOICE);
            handler.postDelayed(new Runnable() {
                public void run() {
                    streamID = playMakeCallSounds();
                }
            }, 300);
        } else { // incoming call
            voiceContronlLayout.setVisibility(View.INVISIBLE);
            Uri ringUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            audioManager.setMode(AudioManager.MODE_RINGTONE);
            audioManager.setSpeakerphoneOn(true);
            ringtone = RingtoneManager.getRingtone(this, ringUri);
            ringtone.play();
        }
        final int MAKE_CALL_TIMEOUT = 50 * 1000;
        handler.removeCallbacks(timeoutHangup);
        handler.postDelayed(timeoutHangup, MAKE_CALL_TIMEOUT);

    }



    /**
     * set call state listener
     */
    void addCallStateListener() {
        callStateListener = new EMCallStateChangeListener() {

            @Override
            public void onCallStateChanged(CallState callState, final CallError error) {
                // Message msg = handler.obtainMessage();
                EMLog.d("EMCallManager", "onCallStateChanged:" + callState);
                switch (callState) {

                    case CONNECTING:
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                callStateTextView.setText(st1);
                            }
                        });
                        break;
                    case CONNECTED:
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                String st3 = "已经和对方建立连接";
                                callStateTextView.setText(st3);

                            }
                        });
                        break;

                    case ACCEPTED:
                        handler.removeCallbacks(timeoutHangup);
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    if (soundPool != null)
                                        soundPool.stop(streamID);
                                } catch (Exception e) {
                                }
                                if(!isHandsfreeState)
                                    closeSpeakerOn();
                                //show relay or direct call, for testing purpose
//                                ((TextView)findViewById(R.id.tv_is_p2p)).setText(EMClient.getInstance().callManager().isDirectCall()
//                                        ? R.string.direct_call : R.string.relay_call);
                                chronometer.setVisibility(View.VISIBLE);
                                chronometer.setBase(SystemClock.elapsedRealtime());
                                // duration start
                                chronometer.start();
                                String str4 = getResources().getString(R.string.In_the_call);
                                isgoning=true;
                                callStateTextView.setText(str4);
                                callingState = CallingState.NORMAL;
                                startMonitor();
                            }
                        });
                        break;
                    case NETWORK_UNSTABLE:
                        runOnUiThread(new Runnable() {
                            public void run() {
                                netwrokStatusVeiw.setVisibility(View.VISIBLE);
                                if(error == CallError.ERROR_NO_DATA){
                                    netwrokStatusVeiw.setText("没有通话数据");
                                }else{
                                    netwrokStatusVeiw.setText("网络不稳定");
                                }
                            }
                        });
                        break;
                    case NETWORK_NORMAL:
                        runOnUiThread(new Runnable() {
                            public void run() {
                                netwrokStatusVeiw.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case VOICE_PAUSE:
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "VOICE_PAUSE", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case VOICE_RESUME:
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "VOICE_RESUME", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case DISCONNECTED:
                        handler.removeCallbacks(timeoutHangup);
                        changeData();
                        @SuppressWarnings("UnnecessaryLocalVariable") final CallError fError = error;
                        runOnUiThread(new Runnable() {
                            private void postDelayedCloseMsg() {
                                handler.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Log.d("AAA", "CALL DISCONNETED");
                                                removeCallStateListener();
                                                saveCallRecord();
                                                Animation animation = new AlphaAnimation(1.0f, 0.0f);
                                                animation.setDuration(800);
                                                findViewById(R.id.root_layout).startAnimation(animation);
                                                finish();
                                            }
                                        });
                                    }
                                }, 200);
                            }

                            @Override
                            public void run() {
                                chronometer.stop();
                                callDruationText = chronometer.getText().toString();
                                String st1 = getResources().getString(R.string.Refused);
                                String st2 = getResources().getString(R.string.The_other_party_refused_to_accept);
                                String st3 = getResources().getString(R.string.Connection_failure);
                                String st4 = getResources().getString(R.string.The_other_party_is_not_online);
                                String st5 = getResources().getString(R.string.The_other_is_on_the_phone_please);

                                String st6 = getResources().getString(R.string.The_other_party_did_not_answer_new);
                                String st7 = getResources().getString(R.string.hang_up);
                                String st8 = getResources().getString(R.string.The_other_is_hang_up);

                                String st9 = getResources().getString(R.string.did_not_answer);
                                String st10 = getResources().getString(R.string.Has_been_cancelled);
                                String st11 = getResources().getString(R.string.hang_up);

                                if (fError == CallError.REJECTED) {
                                    callingState = CallingState.BEREFUSED;
                                    callStateTextView.setText(st2);
                                } else if (fError == CallError.ERROR_TRANSPORT) {
                                    callStateTextView.setText(st3);
                                } else if (fError == CallError.ERROR_UNAVAILABLE) {
                                    callingState = CallingState.OFFLINE;
                                    callStateTextView.setText(st4);
                                } else if (fError == CallError.ERROR_BUSY) {
                                    callingState = CallingState.BUSY;
                                    callStateTextView.setText(st5);
                                } else if (fError == CallError.ERROR_NORESPONSE) {
                                    callingState = CallingState.NO_RESPONSE;
                                    callStateTextView.setText(st6);
                                } else if (fError == CallError.ERROR_LOCAL_SDK_VERSION_OUTDATED || fError == CallError.ERROR_REMOTE_SDK_VERSION_OUTDATED){
                                    callingState = CallingState.VERSION_NOT_SAME;
                                    callStateTextView.setText("通话协议版本不一致");
                                } else {
                                    if (isRefused) {
                                        callingState = CallingState.REFUSED;
                                        callStateTextView.setText(st1);
                                    }
                                    else if (isAnswered) {
                                        callingState = CallingState.NORMAL;
                                        if (endCallTriggerByMe) {
//                                        callStateTextView.setText(st7);
                                        } else {
                                            callStateTextView.setText(st8);
                                        }
                                    } else {
                                        if (isInComingCall) {
                                            callingState = CallingState.UNANSWERED;
                                            callStateTextView.setText(st9);
                                        } else {
                                            if (callingState != CallingState.NORMAL) {
                                                callingState = CallingState.CANCELLED;
                                                callStateTextView.setText(st10);
                                            }else {
                                                callStateTextView.setText(st11);
                                            }
                                        }
                                    }
                                }
                                postDelayedCloseMsg();
                            }

                        });

                        break;

                    default:
                        break;
                }

            }
        };
        EMClient.getInstance().callManager().addCallStateChangeListener(callStateListener);
    }

    void removeCallStateListener() {
        EMClient.getInstance().callManager().removeCallStateChangeListener(callStateListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_refuse_call:
                isRefused = true;
                refuseBtn.setEnabled(false);
                handler.sendEmptyMessage(MSG_CALL_REJECT);
                break;

            case R.id.btn_answer_call:
                answerBtn.setEnabled(false);
                closeSpeakerOn();
                // TODO: 2018/10/23 添加修改状态
                callStateTextView.setText("正在接听...");
                comingBtnContainer.setVisibility(View.INVISIBLE);
                hangupBtn.setVisibility(View.VISIBLE);
                voiceContronlLayout.setVisibility(View.VISIBLE);
                handler.sendEmptyMessage(MSG_CALL_ANSWER);
                break;
            case R.id.btn_hangup_call:
                final CustomDialog.Builder builder = new CustomDialog.Builder(VoiceCallActivity.this);
                builder.setMessage("是否确认挂断？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        hangupBtn.setEnabled(false);
                        chronometer.stop();
                        endCallTriggerByMe = true;
                        callStateTextView.setText(getResources().getString(R.string.hanging_up));
                        handler.sendEmptyMessage(MSG_CALL_END);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();

                break;

            case R.id.iv_mute:
                if (isMuteState) {
                    muteImage.setImageResource(R.drawable.em_icon_mute_normal);
                    try {
                        EMClient.getInstance().callManager().resumeVoiceTransfer();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                    isMuteState = false;
                } else {
                    muteImage.setImageResource(R.drawable.em_icon_mute_on);
                    try {
                        EMClient.getInstance().callManager().pauseVoiceTransfer();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                    isMuteState = true;
                }
                break;
            case R.id.iv_handsfree:
                if (isHandsfreeState) {
                    handsFreeImage.setImageResource(R.drawable.em_icon_speaker_normal);
                    closeSpeakerOn();
                    isHandsfreeState = false;
                } else {
                    handsFreeImage.setImageResource(R.drawable.em_icon_speaker_on);
                    openSpeakerOn();
                    isHandsfreeState = true;
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
      //  DemoHelper.getInstance().isVoiceCalling = false;
        stopMonitor();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        callDruationText = chronometer.getText().toString();
    }

    /**
     * for debug & testing, you can remove this when release
     */
    void startMonitor(){
        monitor = true;
        new Thread(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
//                        ((TextView)findViewById(R.id.tv_is_p2p)).setText(EMClient.getInstance().callManager().isDirectCall()
//                                ? R.string.direct_call : R.string.relay_call);
                    }
                });
                while(monitor){
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }, "CallMonitor").start();
    }

    void stopMonitor() {

    }
    private void Networkequest(Map<String, String> parmer, final String str) {

        ((App)VoiceCallActivity.this.getApplication()).getAppCommponent().getAPIService()
                .updateConsultState(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {


                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("Response:" + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(HttpResult<String> userBeanHttpResult) {
                        LogUtils.e("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (!TextUtils.isEmpty(str) && str.equals("1")) {
                                ToastUtils.showToast(VoiceCallActivity.this, "结束咨询");
                                EventBus.getDefault().post(new FirstEvent("close_advisory"));
                                //结束咨询 删除聊天记录
                                // EMClient.getInstance().chatManager().deleteConversation(toChatUsername, true);
                                VoiceCallActivity.this.finish();
                            }


                        }
                    }
                });


    }

    private void changeData() {
        UserBean userBean1 = SpSingleInstance.getSpSingleInstance().getUserBean();
        if ("1".equals(userBean1.getDoctor_type())&&isgoning){
            Map<String, String> mMap = new HashMap<>();
            mMap.put("member_id", userBean1.getMember_id());
            mMap.put("member_token", userBean1.getMember_token());
            mMap.put("consult_record_id", consultrecordid);
            mMap.put("state_show", "已结束");
            mMap.put("is_done", "1");
            Networkequest(mMap, "1");
        }



    }

}
