package com.live.tv.bean;

/**
 * @author Created by stone
 * @since 2018/7/17
 * 社区全部模块的bean
 */

public class CommAllBean {


    /**
     * plate_id : 1
     * plate_name : 饮食
     * plate_image : /images/member/default.png
     * topic_num : 0
     * is_hot : 1
     * is_show_home : 1
     * is_delete : 0
     * create_time : 2018-06-20 16:35:45
     * update_time :
     */

    private int plate_id;
    private String plate_name;
    private String plate_image;
    private String topic_num;
    private String is_hot;
    private String is_show_home;
    private String is_delete;
    private String create_time;
    private String update_time;

    public int getPlate_id() {
        return plate_id;
    }

    public void setPlate_id(int plate_id) {
        this.plate_id = plate_id;
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name;
    }

    public String getPlate_image() {
        return plate_image;
    }

    public void setPlate_image(String plate_image) {
        this.plate_image = plate_image;
    }

    public String getTopic_num() {
        return topic_num;
    }

    public void setTopic_num(String topic_num) {
        this.topic_num = topic_num;
    }

    public String getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(String is_hot) {
        this.is_hot = is_hot;
    }

    public String getIs_show_home() {
        return is_show_home;
    }

    public void setIs_show_home(String is_show_home) {
        this.is_show_home = is_show_home;
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
