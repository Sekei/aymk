package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * 会员
 * Created by taoh on 2018/4/3.
 */

public class AssociatorBean implements Parcelable {


    /**
     * associator_id : 1
     * associator_desc : 普通会员
     * associator_title : 普通会员
     * associator_img : /images/goods/20171209//1512809354540.jpg
     * associator_price : 100.00
     * associator_type : common
     * associator_url : /html1/goods/20180308163109677541.html
     * associator_time :
     * associator_quarter :
     * member_id :
     * create_time : 2018-03-06 17:08:44
     * update_time :
     * is_delete : 0
     */

    private String associator_id;
    private String associator_desc;
    private String associator_title;
    private String associator_img;
    private String associator_price;
    private String associator_type;
    private String associator_url;
    private String associator_time;
    private String associator_quarter;
    private String member_id;
    private String create_time;
    private String update_time;
    private String is_delete;

    public String getAssociator_id() {
        return associator_id;
    }

    public void setAssociator_id(String associator_id) {
        this.associator_id = associator_id;
    }

    public String getAssociator_desc() {
        return associator_desc;
    }

    public void setAssociator_desc(String associator_desc) {
        this.associator_desc = associator_desc;
    }

    public String getAssociator_title() {
        return associator_title;
    }

    public void setAssociator_title(String associator_title) {
        this.associator_title = associator_title;
    }

    public String getAssociator_img() {
        return associator_img;
    }

    public void setAssociator_img(String associator_img) {
        this.associator_img = associator_img;
    }

    public String getAssociator_price() {
        return associator_price;
    }

    public void setAssociator_price(String associator_price) {
        this.associator_price = associator_price;
    }

    public String getAssociator_type() {
        return associator_type;
    }

    public void setAssociator_type(String associator_type) {
        this.associator_type = associator_type;
    }

    public String getAssociator_url() {
        return associator_url;
    }

    public void setAssociator_url(String associator_url) {
        this.associator_url = associator_url;
    }

    public String getAssociator_time() {
        return associator_time;
    }

    public void setAssociator_time(String associator_time) {
        this.associator_time = associator_time;
    }

    public String getAssociator_quarter() {
        return associator_quarter;
    }

    public void setAssociator_quarter(String associator_quarter) {
        this.associator_quarter = associator_quarter;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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
        dest.writeString(this.associator_id);
        dest.writeString(this.associator_desc);
        dest.writeString(this.associator_title);
        dest.writeString(this.associator_img);
        dest.writeString(this.associator_price);
        dest.writeString(this.associator_type);
        dest.writeString(this.associator_url);
        dest.writeString(this.associator_time);
        dest.writeString(this.associator_quarter);
        dest.writeString(this.member_id);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.is_delete);
    }

    public AssociatorBean() {
    }

    protected AssociatorBean(Parcel in) {
        this.associator_id = in.readString();
        this.associator_desc = in.readString();
        this.associator_title = in.readString();
        this.associator_img = in.readString();
        this.associator_price = in.readString();
        this.associator_type = in.readString();
        this.associator_url = in.readString();
        this.associator_time = in.readString();
        this.associator_quarter = in.readString();
        this.member_id = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.is_delete = in.readString();
    }

    public static final Parcelable.Creator<AssociatorBean> CREATOR = new Parcelable.Creator<AssociatorBean>() {
        @Override
        public AssociatorBean createFromParcel(Parcel source) {
            return new AssociatorBean(source);
        }

        @Override
        public AssociatorBean[] newArray(int size) {
            return new AssociatorBean[size];
        }
    };
}
