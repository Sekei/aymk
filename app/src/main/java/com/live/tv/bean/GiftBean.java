package com.live.tv.bean;

/**
 * @author Created by stone
 * @since 2018/3/22
 * 礼物的bean
 *
 */

public class GiftBean {


    public boolean ischecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    /**
     * gift_id : 10
     * name : 劳斯莱斯
     * img : http://dspx.tstmobile.com/uploads//image/goods/20171101/c66fb4584ad19c7131e41b80425588ed.png
     * price : 2000
     * experience : 2333
     * sort : 10
     * num_norms :
     * intime : 1509449233
     * uptime : 1518074901
     * is_running : 2
     */

    private boolean ischecked;

    private String gift_id;
    private String gift_name;
    private String gift_img;
    private String img;
    private String gift_price;
    private String gift_price_unit_img;
    private String gift_state;
    private String experience;
    private String sort;
    private String num_norms;
    private String intime;
    private String uptime;
    private String is_running="2";

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGift_id() {
        return gift_id;
    }

    public void setGift_id(String gift_id) {
        this.gift_id = gift_id;
    }

    public String getGift_name() {
        return gift_name;
    }

    public void setGift_name(String gift_name) {
        this.gift_name = gift_name;
    }

    public String getGift_img() {
        return gift_img;
    }

    public void setGift_img(String gift_img) {
        this.gift_img = gift_img;
    }

    public String getGift_price() {
        return gift_price;
    }

    public void setGift_price(String gift_price) {
        this.gift_price = gift_price;
    }

    public String getGift_price_unit_img() {
        return gift_price_unit_img;
    }

    public void setGift_price_unit_img(String gift_price_unit_img) {
        this.gift_price_unit_img = gift_price_unit_img;
    }

    public String getGift_state() {
        return gift_state;
    }

    public void setGift_state(String gift_state) {
        this.gift_state = gift_state;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getNum_norms() {
        return num_norms;
    }

    public void setNum_norms(String num_norms) {
        this.num_norms = num_norms;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getIs_running() {
        return is_running;
    }

    public void setIs_running(String is_running) {
        this.is_running = is_running;
    }
}
