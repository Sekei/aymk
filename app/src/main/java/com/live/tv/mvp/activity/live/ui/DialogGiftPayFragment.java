package com.live.tv.mvp.activity.live.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.util.PassWordEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/26
 */

public class DialogGiftPayFragment extends DialogFragment {


    public interface OKOnclickListener {
        void onOk(String pwd);
        void cancel();
    }

    private OKOnclickListener onclickListener;

    public void setOkClickListener(OKOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }

    @BindView(R.id.pwd_et)
    PassWordEditText pwd_et;
    @BindView(R.id.total_price)
    TextView total_price;
    Unbinder unbinder;
    private String price;
    private int type=0;//支付界面消失的状态

    public static DialogGiftPayFragment newInstance(String price) {
        Bundle args = new Bundle();
        DialogGiftPayFragment fragment = new DialogGiftPayFragment();
        fragment.price = price;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        if ("0".equals(type)){
        onclickListener.cancel();}
        super.onDismiss(dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift_pay, container, false);
        unbinder = ButterKnife.bind(this, view);
        setLayout();
        initViews();
        return view;
    }

    private void setLayout() {
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawableResource(R.color.black_transparent);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.dimAmount = 0;
        window.setAttributes(lp);
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);//显
//        InputMethodManager imm = (InputMethodManager)getActivity().
//                getSystemService(getActivity().INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); //显示软键盘
    }

    private void initViews() {
        total_price.setText("¥" + price);
        pwd_et.setOnTextEndListener(new PassWordEditText.OnTextEndListener() {
            @Override
            public void onTextEndListener(String textString) {
                onclickListener.onOk(textString);
                type=1;
                dismiss();
            }
        });

    }


    @OnClick({R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;


        }
    }

}
