package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by taoh on 2018/5/7.
 */

public class MerchantsBean implements Parcelable {


    /**
     * merchants_id : 6
     * member_id : 68
     * member_nick_name :
     * member_real_name :
     * member_account :
     * live_id :
     * merchants_name : 鲍先生的店
     * merchants_img : /images/member/default.png
     * merchants_star1 : 5
     * merchants_star2 : 5
     * merchants_star3 : 5
     * assessment_count : 0
     * merchants_type : 2
     * contact_mobile : 18800244020
     * contact_name : 鲍先生
     * merchants_address : 新骏环路588号
     * merchants_state : 0
     * merchants_state_show :
     * merchants_province : 上海市
     * merchants_city : 上海市
     * merchants_country : 闵行区
     * merchants_email : 445647569@qq.com
     * company_mobile :
     * company_name :
     * is_delete : 0
     * create_time : 2018-05-02 16:45:10.0
     * update_time : 2018-07-06 14:56:10.0
     * apply_state : 1
     * apply_state_show :
     * pay_state : 0
     * refuse_remark :
     * hx_account :
     * hx_password :
     * hx_custom_id :
     * lng : 121.525901
     * lat : 31.091778
     * legal_img :
     * legal_face_img : /images/others/15266229045001607902013.jpg
     * legal_opposite_img : /images/others/1526622904883183011422.jpg
     * legal_hand_img : /images/others/15266229052631048957737.jpg
     * business_img : /images/others/1526622904241791203326.jpg
     * business_img1 :
     * business_img2 :
     * business_img3 :
     * merchants_introduction :
     * day_sales :
     * manage_range :
     * merchants_position :
     * goods_id :
     * is_collection : 0
     * is_stores : 0
     * is_stores_show :
     * is_hot : 1
     * distance :
     * background_image :
     */

    private String merchants_id;
    private String member_id;
    private String member_nick_name;
    private String member_real_name;
    private String member_account;
    private String live_id;
    private String merchants_name;
    private String merchants_img;
    private String merchants_star1;
    private String merchants_star2;
    private String merchants_star3;
    private String assessment_count;
    private String merchants_type;
    private String contact_mobile;
    private String contact_name;
    private String merchants_address;
    private String merchants_state;
    private String merchants_state_show;
    private String merchants_province;
    private String merchants_city;
    private String merchants_country;
    private String merchants_email;
    private String company_mobile;
    private String company_name;
    private String is_delete;
    private String create_time;
    private String update_time;
    private String apply_state;
    private String apply_state_show;
    private String pay_state;
    private String refuse_remark;
    private String hx_account;
    private String hx_password;
    private String hx_custom_id;
    private String lng;
    private String lat;
    private String legal_img;
    private String legal_face_img;
    private String legal_opposite_img;
    private String legal_hand_img;
    private String business_img;
    private String business_img1;
    private String business_img2;
    private String business_img3;
    private String merchants_introduction;
    private String day_sales;
    private String manage_range;
    private String merchants_position;
    private String goods_id;
    private String is_collection;
    private String is_stores;
    private String is_stores_show;
    private String is_hot;
    private String distance;
    private String background_image;
    public MerchantsBean() {
    }
    protected MerchantsBean(Parcel in) {
        merchants_id = in.readString();
        member_id = in.readString();
        member_nick_name = in.readString();
        member_real_name = in.readString();
        member_account = in.readString();
        live_id = in.readString();
        merchants_name = in.readString();
        merchants_img = in.readString();
        merchants_star1 = in.readString();
        merchants_star2 = in.readString();
        merchants_star3 = in.readString();
        assessment_count = in.readString();
        merchants_type = in.readString();
        contact_mobile = in.readString();
        contact_name = in.readString();
        merchants_address = in.readString();
        merchants_state = in.readString();
        merchants_state_show = in.readString();
        merchants_province = in.readString();
        merchants_city = in.readString();
        merchants_country = in.readString();
        merchants_email = in.readString();
        company_mobile = in.readString();
        company_name = in.readString();
        is_delete = in.readString();
        create_time = in.readString();
        update_time = in.readString();
        apply_state = in.readString();
        apply_state_show = in.readString();
        pay_state = in.readString();
        refuse_remark = in.readString();
        hx_account = in.readString();
        hx_password = in.readString();
        hx_custom_id = in.readString();
        lng = in.readString();
        lat = in.readString();
        legal_img = in.readString();
        legal_face_img = in.readString();
        legal_opposite_img = in.readString();
        legal_hand_img = in.readString();
        business_img = in.readString();
        business_img1 = in.readString();
        business_img2 = in.readString();
        business_img3 = in.readString();
        merchants_introduction = in.readString();
        day_sales = in.readString();
        manage_range = in.readString();
        merchants_position = in.readString();
        goods_id = in.readString();
        is_collection = in.readString();
        is_stores = in.readString();
        is_stores_show = in.readString();
        is_hot = in.readString();
        distance = in.readString();
        background_image = in.readString();
    }

