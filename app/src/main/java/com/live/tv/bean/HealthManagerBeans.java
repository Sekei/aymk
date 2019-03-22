package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by taoh on 2018/3/8.
 */

public class HealthManagerBeans implements Parcelable {

    /**
     * health_manager_id : 1
     * service_type : 体验
     * service_price : 99
     * service_desc : 体验卡
     * service_title : 体验卡
     * create_time : 2017-12-29 10:49:28
     * update_time :
     * is_delete : 0
     * member_id : 28
     * doctor_id : 1
     * service_end_time :
     */

    private String health_manager_id;
    private String service_type;
    private String service_price;
    private String service_desc;
    private String service_title;
    private String create_time;
    private String update_time;
    private String is_delete;
    private String member_id;
    private String doctor_id;
    private String service_end_time;

    public String getHealth_manager_id() {
        return health_manager_id;
    }

    public void setHealth_manager_id(String health_manager_id) {
        this.health_manager_id = health_manager_id;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_price() {
        return service_price;
    }

    public void setService_price(String service_price) {
        this.service_price = service_price;
    }

    public String getService_desc() {
        return service_desc;
    }

    public void setService_desc(String service_desc) {
        this.service_desc = service_desc;
    }

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
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

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getService_end_time() {
        return service_end_time;
    }

    public void setService_end_time(String service_end_time) {
        this.service_end_time = service_end_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.health_manager_id);
        dest.writeString(this.service_type);
        dest.writeString(this.service_price);
        dest.writeString(this.service_desc);
        dest.writeString(this.service_title);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.is_delete);
        dest.writeString(this.member_id);
        dest.writeString(this.doctor_id);
        dest.writeString(this.service_end_time);
    }

    public HealthManagerBeans() {
    }

    protected HealthManagerBeans(Parcel in) {
        this.health_manager_id = in.readString();
        this.service_type = in.readString();
        this.service_price = in.readString();
        this.service_desc = in.readString();
        this.service_title = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.is_delete = in.readString();
        this.member_id = in.readString();
        this.doctor_id = in.readString();
        this.service_end_time = in.readString();
    }

    public static final Parcelable.Creator<HealthManagerBeans> CREATOR = new Parcelable.Creator<HealthManagerBeans>() {
        @Override
        public HealthManagerBeans createFromParcel(Parcel source) {
            return new HealthManagerBeans(source);
        }

        @Override
        public HealthManagerBeans[] newArray(int size) {
            return new HealthManagerBeans[size];
        }
    };
}
