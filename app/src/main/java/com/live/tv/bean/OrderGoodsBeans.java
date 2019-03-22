package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by taoh on 2018/4/9.
 */

public class OrderGoodsBeans implements Parcelable {


    /**
     * order_goods_id : 62
     * order_id : 71
     * goods_id : 7
     * goods_num : 2
     * goods_name : 眼睛保护用药1
     * goods_img :
     * specification_id : 7
     * specification_ids : 12
     * specification_names : 眼镜用药规格1
     * specification_stock : 10000
     * specification_img :
     * specification_price : 200.00
     * goods_group_id :
     * group_specification_id :
     * group_price :
     * group_stock :
     * goods_welfare_id :
     * welfare_id :
     * welfare_percent_value :
     * distributor_percent_value :
     * is_delete : 0
     * create_time : 2018-03-02 15:53:09.0
     * update_time : 2018-03-02 15:53:09.0
     * is_give_integral : 0
     * give_integral_value : 0.00
     * refund_id :
     * refund_state :
     * goods_now_price : 0.00
     * goodsBean : {}
     * goodsSpecificationBean : {}
     */

    private String order_goods_id;
    private String order_id;
    private String goods_id;
    private String goods_num;
    private String goods_name;
    private String goods_img;
    private String specification_id;
    private String specification_ids;
    private String specification_names;
    private String specification_stock;
    private String specification_img;
    private String specification_price;
    private String goods_group_id;
    private String group_specification_id;
    private String group_price;
    private String group_stock;
    private String goods_welfare_id;
    private String welfare_id;
    private String welfare_percent_value;
    private String distributor_percent_value;
    private String is_delete;
    private String create_time;
    private String update_time;
    private String is_give_integral;
    private String give_integral_value;
    private String refund_id;
    private String refund_state;
    private String goods_now_price;
    private String content;
    private float goods_mark;

    private List<String> commentsImgs;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getGoods_mark() {
        return goods_mark;
    }

    public void setGoods_mark(float goods_mark) {
        this.goods_mark = goods_mark;
    }

    public List<String> getCommentsImgs() {
        return commentsImgs;
    }

    public void setCommentsImgs(List<String> commentsImgs) {
        this.commentsImgs = commentsImgs;
    }

    public String getOrder_goods_id() {
        return order_goods_id;
    }

    public void setOrder_goods_id(String order_goods_id) {
        this.order_goods_id = order_goods_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
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

    public String getGoods_welfare_id() {
        return goods_welfare_id;
    }

    public void setGoods_welfare_id(String goods_welfare_id) {
        this.goods_welfare_id = goods_welfare_id;
    }

    public String getWelfare_id() {
        return welfare_id;
    }

    public void setWelfare_id(String welfare_id) {
        this.welfare_id = welfare_id;
    }

    public String getWelfare_percent_value() {
        return welfare_percent_value;
    }

    public void setWelfare_percent_value(String welfare_percent_value) {
        this.welfare_percent_value = welfare_percent_value;
    }

    public String getDistributor_percent_value() {
        return distributor_percent_value;
    }

    public void setDistributor_percent_value(String distributor_percent_value) {
        this.distributor_percent_value = distributor_percent_value;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
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

    public String getIs_give_integral() {
        return is_give_integral;
    }

    public void setIs_give_integral(String is_give_integral) {
        this.is_give_integral = is_give_integral;
    }

    public String getGive_integral_value() {
        return give_integral_value;
    }

    public void setGive_integral_value(String give_integral_value) {
        this.give_integral_value = give_integral_value;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_state() {
        return refund_state;
    }

    public void setRefund_state(String refund_state) {
        this.refund_state = refund_state;
    }

    public String getGoods_now_price() {
        return goods_now_price;
    }

    public void setGoods_now_price(String goods_now_price) {
        this.goods_now_price = goods_now_price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.order_goods_id);
        dest.writeString(this.order_id);
        dest.writeString(this.goods_id);
        dest.writeString(this.goods_num);
        dest.writeString(this.goods_name);
        dest.writeString(this.goods_img);
        dest.writeString(this.specification_id);
        dest.writeString(this.specification_ids);
        dest.writeString(this.specification_names);
        dest.writeString(this.specification_stock);
        dest.writeString(this.specification_img);
        dest.writeString(this.specification_price);
        dest.writeString(this.goods_group_id);
        dest.writeString(this.group_specification_id);
        dest.writeString(this.group_price);
        dest.writeString(this.group_stock);
        dest.writeString(this.goods_welfare_id);
        dest.writeString(this.welfare_id);
        dest.writeString(this.welfare_percent_value);
        dest.writeString(this.distributor_percent_value);
        dest.writeString(this.is_delete);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.is_give_integral);
        dest.writeString(this.give_integral_value);
        dest.writeString(this.refund_id);
        dest.writeString(this.refund_state);
        dest.writeString(this.goods_now_price);
        dest.writeString(this.content);
        dest.writeFloat(this.goods_mark);
        dest.writeStringList(this.commentsImgs);
    }

    public OrderGoodsBeans() {
    }

    protected OrderGoodsBeans(Parcel in) {
        this.order_goods_id = in.readString();
        this.order_id = in.readString();
        this.goods_id = in.readString();
        this.goods_num = in.readString();
        this.goods_name = in.readString();
        this.goods_img = in.readString();
        this.specification_id = in.readString();
        this.specification_ids = in.readString();
        this.specification_names = in.readString();
        this.specification_stock = in.readString();
        this.specification_img = in.readString();
        this.specification_price = in.readString();
        this.goods_group_id = in.readString();
        this.group_specification_id = in.readString();
        this.group_price = in.readString();
        this.group_stock = in.readString();
        this.goods_welfare_id = in.readString();
        this.welfare_id = in.readString();
        this.welfare_percent_value = in.readString();
        this.distributor_percent_value = in.readString();
        this.is_delete = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.is_give_integral = in.readString();
        this.give_integral_value = in.readString();
        this.refund_id = in.readString();
        this.refund_state = in.readString();
        this.goods_now_price = in.readString();
        this.content = in.readString();
        this.goods_mark = in.readFloat();
        this.commentsImgs = in.createStringArrayList();
    }

    public static final Parcelable.Creator<OrderGoodsBeans> CREATOR = new Parcelable.Creator<OrderGoodsBeans>() {
        @Override
        public OrderGoodsBeans createFromParcel(Parcel source) {
            return new OrderGoodsBeans(source);
        }

        @Override
        public OrderGoodsBeans[] newArray(int size) {
            return new OrderGoodsBeans[size];
        }
    };
}