    public static final Creator<MerchantsBean> CREATOR = new Creator<MerchantsBean>() {
        @Override
        public MerchantsBean createFromParcel(Parcel in) {
            return new MerchantsBean(in);
        }

        @Override
        public MerchantsBean[] newArray(int size) {
            return new MerchantsBean[size];
        }
    };

    public String getMerchants_id() {
        return merchants_id;
    }

    public void setMerchants_id(String merchants_id) {
        this.merchants_id = merchants_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_nick_name() {
        return member_nick_name;
    }

    public void setMember_nick_name(String member_nick_name) {
        this.member_nick_name = member_nick_name;
    }

    public String getMember_real_name() {
        return member_real_name;
    }

    public void setMember_real_name(String member_real_name) {
        this.member_real_name = member_real_name;
    }

    public String getMember_account() {
        return member_account;
    }

    public void setMember_account(String member_account) {
        this.member_account = member_account;
    }

    public String getLive_id() {
        return live_id;
    }

    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }

    public String getMerchants_name() {
        return merchants_name;
    }

    public void setMerchants_name(String merchants_name) {
        this.merchants_name = merchants_name;
    }

    public String getMerchants_img() {
        return merchants_img;
    }

    public void setMerchants_img(String merchants_img) {
        this.merchants_img = merchants_img;
    }

    public String getMerchants_star1() {
        return merchants_star1;
    }

    public void setMerchants_star1(String merchants_star1) {
        this.merchants_star1 = merchants_star1;
    }

    public String getMerchants_star2() {
        return merchants_star2;
    }

    public void setMerchants_star2(String merchants_star2) {
        this.merchants_star2 = merchants_star2;
    }

    public String getMerchants_star3() {
        return merchants_star3;
    }

    public void setMerchants_star3(String merchants_star3) {
        this.merchants_star3 = merchants_star3;
    }

    public String getAssessment_count() {
        return assessment_count;
    }

    public void setAssessment_count(String assessment_count) {
        this.assessment_count = assessment_count;
    }

    public String getMerchants_type() {
        return merchants_type;
    }

    public void setMerchants_type(String merchants_type) {
        this.merchants_type = merchants_type;
    }

    public String getContact_mobile() {
        return contact_mobile;
    }

