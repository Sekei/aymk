package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Created by stone
 * @since 2018/1/24
 */

public class ConsultTimesBean implements Parcelable {


    /**
     * consult_set_id : 17
     * consult_set_day : 2018-12-26
     * start_time : 2017-12-26 10:33:00
     * end_time : 2017-12-26 10:34:00
     * create_time : 2018-01-25 14:20:32
     * update_time :
     * is_delete : 0
     * consult_type : phone
     * member_id : 28
     * doctor_id : 1
     * member_num : 2
     * is_open : 0  是开   1  是关
     * date : 星期三
     * service_time : 15
     * service_money : 0
     * now_day :
     * consultSetBeans : []
     */

    private int consult_set_id;
    private String consult_set_day;
    private String start_time;
    private String end_time;
    private String create_time;
    private String update_time;
    private String is_delete;
    private String consult_type;
    private int member_id;
    private int doctor_id;
    private int member_num;
    private String is_open;
    private String date;
    private String service_time;
    private int service_money;
    private String now_day;
    private String doctor_name;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public int getConsult_set_id() {
        return consult_set_id;
    }

    public void setConsult_set_id(int consult_set_id) {
        this.consult_set_id = consult_set_id;
    }

    public String getConsult_set_day() {
        return consult_set_day;
    }

    public void setConsult_set_day(String consult_set_day) {
        this.consult_set_day = consult_set_day;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
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

    public String getConsult_type() {
        return consult_type;
    }

    public void setConsult_type(String consult_type) {
        this.consult_type = consult_type;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getMember_num() {
        return member_num;
    }

    public void setMember_num(int member_num) {
        this.member_num = member_num;
    }

    public String getIs_open() {
        return is_open;
    }

    public void setIs_open(String is_open) {
        this.is_open = is_open;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getService_time() {
        return service_time;
    }

    public void setService_time(String service_time) {
        this.service_time = service_time;
    }

    public int getService_money() {
        return service_money;
    }

    public void setService_money(int service_money) {
        this.service_money = service_money;
    }

    public String getNow_day() {
        return now_day;
    }

    public void setNow_day(String now_day) {
        this.now_day = now_day;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.consult_set_id);
        dest.writeString(this.consult_set_day);
        dest.writeString(this.start_time);
        dest.writeString(this.end_time);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.is_delete);
        dest.writeString(this.consult_type);
        dest.writeInt(this.member_id);
        dest.writeInt(this.doctor_id);
        dest.writeInt(this.member_num);
        dest.writeString(this.is_open);
        dest.writeString(this.date);
        dest.writeString(this.service_time);
        dest.writeInt(this.service_money);
        dest.writeString(this.now_day);
    }

    public ConsultTimesBean() {
    }

    protected ConsultTimesBean(Parcel in) {
        this.consult_set_id = in.readInt();
        this.consult_set_day = in.readString();
        this.start_time = in.readString();
        this.end_time = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.is_delete = in.readString();
        this.consult_type = in.readString();
        this.member_id = in.readInt();
        this.doctor_id = in.readInt();
        this.member_num = in.readInt();
        this.is_open = in.readString();
        this.date = in.readString();
        this.service_time = in.readString();
        this.service_money = in.readInt();
        this.now_day = in.readString();
    }

    public static final Parcelable.Creator<ConsultTimesBean> CREATOR = new Parcelable.Creator<ConsultTimesBean>() {
        @Override
        public ConsultTimesBean createFromParcel(Parcel source) {
            return new ConsultTimesBean(source);
        }

        @Override
        public ConsultTimesBean[] newArray(int size) {
            return new ConsultTimesBean[size];
        }
    };
}
