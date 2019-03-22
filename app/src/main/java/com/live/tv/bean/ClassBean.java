package com.live.tv.bean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class ClassBean {
    private String class_id;
    private String class_name;
    private String class_desc;
    private String class_state;
    private String class_img;
    private String class_color;
    private String class_url;
    private String parent_id;
    private String class_uuid;
    private String class_parent_uuid;
    private String sort;
    private String create_time;
    private String update_time;
    private String is_delete;
    private String is_recommend;
    private List<ClassBean>  goodsClassBeans;

    public List<ClassBean> getGoodsClassBeans() {
        return goodsClassBeans;
    }

    public void setGoodsClassBeans(List<ClassBean> goodsClassBeans) {
        this.goodsClassBeans = goodsClassBeans;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_desc() {
        return class_desc;
    }

    public void setClass_desc(String class_desc) {
        this.class_desc = class_desc;
    }

    public String getClass_state() {
        return class_state;
    }

    public void setClass_state(String class_state) {
        this.class_state = class_state;
    }

    public String getClass_img() {
        return class_img;
    }

    public void setClass_img(String class_img) {
        this.class_img = class_img;
    }

    public String getClass_color() {
        return class_color;
    }

    public void setClass_color(String class_color) {
        this.class_color = class_color;
    }

    public String getClass_url() {
        return class_url;
    }

    public void setClass_url(String class_url) {
        this.class_url = class_url;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getClass_uuid() {
        return class_uuid;
    }

    public void setClass_uuid(String class_uuid) {
        this.class_uuid = class_uuid;
    }

    public String getClass_parent_uuid() {
        return class_parent_uuid;
    }

    public void setClass_parent_uuid(String class_parent_uuid) {
        this.class_parent_uuid = class_parent_uuid;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    public String getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(String is_recommend) {
        this.is_recommend = is_recommend;
    }
}
