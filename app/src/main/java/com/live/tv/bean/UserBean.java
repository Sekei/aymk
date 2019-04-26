package com.live.tv.bean;

/**
 * Created by sh-lx on 2017/5/31.
 */

public class UserBean {

    private String member_id;
    private String room_id;
    private String member_open_id;
    private String member_account;
    private String member_password;
    private String withdrawals_password;
    private String member_token;
    private String member_real_name;
    private String member_nick_name;
    private String member_phone;
    private String member_head_image;
    private String member_sex;
    private String member_sex_show;
    private String member_age;
    private String member_birthday;
    private String member_pay_password;
    private String member_type;
    private String invitation_code;
    private String fill_invitation_code;
    private String fill_member_id;
    private String fill_member_nick_name;
    private String member_create_time;
    private String member_update_time;
    private String is_delete;
    private String apply_state;
    private String hx_account;
    private String hx_password;
    private String hx_nick_name;
    private String distributor_id;
    private String distributor_state;
    private String distributor_state_show;
    private String merchants_id;
    private String tengxun_im_account;
    private String integral_value;
    private String member_amount;
    private String total_balance;
    private String cash_balance;
    private String today_balance;
    private String yesterday_balance;
    private String bank_name;
    private String bank_user_name;
    private String bank_no;
    private String bank_open_name;
    private String bank_mobile;
    private String qrcode_img;
    private String create_time;
    private String update_time;
    private String is_lock;
    private String is_lock_show;
    private String legal_face_img;
    private String legal_opposite_img;
    private String legal_hand_img;
    private String is_authentication;
    private String member_level;
    private String idcard;
    private String is_sign;
    private String doctor_type;
    private String is_associator;
    private String associator_type;
    private String houseService_state;
    private String merchants_state;
    private String doctor_state;
    private String doctor_type_show;
    private String merchants_type_show;
    private String houseService_type_show;
    private String is_binding_alipay;
    private String is_binding_wx;
    private String is_set_password;
    private String profit_amount;
    private String follow_num;
    private String fans_num;
    private String collection_num;
    private String shop_car_num;
    private String signNum;
    private String merchants_type;//商家
    private String houseService_type;//家政用户
    private String house_service_id;
    private String health_record_id;

    public String getMerchants_type() {
        return merchants_type;
    }

    public void setMerchants_type(String merchants_type) {
        this.merchants_type = merchants_type;
    }

    public String getHouseService_type() {
        return houseService_type;
    }

    public void setHouseService_type(String houseService_type) {
        this.houseService_type = houseService_type;
    }

    public String getHouse_service_id() {
        return house_service_id;
    }

    public void setHouse_service_id(String house_service_id) {
        this.house_service_id = house_service_id;
    }

    private HealthRecordDetailBean healthRecordBean;
    private DoctorDetailBean doctorBean;

    public String getSignNum() {
        return signNum;
    }

    public void setSignNum(String signNum) {
        this.signNum = signNum;
    }

    public String getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(String follow_num) {
        this.follow_num = follow_num;
    }

    public String getFans_num() {
        return fans_num;
    }

    public void setFans_num(String fans_num) {
        this.fans_num = fans_num;
    }

    public String getCollection_num() {
        return collection_num;
    }

    public void setCollection_num(String collection_num) {
        this.collection_num = collection_num;
    }

    public String getShop_car_num() {
        return shop_car_num;
    }

    public void setShop_car_num(String shop_car_num) {
        this.shop_car_num = shop_car_num;
    }

    public String getProfit_amount() {
        return profit_amount;
    }

    public void setProfit_amount(String profit_amount) {
        this.profit_amount = profit_amount;
    }

    public String getIs_set_password() {
        return is_set_password;
    }

    public void setIs_set_password(String is_set_password) {
        this.is_set_password = is_set_password;
    }

    public String getIs_binding_alipay() {
        return is_binding_alipay;
    }

    public void setIs_binding_alipay(String is_binding_alipay) {
        this.is_binding_alipay = is_binding_alipay;
    }

    public String getIs_binding_wx() {
        return is_binding_wx;
    }

    public void setIs_binding_wx(String is_binding_wx) {
        this.is_binding_wx = is_binding_wx;
    }

    public String getHouseService_state() {
        return houseService_state;
    }

