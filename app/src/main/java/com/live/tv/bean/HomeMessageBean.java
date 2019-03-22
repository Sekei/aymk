package com.live.tv.bean;

/**
 * Created by mac1010 on 2018/9/26.
 */

public class HomeMessageBean {


    /**
     * message_id : 2
     * message_desc : 爱医美康火热招商，招商热线021-60910020
     * is_delete : 0
     * create_time : 2018-09-14 10:33:43
     * update_time : 2018-09-19 10:58:38
     */

    private int message_id;
    private String message_desc;
    private String is_delete;
    private String create_time;
    private String update_time;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getMessage_desc() {
        return message_desc;
    }

    public void setMessage_desc(String message_desc) {
        this.message_desc = message_desc;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
