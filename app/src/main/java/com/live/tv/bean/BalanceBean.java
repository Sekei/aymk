package com.live.tv.bean;

import java.util.List;

/**
 * Created by taoh on 2018/5/7.
 */

public class BalanceBean {

    private String  order_id;
    private String  health_record_id;
    private String  order_actual_price;
    private String  order_date;
    private String  member_nick_name;
    private String  order_type;

    private List<BalanceBean>  orderBeans;

    public List<BalanceBean> getOrderBeans() {
        return orderBeans;
    }

    public void setOrderBeans(List<BalanceBean> orderBeans) {
        this.orderBeans = orderBeans;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getHealth_record_id() {
        return health_record_id;
    }

    public void setHealth_record_id(String health_record_id) {
        this.health_record_id = health_record_id;
    }

    public String getOrder_actual_price() {
        return order_actual_price;
    }

    public void setOrder_actual_price(String order_actual_price) {
        this.order_actual_price = order_actual_price;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getMember_nick_name() {
        return member_nick_name;
    }

    public void setMember_nick_name(String member_nick_name) {
        this.member_nick_name = member_nick_name;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }
}
