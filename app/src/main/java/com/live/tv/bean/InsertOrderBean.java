package com.live.tv.bean;

import java.util.List;

/**
 * Created by taoh on 2018/5/9.
 */

public class InsertOrderBean {


    private String merchants_id;
    private String order_remark;
    private String order_type;
    private List<InsertOrderGoodBean> orderGoodsBeans;

    public String getMerchants_id() {
        return merchants_id;
    }

    public void setMerchants_id(String merchants_id) {
        this.merchants_id = merchants_id;
    }

    public String getOrder_remark() {
        return order_remark;
    }

    public void setOrder_remark(String order_remark) {
        this.order_remark = order_remark;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public List<InsertOrderGoodBean> getOrderGoodsBeans() {
        return orderGoodsBeans;
    }

    public void setOrderGoodsBeans(List<InsertOrderGoodBean> orderGoodsBeans) {
        this.orderGoodsBeans = orderGoodsBeans;
    }
}
