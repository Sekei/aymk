package com.live.tv.mvp.activity.live;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.activity.live.bean.BeautyDialogFragment;
import com.live.tv.mvp.activity.live.ui.CameraPreviewFrameView;
import com.live.tv.mvp.activity.live.widget.NewLiveRoomFragment;
import com.live.tv.mvp.base.BaseActivity;
import com.live.tv.mvp.presenter.live.PushFlowPresenter;
import com.live.tv.mvp.view.live.IPushFlowView;
import com.live.tv.util.SpSingleInstance;
import com.qiniu.pili.droid.rtcstreaming.RTCAudioLevelCallback;
import com.qiniu.pili.droid.rtcstreaming.RTCConferenceOptions;
import com.qiniu.pili.droid.rtcstreaming.RTCConferenceState;
import com.qiniu.pili.droid.rtcstreaming.RTCConferenceStateChangedListener;
import com.qiniu.pili.droid.rtcstreaming.RTCFrameMixedCallback;
import com.qiniu.pili.droid.rtcstreaming.RTCMediaStreamingManager;
import com.qiniu.pili.droid.rtcstreaming.RTCRemoteWindowEventListener;
import com.qiniu.pili.droid.rtcstreaming.RTCStreamStatsCallback;
import com.qiniu.pili.droid.rtcstreaming.RTCUserEventListener;
import com.qiniu.pili.droid.rtcstreaming.RTCVideoWindow;
import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingSessionListener;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.live.tv.Constants.MESSAGE_ID_RECONNECTING;

/**
 * 推流端
 */
public class PushFlowActivity extends BaseActivity<IPushFlowView, PushFlowPresenter> implements IPushFlowView, BeautyDialogFragment.OnBeautyParamsChangeListener {

    private static final String TAG = "dfc";
    private CameraPreviewFrameView mCameraPreviewFrameView;
    private RTCMediaStreamingManager mRTCStreamingManager;
    private NewLiveRoomFragment liveRoomFragment;
    private int mRole;//角色 主播 附主播  观众
    private String mRoomName;

    private boolean mIsPreviewMirror = false;
    private boolean mIsEncodingMirror = false;
    private boolean mIsSpeakerMuted = false;
    private boolean mIsAudioLevelCallbackEnabled = false;
    private boolean mIsPictureStreaming = false;
    private boolean mIsPreviewOnTop = false;
    private boolean mIsWindowAOnBottom = false;
    private boolean mIsInReadyState = true;
    private boolean mIsActivityPaused = true;
    private boolean mIsPublishStreamStarted = false;
    private boolean mIsConferenceStarted = false;
    private CameraStreamingSetting cameraStreamingSetting;
    private int mEncodingFps = 16;
    private int mEncodingBitrate = 1000 * 1024;
    private int mCurrentZoom = 0;
    private int mMaxZoom = 0;

    private int mCurrentCamFacingIndex;
    private ProgressDialog mProgressDialog;
    private String mRemoteUserId;
    private String roomId;
    private String liveId;
    private String stream_id;

    private UserBean userBean;

    private String push_address = "rtmp://fanlilive.tstmobile.com/vxiu1/php-sdk-test1527064942?e=1527068542&token=pR_CsEkFcTn1Kgf8ZNIh2zUB_w8bzaeLYEgjBItT:xK9DbPBBbqOgqzXFPCmRn0V6OEI=";
    StreamingProfile mStreamingProfile;
    //美颜
    private BeautyDialogFragment mBeautyDialogFragment;
    private BeautyDialogFragment.BeautyParams mBeautyParams = new BeautyDialogFragment.BeautyParams();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public int getRootViewId() {

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        return R.layout.activity_push_flow;

    }


