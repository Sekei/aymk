package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 咨询列表
 * Created by taoh on 2018/3/14.
 */

public class ConsultBean implements Parcelable {


    /**
     * consult_record_id : 138
     * consult_record_desc : You are the only person who has been a great friend to
     * create_time : 2018-03-12 17:07:03
     * is_delete : 0
     * consult_type : text
     * consult_amount : 0.00
     * consult_start_time :
     * consult_end_time :
     * consult_time :
     * pay_way :
     * member_id : 50
     * doctor_id : 10
     * minute : 0
     * is_done : 0
     * consult_state : 0
     * type_show : 图文咨询
     * state_show : 回复中
     * is_cancel : 0
     * doctor_name :
     * doctor_department :
     * member_nick_name : 用户端
     * member_age : 0
     * member_sex : m
     * hx_account : 13089403813157884
     * consult_set_id : 0
     * member_head_image : /images/member/default.png
     * doctor_hospital :
     * pay_no :
     * is_buy_success : 1
     * consultImages :
     */

    private String consult_record_id;
    private String consult_record_desc;
    private String create_time;
    private String is_delete;
    private String consult_type;
    private String consult_amount;
    private String consult_start_time;
    private String consult_end_time;
    private String consult_time;
    private String pay_way;
    private String member_id;
    private String doctor_id;
    private String minute;
    private String is_done;
    private String consult_state;
    private String type_show;
    private String state_show;
    private String is_cancel;
    private String doctor_name;
    private String doctor_department;
    private String member_nick_name;
    private String member_age;
    private String member_sex;
    private String hx_account;
    private String consult_set_id;
    private String member_head_image;
    private String doctor_head_image;
    private String doctor_hospital;
    private String pay_no;
    private String is_buy_success;
    private String health_record_id;
    private String doctor_age;
    private String doctor_sex;
    private String record_relation;
    private String record_age;
    private String record_sex;
    private String record_name;


    private List<ConsultImagesBean> consultImages;

    public String getRecord_name() {
        return record_name;
    }

    public void setRecord_name(String record_name) {
        this.record_name = record_name;
    }

    public String getConsult_record_id() {
        return consult_record_id;
    }

    public void setConsult_record_id(String consult_record_id) {
        this.consult_record_id = consult_record_id;
    }

    public String getConsult_record_desc() {
        return consult_record_desc;
    }

