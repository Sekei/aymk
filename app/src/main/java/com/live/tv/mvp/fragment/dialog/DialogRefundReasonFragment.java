package com.live.tv.mvp.fragment.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.RefundsReasons;
import com.live.tv.mvp.adapter.dialogadapter.RefundReasonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 退款原因选择
 * Created by taoh on 2018/5/8.
 */

public class DialogRefundReasonFragment extends DialogFragment {


    Unbinder unbinder;
    public SelectListener selectListener;
    View view;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private RefundReasonAdapter mAdapter;
    List<RefundsReasons> refundsReasonsList;

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    public interface SelectListener {
        void OnSelectListener(RefundsReasons refundsReason);
    }

    public void setOnSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }
    public static DialogRefundReasonFragment newInstance(List<RefundsReasons> refundsReasonsList, Context context) {
        Bundle args = new Bundle();
        DialogRefundReasonFragment fragment = new DialogRefundReasonFragment();
        fragment.refundsReasonsList = refundsReasonsList;

        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_refund_reason, container, false);
        unbinder = ButterKnife.bind(this, view);
        mAdapter = new RefundReasonAdapter(new ArrayList<RefundsReasons>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(refundsReasonsList);

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                RefundsReasons refundsReasons = (RefundsReasons) adapter.getData().get(position);


                selectListener.OnSelectListener(refundsReasons);
//                startDownAnimation(view);

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
