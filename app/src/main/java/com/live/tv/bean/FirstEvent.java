package com.live.tv.bean;

/**
 * Created by sh-lx on 2017/6/7.
 */

public class FirstEvent {
    //mMsg = 1 启动直播
    //mMsg = 2 启动供灯
    //mMsg = 3 退出登录
    private String mMsg;
    public FirstEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
