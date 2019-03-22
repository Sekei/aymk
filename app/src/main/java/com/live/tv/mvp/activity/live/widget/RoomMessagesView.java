package com.live.tv.mvp.activity.live.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.activity.live.MessageViewListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taoh on 2018/5/23.
 */

public class RoomMessagesView extends RelativeLayout {

    /**
     * 环信聊天对象
     */
    private EMConversation conversation;
    /**
     * 消息适配器
     */
    RoomMessageAdapter adapter;
    /**
     * 消息列表
     */
    RecyclerView recyclerView;
    /**
     * 输入面板
     */
    View sendContainer;
    /**
     * 编辑框
     */
    EditText editText;
    /**
     * 发送
     */
    Button sendBtn;
    /**
     * 关闭
     */
    ImageView closeView;
    /**
     * 弹幕
     */
    ImageView danmuImage;
    /**
     * 房间监听
     */
    MessageViewListener messageViewListener;
    /**
     * 消息列表
     */
    List<EMMessage> list = new ArrayList<>();

    /**
     * 艾特的某人的集合
     */
    private List<String> atList = new ArrayList<String>();
    private List<EMMessage> temp;
    private RelativeLayout rootview;


    private Toast mToast = null;

    public RoomMessagesView(Context context) {
        super(context);
        init(context, null);
    }

    public RoomMessagesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoomMessagesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    long cur_time = 0;

    private void init(Context context, AttributeSet attrs) {

        cur_time = System.currentTimeMillis();
        LayoutInflater.from(context).inflate(R.layout.live_widget_room_messages, this);
        recyclerView = (RecyclerView) findViewById(R.id.listview);

        rootview = (RelativeLayout) findViewById(R.id.rootview);
        editText = (EditText) findViewById(R.id.edit_text);

        sendBtn = (Button) findViewById(R.id.btn_send);


        sendContainer = findViewById(R.id.container_send);

        sendBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageViewListener != null) {
                    if (TextUtils.isEmpty(editText.getText())) {
                        showToast("文字内容不能为空", Toast.LENGTH_SHORT);
                        return;
                    }
                    messageViewListener.onMessageSend(editText.getText().toString());
                    editText.setText("");
                }
            }
        });



    }

    /**
     * 获取输入框
     */
    public EditText getInputView() {
        return editText;
    }

    public Button getSendBtn() {
        return sendBtn;
    }



    public void init(String chatroomId) {



        try {
            conversation = EMClient.getInstance().chatManager().getConversation(chatroomId, EMConversation.EMConversationType.ChatRoom, true);
            temp = conversation.getAllMessages();
            if (temp != null && temp.size() > 0) {
                list.clear();
                list.addAll(temp);
            }
            adapter = new RoomMessageAdapter(getContext(), list);
            //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            LinearLayoutManager layout = new LinearLayoutManager(getContext());

            layout.setStackFromEnd(true);
            layout.setReverseLayout(true);

            //((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

            recyclerView.setLayoutManager(layout);
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new OnItemClickRecyclerListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    onItemClickListener(postion, list.get(postion));

                }
            });




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onItemClickListener(int id, EMMessage message) {
        if (messageViewListener != null) {
            messageViewListener.onHiderBottomBar();
            messageViewListener.onItemClickListener(id, message);
        }
    }

    public void showToast(final String text, final int duration) {

        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getContext(), text, duration);
        mToast.show();
    }

    public void closeEdittext() {
        if (messageViewListener != null) {
            messageViewListener.onHiderBottomBar();
        }
    }





    public void setMessageViewListener(MessageViewListener messageViewListener) {
        this.messageViewListener = messageViewListener;
    }

    public void addData(final EMMessage message) {
        if (adapter != null&&message!=null) {

            ((Activity) getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    list.add(0, message);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }


    public void  notifyDataSetChanged(){

        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

}
