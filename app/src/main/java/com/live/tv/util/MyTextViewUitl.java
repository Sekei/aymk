package com.live.tv.util;

import android.text.Html;
import android.widget.TextView;


/**
 * Created by mac1010 on 2017/9/30.
 */

public class MyTextViewUitl {


    public static void setappend(TextView tv, String content, String color){

        String sl  = "<font color = \""+color+"\">"+content+"</font>";
        tv.append(Html.fromHtml(sl));
    }

    /**
     *
     * @param tv
     * @param content
     * @param color "#cccccc"
     */
    public static void setText(TextView tv, String[] content, String[] color){
        String sl = "";

        for (int i = 0;i<content.length;i++){
            sl = sl + "<font color = \""+color[i].trim()+"\">"+content[i].trim()+"</font>";
        }
        tv.setText(Html.fromHtml(sl));
    }



}
