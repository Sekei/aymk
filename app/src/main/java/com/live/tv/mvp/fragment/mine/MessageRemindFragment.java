package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.SimpleFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 消息提醒
 * Created by sh-lx on 2017/7/12.
 */

public class MessageRemindFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public static MessageRemindFragment newInstance() {

        Bundle args = new Bundle();

        MessageRemindFragment fragment = new MessageRemindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_message_remind;
    }

    @Override
    public void initUI() {
        tvTitle.setText("消息提醒");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
}
