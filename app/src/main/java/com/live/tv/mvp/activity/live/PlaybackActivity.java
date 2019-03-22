package com.live.tv.mvp.activity.live;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.activity.live.widget.NewLiveRoomFragment;
import com.live.tv.mvp.base.BaseActivity;
import com.live.tv.mvp.presenter.live.PlayBackPresenter;
import com.live.tv.mvp.view.live.IPlayBackView;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.widget.PLVideoView;

import java.util.HashMap;
import java.util.Map;

import static com.live.tv.Constants.MESSAGE_ID_RECONNECTING;

/**
 * 播放端
 */

public class PlaybackActivity extends BaseActivity<IPlayBackView, PlayBackPresenter> implements IPlayBackView{

    private static final String TAG = PlaybackActivity.class.getSimpleName();
    private PLVideoView mVideoView;
    private NewLiveRoomFragment liveRoomFragment;
    private String roomId;
    private String liveId;
    private String stream_id;
    String videoPath="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        return R.layout.activity_play_back;
    }

    @Override
    public void initUI() {
         videoPath = getIntent().getStringExtra("videoPath");
        roomId = getIntent().getStringExtra("roomId");
        stream_id = getIntent().getStringExtra("stream_id");
        liveId = getIntent().getStringExtra("liveId");

        mVideoView = (PLVideoView) findViewById(R.id.VideoView);
        View mCoverView = findViewById(R.id.CoverView);
        View loadingLayout = findViewById(R.id.loading_layout);


       // mVideoView.setCoverView(mCoverView);
        mVideoView.setCoverView(loadingLayout);
//        mVideoView.setBufferingIndicator(loadingLayout);

        AVOptions options = new AVOptions();
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_MEDIACODEC, 0);
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);
        options.setInteger(AVOptions.KEY_VIDEO_DATA_CALLBACK, 1);
        options.setInteger(AVOptions.KEY_AUDIO_DATA_CALLBACK, 1);
        mVideoView.setAVOptions(options);
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
        // Set some listeners
        mVideoView.setOnInfoListener(mOnInfoListener);
        mVideoView.setOnCompletionListener(mOnCompletionListener);
        mVideoView.setOnErrorListener(mOnErrorListener);
        mVideoView.setVideoPath(videoPath);

        showLiveRoomFragment();


        /**
         * fragment中的监听
         */

        liveRoomFragment.setFragmentViewOnclick(new NewLiveRoomFragment.fragmentViewOnclick() {
            @Override
            public void viewOnclick(int id) {

                switch (id) {
                    case R.id.img_camera:
                        break;
                    case R.id.img_close:
                        finish();
                        break;
                    case 0:
                        finish();
                        break;
                }
            }
        });


    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();
    }

    @NonNull
    @Override
    public PlayBackPresenter createPresenter() {
        return new PlayBackPresenter(getApp());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }


    private PLOnInfoListener mOnInfoListener = new PLOnInfoListener() {
        @Override
        public void onInfo(int what, int extra) {
            Log.i(TAG, "OnInfo, what = " + what + ", extra = " + extra);
            switch (what) {
                case PLOnInfoListener.MEDIA_INFO_BUFFERING_START:
                    break;
                case PLOnInfoListener.MEDIA_INFO_BUFFERING_END:
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_RENDERING_START:
                    //Utils.showToastTips(PLVideoViewActivity.this, "first video render time: " + extra + "ms");
                    break;
                case PLOnInfoListener.MEDIA_INFO_AUDIO_RENDERING_START:
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_FRAME_RENDERING:
                    Log.i(TAG, "video frame rendering, ts = " + extra);
                    break;
                case PLOnInfoListener.MEDIA_INFO_AUDIO_FRAME_RENDERING:
                    Log.i(TAG, "audio frame rendering, ts = " + extra);
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_GOP_TIME:
                    Log.i(TAG, "Gop Time: " + extra);
                    break;
                case PLOnInfoListener.MEDIA_INFO_SWITCHING_SW_DECODE:
                    Log.i(TAG, "Hardware decoding failure, switching software decoding!");
                    break;
                case PLOnInfoListener.MEDIA_INFO_METADATA:
                    Log.i(TAG, mVideoView.getMetadata().toString());
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_BITRATE:
                case PLOnInfoListener.MEDIA_INFO_VIDEO_FPS:

                    break;
                case PLOnInfoListener.MEDIA_INFO_CONNECTED:
                    Log.i(TAG, "Connected !");
                    break;
                case PLOnInfoListener.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                    Log.i(TAG, "Rotation changed: " + extra);
                default:
                    break;
            }
        }
    };


    /**
     * 判断网络是否正常
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }



    private PLOnErrorListener mOnErrorListener = new PLOnErrorListener() {
        @Override
        public boolean onError(int errorCode) {
            Log.e(TAG, "Error happened, errorCode = " + errorCode);
            switch (errorCode) {
                case PLOnErrorListener.ERROR_CODE_IO_ERROR:
                    /**
                     * SDK will do reconnecting automatically
                     */
                    Log.e(TAG, "IO Error!");
                    if (!isNetworkAvailable(PlaybackActivity.this)){
                        //断网，关闭直播
                        ToastUtils.showToast(PlaybackActivity.this, "网络断开");
                        finish();
                    }else {
                        //判断直播是否结束
                        //如果没有结束，开始重连
                        Map<String,String> mMap = new HashMap<>();
                        mMap.put("live_id",liveId);
                        getPresenter().IsLiveEnd(mMap);
                    }
                    break;
                case PLOnErrorListener.ERROR_CODE_OPEN_FAILED:
                   // ToastUtils.showToast(PlaybackActivity.this, "播放器打开失败");
                    finish();
                    break;
                case PLOnErrorListener.ERROR_CODE_SEEK_FAILED:
                    ToastUtils.showToast(PlaybackActivity.this, "拖动失败");
                    break;

                case PLOnErrorListener.ERROR_CODE_HW_DECODE_FAILURE:
                    ToastUtils.showToast(PlaybackActivity.this, "解码失败");
                    break;


                default:
                    // Utils.showToastTips(PLVideoViewActivity.this, "unknown error !");
                    break;
            }

            return true;
        }
    };

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != MESSAGE_ID_RECONNECTING) {
                return;
            }
            if (!isNetworkAvailable(PlaybackActivity.this)) {
                sendReconnectMessage();
                return;
            }
            mVideoView.setVideoPath(videoPath);
            mVideoView.start();
        }
    };

    private void sendReconnectMessage() {
        ToastUtils.showToast(PlaybackActivity.this,"正在重连...");
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_ID_RECONNECTING), 5000);
    }

    private PLOnCompletionListener mOnCompletionListener = new PLOnCompletionListener() {
        @Override
        public void onCompletion() {
            finish();
        }
    };


    public void showLiveRoomFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (liveRoomFragment == null) {
            liveRoomFragment = NewLiveRoomFragment.newInstance("play", roomId, liveId);
            fragmentTransaction.add(R.id.fragmentContent, liveRoomFragment);
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


    @Override
    public void onBackPressed() {


        //正在直播时，按返回键，弹出提示

        liveRoomFragment.cancelDialog(0);


    }

    @Override
    public void IsLiveEnd(String data) {

        if ("open".equals(data)){
            sendReconnectMessage();
        }else {
            ToastUtils.showToast(PlaybackActivity.this,"直播结束");
            finish();

        }

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
}
