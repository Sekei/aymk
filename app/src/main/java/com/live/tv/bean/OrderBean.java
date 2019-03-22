package com.live.tv.bean;

import java.util.List;

/**
 * Created by taoh on 2018/4/9.
 */

public class OrderBean {


    /**
     * order_id : 71
     * merchants_id : 1
     * doctor_id : 0
     * health_record_id : 0
     * member_id : 28
     * member_nick_name :
     * member_account :
     * distributor_id :
     * order_no : 1938961450814931977
     * address_id : 25
     * address_mobile : 123
     * address_name : 黄
     * address_province : 浙江省
     * address_city : 丽水市
     * address_country : 遂昌县
     * address_detailed : 大柘
     * address_road :
     * address_zip_code : 3333
     * address_longitude :
     * address_latitude :
     * order_total_price : 400.00
     * order_actual_price : 400.00
     * order_type : goods
     * order_type_show : 正常购买
     * order_state : cancel
     * order_state_show : 已取消
     * order_remark :
     * is_deduct_integral :
     * deduct_integral_value :
     * deduct_integral_price :
     * deduct_integral_percent :
     * custom_remark :
     * create_time : 2018-03-02 15:53:09.0
     * pay_time :
     * cancel_time : 2018-03-02 15:53:50.0
     * cancel_end_time : 1519978991000
     * receive_time :
     * send_time :
     * assessment_time :
     * is_delete : 0
     * pay_way :
     * pay_no :
     * pay_charge :
     * ping_no :
     * member_group_id :
     * is_give_integral : 0
     * give_integral_value :
     * logistics_no :
     * logistics_name :
     * logistics_pinyin :
     * logistics_mobile :
     * logistics_state :
     * invoice_state : wait_apply
     * invoice_type : 0
     * invoice_type_show : 个人
     * invoice_rise :
     * invoice_no :
     * invoice_desc :
     * invoice_price :
     * invoice_mobile :
     * invoice_name :
     * invoice_province :
     * invoice_city :
     * invoice_country :
     * invoice_address :
     * invoice_time :
     * consult_start_time :
     * consult_end_time :
     * goods_name :
     * goods_welfare_id :
     * goods_id :
     * goods_num :
     * express_price :
     * addressBean : {}
     */

    private String order_id;
    private String merchants_id;
    private String doctor_id;
    private String health_record_id;
    private String member_id;
    private String member_nick_name;
    private String member_account;
    private String distributor_id;
    private String order_no;
    private String address_id;
    private String address_mobile;
    private String address_name;
    private String address_province;
    private String address_city;
    private String address_country;
    private String address_detailed;
    private String address_road;
    private String address_zip_code;
    private String address_longitude;
    private String address_latitude;
    private String order_total_price;
    private String order_actual_price;

    private String order_type;
    private String order_type_show;
    private String order_state;
    private String order_state_show;
    private String order_remark;
    private String is_deduct_integral;
    private String deduct_integral_value;
    private String deduct_integral_price;
    private String deduct_integral_percent;
    private String custom_remark;
    private String create_time;
    private String pay_time;
    private String cancel_time;
    private String cancel_end_time;
    private String receive_time;
    private String send_time;
    private String assessment_time;
    private String is_delete;
    private String pay_way;
    private String pay_no;
    private String pay_charge;
    private String ping_no;
    private String member_group_id;
    private String is_give_integral;
    private String give_integral_value;
    private String logistics_no;
    private String logistics_name;
    private String logistics_pinyin;
    private String logistics_mobile;
    private String logistics_state;
    private String invoice_state;
    private String invoice_type;
    private String invoice_type_show;
    private String invoice_rise;
    private String invoice_no;
    private String invoice_desc;
    private String invoice_price;
    private String invoice_mobile;
    private String invoice_name;
    private String invoice_province;
    private String invoice_city;
    private String invoice_country;
    private String invoice_address;
    private String invoice_time;
    private String consult_start_time;
    private String consult_end_time;
    private String goods_name;
    private String goods_welfare_id;
    private String goods_id;
    private String goods_num;
    private String express_price;
    private String merchants_name;
    private String merchants_img;
    private String contact_phone;
    private String coupon_code;

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
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