    public void setHouseService_state(String houseService_state) {
        this.houseService_state = houseService_state;
    }

    public String getMerchants_state() {
        return merchants_state;
    }

    public void setMerchants_state(String merchants_state) {
        this.merchants_state = merchants_state;
    }

    public String getDoctor_state() {
        return doctor_state;
    }

    public void setDoctor_state(String doctor_state) {
        this.doctor_state = doctor_state;
    }

    public String getDoctor_type_show() {
        return doctor_type_show;
    }

    public void setDoctor_type_show(String doctor_type_show) {
        this.doctor_type_show = doctor_type_show;
    }

    public String getMerchants_type_show() {
        return merchants_type_show;
    }

    public void setMerchants_type_show(String merchants_type_show) {
        this.merchants_type_show = merchants_type_show;
    }

    public String getHouseService_type_show() {
        return houseService_type_show;
    }

    public void setHouseService_type_show(String houseService_type_show) {
        this.houseService_type_show = houseService_type_show;
    }

    public String getIs_associator() {
        return is_associator;
    }

    public void setIs_associator(String is_associator) {
        this.is_associator = is_associator;
    }

    public String getAssociator_type() {
        return associator_type;
    }

    public void setAssociator_type(String associator_type) {
        this.associator_type = associator_type;
    }

    public String getDoctor_type() {
        return doctor_type;
    }

    public void setDoctor_type(String doctor_type) {
        this.doctor_type = doctor_type;
    }

    public String getIs_sign() {
        return is_sign;
    }

