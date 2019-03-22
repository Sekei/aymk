package com.live.tv.mvp.activity.live.ui;

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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ysjk.health.iemk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by taoh on 2018/5/8.
 */

public class DialogDashangPayFragment extends DialogFragment {


    Unbinder unbinder;
    public SelectListener selectListener;
    @BindView(R.id.img_close_pay)
    ImageView imgClosePay;
    @BindView(R.id.alipay)
    RadioButton alipay;
    @BindView(R.id.wechat)
    RadioButton wechat;
    @BindView(R.id.wallet)
    RadioButton wallet;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.price)
    TextView price;


    private String price_str = "";

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }

    @OnClick({R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ok:

                if (alipay.isChecked()){
                    selectListener.OnPay(price_str,"alipay");

                }else if (wechat.isChecked()) {
                    selectListener.OnPay(price_str,"wx");

                }else if (wallet.isChecked()){
                    selectListener.OnPay(price_str,"balance_pay");
                }


                break;

        }
    }

    public interface SelectListener {

        void OnPay(String price, String pay_type);
    }

    public void setOnSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dialog_dashang_pay, container, false);
        unbinder = ButterKnife.bind(this, view);
        price_str = getArguments().getString("price");
        price.setText("总计：¥" + price_str);

        imgClosePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startDownAnimation(view);
            }
        });
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
            window.setBackgroundDrawable(new ColorDrawable(0x00000000));
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);


        }

    }


}
