package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by taoh on 2018/4/9.
 */

public class GoodsBean implements Parcelable {
    private String goods_id;
    private String goods_ids;
    private String merchants_id;
    private String merchants_name;
    private String distributor_id;
    private String distributor_goods_id;
    private String goods_num;
    private String goods_name;
    private String goods_img;
    private String goods_origin_price;
    private String goods_pc_price;
    private String goods_now_price;
    private String goods_desc;
    private String goods_url;
    private String goods_url_desc;
    private String actual_sales;
    private String total_sales;
    private String month_sales;
    private String day_sales;
    private String goods_stock;
    private String goods_star1;
    private String goods_star2;
    private String goods_star3;
    private String assessment_count;
    private String goods_address;
    private String production_start_time;
    private String production_end_time;
    private String goods_state;
    private String goods_state_show;
    private String goods_position;
    private String express_way;
    private String package_way;
    private String send_range;
    private String is_give_integral;
    private String give_integral_value;
    private String is_delete;
    private String create_time;
    private String update_time;
    private String goods_class_id;
    private String class_id;
    private String class_uuid;
    private String goods_welfare_id;
    private String member_id;
    private String collection_id;
    private String member_group_count;
    private String welfare_status;
    private String temp_img;
    private String min_price;
    private String max_price;
    private String region_id;
    private String is_sell_hot;
    private String express_price;
    private String is_recommend;
    private String rate;
    private String goods_type;
    private String expiry_time;
    private String is_collection;
    private MerchantsBean merchantsBean;
    private List<GoodsSpecificationBeans> goodsSpecificationBeans;
    private List<SpecificationBeans> specificationBeans;
    private List<GoodsImgBean> goodsImgBeans;
    private String hx_account;

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

    private String hx_password;

    public String getIs_collection() {
        return is_collection;
    }

    public void setIs_collection(String is_collection) {
        this.is_collection = is_collection;
    }

