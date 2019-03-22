package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class DoctorDetailBean implements Parcelable {


    /**
     * doctor_id : 1
     * doctor_desc :
     * doctor_name : 王医生
     * doctor_phone :
     * doctor_email : 12133121
     * doctor_title : 主治医师
     * doctor_hospital : 上海医院
     * doctor_department : 心内科
     * doctor_attendingtype : 精神病
     * medical_certification_img : /images/goods/20171225//1514186915497.jpg
     * physician_certificate_img : /images/goods/20171225//1514186915497.jpg
     * license_img :
     * doctor_certificate_img :
     * legal_face_img : /images/goods/20171225//1514186915497.jpg
     * legal_opposite_img : /images/goods/20171225//1514186915497.jpg
     * legal_hand_img : /images/goods/20171225//1514186915497.jpg
     * apply_state : 1
     * doctor_province : 上海市
     * doctor_city : 上海市
     * doctor_country : 闵行区
     * is_delete : 0
     * hx_account :
     * hx_password :
     * hx_custom_id :
     * doctor_address :
     * member_id : 28
     * live_id :
     * assessment_count : 8
     * consulting_count : 0
     * rate : 25.0%
     * attention_count : 0
     * graphic_price : 100
     * phone_price : 0
     * video_price : 0
     * health_management_price : 0
     * doctor_introduce :
     * doctor_background :
     * doctor_winning :
     * doctor_remarks :
     * department_level1 :
     * department_level2 :
     * job_level :
     * hospital_level :
     * create_time : 2017-12-27 14:58:09
     * update_time : 2018-01-15 17:16:04
     * is_collection : 0
     * text_consult_price : 0
     * phone_consult_price : 0
     * video_consult_price : 0
     * consultSetBeans : []
     * healthManagerBeans : []
     * doctorAssessmentBeans : []
     * fetureDateBeans : []
     * current_time :
     */

    private String doctor_id;
    private String doctor_desc;
    private String doctor_name;
    private String doctor_phone;
    private String doctor_email;
    private String doctor_title;
    private String doctor_hospital;
    private String doctor_department;
    private String doctor_attendingtype;
    private String medical_certification_img;
    private String physician_certificate_img;
    private String license_img;
    private String doctor_certificate_img;
    private String legal_face_img;
    private String legal_opposite_img;
    private String legal_hand_img;
    private String apply_state;
    private String doctor_province;
    private String doctor_city;
    private String doctor_country;
    private String is_delete;
    private String hx_account;
    private String hx_password;
    private String hx_custom_id;
    private String doctor_address;
    private String member_id;
    private String live_id;
    private String assessment_count;
    private String consulting_count;
    private String rate;
    private String attention_count;
    private String graphic_price;
    private String phone_price;
    private String video_price;
    private String health_management_price;
    private String doctor_introduce;
    private String doctor_background;
    private String doctor_winning;
    private String doctor_remarks;
    private String department_level1;
    private String department_level2;
    private String job_level;
    private String hospital_level;
    private String create_time;
    private String update_time;
    private String is_collection;
    private String text_consult_price;
    private String phone_consult_price;
    private String video_consult_price;
    private String current_time;
    private String member_head_image;
    private String doctor_head_image;
    private String doctor_sex;
    private String doctor_birthday;
    private String doctor_real_name;
    private String doctor_age;
    private String is_play_card;

    private List<FetureDateBeans> fetureDateBeans;
    private List<HealthManagerBeans> healthManagerBeans;
    private List<HealthPlanBean> healthPlanBeans;

private  String collection_id;


    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public List<HealthPlanBean> getHealthPlanBeans() {
        return healthPlanBeans;
    }

    public void setHealthPlanBeans(List<HealthPlanBean> healthPlanBeans) {
        this.healthPlanBeans = healthPlanBeans;
    }

    public String getIs_play_card() {
        return is_play_card;
    }

    public void setIs_play_card(String is_play_card) {
        this.is_play_card = is_play_card;
    }

    public String getDoctor_head_image() {
        return doctor_head_image;
    }

    public void setDoctor_head_image(String doctor_head_image) {
        this.doctor_head_image = doctor_head_image;
    }

    public String getDoctor_sex() {
        return doctor_sex;
    }

    public void setDoctor_sex(String doctor_sex) {
        this.doctor_sex = doctor_sex;
    }

    public String getDoctor_birthday() {
        return doctor_birthday;
    }

    public void setDoctor_birthday(String doctor_birthday) {
        this.doctor_birthday = doctor_birthday;
    }

    public String getDoctor_real_name() {
        return doctor_real_name;
    }

    public void setDoctor_real_name(String doctor_real_name) {
        this.doctor_real_name = doctor_real_name;
    }

    public String getDoctor_age() {
        return doctor_age;
    }

    public void setDoctor_age(String doctor_age) {
        this.doctor_age = doctor_age;
    }

    public List<HealthManagerBeans> getHealthManagerBeans() {
        return healthManagerBeans;
    }

    public void setHealthManagerBeans(List<HealthManagerBeans> healthManagerBeans) {
        this.healthManagerBeans = healthManagerBeans;
    }

    public List<FetureDateBeans> getFetureDateBeans() {
        return fetureDateBeans;
    }

    public void setFetureDateBeans(List<FetureDateBeans> fetureDateBeans) {
        this.fetureDateBeans = fetureDateBeans;
    }

    public String getMember_head_image() {
        return member_head_image;
    }

    public void setMember_head_image(String member_head_image) {
        this.member_head_image = member_head_image;
    }

    public String getDoctor_desc() {
        return doctor_desc;
    }

    public void setDoctor_desc(String doctor_desc) {
        this.doctor_desc = doctor_desc;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_phone() {
        return doctor_phone;
    }

    public void setDoctor_phone(String doctor_phone) {
        this.doctor_phone = doctor_phone;
    }

    public String getDoctor_email() {
        return doctor_email;
    }

    public void setDoctor_email(String doctor_email) {
        this.doctor_email = doctor_email;
    }

    public String getDoctor_title() {
        return doctor_title;
    }

    public void setDoctor_title(String doctor_title) {
        this.doctor_title = doctor_title;
    }

    public String getDoctor_hospital() {
        return doctor_hospital;
    }

    public void setDoctor_hospital(String doctor_hospital) {
        this.doctor_hospital = doctor_hospital;
    }

    public String getDoctor_department() {
        return doctor_department;
    }

    public void setDoctor_department(String doctor_department) {
        this.doctor_department = doctor_department;
    }

    public String getDoctor_attendingtype() {
        return doctor_attendingtype;
    }

    public void setDoctor_attendingtype(String doctor_attendingtype) {
        this.doctor_attendingtype = doctor_attendingtype;
    }

    public String getMedical_certification_img() {
        return medical_certification_img;
    }

    public void setMedical_certification_img(String medical_certification_img) {
        this.medical_certification_img = medical_certification_img;
    }

    public String getPhysician_certificate_img() {
        return physician_certificate_img;
    }

    public void setPhysician_certificate_img(String physician_certificate_img) {
        this.physician_certificate_img = physician_certificate_img;
    }

    public String getLicense_img() {
        return license_img;
    }

    public void setLicense_img(String license_img) {
        this.license_img = license_img;
    }

    public String getDoctor_certificate_img() {
        return doctor_certificate_img;
    }

    public void setDoctor_certificate_img(String doctor_certificate_img) {
        this.doctor_certificate_img = doctor_certificate_img;
    }

    public String getLegal_face_img() {
        return legal_face_img;
    }

    public void setLegal_face_img(String legal_face_img) {
        this.legal_face_img = legal_face_img;
    }

    public String getLegal_opposite_img() {
        return legal_opposite_img;
    }

    public void setLegal_opposite_img(String legal_opposite_img) {
        this.legal_opposite_img = legal_opposite_img;
    }

    public String getLegal_hand_img() {
        return legal_hand_img;
    }

    public void setLegal_hand_img(String legal_hand_img) {
        this.legal_hand_img = legal_hand_img;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }

    public String getDoctor_province() {
        return doctor_province;
    }

    public void setDoctor_province(String doctor_province) {
        this.doctor_province = doctor_province;
    }

    public String getDoctor_city() {
        return doctor_city;
    }

    public void setDoctor_city(String doctor_city) {
        this.doctor_city = doctor_city;
    }

    public String getDoctor_country() {
        return doctor_country;
    }

    public void setDoctor_country(String doctor_country) {
        this.doctor_country = doctor_country;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getHx_account() {
        return hx_account;
    }

    public void setHx_account(String hx_account) {
        this.hx_account = hx_account;
    }

    public String getHx_password() {
        return hx_password;
    }

    public void setHx_password(String hx_password) {
        this.hx_password = hx_password;
    }

    public String getHx_custom_id() {
        return hx_custom_id;
    }

    public void setHx_custom_id(String hx_custom_id) {
        this.hx_custom_id = hx_custom_id;
    }

    public String getDoctor_address() {
        return doctor_address;
    }

    public void setDoctor_address(String doctor_address) {
        this.doctor_address = doctor_address;
    }



    public String getLive_id() {
        return live_id;
    }

    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }



    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }


    public String getGraphic_price() {
        return graphic_price;
    }

    public void setGraphic_price(String graphic_price) {
        this.graphic_price = graphic_price;
    }



    public String getDoctor_introduce() {
        return doctor_introduce;
    }

    public void setDoctor_introduce(String doctor_introduce) {
        this.doctor_introduce = doctor_introduce;
    }

    public String getDoctor_background() {
        return doctor_background;
    }

    public void setDoctor_background(String doctor_background) {
        this.doctor_background = doctor_background;
    }

    public String getDoctor_winning() {
        return doctor_winning;
    }

    public void setDoctor_winning(String doctor_winning) {
        this.doctor_winning = doctor_winning;
    }

    public String getDoctor_remarks() {
        return doctor_remarks;
    }

    public void setDoctor_remarks(String doctor_remarks) {
        this.doctor_remarks = doctor_remarks;
    }

    public String getDepartment_level1() {
        return department_level1;
    }

    public void setDepartment_level1(String department_level1) {
        this.department_level1 = department_level1;
    }

    public String getDepartment_level2() {
        return department_level2;
    }

    public void setDepartment_level2(String department_level2) {
        this.department_level2 = department_level2;
    }

    public String getJob_level() {
        return job_level;
    }

    public void setJob_level(String job_level) {
        this.job_level = job_level;
    }

    public String getHospital_level() {
        return hospital_level;
    }

    public void setHospital_level(String hospital_level) {
        this.hospital_level = hospital_level;
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

    public String getIs_collection() {
        return is_collection;
    }

    public void setIs_collection(String is_collection) {
        this.is_collection = is_collection;
    }



    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }



    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getAssessment_count() {
        return assessment_count;
    }

    public void setAssessment_count(String assessment_count) {
        this.assessment_count = assessment_count;
    }

    public String getConsulting_count() {
        return consulting_count;
    }

    public void setConsulting_count(String consulting_count) {
        this.consulting_count = consulting_count;
    }

    public String getAttention_count() {
        return attention_count;
    }

    public void setAttention_count(String attention_count) {
        this.attention_count = attention_count;
    }

    public String getPhone_price() {
        return phone_price;
    }

    public void setPhone_price(String phone_price) {
        this.phone_price = phone_price;
    }

    public String getVideo_price() {
        return video_price;
    }

    public void setVideo_price(String video_price) {
        this.video_price = video_price;
    }

    public String getHealth_management_price() {
        return health_management_price;
    }

    public void setHealth_management_price(String health_management_price) {
        this.health_management_price = health_management_price;
    }

    public String getText_consult_price() {
        return text_consult_price;
    }

    public void setText_consult_price(String text_consult_price) {
        this.text_consult_price = text_consult_price;
    }

    public String getPhone_consult_price() {
        return phone_consult_price;
    }

    public void setPhone_consult_price(String phone_consult_price) {
        this.phone_consult_price = phone_consult_price;
    }

    public String getVideo_consult_price() {
        return video_consult_price;
    }

    public void setVideo_consult_price(String video_consult_price) {
        this.video_consult_price = video_consult_price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.doctor_id);
        dest.writeString(this.doctor_desc);
        dest.writeString(this.doctor_name);
        dest.writeString(this.doctor_phone);
        dest.writeString(this.doctor_email);
        dest.writeString(this.doctor_title);
        dest.writeString(this.doctor_hospital);
        dest.writeString(this.doctor_department);
        dest.writeString(this.doctor_attendingtype);
        dest.writeString(this.medical_certification_img);
        dest.writeString(this.physician_certificate_img);
        dest.writeString(this.license_img);
        dest.writeString(this.doctor_certificate_img);
        dest.writeString(this.legal_face_img);
        dest.writeString(this.legal_opposite_img);
        dest.writeString(this.legal_hand_img);
        dest.writeString(this.apply_state);
        dest.writeString(this.doctor_province);
        dest.writeString(this.doctor_city);
        dest.writeString(this.doctor_country);
        dest.writeString(this.is_delete);
        dest.writeString(this.hx_account);
        dest.writeString(this.hx_password);
        dest.writeString(this.hx_custom_id);
        dest.writeString(this.doctor_address);
        dest.writeString(this.member_id);
        dest.writeString(this.live_id);
        dest.writeString(this.assessment_count);
        dest.writeString(this.consulting_count);
        dest.writeString(this.rate);
        dest.writeString(this.attention_count);
        dest.writeString(this.graphic_price);
        dest.writeString(this.phone_price);
        dest.writeString(this.video_price);
        dest.writeString(this.health_management_price);
        dest.writeString(this.doctor_introduce);
        dest.writeString(this.doctor_background);
        dest.writeString(this.doctor_winning);
        dest.writeString(this.doctor_remarks);
        dest.writeString(this.department_level1);
        dest.writeString(this.department_level2);
        dest.writeString(this.job_level);
        dest.writeString(this.hospital_level);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.is_collection);
        dest.writeString(this.text_consult_price);
        dest.writeString(this.phone_consult_price);
        dest.writeString(this.video_consult_price);
        dest.writeString(this.current_time);
        dest.writeString(this.member_head_image);
        dest.writeString(this.doctor_head_image);
        dest.writeString(this.doctor_sex);
        dest.writeString(this.doctor_birthday);
        dest.writeString(this.doctor_real_name);
        dest.writeString(this.doctor_age);
        dest.writeList(this.fetureDateBeans);
        dest.writeTypedList(this.healthManagerBeans);
        dest.writeList(this.healthPlanBeans);
    }

    public DoctorDetailBean() {
    }

    protected DoctorDetailBean(Parcel in) {
        this.doctor_id = in.readString();
        this.doctor_desc = in.readString();
        this.doctor_name = in.readString();
        this.doctor_phone = in.readString();
        this.doctor_email = in.readString();
        this.doctor_title = in.readString();
        this.doctor_hospital = in.readString();
        this.doctor_department = in.readString();
        this.doctor_attendingtype = in.readString();
        this.medical_certification_img = in.readString();
        this.physician_certificate_img = in.readString();
        this.license_img = in.readString();
        this.doctor_certificate_img = in.readString();
        this.legal_face_img = in.readString();
        this.legal_opposite_img = in.readString();
        this.legal_hand_img = in.readString();
        this.apply_state = in.readString();
        this.doctor_province = in.readString();
        this.doctor_city = in.readString();
        this.doctor_country = in.readString();
        this.is_delete = in.readString();
        this.hx_account = in.readString();
        this.hx_password = in.readString();
        this.hx_custom_id = in.readString();
        this.doctor_address = in.readString();
        this.member_id = in.readString();
        this.live_id = in.readString();
        this.assessment_count = in.readString();
        this.consulting_count = in.readString();
        this.rate = in.readString();
        this.attention_count = in.readString();
        this.graphic_price = in.readString();
        this.phone_price = in.readString();
        this.video_price = in.readString();
        this.health_management_price = in.readString();
        this.doctor_introduce = in.readString();
        this.doctor_background = in.readString();
        this.doctor_winning = in.readString();
        this.doctor_remarks = in.readString();
        this.department_level1 = in.readString();
        this.department_level2 = in.readString();
        this.job_level = in.readString();
        this.hospital_level = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.is_collection = in.readString();
        this.text_consult_price = in.readString();
        this.phone_consult_price = in.readString();
        this.video_consult_price = in.readString();
        this.current_time = in.readString();
        this.member_head_image = in.readString();
        this.doctor_head_image = in.readString();
        this.doctor_sex = in.readString();
        this.doctor_birthday = in.readString();
        this.doctor_real_name = in.readString();
        this.doctor_age = in.readString();
        this.fetureDateBeans = new ArrayList<FetureDateBeans>();
        in.readList(this.fetureDateBeans, FetureDateBeans.class.getClassLoader());
        this.healthManagerBeans = in.createTypedArrayList(HealthManagerBeans.CREATOR);
        this.healthPlanBeans = new ArrayList<HealthPlanBean>();
        in.readList(this.healthPlanBeans, HealthPlanBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<DoctorDetailBean> CREATOR = new Parcelable.Creator<DoctorDetailBean>() {
        @Override
        public DoctorDetailBean createFromParcel(Parcel source) {
            return new DoctorDetailBean(source);
        }

        @Override
        public DoctorDetailBean[] newArray(int size) {
            return new DoctorDetailBean[size];
        }
    };
}
