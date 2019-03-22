package com.live.tv.mvp.fragment.huanxin;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.ysjk.health.iemk.R;

/**
 * Created by taoh on 2018/3/31.
 */

public class EaseChatRowRecall extends EaseChatRow {

    private TextView contentView;

    public EaseChatRowRecall(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(R.layout.em_row_recall_message, this);
    }

    @Override
    protected void onFindViewById() {
        contentView = (TextView) findViewById(R.id.text_content);
    }

    @Override
    protected void onSetUpView() {
        // 设置显示内容
        String messageStr = null;
        if (message.direct() == EMMessage.Direct.SEND) {
            messageStr = String.format("您撤回了一条消息");
        } else {
            messageStr = String.format("%1$s 撤回了一条消息", message.getFrom());
        }
        contentView.setText(messageStr);
    }

    @Override
    protected void onViewUpdate(EMMessage msg) {
    }
}
