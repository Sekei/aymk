package com.live.tv.mvp.activity.live;

import com.hyphenate.chat.EMMessage;

public interface MessageViewListener {

    /**
     * 消息发送监听
     */
    void onMessageSend(String content);

    /**
     * 隐藏输入面板
     */
    void onHiderBottomBar();

    /**
     * 消息点击监听
     * @param id  点击回传内容随便定义满足int类型
     * @param sid 点击回传内容随便定义满足String类型
     */
    void onItemClickListener(int id, EMMessage message);

    /**
     * 消息加载更多
     */
    void onLoadMore();
}