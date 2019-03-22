package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taoh on 2017/9/20.
 */

public class ShopCarBean implements Parcelable {

    private String car_id;
    private String member_id;
    private String goods_id;
    private String goods_name;
    private String goods_img;
    private String merchants_id;
    private String specification_id;
    private String specification_ids;
    private String specification_names;
    private String goods_num;
    private String create_time;
    private String update_time;
    private String is_delete;
    private String specification_price;
    private boolean isSelected;
    private List<ShopCarBean> shopCarBeans;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSpecification_price() {
        return specification_price;
    }

    public void setSpecification_price(String specification_price) {
        this.specification_price = specification_price;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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

    public String getMerchants_id() {
        return merchants_id;
    }

    public void setMerchants_id(String merchants_id) {
        this.merchants_id = merchants_id;
    }

    public String getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(String specification_id) {
        this.specification_id = specification_id;
    }

    public String getSpecification_ids() {
        return specification_ids;
    }

    public void setSpecification_ids(String specification_ids) {
        this.specification_ids = specification_ids;
    }

    public String getSpecification_names() {
        return specification_names;
    }

    public void setSpecification_names(String specification_names) {
        this.specification_names = specification_names;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
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

    public List<ShopCarBean> getShopCarBeans() {
        return shopCarBeans;
    }

    public void setShopCarBeans(List<ShopCarBean> shopCarBeans) {
        this.shopCarBeans = shopCarBeans;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.car_id);
        dest.writeString(this.member_id);
        dest.writeString(this.goods_id);
        dest.writeString(this.goods_name);
        dest.writeString(this.goods_img);
        dest.writeString(this.merchants_id);
        dest.writeString(this.specification_id);
        dest.writeString(this.specification_ids);
        dest.writeString(this.specification_names);
        dest.writeString(this.goods_num);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.is_delete);
        dest.writeString(this.specification_price);
        dest.writeList(this.shopCarBeans);
    }

    public ShopCarBean() {
    }

    protected ShopCarBean(Parcel in) {
        this.car_id = in.readString();
        this.member_id = in.readString();
        this.goods_id = in.readString();
        this.goods_name = in.readString();
        this.goods_img = in.readString();
        this.merchants_id = in.readString();
        this.specification_id = in.readString();
        this.specification_ids = in.readString();
        this.specification_names = in.readString();
        this.goods_num = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.is_delete = in.readString();
        this.specification_price = in.readString();
        this.shopCarBeans = new ArrayList<ShopCarBean>();
        in.readList(this.shopCarBeans, ShopCarBean.class.getClassLoader());
    }

    public static final Creator<ShopCarBean> CREATOR = new Creator<ShopCarBean>() {
        @Override
        public ShopCarBean createFromParcel(Parcel source) {
            return new ShopCarBean(source);
        }

        @Override
        public ShopCarBean[] newArray(int size) {
            return new ShopCarBean[size];
        }
    };
}
