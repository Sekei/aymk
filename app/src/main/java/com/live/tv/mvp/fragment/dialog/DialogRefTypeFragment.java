package com.live.tv.mvp.fragment.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.ysjk.health.iemk.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by taoh on 2018/5/8.
 */

public class DialogRefTypeFragment extends DialogFragment {



    Unbinder unbinder;
    public SelectListener selectListener;
    View view;

    public interface SelectListener {
        void OnSelectListener(String ids, String name);
    }

    public void setOnSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.dialog_ref_type, container, false);
        unbinder = ButterKnife.bind(this, view);

        startUpAnimation(view);
        return view;

    }

    private void startUpAnimation(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        view.startAnimation(slide);
    }

    private void startDownAnimation(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(slide);

}






    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.BOTTOM; // 显示在底部
            params.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度填充满屏
            window.setAttributes(params);
            window.setBackgroundDrawable( new ColorDrawable( 0x00000000 ) );
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
            dialog.getWindow().setLayout( dm.widthPixels, getDialog().getWindow().getAttributes().height );


        }

    }



    @OnClick({R.id.tv_tuihuo, R.id.tv_tuikuan})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_tuihuo://2
                selectListener.OnSelectListener("2","退货退款");
                startDownAnimation(view);
                break;
            case R.id.tv_tuikuan://1
                selectListener.OnSelectListener("1","仅退款");
                startDownAnimation(view);
                break;


        }
    }
}
