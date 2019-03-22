package com.live.tv.bean;

/**
 * @author Created by stone
 * @since 2018/1/26
 */

public class PlanPriceBean {


    /**
     * price_id : 1
     * plan_price : 100
     * create_time : 2018-01-26 11:18:26
     * update_time :
     * is_delete : 0
     */

    private int price_id;
    private String plan_price;
    private String create_time;
    private String update_time;
    private String is_delete;

    public int getPrice_id() {
        return price_id;
    }

    public void setPrice_id(int price_id) {
        this.price_id = price_id;
    }

    public String getPlan_price() {
        return plan_price;
    }

    public void setPlan_price(String plan_price) {
        this.plan_price = plan_price;
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
