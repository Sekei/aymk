package com.live.tv.mvp.activity.live.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *
 * 输入打赏金额
 * @author Created by stone
 * @since 2018/1/12
 */

public class DaShangFragmet extends DialogFragment {

    @BindView(R.id.select_content)
    EditText selectContent;

    public interface OKOnclickListener {
        void onOk(String state);
    }

    private OKOnclickListener onclickListener;

    public void setOkClickListener(OKOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }


    private String price = "0";
    Unbinder unbinder;

    public static DaShangFragmet newInstance(String price) {
        Bundle args = new Bundle();
        DaShangFragmet fragment = new DaShangFragmet();
        fragment.price = price;
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_dashang, container, false);
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
    }

    private void initViews() {
        if (!TextUtils.isEmpty(price)) {
            selectContent.setText(price);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.select_cancel, R.id.select_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_cancel:
                dismiss();
                break;
            case R.id.select_ok:
                String s = selectContent.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtils.showToast(getContext().getApplicationContext(), "请输入金额");
                    return;
                }
                int i = Integer.parseInt(s);
                if (i <=0) {
                    ToastUtils.showToast(getContext().getApplicationContext(), "请输入正确金额");
                    return;
                }
                onclickListener.onOk(s);
                dismiss();
                break;


        }
    }

}
