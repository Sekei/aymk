package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by taoh on 2017/9/20.
 */

public class CartBean implements Parcelable {

    private String member_id;
    private String merchants_id;
    private MerchantsBean merchantsBean;
    private List<ShopCarBean> shopCarBeans;
    private boolean selected;
    private boolean valid;
    private String order_remark;
    private String contact_phone;
    private String expiry_time;
    private String express_price;

    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMerchants_id() {
        return merchants_id;
    }

    public void setMerchants_id(String merchants_id) {
        this.merchants_id = merchants_id;
    }

    public MerchantsBean getMerchantsBean() {
        return merchantsBean;
    }

    public void setMerchantsBean(MerchantsBean merchantsBean) {
        this.merchantsBean = merchantsBean;
    }

    public List<ShopCarBean> getShopCarBeans() {
        return shopCarBeans;
    }

    public void setShopCarBeans(List<ShopCarBean> shopCarBeans) {
        this.shopCarBeans = shopCarBeans;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getOrder_remark() {
        return order_remark;
    }

    public void setOrder_remark(String order_remark) {
        this.order_remark = order_remark;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(String expiry_time) {
        this.expiry_time = expiry_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.member_id);
        dest.writeString(this.merchants_id);
        dest.writeParcelable(this.merchantsBean, flags);
        dest.writeTypedList(this.shopCarBeans);
        dest.writeByte(this.selected ? (byte) 1 : (byte) 0);
        dest.writeByte(this.valid ? (byte) 1 : (byte) 0);
        dest.writeString(this.order_remark);
        dest.writeString(this.contact_phone);
        dest.writeString(this.expiry_time);
    }

    public CartBean() {
    }

    protected CartBean(Parcel in) {
        this.member_id = in.readString();
        this.merchants_id = in.readString();
        this.merchantsBean = in.readParcelable(MerchantsBean.class.getClassLoader());
        this.shopCarBeans = in.createTypedArrayList(ShopCarBean.CREATOR);
        this.selected = in.readByte() != 0;
        this.valid = in.readByte() != 0;
        this.order_remark = in.readString();
        this.contact_phone = in.readString();
        this.expiry_time = in.readString();
    }

    public static final Creator<CartBean> CREATOR = new Creator<CartBean>() {
        @Override
        public CartBean createFromParcel(Parcel source) {
            return new CartBean(source);
        }

        @Override
        public CartBean[] newArray(int size) {
            return new CartBean[size];
        }
    };
}