    public String getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(String expiry_time) {
        this.expiry_time = expiry_time;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_ids() {
        return goods_ids;
    }

    public void setGoods_ids(String goods_ids) {
        this.goods_ids = goods_ids;
    }

    public String getMerchants_id() {
        return merchants_id;
    }

    public void setMerchants_id(String merchants_id) {
        this.merchants_id = merchants_id;
    }

    public String getMerchants_name() {
        return merchants_name;
    }

    public void setMerchants_name(String merchants_name) {
        this.merchants_name = merchants_name;
    }

    public String getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(String distributor_id) {
        this.distributor_id = distributor_id;
    }

    public String getDistributor_goods_id() {
        return distributor_goods_id;
    }

    public void setDistributor_goods_id(String distributor_goods_id) {
        this.distributor_goods_id = distributor_goods_id;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getGoods_origin_price() {
        return goods_origin_price;
    }

    public void setGoods_origin_price(String goods_origin_price) {
        this.goods_origin_price = goods_origin_price;
    }

    public String getGoods_pc_price() {
        return goods_pc_price;
    }

    public void setGoods_pc_price(String goods_pc_price) {
        this.goods_pc_price = goods_pc_price;
    }

    public String getGoods_now_price() {
        return goods_now_price;
    }

    public void setGoods_now_price(String goods_now_price) {
        this.goods_now_price = goods_now_price;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getGoods_url() {
        return goods_url;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }

    public String getGoods_url_desc() {
        return goods_url_desc;
    }

    public void setGoods_url_desc(String goods_url_desc) {
        this.goods_url_desc = goods_url_desc;
    }

    public String getActual_sales() {
        return actual_sales;
    }

    public void setActual_sales(String actual_sales) {
        this.actual_sales = actual_sales;
    }

    public String getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(String total_sales) {
        this.total_sales = total_sales;
    }

    public String getMonth_sales() {
        return month_sales;
    }

    public void setMonth_sales(String month_sales) {
        this.month_sales = month_sales;
    }

    public String getDay_sales() {
        return day_sales;
    }

    public void setDay_sales(String day_sales) {
        this.day_sales = day_sales;
    }

    public String getGoods_stock() {
        return goods_stock;
    }

    public void setGoods_stock(String goods_stock) {
        this.goods_stock = goods_stock;
    }

    public String getGoods_star1() {
        return goods_star1;
    }

    public void setGoods_star1(String goods_star1) {
        this.goods_star1 = goods_star1;
    }

    public String getGoods_star2() {
        return goods_star2;
    }

    public void setGoods_star2(String goods_star2) {
        this.goods_star2 = goods_star2;
    }

    public String getGoods_star3() {
        return goods_star3;
    }

    public void setGoods_star3(String goods_star3) {
        this.goods_star3 = goods_star3;
    }

    public String getAssessment_count() {
        return assessment_count;
    }

    public void setAssessment_count(String assessment_count) {
        this.assessment_count = assessment_count;
    }

    public String getGoods_address() {
        return goods_address;
    }

    public void setGoods_address(String goods_address) {
        this.goods_address = goods_address;
    }

    public String getProduction_start_time() {
        return production_start_time;
    }

    public void setProduction_start_time(String production_start_time) {
        this.production_start_time = production_start_time;
    }

    public String getProduction_end_time() {
        return production_end_time;
    }

    public void setProduction_end_time(String production_end_time) {
        this.production_end_time = production_end_time;
    }

    public String getGoods_state() {
        return goods_state;
    }

    public void setGoods_state(String goods_state) {
        this.goods_state = goods_state;
    }

    public String getGoods_state_show() {
        return goods_state_show;
    }

    public void setGoods_state_show(String goods_state_show) {
        this.goods_state_show = goods_state_show;
    }

    public String getGoods_position() {
        return goods_position;
    }

    public void setGoods_position(String goods_position) {
        this.goods_position = goods_position;
    }

    public String getExpress_way() {
        return express_way;
    }

    public void setExpress_way(String express_way) {
        this.express_way = express_way;
    }

    public String getPackage_way() {
        return package_way;
    }

    public void setPackage_way(String package_way) {
        this.package_way = package_way;
    }

    public String getSend_range() {
        return send_range;
    }

    public void setSend_range(String send_range) {
        this.send_range = send_range;
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

    public String getGoods_class_id() {
        return goods_class_id;
    }

    public void setGoods_class_id(String goods_class_id) {
        this.goods_class_id = goods_class_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_uuid() {
        return class_uuid;
    }

    public void setClass_uuid(String class_uuid) {
        this.class_uuid = class_uuid;
    }

    public String getGoods_welfare_id() {
        return goods_welfare_id;
    }

    public void setGoods_welfare_id(String goods_welfare_id) {
        this.goods_welfare_id = goods_welfare_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public String getMember_group_count() {
        return member_group_count;
    }

    public void setMember_group_count(String member_group_count) {
        this.member_group_count = member_group_count;
    }

    public String getWelfare_status() {
        return welfare_status;
    }

    public void setWelfare_status(String welfare_status) {
        this.welfare_status = welfare_status;
    }

    public String getTemp_img() {
        return temp_img;
    }

    public void setTemp_img(String temp_img) {
        this.temp_img = temp_img;
    }

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getMax_price() {
        return max_price;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getIs_sell_hot() {
        return is_sell_hot;
    }

    public void setIs_sell_hot(String is_sell_hot) {
        this.is_sell_hot = is_sell_hot;
    }

    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

    public String getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(String is_recommend) {
        this.is_recommend = is_recommend;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public MerchantsBean getMerchantsBean() {
        return merchantsBean;
    }

    public void setMerchantsBean(MerchantsBean merchantsBean) {
        this.merchantsBean = merchantsBean;
    }

    public List<GoodsSpecificationBeans> getGoodsSpecificationBeans() {
        return goodsSpecificationBeans;
    }

    public void setGoodsSpecificationBeans(List<GoodsSpecificationBeans> goodsSpecificationBeans) {
        this.goodsSpecificationBeans = goodsSpecificationBeans;
    }

    public List<SpecificationBeans> getSpecificationBeans() {
        return specificationBeans;
    }

    public void setSpecificationBeans(List<SpecificationBeans> specificationBeans) {
        this.specificationBeans = specificationBeans;
    }

    public List<GoodsImgBean> getGoodsImgBeans() {
        return goodsImgBeans;
    }

    public void setGoodsImgBeans(List<GoodsImgBean> goodsImgBeans) {
        this.goodsImgBeans = goodsImgBeans;
    }

    public GoodsBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.goods_id);
        dest.writeString(this.goods_ids);
        dest.writeString(this.merchants_id);
        dest.writeString(this.merchants_name);
        dest.writeString(this.distributor_id);
        dest.writeString(this.distributor_goods_id);
        dest.writeString(this.goods_num);
        dest.writeString(this.goods_name);
        dest.writeString(this.goods_img);
        dest.writeString(this.goods_origin_price);
        dest.writeString(this.goods_pc_price);
        dest.writeString(this.goods_now_price);
        dest.writeString(this.goods_desc);
        dest.writeString(this.goods_url);
        dest.writeString(this.goods_url_desc);
        dest.writeString(this.actual_sales);
        dest.writeString(this.total_sales);
        dest.writeString(this.month_sales);
        dest.writeString(this.day_sales);
        dest.writeString(this.goods_stock);
        dest.writeString(this.goods_star1);
        dest.writeString(this.goods_star2);
        dest.writeString(this.goods_star3);
        dest.writeString(this.assessment_count);
        dest.writeString(this.goods_address);
        dest.writeString(this.production_start_time);
        dest.writeString(this.production_end_time);
        dest.writeString(this.goods_state);
        dest.writeString(this.goods_state_show);
        dest.writeString(this.goods_position);
        dest.writeString(this.express_way);
        dest.writeString(this.package_way);
        dest.writeString(this.send_range);
        dest.writeString(this.is_give_integral);
        dest.writeString(this.give_integral_value);
        dest.writeString(this.is_delete);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.goods_class_id);
        dest.writeString(this.class_id);
        dest.writeString(this.class_uuid);
        dest.writeString(this.goods_welfare_id);
        dest.writeString(this.member_id);
        dest.writeString(this.collection_id);
        dest.writeString(this.member_group_count);
        dest.writeString(this.welfare_status);
        dest.writeString(this.temp_img);
        dest.writeString(this.min_price);
        dest.writeString(this.max_price);
        dest.writeString(this.region_id);
        dest.writeString(this.is_sell_hot);
        dest.writeString(this.express_price);
        dest.writeString(this.is_recommend);
        dest.writeString(this.rate);
        dest.writeString(this.goods_type);
        dest.writeString(this.expiry_time);
        dest.writeParcelable(this.merchantsBean, flags);
        dest.writeTypedList(this.goodsSpecificationBeans);
        dest.writeTypedList(this.specificationBeans);
        dest.writeTypedList(this.goodsImgBeans);
        dest.writeString(this.hx_account);
        dest.writeString(this.hx_password);
    }

    protected GoodsBean(Parcel in) {
        this.hx_account = in.readString();
        this.hx_password = in.readString();
        this.goods_id = in.readString();
        this.goods_ids = in.readString();
        this.merchants_id = in.readString();
        this.merchants_name = in.readString();
        this.distributor_id = in.readString();
        this.distributor_goods_id = in.readString();
        this.goods_num = in.readString();
        this.goods_name = in.readString();
        this.goods_img = in.readString();
        this.goods_origin_price = in.readString();
        this.goods_pc_price = in.readString();
        this.goods_now_price = in.readString();
        this.goods_desc = in.readString();
        this.goods_url = in.readString();
        this.goods_url_desc = in.readString();
        this.actual_sales = in.readString();
        this.total_sales = in.readString();
        this.month_sales = in.readString();
        this.day_sales = in.readString();
        this.goods_stock = in.readString();
        this.goods_star1 = in.readString();
        this.goods_star2 = in.readString();
        this.goods_star3 = in.readString();
        this.assessment_count = in.readString();
        this.goods_address = in.readString();
        this.production_start_time = in.readString();
        this.production_end_time = in.readString();
        this.goods_state = in.readString();
        this.goods_state_show = in.readString();
        this.goods_position = in.readString();
        this.express_way = in.readString();
        this.package_way = in.readString();
        this.send_range = in.readString();
        this.is_give_integral = in.readString();
        this.give_integral_value = in.readString();
        this.is_delete = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.goods_class_id = in.readString();
        this.class_id = in.readString();
        this.class_uuid = in.readString();
        this.goods_welfare_id = in.readString();
        this.member_id = in.readString();
        this.collection_id = in.readString();
        this.member_group_count = in.readString();
        this.welfare_status = in.readString();
        this.temp_img = in.readString();
        this.min_price = in.readString();
        this.max_price = in.readString();
        this.region_id = in.readString();
        this.is_sell_hot = in.readString();
        this.express_price = in.readString();
        this.is_recommend = in.readString();
        this.rate = in.readString();
        this.goods_type = in.readString();
        this.expiry_time = in.readString();
        this.merchantsBean = in.readParcelable(MerchantsBean.class.getClassLoader());
        this.goodsSpecificationBeans = in.createTypedArrayList(GoodsSpecificationBeans.CREATOR);
        this.specificationBeans = in.createTypedArrayList(SpecificationBeans.CREATOR);
        this.goodsImgBeans = in.createTypedArrayList(GoodsImgBean.CREATOR);
    }

    public static final Creator<GoodsBean> CREATOR = new Creator<GoodsBean>() {
        @Override
        public GoodsBean createFromParcel(Parcel source) {
            return new GoodsBean(source);
        }

        @Override
        public GoodsBean[] newArray(int size) {
            return new GoodsBean[size];
        }
    };
}
