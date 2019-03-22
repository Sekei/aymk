package com.live.tv.bean;

/**
 * @author Created by stone
 * @since 2018/1/27
 */

public class HeartBean {


    /**
     * heart_record_id : 4
     * member_id : 1
     * doctor_id : 1
     * record_desc : 白衣天使
     * member_nick_name : 我的地盘我做主
     * is_delete : 0
     * create_time : 2018-01-26 11:40:24
     * amount : 100
     */

    private int heart_record_id;
    private int member_id;
    private int doctor_id;
    private String record_desc;
    private String member_nick_name;
    private String is_delete;
    private String create_time;
    private int amount;

    public int getHeart_record_id() {
        return heart_record_id;
    }

    public void setHeart_record_id(int heart_record_id) {
        this.heart_record_id = heart_record_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getRecord_desc() {
        return record_desc;
    }

    public void setRecord_desc(String record_desc) {
        this.record_desc = record_desc;
    }

    public String getMember_nick_name() {
        return member_nick_name;
    }

    public void setMember_nick_name(String member_nick_name) {
        this.member_nick_name = member_nick_name;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
