package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by taoh on 2017/9/15.
 */

public class AddressBean implements Parcelable {
    private String address_id;
    private String member_id;
    private String address_mobile;
    private String address_name;
    private String address_province;
    private String address_city;
    private String address_country;
    private String address_detailed;
    private String address_zip_code;
    private String address_road;
    private String address_latitude;
    private String address_longitude;
    private String create_time;
    private String update_time;
    private String is_default;
    private String is_delete;


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

    public String getAddress_mobile() {
        return address_mobile;
    }

    public void setAddress_mobile(String address_mobile) {
        this.address_mobile = address_mobile;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getAddress_province() {
        return address_province;
    }

    public void setAddress_province(String address_province) {
        this.address_province = address_province;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_country() {
        return address_country;
    }

    public void setAddress_country(String address_country) {
        this.address_country = address_country;
    }

    public String getAddress_detailed() {
        return address_detailed;
    }

    public void setAddress_detailed(String address_detailed) {
        this.address_detailed = address_detailed;
    }

    public String getAddress_zip_code() {
        return address_zip_code;
    }

    public void setAddress_zip_code(String address_zip_code) {
        this.address_zip_code = address_zip_code;
    }

    public String getAddress_road() {
        return address_road;
    }

    public void setAddress_road(String address_road) {
        this.address_road = address_road;
    }

    public String getAddress_latitude() {
        return address_latitude;
    }

    public void setAddress_latitude(String address_latitude) {
        this.address_latitude = address_latitude;
    }

    public String getAddress_longitude() {
        return address_longitude;
    }

    public void setAddress_longitude(String address_longitude) {
        this.address_longitude = address_longitude;
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

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
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
        dest.writeString(this.address_id);
        dest.writeString(this.member_id);
        dest.writeString(this.address_mobile);
        dest.writeString(this.address_name);
        dest.writeString(this.address_province);
        dest.writeString(this.address_city);
        dest.writeString(this.address_country);
        dest.writeString(this.address_detailed);
        dest.writeString(this.address_zip_code);
        dest.writeString(this.address_road);
        dest.writeString(this.address_latitude);
        dest.writeString(this.address_longitude);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.is_default);
        dest.writeString(this.is_delete);
    }

    public AddressBean() {
    }

    protected AddressBean(Parcel in) {
        this.address_id = in.readString();
        this.member_id = in.readString();
        this.address_mobile = in.readString();
        this.address_name = in.readString();
        this.address_province = in.readString();
        this.address_city = in.readString();
        this.address_country = in.readString();
        this.address_detailed = in.readString();
        this.address_zip_code = in.readString();
        this.address_road = in.readString();
        this.address_latitude = in.readString();
        this.address_longitude = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.is_default = in.readString();
        this.is_delete = in.readString();
    }

    public static final Creator<AddressBean> CREATOR = new Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel source) {
            return new AddressBean(source);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
        }
    };
}