    public void setConsult_record_desc(String consult_record_desc) {
        this.consult_record_desc = consult_record_desc;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
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

    public String getConsult_amount() {
        return consult_amount;
    }

    public void setConsult_amount(String consult_amount) {
        this.consult_amount = consult_amount;
    }

    public String getConsult_start_time() {
        return consult_start_time;
    }

    public void setConsult_start_time(String consult_start_time) {
        this.consult_start_time = consult_start_time;
    }

    public String getRecord_age() {
        return record_age;
    }

    public void setRecord_age(String record_age) {
        this.record_age = record_age;
    }

    public String getRecord_sex() {
        return record_sex;
    }

    public void setRecord_sex(String record_sex) {
        this.record_sex = record_sex;
    }

    public String getConsult_end_time() {
        return consult_end_time;
    }

    public void setConsult_end_time(String consult_end_time) {
        this.consult_end_time = consult_end_time;
    }

    public String getConsult_time() {
        return consult_time;
    }

    public void setConsult_time(String consult_time) {
        this.consult_time = consult_time;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
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

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getIs_done() {
        return is_done;
    }

    public void setIs_done(String is_done) {
        this.is_done = is_done;
    }

    public String getConsult_state() {
        return consult_state;
    }

    public void setConsult_state(String consult_state) {
        this.consult_state = consult_state;
    }

    public String getType_show() {
        return type_show;
    }

    public void setType_show(String type_show) {
        this.type_show = type_show;
    }

    public String getState_show() {
        return state_show;
    }

    public void setState_show(String state_show) {
        this.state_show = state_show;
    }

    public String getIs_cancel() {
        return is_cancel;
    }

    public void setIs_cancel(String is_cancel) {
        this.is_cancel = is_cancel;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_department() {
        return doctor_department;
    }

    public void setDoctor_department(String doctor_department) {
        this.doctor_department = doctor_department;
    }

    public String getMember_nick_name() {
        return member_nick_name;
    }

    public void setMember_nick_name(String member_nick_name) {
        this.member_nick_name = member_nick_name;
    }

    public String getMember_age() {
        return member_age;
    }

    public void setMember_age(String member_age) {
        this.member_age = member_age;
    }

    public String getMember_sex() {
        return member_sex;
    }

    public void setMember_sex(String member_sex) {
        this.member_sex = member_sex;
    }

    public String getHx_account() {
        return hx_account;
    }

    public void setHx_account(String hx_account) {
        this.hx_account = hx_account;
    }

    public String getConsult_set_id() {
        return consult_set_id;
    }

    public void setConsult_set_id(String consult_set_id) {
        this.consult_set_id = consult_set_id;
    }

    public String getMember_head_image() {
        return member_head_image;
    }

    public void setMember_head_image(String member_head_image) {
        this.member_head_image = member_head_image;
    }

    public String getDoctor_head_image() {
        return doctor_head_image;
    }

    public void setDoctor_head_image(String doctor_head_image) {
        this.doctor_head_image = doctor_head_image;
    }

    public String getDoctor_hospital() {
        return doctor_hospital;
    }

    public void setDoctor_hospital(String doctor_hospital) {
        this.doctor_hospital = doctor_hospital;
    }

    public String getPay_no() {
        return pay_no;
    }

    public void setPay_no(String pay_no) {
        this.pay_no = pay_no;
    }

    public String getIs_buy_success() {
        return is_buy_success;
    }

    public void setIs_buy_success(String is_buy_success) {
        this.is_buy_success = is_buy_success;
    }

    public String getHealth_record_id() {
        return health_record_id;
    }

    public void setHealth_record_id(String health_record_id) {
        this.health_record_id = health_record_id;
    }

    public String getDoctor_age() {
        return doctor_age;
    }

    public void setDoctor_age(String doctor_age) {
        this.doctor_age = doctor_age;
    }

    public String getDoctor_sex() {
        return doctor_sex;
    }

    public void setDoctor_sex(String doctor_sex) {
        this.doctor_sex = doctor_sex;
    }

    public List<ConsultImagesBean> getConsultImages() {
        return consultImages;
    }

    public void setConsultImages(List<ConsultImagesBean> consultImages) {
        this.consultImages = consultImages;
    }

    public String getRecord_relation() {
        return record_relation;
    }

    public void setRecord_relation(String record_relation) {
        this.record_relation = record_relation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.consult_record_id);
        dest.writeString(this.consult_record_desc);
        dest.writeString(this.create_time);
        dest.writeString(this.is_delete);
        dest.writeString(this.consult_type);
        dest.writeString(this.consult_amount);
        dest.writeString(this.consult_start_time);
        dest.writeString(this.consult_end_time);
        dest.writeString(this.consult_time);
        dest.writeString(this.pay_way);
        dest.writeString(this.member_id);
        dest.writeString(this.doctor_id);
        dest.writeString(this.minute);
        dest.writeString(this.is_done);
        dest.writeString(this.consult_state);
        dest.writeString(this.type_show);
        dest.writeString(this.state_show);
        dest.writeString(this.is_cancel);
        dest.writeString(this.doctor_name);
        dest.writeString(this.doctor_department);
        dest.writeString(this.member_nick_name);
        dest.writeString(this.member_age);
        dest.writeString(this.member_sex);
        dest.writeString(this.hx_account);
        dest.writeString(this.consult_set_id);
        dest.writeString(this.member_head_image);
        dest.writeString(this.doctor_head_image);
        dest.writeString(this.doctor_hospital);
        dest.writeString(this.pay_no);
        dest.writeString(this.is_buy_success);
        dest.writeString(this.health_record_id);
        dest.writeString(this.doctor_age);
        dest.writeString(this.doctor_sex);
        dest.writeTypedList(this.consultImages);
    }

    public ConsultBean() {
    }

    protected ConsultBean(Parcel in) {
        this.consult_record_id = in.readString();
        this.consult_record_desc = in.readString();
        this.create_time = in.readString();
        this.is_delete = in.readString();
        this.consult_type = in.readString();
        this.consult_amount = in.readString();
        this.consult_start_time = in.readString();
        this.consult_end_time = in.readString();
        this.consult_time = in.readString();
        this.pay_way = in.readString();
        this.member_id = in.readString();
        this.doctor_id = in.readString();
        this.minute = in.readString();
        this.is_done = in.readString();
        this.consult_state = in.readString();
        this.type_show = in.readString();
        this.state_show = in.readString();
        this.is_cancel = in.readString();
        this.doctor_name = in.readString();
        this.doctor_department = in.readString();
        this.member_nick_name = in.readString();
        this.member_age = in.readString();
        this.member_sex = in.readString();
        this.hx_account = in.readString();
        this.consult_set_id = in.readString();
        this.member_head_image = in.readString();
        this.doctor_head_image = in.readString();
        this.doctor_hospital = in.readString();
        this.pay_no = in.readString();
        this.is_buy_success = in.readString();
        this.health_record_id = in.readString();
        this.doctor_age = in.readString();
        this.doctor_sex = in.readString();
        this.consultImages = in.createTypedArrayList(ConsultImagesBean.CREATOR);
    }

    public static final Creator<ConsultBean> CREATOR = new Creator<ConsultBean>() {
        @Override
        public ConsultBean createFromParcel(Parcel source) {
            return new ConsultBean(source);
        }

        @Override
        public ConsultBean[] newArray(int size) {
            return new ConsultBean[size];
        }
    };
}
