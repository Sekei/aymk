package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 商品图片
 * Created by taoh on 2017/9/18.
 */

public class GoodsImgBean implements Parcelable {


    /**
     * goods_img_id : 3
     * goods_id : 11
     * goods_img : /images/20180308//1520478983866.png
     * sort : 1
     * create_time : 2018-03-08 11:19:08.0
     * update_time : 2018-03-08 11:26:22.0
     * is_delete : 0
     */

    private String goods_img_id;
    private String goods_id;
    private String goods_img;
    private String sort;
    private String create_time;
    private String update_time;
    private String is_delete;

    public String getGoods_img_id() {
        return goods_img_id;
    }

    public void setGoods_img_id(String goods_img_id) {
        this.goods_img_id = goods_img_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.goods_img_id);
        dest.writeString(this.goods_id);
        dest.writeString(this.goods_img);
        dest.writeString(this.sort);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.is_delete);
    }

    public GoodsImgBean() {
    }

    protected GoodsImgBean(Parcel in) {
        this.goods_img_id = in.readString();
        this.goods_id = in.readString();
        this.goods_img = in.readString();
        this.sort = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.is_delete = in.readString();
    }

    public static final Creator<GoodsImgBean> CREATOR = new Creator<GoodsImgBean>() {
        @Override
        public GoodsImgBean createFromParcel(Parcel source) {
            return new GoodsImgBean(source);
        }

        @Override
        public GoodsImgBean[] newArray(int size) {
            return new GoodsImgBean[size];
        }
    };
}
