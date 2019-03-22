package com.live.tv.bean;

/**
 * @author Created by stone
 * @since 2018/1/24
 */

public class RelationsBean {


    /**
     * relation_id : 1
     * relation_name : 父亲
     * is_delete : 0
     * create_time : 2017-12-16 16:24:43
     * update_time :
     */

    private int relation_id;
    private String relation_name;
    private String is_delete;
    private String create_time;
    private String update_time;

    public int getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(int relation_id) {
        this.relation_id = relation_id;
    }

    public String getRelation_name() {
        return relation_name;
    }

    public void setRelation_name(String relation_name) {
        this.relation_name = relation_name;
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
