package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by taoh on 2018/5/7.
 */

public class GoodsSpecificationBeans implements Parcelable {


    /**
     * specification_id : 7
     * goods_id : 7
     * specification_state : 1
     * specification_sku : 0C843B186F
     * specification_ids : 12
     * specification_names : 眼镜用药规格1
     * specification_sales : 0
     * specification_stock : 9996
     * specification_img :
     * specification_price : 200.00
     * specification_cost_price : 0.00
     * goods_group_id :
     * group_specification_id :
     * group_price :
     * group_stock :
     * parent_id :
     * create_time : 2018-01-09 10:44:12.0
     * update_time : 2018-01-09 10:44:12.0
     */

    private String specification_id;
    private String goods_id;
    private String specification_state;
    private String specification_sku;
    private String specification_ids;
    private String specification_names;
    private String specification_sales;
    private String specification_stock;
    private String specification_img;
    private String specification_price;
    private String specification_cost_price;
    private String goods_group_id;
    private String group_specification_id;
    private String group_price;
    private String group_stock;
    private String parent_id;
    private String create_time;
    private String update_time;

    public String getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(String specification_id) {
        this.specification_id = specification_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getSpecification_state() {
        return specification_state;
    }

    public void setSpecification_state(String specification_state) {
        this.specification_state = specification_state;
    }

    public String getSpecification_sku() {
        return specification_sku;
    }

    public void setSpecification_sku(String specification_sku) {
        this.specification_sku = specification_sku;
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

    public String getSpecification_sales() {
        return specification_sales;
    }

    public void setSpecification_sales(String specification_sales) {
        this.specification_sales = specification_sales;
    }

    public String getSpecification_stock() {
        return specification_stock;
    }

    public void setSpecification_stock(String specification_stock) {
        this.specification_stock = specification_stock;
    }

    public String getSpecification_img() {
        return specification_img;
    }

    public void setSpecification_img(String specification_img) {
        this.specification_img = specification_img;
    }

    public String getSpecification_price() {
        return specification_price;
    }

    public void setSpecification_price(String specification_price) {
        this.specification_price = specification_price;
    }

    public String getSpecification_cost_price() {
        return specification_cost_price;
    }

    public void setSpecification_cost_price(String specification_cost_price) {
        this.specification_cost_price = specification_cost_price;
    }

    public String getGoods_group_id() {
        return goods_group_id;
    }

    public void setGoods_group_id(String goods_group_id) {
        this.goods_group_id = goods_group_id;
    }

    public String getGroup_specification_id() {
        return group_specification_id;
    }

    public void setGroup_specification_id(String group_specification_id) {
        this.group_specification_id = group_specification_id;
    }

    public String getGroup_price() {
        return group_price;
    }

    public void setGroup_price(String group_price) {
        this.group_price = group_price;
    }

    public String getGroup_stock() {
        return group_stock;
    }

    public void setGroup_stock(String group_stock) {
        this.group_stock = group_stock;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.specification_id);
        dest.writeString(this.goods_id);
        dest.writeString(this.specification_state);
        dest.writeString(this.specification_sku);
        dest.writeString(this.specification_ids);
        dest.writeString(this.specification_names);
        dest.writeString(this.specification_sales);
        dest.writeString(this.specification_stock);
        dest.writeString(this.specification_img);
        dest.writeString(this.specification_price);
        dest.writeString(this.specification_cost_price);
        dest.writeString(this.goods_group_id);
        dest.writeString(this.group_specification_id);
        dest.writeString(this.group_price);
        dest.writeString(this.group_stock);
        dest.writeString(this.parent_id);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
    }

    public GoodsSpecificationBeans() {
    }

    protected GoodsSpecificationBeans(Parcel in) {
        this.specification_id = in.readString();
        this.goods_id = in.readString();
        this.specification_state = in.readString();
        this.specification_sku = in.readString();
        this.specification_ids = in.readString();
        this.specification_names = in.readString();
        this.specification_sales = in.readString();
        this.specification_stock = in.readString();
        this.specification_img = in.readString();
        this.specification_price = in.readString();
        this.specification_cost_price = in.readString();
        this.goods_group_id = in.readString();
        this.group_specification_id = in.readString();
        this.group_price = in.readString();
        this.group_stock = in.readString();
        this.parent_id = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
    }

    public static final Parcelable.Creator<GoodsSpecificationBeans> CREATOR = new Parcelable.Creator<GoodsSpecificationBeans>() {
        @Override
        public GoodsSpecificationBeans createFromParcel(Parcel source) {
            return new GoodsSpecificationBeans(source);
        }

        @Override
        public GoodsSpecificationBeans[] newArray(int size) {
            return new GoodsSpecificationBeans[size];
        }
    };
}
