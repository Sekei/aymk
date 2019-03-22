package com.live.tv.mvp.activity.live.widget;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import com.ysjk.health.iemk.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RoomMessageItemHolder extends BaseViewHolder<EMMessage> {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.content)
    TextView content;


    private EMMessage message;

    public RoomMessageItemHolder(Context mContext, OnItemClickRecyclerListener listener, View itemView) {
        super(mContext, listener, itemView);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void refreshView() {
        message = getData();

        String name_str = "";
        String message_type = "";
        String html="";

        try {

            name_str = message.getStringAttribute("username");
            message_type = message.getStringAttribute("message_type");



        } catch (HyphenateException e) {
            e.printStackTrace();
        }


        if ("join".equals(message_type)) {
            html=" <font color = \"#6EB92B\">"+name_str+""+"</font>"+" <font color = \"#6EB92B\">"+((EMTextMessageBody) message.getBody()).getMessage()+"</font>";
        }else {

            html=" <font color = \"#6EB92B\">"+name_str+":"+"</font>"+" <font color = \"#ffffff\">"+((EMTextMessageBody) message.getBody()).getMessage()+"</font>";


        }
        content.setText(Html.fromHtml(html));

//     String html=" <font color = \"#6EB92B\">"+name_str+":"+"</font>"+" <font color = \"#ffffff\">"+((EMTextMessageBody) message.getBody()).getMessage()+"</font>";
//
//        content.setText(Html.fromHtml(html));

//        name.setText(name_str + ":");
//        content.setText(((EMTextMessageBody) message.getBody()).getMessage());


    }
}
