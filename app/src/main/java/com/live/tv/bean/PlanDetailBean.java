package com.live.tv.bean;

/**
 * @author Created by stone
 * @since 2018/1/26
 */

public class PlanDetailBean {


    /**
     * cond_plan_id : 5
     * plan_title : 生活习惯调养
     * plan_desc : 拒绝熬夜，23点前睡觉是底线（熬夜消耗肝血，自然阴虚火旺）
     * create_time : 2018-01-18 17:57:23
     * update_time :
     * physical_type :
     * type : 阴虚
     * is_delete : 0
     */

    private int cond_plan_id;
    private String plan_title;
    private String plan_desc;
    private String create_time;
    private String update_time;
    private String physical_type;
    private String type;
    private String is_delete;

    public int getCond_plan_id() {
        return cond_plan_id;
    }

    public void setCond_plan_id(int cond_plan_id) {
        this.cond_plan_id = cond_plan_id;
    }

    public String getPlan_title() {
        return plan_title;
    }

    public void setPlan_title(String plan_title) {
        this.plan_title = plan_title;
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

    public String getPhysical_type() {
        return physical_type;
    }

    public void setPhysical_type(String physical_type) {
        this.physical_type = physical_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}