    public void setContact_mobile(String contact_mobile) {
        this.contact_mobile = contact_mobile;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getMerchants_address() {
        return merchants_address;
    }

    public void setMerchants_address(String merchants_address) {
        this.merchants_address = merchants_address;
    }

    public String getMerchants_state() {
        return merchants_state;
    }

    public void setMerchants_state(String merchants_state) {
        this.merchants_state = merchants_state;
    }

    public String getMerchants_state_show() {
        return merchants_state_show;
    }

    public void setMerchants_state_show(String merchants_state_show) {
        this.merchants_state_show = merchants_state_show;
    }

    public String getMerchants_province() {
        return merchants_province;
    }

    public void setMerchants_province(String merchants_province) {
        this.merchants_province = merchants_province;
    }

    public String getMerchants_city() {
        return merchants_city;
    }

    public void setMerchants_city(String merchants_city) {
        this.merchants_city = merchants_city;
    }

    public String getMerchants_country() {
        return merchants_country;
    }

    public void setMerchants_country(String merchants_country) {
        this.merchants_country = merchants_country;
    }

    public String getMerchants_email() {
        return merchants_email;
    }

    public void setMerchants_email(String merchants_email) {
        this.merchants_email = merchants_email;
    }

    public String getCompany_mobile() {
        return company_mobile;
    }

    public void setCompany_mobile(String company_mobile) {
        this.company_mobile = company_mobile;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
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

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }

    public String getApply_state_show() {
        return apply_state_show;
    }

    public void setApply_state_show(String apply_state_show) {
        this.apply_state_show = apply_state_show;
    }

    public String getPay_state() {
        return pay_state;
    }

    public void setPay_state(String pay_state) {
        this.pay_state = pay_state;
    }

    public String getRefuse_remark() {
        return refuse_remark;
    }

    public void setRefuse_remark(String refuse_remark) {
        this.refuse_remark = refuse_remark;
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

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLegal_img() {
        return legal_img;
    }

    public void setLegal_img(String legal_img) {
        this.legal_img = legal_img;
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

    public String getBusiness_img() {
        return business_img;
    }

    public void setBusiness_img(String business_img) {
        this.business_img = business_img;
    }

    public String getBusiness_img1() {
        return business_img1;
    }

    public void setBusiness_img1(String business_img1) {
        this.business_img1 = business_img1;
    }

    public String getBusiness_img2() {
        return business_img2;
    }

    public void setBusiness_img2(String business_img2) {
        this.business_img2 = business_img2;
    }

    public String getBusiness_img3() {
        return business_img3;
    }

    public void setBusiness_img3(String business_img3) {
        this.business_img3 = business_img3;
    }

    public String getMerchants_introduction() {
        return merchants_introduction;
    }

    public void setMerchants_introduction(String merchants_introduction) {
        this.merchants_introduction = merchants_introduction;
    }

    public String getDay_sales() {
        return day_sales;
    }

    public void setDay_sales(String day_sales) {
        this.day_sales = day_sales;
    }

    public String getManage_range() {
        return manage_range;
    }

    public void setManage_range(String manage_range) {
        this.manage_range = manage_range;
    }

    public String getMerchants_position() {
        return merchants_position;
    }

    public void setMerchants_position(String merchants_position) {
        this.merchants_position = merchants_position;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getIs_collection() {
        return is_collection;
    }

    public void setIs_collection(String is_collection) {
        this.is_collection = is_collection;
    }

    public String getIs_stores() {
        return is_stores;
    }

    public void setIs_stores(String is_stores) {
        this.is_stores = is_stores;
    }

    public String getIs_stores_show() {
        return is_stores_show;
    }

    public void setIs_stores_show(String is_stores_show) {
        this.is_stores_show = is_stores_show;
    }

    public String getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(String is_hot) {
        this.is_hot = is_hot;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(merchants_id);
        dest.writeString(member_id);
        dest.writeString(member_nick_name);
        dest.writeString(member_real_name);
        dest.writeString(member_account);
        dest.writeString(live_id);
        dest.writeString(merchants_name);
        dest.writeString(merchants_img);
        dest.writeString(merchants_star1);
        dest.writeString(merchants_star2);
        dest.writeString(merchants_star3);
        dest.writeString(assessment_count);
        dest.writeString(merchants_type);
        dest.writeString(contact_mobile);
        dest.writeString(contact_name);
        dest.writeString(merchants_address);
        dest.writeString(merchants_state);
        dest.writeString(merchants_state_show);
        dest.writeString(merchants_province);
        dest.writeString(merchants_city);
        dest.writeString(merchants_country);
        dest.writeString(merchants_email);
        dest.writeString(company_mobile);
        dest.writeString(company_name);
        dest.writeString(is_delete);
        dest.writeString(create_time);
        dest.writeString(update_time);
        dest.writeString(apply_state);
        dest.writeString(apply_state_show);
        dest.writeString(pay_state);
        dest.writeString(refuse_remark);
        dest.writeString(hx_account);
        dest.writeString(hx_password);
        dest.writeString(hx_custom_id);
        dest.writeString(lng);
        dest.writeString(lat);
        dest.writeString(legal_img);
        dest.writeString(legal_face_img);
        dest.writeString(legal_opposite_img);
        dest.writeString(legal_hand_img);
        dest.writeString(business_img);
        dest.writeString(business_img1);
        dest.writeString(business_img2);
        dest.writeString(business_img3);
        dest.writeString(merchants_introduction);
        dest.writeString(day_sales);
        dest.writeString(manage_range);
        dest.writeString(merchants_position);
        dest.writeString(goods_id);
        dest.writeString(is_collection);
        dest.writeString(is_stores);
        dest.writeString(is_stores_show);
        dest.writeString(is_hot);
        dest.writeString(distance);
        dest.writeString(background_image);
    }
}
