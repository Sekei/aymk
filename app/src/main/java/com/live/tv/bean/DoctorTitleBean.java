package com.live.tv.bean;

/**
 * 医生职称
 * Created by taoh on 2018/3/15.
 */

public class DoctorTitleBean {


    /**
     * title_id : 1
     * doctor_title : 心内科
     * is_delete : 0
     * create_time : 2018-01-15 15:56:56
     * update_time :
     */

    private int title_id;
    private String doctor_title;
    private String is_delete;
    private String create_time;
    private String update_time;

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    public String getDoctor_title() {
        return doctor_title;
    }

    public void setDoctor_title(String doctor_title) {
        this.doctor_title = doctor_title;
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
