package com.live.tv.bean;

/**
 * @author Created by stone
 * @since 2018/1/25
 */

public class SimplePlansBean {


    /**
     * simple_plan_id : 1
     * type : 平和
     * way : 自我保健
     * plan_desc : 饮食上：吃不能过饥过饱，不能太冷太热。
     * create_time : 2017-09-24 10:36:09
     * update_time :
     * is_delete : 0
     */

    private int simple_plan_id;
    private String type;
    private String way;
    private String plan_desc;
    private String create_time;
    private String update_time;
    private String is_delete;

    public int getSimple_plan_id() {
        return simple_plan_id;
    }

    public void setSimple_plan_id(int simple_plan_id) {
        this.simple_plan_id = simple_plan_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getPlan_desc() {
        return plan_desc;
    }

    public void setPlan_desc(String plan_desc) {
        this.plan_desc = plan_desc;
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