    @Override
    public void initUI() {


        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        roomId = getIntent().getStringExtra("roomId");
        stream_id = getIntent().getStringExtra("stream_id");
        liveId = getIntent().getStringExtra("liveId");


        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//圆环风格


        /**
         * Step 1: find & init views
         */
        mCameraPreviewFrameView = (CameraPreviewFrameView) findViewById(R.id.cameraPreview_surfaceView);


        mRole = 0x01;
        mRoomName = getIntent().getStringExtra("roomName");
        boolean isSwCodec = getIntent().getBooleanExtra("swcodec", true);
        boolean isLandscape = getIntent().getBooleanExtra("orientation", false);
        setRequestedOrientation(isLandscape ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        boolean isBeautyEnabled = getIntent().getBooleanExtra("beauty", false);
        boolean isWaterMarkEnabled = getIntent().getBooleanExtra("watermark", false);
        boolean isQuicEnable = getIntent().getBooleanExtra("quic", false);
        boolean isDebugModeEnabled = getIntent().getBooleanExtra("debugMode", false);
        boolean isCustomSettingEnabled = getIntent().getBooleanExtra("customSetting", false);
        boolean isStatsEnabled = getIntent().getBooleanExtra("enableStats", false);
        mIsAudioLevelCallbackEnabled = getIntent().getBooleanExtra("audioLevelCallback", false);


        CameraStreamingSetting.CAMERA_FACING_ID facingId = chooseCameraFacingId();
        mCurrentCamFacingIndex = facingId.ordinal();

        /**
         * Step 2: config camera & microphone settings
         */
        cameraStreamingSetting = new CameraStreamingSetting();
        cameraStreamingSetting
                .setCameraFacingId(facingId)
                // .setCameraFacingId(CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK)
                .setContinuousFocusModeEnabled(true)
                .setRecordingHint(false)
                .setResetTouchFocusDelayInMs(3000)
                .setFocusMode(CameraStreamingSetting.FOCUS_MODE_CONTINUOUS_PICTURE)
                .setCameraPrvSizeLevel(CameraStreamingSetting.PREVIEW_SIZE_LEVEL.LARGE)
                .setCameraPrvSizeRatio(CameraStreamingSetting.PREVIEW_SIZE_RATIO.RATIO_16_9)
                .setPreviewAdaptToEncodingSize(false);

            cameraStreamingSetting.setBuiltInFaceBeautyEnabled(true); // Using sdk built in face beauty algorithm
            cameraStreamingSetting.setFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(1.0f, 1.0f, 0.8f)); // 美颜设置
            cameraStreamingSetting.setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY); // set the beauty on/off

        /**
         * Step 3: create streaming manager and set listeners
         */
        AVCodecType codecType = isSwCodec ? AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC : AVCodecType.HW_VIDEO_YUV_AS_INPUT_WITH_HW_AUDIO_CODEC;
        mRTCStreamingManager = new RTCMediaStreamingManager(getApplicationContext(), mCameraPreviewFrameView, codecType);
        mRTCStreamingManager.setConferenceStateListener(mRTCStreamingStateChangedListener);
        mRTCStreamingManager.setRemoteWindowEventListener(mRTCRemoteWindowEventListener);
        mRTCStreamingManager.setUserEventListener(mRTCUserEventListener);
        mRTCStreamingManager.setDebugLoggingEnabled(isDebugModeEnabled);

        if (mIsAudioLevelCallbackEnabled) {
            mRTCStreamingManager.setAudioLevelCallback(mRTCAudioLevelCallback);
        }

        /**
         * Step 4: set conference options
         */
        RTCConferenceOptions options = new RTCConferenceOptions();

        options.setVideoEncodingSizeRatio(RTCConferenceOptions.VIDEO_ENCODING_SIZE_RATIO.RATIO_16_9);
        options.setVideoEncodingSizeLevel(RTCConferenceOptions.VIDEO_ENCODING_SIZE_HEIGHT_720);
        options.setVideoBitrateRange(500 * 1024, 800 * 1024);
        // 15 fps is enough
        options.setVideoEncodingFps(mEncodingFps);
        options.setHWCodecEnabled(!isSwCodec);
        if (isStatsEnabled) {
            options.setStreamStatsInterval(500);
            mRTCStreamingManager.setRTCStreamStatsCallback(mRTCStreamStatsCallback);
        }