    public void setIs_sign(String is_sign) {
        this.is_sign = is_sign;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getMember_open_id() {
        return member_open_id;
    }

    public void setMember_open_id(String member_open_id) {
        this.member_open_id = member_open_id;
    }

    public String getMember_account() {
        return member_account;
    }

    public void setMember_account(String member_account) {
        this.member_account = member_account;
    }

    public String getMember_password() {
        return member_password;
    }

    public void setMember_password(String member_password) {
        this.member_password = member_password;
    }

    public String getWithdrawals_password() {
        return withdrawals_password;
    }

    public void setWithdrawals_password(String withdrawals_password) {
        this.withdrawals_password = withdrawals_password;
    }

    public String getMember_token() {
        return member_token;
    }

    public void setMember_token(String member_token) {
        this.member_token = member_token;
    }

    public String getMember_real_name() {
        return member_real_name;
    }

    public void setMember_real_name(String member_real_name) {
        this.member_real_name = member_real_name;
    }

    public String getMember_nick_name() {
        return member_nick_name;
    }

    public void setMember_nick_name(String member_nick_name) {
        this.member_nick_name = member_nick_name;
    }

    public String getMember_phone() {
        return member_phone;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }

    public String getMember_head_image() {
        return member_head_image;
    }

    public void setMember_head_image(String member_head_image) {
        this.member_head_image = member_head_image;
    }

    public String getMember_sex() {
        return member_sex;
    }

    public void setMember_sex(String member_sex) {
        this.member_sex = member_sex;
    }

    public String getMember_sex_show() {
        return member_sex_show;
    }

    public void setMember_sex_show(String member_sex_show) {
        this.member_sex_show = member_sex_show;
    }

    public String getMember_age() {
        return member_age;
    }

    public void setMember_age(String member_age) {
        this.member_age = member_age;
    }

    public String getMember_birthday() {
        return member_birthday;
    }

    public void setMember_birthday(String member_birthday) {
        this.member_birthday = member_birthday;
    }

    public String getMember_pay_password() {
        return member_pay_password;
    }

    public void setMember_pay_password(String member_pay_password) {
        this.member_pay_password = member_pay_password;
    }

    public String getMember_type() {
        return member_type;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public String getInvitation_code() {
        return invitation_code;
    }

    public void setInvitation_code(String invitation_code) {
        this.invitation_code = invitation_code;
    }

    public String getFill_invitation_code() {
        return fill_invitation_code;
    }

    public void setFill_invitation_code(String fill_invitation_code) {
        this.fill_invitation_code = fill_invitation_code;
    }

    public String getFill_member_id() {
        return fill_member_id;
    }

    public void setFill_member_id(String fill_member_id) {
        this.fill_member_id = fill_member_id;
    }

    public String getFill_member_nick_name() {
        return fill_member_nick_name;
    }

    public void setFill_member_nick_name(String fill_member_nick_name) {
        this.fill_member_nick_name = fill_member_nick_name;
    }

    public String getMember_create_time() {
        return member_create_time;
    }

    public void setMember_create_time(String member_create_time) {
        this.member_create_time = member_create_time;
    }

    public String getMember_update_time() {
        return member_update_time;
    }

    public void setMember_update_time(String member_update_time) {
        this.member_update_time = member_update_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
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

    public String getHx_nick_name() {
        return hx_nick_name;
    }

    public void setHx_nick_name(String hx_nick_name) {
        this.hx_nick_name = hx_nick_name;
    }

    public String getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(String distributor_id) {
        this.distributor_id = distributor_id;
    }

    public String getDistributor_state() {
        return distributor_state;
    }

    public void setDistributor_state(String distributor_state) {
        this.distributor_state = distributor_state;
    }

    public String getDistributor_state_show() {
        return distributor_state_show;
    }

    public void setDistributor_state_show(String distributor_state_show) {
        this.distributor_state_show = distributor_state_show;
    }

    public String getMerchants_id() {
        return merchants_id;
    }

    public void setMerchants_id(String merchants_id) {
        this.merchants_id = merchants_id;
    }

    public String getTengxun_im_account() {
        return tengxun_im_account;
    }

    public void setTengxun_im_account(String tengxun_im_account) {
        this.tengxun_im_account = tengxun_im_account;
    }

    public String getIntegral_value() {
        return integral_value;
    }

    public void setIntegral_value(String integral_value) {
        this.integral_value = integral_value;
    }

    public String getMember_amount() {
        return member_amount;
    }

    public void setMember_amount(String member_amount) {
        this.member_amount = member_amount;
    }

    public String getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(String total_balance) {
        this.total_balance = total_balance;
    }

    public String getCash_balance() {
        return cash_balance;
    }

    public void setCash_balance(String cash_balance) {
        this.cash_balance = cash_balance;
    }

    public String getToday_balance() {
        return today_balance;
    }

    public void setToday_balance(String today_balance) {
        this.today_balance = today_balance;
    }

    public String getYesterday_balance() {
        return yesterday_balance;
    }

    public void setYesterday_balance(String yesterday_balance) {
        this.yesterday_balance = yesterday_balance;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_user_name() {
        return bank_user_name;
    }

    public void setBank_user_name(String bank_user_name) {
        this.bank_user_name = bank_user_name;
    }

    public String getBank_no() {
        return bank_no;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getBank_open_name() {
        return bank_open_name;
    }

    public void setBank_open_name(String bank_open_name) {
        this.bank_open_name = bank_open_name;
    }

    public String getBank_mobile() {
        return bank_mobile;
    }

    public void setBank_mobile(String bank_mobile) {
        this.bank_mobile = bank_mobile;
    }

    public String getQrcode_img() {
        return qrcode_img;
    }

    public void setQrcode_img(String qrcode_img) {
        this.qrcode_img = qrcode_img;
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

    public String getIs_lock() {
        return is_lock;
    }

    public void setIs_lock(String is_lock) {
        this.is_lock = is_lock;
    }

    public String getIs_lock_show() {
        return is_lock_show;
    }

    public void setIs_lock_show(String is_lock_show) {
        this.is_lock_show = is_lock_show;
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

    public String getIs_authentication() {
        return is_authentication;
    }

    public void setIs_authentication(String is_authentication) {
        this.is_authentication = is_authentication;
    }

    public String getHealth_record_id() {
        return health_record_id;
    }

    public void setHealth_record_id(String health_record_id) {
        this.health_record_id = health_record_id;
    }

    public String getMember_level() {
        return member_level;
    }

    public void setMember_level(String member_level) {
        this.member_level = member_level;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public HealthRecordDetailBean getHealthRecordBean() {
        return healthRecordBean;
    }

    public void setHealthRecordBean(HealthRecordDetailBean healthRecordBean) {
        this.healthRecordBean = healthRecordBean;
    }

    public DoctorDetailBean getDoctorBean() {
        return doctorBean;
    }

    public void setDoctorBean(DoctorDetailBean doctorBean) {
        this.doctorBean = doctorBean;
    }
}