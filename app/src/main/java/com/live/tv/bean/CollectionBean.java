package com.live.tv.bean;

/**
 * Created by taoh on 2018/5/4.
 */

public class CollectionBean {

    private int collection_id;
    private String member_id;
    private String collection_type;
    private String goods_id;
    private String merchants_id;
    private String user_id;
    private String doctor_id;
    private String house_service_id;
    private String create_time;
    private String update_time;
    private String is_delete;
    private GoodsBean goodsBean;
    private MerchantsBean merchantsBean;
    private UserBean memberBean;
    private DoctorDetailBean doctorBean;
//    private HouseServiceBeanBean houseServiceBean;


    public int getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(int collection_id) {
        this.collection_id = collection_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getCollection_type() {
        return collection_type;
    }

    public void setCollection_type(String collection_type) {
        this.collection_type = collection_type;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getMerchants_id() {
        return merchants_id;
    }

    public void setMerchants_id(String merchants_id) {
        this.merchants_id = merchants_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getHouse_service_id() {
        return house_service_id;
    }

    public void setHouse_service_id(String house_service_id) {
        this.house_service_id = house_service_id;
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

    public GoodsBean getGoodsBean() {
        return goodsBean;
    }

    public void setGoodsBean(GoodsBean goodsBean) {
        this.goodsBean = goodsBean;
    }

    public MerchantsBean getMerchantsBean() {
        return merchantsBean;
    }

    public void setMerchantsBean(MerchantsBean merchantsBean) {
        this.merchantsBean = merchantsBean;
    }

    public UserBean getMemberBean() {
        return memberBean;
    }

    public void setMemberBean(UserBean memberBean) {
        this.memberBean = memberBean;
    }

    public DoctorDetailBean getDoctorBean() {
        return doctorBean;
    }

    public void setDoctorBean(DoctorDetailBean doctorBean) {
        this.doctorBean = doctorBean;
    }
}
