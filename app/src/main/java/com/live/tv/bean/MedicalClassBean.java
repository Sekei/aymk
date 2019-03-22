package com.live.tv.bean;

/**
 * @author Created by stone
 * @since 2018/1/15
 */

public class MedicalClassBean {


    /**
     * medical_class_id : 1
     * medical_class_title : 病历
     * create_time : 2017-12-29 18:11:03
     * update_time :
     * is_delete : 0
     */

    private String medical_class_id;
    private String medical_class_title;
    private String create_time;
    private String update_time;
    private String is_delete;

    public String getMedical_class_id() {
        return medical_class_id;
    }

    public void setMedical_class_id(String medical_class_id) {
        this.medical_class_id = medical_class_id;
    }

    public String getMedical_class_title() {
        return medical_class_title;
    }

    public void setMedical_class_title(String medical_class_title) {
        this.medical_class_title = medical_class_title;
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

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}
