package com.live.tv.bean;

import java.util.List;

/**
 * Created by taoh on 2018/5/9.
 */

public class InsertOrder {

    private String address_id;
    private String member_id;
    private String is_deduct_integral;
    private String deduct_integral_value;
    private String shop_car_ids;
    private String expiry_time;
    private String contact_phone;

    private List<InsertOrderBean> orderBeans;

    public String getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(String expiry_time) {
        this.expiry_time = expiry_time;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getIs_deduct_integral() {
        return is_deduct_integral;
    }

    public void setIs_deduct_integral(String is_deduct_integral) {
        this.is_deduct_integral = is_deduct_integral;
    }

    public String getDeduct_integral_value() {
        return deduct_integral_value;
    }

    public void setDeduct_integral_value(String deduct_integral_value) {
        this.deduct_integral_value = deduct_integral_value;
    }

    public String getShop_car_ids() {
        return shop_car_ids;
    }

    public void setShop_car_ids(String shop_car_ids) {
        this.shop_car_ids = shop_car_ids;
    }

    public List<InsertOrderBean> getOrderBeans() {
        return orderBeans;
    }

    public void setOrderBeans(List<InsertOrderBean> orderBeans) {
        this.orderBeans = orderBeans;
    }
}
