package com.live.tv.bean;

import java.util.List;

/**
 * Created by taoh on 2018/6/8.
 */

public class SearchBean {
    private List<DoctorDetailBean> doctor;
    private List<GoodsBean> goods;
    private List<MerchantsBean> merchants;


    public List<DoctorDetailBean> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<DoctorDetailBean> doctor) {
        this.doctor = doctor;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public List<MerchantsBean> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<MerchantsBean> merchants) {
        this.merchants = merchants;
    }
}
