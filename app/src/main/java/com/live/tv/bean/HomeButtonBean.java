package com.live.tv.bean;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class HomeButtonBean {


    /**
     * button_id : 1
     * button_name : 快速咨询
     * button_img : /images/icon/meishi.png
     * button_url :
     * button_type : consulting
     * is_delete : 0
     * create_time : 2017-10-11 11:01:10.0
     * update_time :
     * class_uuid :
     * type :
     * sort : 1
     * class_id :
     */

    private String button_id;
    private String button_name;
    private String button_img;
    private String button_url;
    private String button_type;
    private String is_delete;
    private String create_time;
    private String update_time;
    private String class_uuid;
    private String type;
    private String sort;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    private String class_id;

    private int img = 0;

    public HomeButtonBean() {
    }

    public HomeButtonBean(String button_name, int img) {
        this.button_name = button_name;
        this.img = img;
    }

    public String getButton_id() {
        return button_id;
    }

    public void setButton_id(String button_id) {
        this.button_id = button_id;
    }

    public String getButton_name() {
        return button_name;
    }

    public void setButton_name(String button_name) {
        this.button_name = button_name;
    }

    public String getButton_img() {
        return button_img;
    }

    public void setButton_img(String button_img) {
        this.button_img = button_img;
    }

    public String getButton_url() {
        return button_url;
    }

    public void setButton_url(String button_url) {
        this.button_url = button_url;
    }

    public String getButton_type() {
        return button_type;
    }

    public void setButton_type(String button_type) {
        this.button_type = button_type;
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

    public String getClass_uuid() {
        return class_uuid;
    }

    public void setClass_uuid(String class_uuid) {
        this.class_uuid = class_uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }
}