        if (isLandscape) {
            options.setVideoEncodingOrientation(RTCConferenceOptions.VIDEO_ENCODING_ORIENTATION.LAND);
        } else {
            options.setVideoEncodingOrientation(RTCConferenceOptions.VIDEO_ENCODING_ORIENTATION.PORT);
        }

        mRTCStreamingManager.setConferenceOptions(options);


        /**
         * Step 8: do prepare, anchor should config streaming profile first
         */
        mRTCStreamingManager.setMixedFrameCallback(new RTCFrameMixedCallback() {
            @Override
            public void onVideoFrameMixed(byte[] data, int width, int height, int fmt, long timestamp) {
//                Log.d(TAG, "Mixed video: " + data.toString() + "  Format: " + fmt);
            }

            @Override
            public void onAudioFrameMixed(byte[] pcm, long timestamp) {
//                Log.i(TAG, "Mixed audio");
            }
        });

//
//            mRTCStreamingManager.setStreamingStateListener(mStreamingStateChangedListener);
//            mRTCStreamingManager.setStreamingSessionListener(mStreamingSessionListener);
//
//            mStreamingProfile = new StreamingProfile();
//            mStreamingProfile.setVideoQuality(StreamingProfile.VIDEO_QUALITY_MEDIUM3)
//                    .setAudioQuality(StreamingProfile.AUDIO_QUALITY_HIGH1)
//                    .setEncoderRCMode(StreamingProfile.EncoderRCModes.QUALITY_PRIORITY)
//                    .setFpsControllerEnable(true)
//                    .setQuicEnable(isQuicEnable)
//                    .setYuvFilterMode(StreamingProfile.YuvFilterMode.None)
//                    .setPictureStreamingResourceId(R.drawable.login_weixin)
//                    .setSendingBufferProfile(new StreamingProfile.SendingBufferProfile(0.2f, 0.8f, 3.0f, 20 * 1000))
//                    .setBitrateAdjustMode(StreamingProfile.BitrateAdjustMode.Auto);
//            mRTCStreamingManager.prepare(cameraStreamingSetting, null, null, mStreamingProfile);
//
        mRTCStreamingManager.setStreamingStateListener(mStreamingStateChangedListener);
        mRTCStreamingManager.setStreamingSessionListener(mStreamingSessionListener);
        mStreamingProfile = new StreamingProfile();
        mStreamingProfile.setVideoQuality(StreamingProfile.VIDEO_QUALITY_HIGH3)
                .setAudioQuality(StreamingProfile.AUDIO_QUALITY_MEDIUM1)
                .setEncoderRCMode(StreamingProfile.EncoderRCModes.QUALITY_PRIORITY)
                .setPreferredVideoEncodingSize( options.getVideoEncodingHeight(),options.getVideoEncodingWidth())
                .setEncodingSizeLevel(StreamingProfile.VIDEO_ENCODING_HEIGHT_720)
                .setFpsControllerEnable(true)
                .setSendingBufferProfile(new StreamingProfile.SendingBufferProfile(0.2f, 0.8f, 3.0f, 20 * 1000));
        mStreamingProfile.setEncodingOrientation(StreamingProfile.ENCODING_ORIENTATION.PORT);
        mRTCStreamingManager.prepare(cameraStreamingSetting, null, null, mStreamingProfile);


        mBeautyDialogFragment = new BeautyDialogFragment();
        mBeautyDialogFragment.setBeautyParamsListner(mBeautyParams, this);

        showLiveRoomFragment();

        /**
         * 点击onclick
         * fragment中的监听
         */

