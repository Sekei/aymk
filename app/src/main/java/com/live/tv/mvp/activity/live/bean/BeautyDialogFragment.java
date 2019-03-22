package com.live.tv.mvp.activity.live.bean;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.ysjk.health.iemk.R;

/**
 * pitu pkg start, P图动效打包专用，只编译，不打包源码，不要在此区域添加代码pitu pkg end
 **/

/**
 * pitu pkg end
 **/


public class BeautyDialogFragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener {

    private static final String TAG = BeautyDialogFragment.class.getSimpleName();

    public static final int BEAUTYPARAM_BEAUTY = 1;
    public static final int BEAUTYPARAM_WHITE = 2;
    public static final int BEAUTYPARAM_FACE_LIFT = 3;
    public static final int BEAUTYPARAM_BIG_EYE = 4;
    public static final int BEAUTYPARAM_FILTER = 5;
    public static final int BEAUTYPARAM_MOTION_TMPL = 6;
    public static final int BEAUTYPARAM_GREEN = 7;

    static public class BeautyParams {
        public int mBeautyProgress = 5;
        public int mWhiteProgress = 3;
    }

    public interface OnBeautyParamsChangeListener {
        void onBeautyParamsChange(BeautyParams params, int key);
    }

    private LinearLayout mBeautyLayout;

    private SeekBar mBeautySeekbar;

    private BeautyParams mBeautyParams;
    private OnBeautyParamsChangeListener mBeautyParamsChangeListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_beauty_area);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        Log.d(TAG, "create fragment");
        mBeautyLayout = (LinearLayout) dialog.findViewById(R.id.layoutBeauty);


        mBeautySeekbar = (SeekBar) dialog.findViewById(R.id.beauty_seekbar);
        mBeautySeekbar.setOnSeekBarChangeListener(this);
        mBeautySeekbar.setProgress(mBeautyParams.mBeautyProgress * mBeautySeekbar.getMax() / 9);


        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);

        /**pitu pkg end**/
        return dialog;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.beauty_seekbar:
                mBeautyParams.mBeautyProgress = TCUtils.filtNumber(9, mBeautySeekbar.getMax(), progress);
                if (mBeautyParamsChangeListener instanceof OnBeautyParamsChangeListener) {
                    mBeautyParamsChangeListener.onBeautyParamsChange(mBeautyParams, BEAUTYPARAM_BEAUTY);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void setBeautyParamsListner(BeautyParams params, OnBeautyParamsChangeListener listener) {
        mBeautyParams = params;
        mBeautyParamsChangeListener = listener;
        //当BeautyDialogFragment重置时，先刷新一遍配置
        if (mBeautyParamsChangeListener instanceof OnBeautyParamsChangeListener) {
            mBeautyParamsChangeListener.onBeautyParamsChange(mBeautyParams, BEAUTYPARAM_BEAUTY);/*
            mBeautyParamsChangeListener.onBeautyParamsChange(mBeautyParams, BEAUTYPARAM_WHITE);
            mBeautyParamsChangeListener.onBeautyParamsChange(mBeautyParams, BEAUTYPARAM_MOTION_TMPL);*/
        }
    }

}