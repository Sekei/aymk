package com.live.tv.mvp.activity.live.bean;

/**
 * Created by Administrator on 2016/8/22.
 * 用户基本信息封装 id、nickname、faceurl
 */
public class TCSimpleUserInfo {

    public String userid;
    public String nickname;
    public String headpic;

    public String giftimg;
    public String gift_id;
    public String giftname;
    public String gift_num;

    public String goods_id;
    public String goods_name;
    public String goods_img;
    public String goods_code;

    public String dashang_price;

    public String getDashang_price() {
        return dashang_price;
    }

    public void setDashang_price(String dashang_price) {
        this.dashang_price = dashang_price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String content;

    public String getGift_num() {
        return gift_num;
    }

    public void setGift_num(String gift_num) {
        this.gift_num = gift_num;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getGiftimg() {
        return giftimg;
    }

    public void setGiftimg(String giftimg) {
        this.giftimg = giftimg;
    }

    public String getGift_id() {
        return gift_id;
    }

    public void setGift_id(String gift_id) {
        this.gift_id = gift_id;
    }

    public String getGiftname() {
        return giftname;
    }

    public void setGiftname(String giftname) {
        this.giftname = giftname;
    }


    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public TCSimpleUserInfo(String userId, String nickname, String headpic) {
        this.userid = userId;
        this.nickname = nickname;
        this.headpic = headpic;
    }
    public TCSimpleUserInfo(String userId, String nickname) {
        this.userid = userId;
        this.nickname = nickname;
    }

    public TCSimpleUserInfo(String content){


        this.content=content;
    }



    //传礼物
    public TCSimpleUserInfo(String userId, String nickname, String headpic, String gift_id, String giftname, String giftimg, String gift_num) {
        this.userid = userId;
        this.nickname = nickname;
        this.headpic = headpic;
        this.giftimg = giftimg;
        this.gift_id = gift_id;
        this.giftname = giftname;
        this.gift_num = gift_num;
    }


        //打赏
    public TCSimpleUserInfo(String userId, String nickname, String headpic,  String dashang_price, String gift_num) {
        this.userid = userId;
        this.nickname = nickname;
        this.headpic = headpic;
        this.dashang_price = dashang_price;
        this.gift_num = gift_num;
    }





}