        liveRoomFragment.setFragmentViewOnclick(new NewLiveRoomFragment.fragmentViewOnclick() {
            @Override
            public void viewOnclick(int id) {

                switch (id) {
                    case R.id.img_camera://切换摄像头

                        mCurrentCamFacingIndex = (mCurrentCamFacingIndex + 1) % CameraStreamingSetting.getNumberOfCameras();
                        CameraStreamingSetting.CAMERA_FACING_ID facingId;
                        if (mCurrentCamFacingIndex == CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK.ordinal()) {
                            facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK;
                        } else if (mCurrentCamFacingIndex == CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT.ordinal()) {
                            facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT;
                        } else {
                            facingId = CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_3RD;
                        }
                        Log.i(TAG, "switchCamera:" + facingId);
                        mRTCStreamingManager.switchCamera(facingId);


                        break;
                    case R.id.img_close:
                        stopPublishStreaming();
                        Map<String, String> mMap = new HashMap<String, String>();
                        mMap.put("member_id", userBean.getMember_id());
                        mMap.put("member_token", userBean.getMember_token());
                        mMap.put("room_id", roomId);
                        mMap.put("stream_id", stream_id);
                        getPresenter().closeQiNiuLive(mMap);
                        finish();
                        break;


                    case R.id.bt_bef:
                        if (mBeautyDialogFragment.isAdded()) {
                            mBeautyDialogFragment.dismiss();
                        } else {
                            mBeautyDialogFragment.show(getFragmentManager(), "");
                        }


                        break;


                    case 0:
                        stopPublishStreaming();
                        Map<String, String> mMaps = new HashMap<String, String>();
                        mMaps.put("member_id", userBean.getMember_id());
                        mMaps.put("member_token", userBean.getMember_token());
                        mMaps.put("room_id", roomId);
                        mMaps.put("stream_id", stream_id);
                        getPresenter().closeQiNiuLive(mMaps);
                        finish();
                        break;
                }
            }
        });


    }

    @Override
    public void initData() {

    }

    /**
     * 开始推流
     *
     * @return
     */

    private boolean startPublishStreaming() {
        if (!isNetworkAvailable(this)) {
            Toast.makeText(PushFlowActivity.this, "network is unavailable!!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mIsPublishStreamStarted) {
            return true;
        }
        if (!mIsInReadyState) {
            ToastUtils.showToast(this, "当前不在就绪状态，不能开启直播 ");
            return false;
        }
        mProgressDialog.setMessage("正在准备推流... ");
        mProgressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                startPublishStreamingInternal();
            }
        }).start();
        return true;
    }


    private boolean startPublishStreamingInternal() {
        String publishAddr = getIntent().getStringExtra("live_push_url");
        // String publishAddr = "rtmp://fanlilive.tstmobile.com/vxiu1/php-sdk-test1527064942?e=1527068542&token=pR_CsEkFcTn1Kgf8ZNIh2zUB_w8bzaeLYEgjBItT:xK9DbPBBbqOgqzXFPCmRn0V6OEI=";
        if (publishAddr == null) {
            dismissProgressDialog();
            PushFlowActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showToast(PushFlowActivity.this, "无法获取房间信息/推流地址  !", Toast.LENGTH_SHORT);
                }
            });

            return false;
        }

        try {
            mStreamingProfile.setPublishUrl(publishAddr);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            dismissProgressDialog();

            PushFlowActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showToast(PushFlowActivity.this, "无效的推流地址 !", Toast.LENGTH_SHORT);
                }
            });


            return false;
        }

        mRTCStreamingManager.setStreamingProfile(mStreamingProfile);
        if (!mRTCStreamingManager.startStreaming()) {
            dismissProgressDialog();
            PushFlowActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showToast(PushFlowActivity.this, "无法成功开启直播 ！", Toast.LENGTH_SHORT);
                }
            });

            return false;
        }
        dismissProgressDialog();


        PushFlowActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(PushFlowActivity.this, "开始直播", Toast.LENGTH_SHORT);
            }
        });

        mIsPublishStreamStarted = true;
        /**
         * Because `startPublishStreaming` need a long time in some weak network
         * So we should check if the activity paused.
         */
        if (mIsActivityPaused) {
            stopPublishStreaming();
        }
        return true;
    }

    /**
     * 停止推流
     *
     * @return
     */
    private boolean stopPublishStreaming() {
        if (!mIsPublishStreamStarted) {
            return true;
        }
        mRTCStreamingManager.stopStreaming();
        mIsPublishStreamStarted = false;
        ToastUtils.showToast(this, "停止直播", Toast.LENGTH_SHORT);
//        updateControlButtonText();
        return false;
    }

    private void dismissProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressDialog.dismiss();
            }
        });
    }

    private RTCConferenceStateChangedListener mRTCStreamingStateChangedListener = new RTCConferenceStateChangedListener() {
        @Override
        public void onConferenceStateChanged(RTCConferenceState state, int extra) {
            switch (state) {
                case READY:
                    // You must `StartConference` after `Ready`
                    mIsInReadyState = true;
                    mMaxZoom = mRTCStreamingManager.getMaxZoom();
                    //ToastUtils.showToast(this, getString(R.string.ready));
                    break;
                case RECONNECTING:
                    // ToastUtils.showToast(this,getString(R.string.reconnecting));
                    break;
                case RECONNECTED:
                    //showToast(getString(R.string.reconnected), Toast.LENGTH_SHORT);
                    break;
                case RECONNECT_FAIL:
                    //showToast(getString(R.string.reconnect_failed), Toast.LENGTH_SHORT);
                    break;
                case VIDEO_PUBLISH_FAILED:
                case AUDIO_PUBLISH_FAILED:
                    // showToast(getString(R.string.failed_to_publish_av_to_rtc) + extra, Toast.LENGTH_SHORT);
                    finish();
                    break;
                case VIDEO_PUBLISH_SUCCESS:
                    //showToast(getString(R.string.success_publish_video_to_rtc), Toast.LENGTH_SHORT);
                    break;
                case AUDIO_PUBLISH_SUCCESS:
                    // showToast(getString(R.string.success_publish_audio_to_rtc), Toast.LENGTH_SHORT);
                    break;
                case USER_JOINED_AGAIN:
                    //showToast(getString(R.string.user_join_other_where), Toast.LENGTH_SHORT);
                    finish();
                    break;
                case USER_KICKOUT_BY_HOST:
                    //showToast(getString(R.string.user_kickout_by_host), Toast.LENGTH_SHORT);
                    finish();
                    break;
                case OPEN_CAMERA_FAIL:
                    //showToast(getString(R.string.failed_open_camera), Toast.LENGTH_SHORT);
                    break;
                case AUDIO_RECORDING_FAIL:
                    //showToast(getString(R.string.failed_open_microphone), Toast.LENGTH_SHORT);
                    break;
                default:
                    return;
            }
        }
    };


    private RTCRemoteWindowEventListener mRTCRemoteWindowEventListener = new RTCRemoteWindowEventListener() {
        @Override
        public void onRemoteWindowAttached(RTCVideoWindow window, String remoteUserId) {
            Log.d(TAG, "onRemoteWindowAttached: " + remoteUserId);
            mRemoteUserId = remoteUserId;
            if (mIsPictureStreaming) {
                mRTCStreamingManager.unsubscribeVideoStream(mRemoteUserId);
            }
        }

        @Override
        public void onRemoteWindowDetached(RTCVideoWindow window, String remoteUserId) {
            Log.d(TAG, "onRemoteWindowDetached: " + remoteUserId);
            if (!mIsPictureStreaming) {
                mRemoteUserId = null;
            }
        }

        @Override
        public void onFirstRemoteFrameArrived(String remoteUserId) {
            Log.d(TAG, "onFirstRemoteFrameArrived: " + remoteUserId);
        }
    };


    private RTCUserEventListener mRTCUserEventListener = new RTCUserEventListener() {
        @Override
        public void onUserJoinConference(String remoteUserId) {
            Log.d(TAG, "onUserJoinConference: " + remoteUserId);
        }

        @Override
        public void onUserLeaveConference(String remoteUserId) {
            Log.d(TAG, "onUserLeaveConference: " + remoteUserId);
        }
    };
    private RTCAudioLevelCallback mRTCAudioLevelCallback = new RTCAudioLevelCallback() {
        @Override
        public void onAudioLevelChanged(String userId, int level) {
            Log.d(TAG, "onAudioLevelChanged: userId = " + userId + " level = " + level);
        }
    };
    private RTCStreamStatsCallback mRTCStreamStatsCallback = new RTCStreamStatsCallback() {
        @Override
        public void onStreamStatsChanged(String userId, int statsType, int value) {
            Log.i(TAG, "userId = " + userId + "statsType = " + statsType + " value = " + value);
        }
    };
    private StreamingStateChangedListener mStreamingStateChangedListener = new StreamingStateChangedListener() {
        @Override
        public void onStateChanged(final StreamingState state, Object o) {
            switch (state) {
                case PREPARING:
                    // setStatusText(getString(R.string.preparing));
                    Log.d(TAG, "onStateChanged state:" + "preparing");
                    break;
                case READY:
                    mIsInReadyState = true;
                    mMaxZoom = mRTCStreamingManager.getMaxZoom();
                    // setStatusText(getString(R.string.ready));

                    PushFlowActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startPublishStreaming();
                        }
                    });


                    Log.d(TAG, "onStateChanged state:" + "ready");
                    break;
                case CONNECTING:
                    Log.d(TAG, "onStateChanged state:" + "connecting");
                    break;
                case STREAMING:
                    //setStatusText(getString(R.string.streaming));
                    Log.d(TAG, "onStateChanged state:" + "streaming");
                    break;
                case SHUTDOWN:
                    mIsInReadyState = true;
                    // setStatusText(getString(R.string.ready));
                    Log.d(TAG, "onStateChanged state:" + "shutdown");
                    break;
                case UNKNOWN:
                    Log.d(TAG, "onStateChanged state:" + "unknown");
                    break;
                case SENDING_BUFFER_EMPTY:
                    Log.d(TAG, "onStateChanged state:" + "sending buffer empty");
                    break;
                case SENDING_BUFFER_FULL:
                    Log.d(TAG, "onStateChanged state:" + "sending buffer full");
                    break;
                case OPEN_CAMERA_FAIL:
                    Log.d(TAG, "onStateChanged state:" + "open camera failed");
                    //showToast(getString(R.string.failed_open_camera), Toast.LENGTH_SHORT);
                    break;
                case AUDIO_RECORDING_FAIL:
                    Log.d(TAG, "onStateChanged state:" + "audio recording failed");
                    // showToast(getString(R.string.failed_open_microphone), Toast.LENGTH_SHORT);
                    break;
                case IOERROR:
                    /**
                     * Network-connection is unavailable when `startStreaming`.
                     * You can do reconnecting or just finish the streaming
                     */
                    Log.d(TAG, "onStateChanged state:" + "io error");
                    // showToast(getString(R.string.io_error), Toast.LENGTH_SHORT);
                    sendReconnectMessage();
                    // stopPublishStreaming();
                    break;
                case DISCONNECTED:
                    /**
                     * Network-connection is broken after `startStreaming`.
                     * You can do reconnecting in `onRestartStreamingHandled`
                     */
                    Log.d(TAG, "onStateChanged state:" + "disconnected");
                    // setStatusText(getString(R.string.disconnected));
                    // we will process this state in `onRestartStreamingHandled`
                    break;
            }
        }
    };


    private StreamingSessionListener mStreamingSessionListener = new StreamingSessionListener() {
        @Override
        public boolean onRecordAudioFailedHandled(int code) {
            return false;
        }

        /**
         * When the network-connection is broken, StreamingState#DISCONNECTED will notified first,
         * and then invoked this method if the environment of restart streaming is ready.
         *
         * @return true means you handled the event; otherwise, given up and then StreamingState#SHUTDOWN
         * will be notified.
         */
        @Override
        public boolean onRestartStreamingHandled(int code) {
            Log.d(TAG, "onRestartStreamingHandled, reconnect ...");
            return mRTCStreamingManager.startStreaming();
        }

        @Override
        public Camera.Size onPreviewSizeSelected(List<Camera.Size> list) {
            for (Camera.Size size : list) {
                if (size.height >= 480) {
                    return size;
                }
            }
            return null;
        }

        @Override
        public int onPreviewFpsSelected(List<int[]> list) {
            return -1;
        }
    };

    private void setStatusText(final String status) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                mStatusTextView.setText(status);
            }
        });
    }

    private CameraStreamingSetting.CAMERA_FACING_ID chooseCameraFacingId() {
        if (CameraStreamingSetting.hasCameraFacing(CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_3RD)) {
            return CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_3RD;
        } else if (CameraStreamingSetting.hasCameraFacing(CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT)) {
            return CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT;
        } else {
            return CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK;
        }
    }


    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != MESSAGE_ID_RECONNECTING || mIsActivityPaused || !mIsPublishStreamStarted) {
                return;
            }
            if (!isNetworkAvailable(PushFlowActivity.this)) {
                sendReconnectMessage();
                return;
            }
            Log.d(TAG, "do reconnecting ...");
            mRTCStreamingManager.startStreaming();
        }
    };

    private void sendReconnectMessage() {
        ToastUtils.showToast(this, "正在重连...");
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_ID_RECONNECTING), 2000);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActivityPaused = false;
        /**
         * Step 9: You must start capture before conference or streaming
         * You will receive `Ready` state callback when capture started success
         */
        mRTCStreamingManager.startCapture();
    }

    @NonNull
    @Override
    public PushFlowPresenter createPresenter() {
        return new PushFlowPresenter(getApp());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsActivityPaused = true;
        /**
         * Step 10: You must stop capture, stop conference, stop streaming when activity paused
         */
        mRTCStreamingManager.stopCapture();
        mIsInReadyState = false;
//        stopConference();
        stopPublishStreaming();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * Step 11: You must call destroy to release some resources when activity destroyed
         */
        mRTCStreamingManager.destroy();
    }


    ///显示上层fragment
    public void showLiveRoomFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (liveRoomFragment == null) {
            liveRoomFragment = NewLiveRoomFragment.newInstance("push", roomId, liveId);
            fragmentTransaction.add(R.id.content, liveRoomFragment);
        }
        commitShowFragment(fragmentTransaction, liveRoomFragment);
    }

    public void commitShowFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        hideFragment(fragmentTransaction, liveRoomFragment);

    }

    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }


    /**
     * 关闭直播
     *
     * @param data
     */
    @Override
    public void closeQiNiuLive(String data) {

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

    @Override
    public void onBackPressed() {


        //正在直播时，按返回键，弹出提示

        liveRoomFragment.cancelDialog(0);


    }

    /**
     * 美颜改变监听
     *
     * @param params
     * @param key
     */
    @Override
    public void onBeautyParamsChange(BeautyDialogFragment.BeautyParams params, int key) {
        switch (key) {
            case BeautyDialogFragment.BEAUTYPARAM_BEAUTY:
                // 设置美颜参数，三个参数分别代表：磨皮、美白、红润
                CameraStreamingSetting.FaceBeautySetting fbSetting = cameraStreamingSetting.getFaceBeautySetting();
                fbSetting.whiten = params.mBeautyProgress / 10.0f;
                fbSetting.beautyLevel = params.mBeautyProgress / 10.0f;
                fbSetting.redden = params.mBeautyProgress / 10.0f;


                mRTCStreamingManager.updateFaceBeautySetting(fbSetting);
            default:
                break;
        }
    }


}
