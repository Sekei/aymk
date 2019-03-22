package com.live.tv.bean;

/**
 * Created by taoh on 2018/3/8.
 */

public class EventBusMessage {
    private String message;

    public EventBusMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