    private List<OrderGoodsBeans> orderGoodsBeans;

    public List<OrderGoodsBeans> getOrderGoodsBeans() {
        return orderGoodsBeans;
    }

    public void setOrderGoodsBeans(List<OrderGoodsBeans> orderGoodsBeans) {
        this.orderGoodsBeans = orderGoodsBeans;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getMerchants_id() {
        return merchants_id;
    }

    public void setMerchants_id(String merchants_id) {
        this.merchants_id = merchants_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getHealth_record_id() {
        return health_record_id;
    }

    public void setHealth_record_id(String health_record_id) {
        this.health_record_id = health_record_id;
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

    public String getMember_account() {
        return member_account;
    }

    public void setMember_account(String member_account) {
        this.member_account = member_account;
    }

    public String getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(String distributor_id) {
        this.distributor_id = distributor_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
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

    public String getAddress_road() {
        return address_road;
    }

    public void setAddress_road(String address_road) {
        this.address_road = address_road;
    }

    public String getAddress_zip_code() {
        return address_zip_code;
    }

    public void setAddress_zip_code(String address_zip_code) {
        this.address_zip_code = address_zip_code;
    }

    public String getAddress_longitude() {
        return address_longitude;
    }

    public void setAddress_longitude(String address_longitude) {
        this.address_longitude = address_longitude;
    }

    public String getAddress_latitude() {
        return address_latitude;
    }

    public void setAddress_latitude(String address_latitude) {
        this.address_latitude = address_latitude;
    }

    public String getOrder_total_price() {
        return order_total_price;
    }

    public void setOrder_total_price(String order_total_price) {
        this.order_total_price = order_total_price;
    }

    public String getOrder_actual_price() {
        return order_actual_price;
    }

    public void setOrder_actual_price(String order_actual_price) {
        this.order_actual_price = order_actual_price;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_type_show() {
        return order_type_show;
    }

    public void setOrder_type_show(String order_type_show) {
        this.order_type_show = order_type_show;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public String getOrder_state_show() {
        return order_state_show;
    }

    public void setOrder_state_show(String order_state_show) {
        this.order_state_show = order_state_show;
    }

    public String getOrder_remark() {
        return order_remark;
    }

    public void setOrder_remark(String order_remark) {
        this.order_remark = order_remark;
    }

    public String getIs_deduct_integral() {
        return is_deduct_integral;
    }

    public void setIs_deduct_integral(String is_deduct_integral) {
        this.is_deduct_integral = is_deduct_integral;
    }

    public String getDeduct_integral_value() {
        return deduct_integral_value;
    }

    public void setDeduct_integral_value(String deduct_integral_value) {
        this.deduct_integral_value = deduct_integral_value;
    }

    public String getDeduct_integral_price() {
        return deduct_integral_price;
    }

    public void setDeduct_integral_price(String deduct_integral_price) {
        this.deduct_integral_price = deduct_integral_price;
    }

    public String getDeduct_integral_percent() {
        return deduct_integral_percent;
    }

    public void setDeduct_integral_percent(String deduct_integral_percent) {
        this.deduct_integral_percent = deduct_integral_percent;
    }

    public String getCustom_remark() {
        return custom_remark;
    }

    public void setCustom_remark(String custom_remark) {
        this.custom_remark = custom_remark;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getCancel_time() {
        return cancel_time;
    }

    public void setCancel_time(String cancel_time) {
        this.cancel_time = cancel_time;
    }

    public String getCancel_end_time() {
        return cancel_end_time;
    }

    public void setCancel_end_time(String cancel_end_time) {
        this.cancel_end_time = cancel_end_time;
    }

    public String getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(String receive_time) {
        this.receive_time = receive_time;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getAssessment_time() {
        return assessment_time;
    }

    public void setAssessment_time(String assessment_time) {
        this.assessment_time = assessment_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public String getPay_no() {
        return pay_no;
    }

    public void setPay_no(String pay_no) {
        this.pay_no = pay_no;
    }

    public String getPay_charge() {
        return pay_charge;
    }

    public void setPay_charge(String pay_charge) {
        this.pay_charge = pay_charge;
    }

    public String getPing_no() {
        return ping_no;
    }

    public void setPing_no(String ping_no) {
        this.ping_no = ping_no;
    }

    public String getMember_group_id() {
        return member_group_id;
    }

    public void setMember_group_id(String member_group_id) {
        this.member_group_id = member_group_id;
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

    public String getLogistics_no() {
        return logistics_no;
    }

    public void setLogistics_no(String logistics_no) {
        this.logistics_no = logistics_no;
    }

    public String getLogistics_name() {
        return logistics_name;
    }

    public void setLogistics_name(String logistics_name) {
        this.logistics_name = logistics_name;
    }

    public String getLogistics_pinyin() {
        return logistics_pinyin;
    }

    public void setLogistics_pinyin(String logistics_pinyin) {
        this.logistics_pinyin = logistics_pinyin;
    }

    public String getLogistics_mobile() {
        return logistics_mobile;
    }

    public void setLogistics_mobile(String logistics_mobile) {
        this.logistics_mobile = logistics_mobile;
    }

    public String getLogistics_state() {
        return logistics_state;
    }

    public void setLogistics_state(String logistics_state) {
        this.logistics_state = logistics_state;
    }

    public String getInvoice_state() {
        return invoice_state;
    }

    public void setInvoice_state(String invoice_state) {
        this.invoice_state = invoice_state;
    }

    public String getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(String invoice_type) {
        this.invoice_type = invoice_type;
    }

    public String getInvoice_type_show() {
        return invoice_type_show;
    }

    public void setInvoice_type_show(String invoice_type_show) {
        this.invoice_type_show = invoice_type_show;
    }

    public String getInvoice_rise() {
        return invoice_rise;
    }

    public void setInvoice_rise(String invoice_rise) {
        this.invoice_rise = invoice_rise;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getInvoice_desc() {
        return invoice_desc;
    }

    public void setInvoice_desc(String invoice_desc) {
        this.invoice_desc = invoice_desc;
    }

    public String getInvoice_price() {
        return invoice_price;
    }

    public void setInvoice_price(String invoice_price) {
        this.invoice_price = invoice_price;
    }

    public String getInvoice_mobile() {
        return invoice_mobile;
    }

    public void setInvoice_mobile(String invoice_mobile) {
        this.invoice_mobile = invoice_mobile;
    }

    public String getInvoice_name() {
        return invoice_name;
    }

    public void setInvoice_name(String invoice_name) {
        this.invoice_name = invoice_name;
    }

    public String getInvoice_province() {
        return invoice_province;
    }

    public void setInvoice_province(String invoice_province) {
        this.invoice_province = invoice_province;
    }

    public String getInvoice_city() {
        return invoice_city;
    }

    public void setInvoice_city(String invoice_city) {
        this.invoice_city = invoice_city;
    }

    public String getInvoice_country() {
        return invoice_country;
    }

    public void setInvoice_country(String invoice_country) {
        this.invoice_country = invoice_country;
    }

    public String getInvoice_address() {
        return invoice_address;
    }

    public void setInvoice_address(String invoice_address) {
        this.invoice_address = invoice_address;
    }

    public String getInvoice_time() {
        return invoice_time;
    }

    public void setInvoice_time(String invoice_time) {
        this.invoice_time = invoice_time;
    }

    public String getConsult_start_time() {
        return consult_start_time;
    }

    public void setConsult_start_time(String consult_start_time) {
        this.consult_start_time = consult_start_time;
    }

    public String getConsult_end_time() {
        return consult_end_time;
    }

    public void setConsult_end_time(String consult_end_time) {
        this.consult_end_time = consult_end_time;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_welfare_id() {
        return goods_welfare_id;
    }

    public void setGoods_welfare_id(String goods_welfare_id) {
        this.goods_welfare_id = goods_welfare_id;
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

    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }
}
