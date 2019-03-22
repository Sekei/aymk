package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mac1010 on 2018/9/18.
 */

public class TextandImgBean implements Parcelable {


    /**
     * order_actual_price : 100.0
     * order_id : 650
     */

    private String order_actual_price;
    private String order_id;

    public String getOrder_actual_price() {
        return order_actual_price;
    }

    public void setOrder_actual_price(String order_actual_price) {
        this.order_actual_price = order_actual_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.order_actual_price);
        dest.writeString(this.order_id);
    }

    public TextandImgBean() {
    }

    protected TextandImgBean(Parcel in) {
        this.order_actual_price = in.readString();
        this.order_id = in.readString();
    }

    public static final Parcelable.Creator<TextandImgBean> CREATOR = new Parcelable.Creator<TextandImgBean>() {
        @Override
        public TextandImgBean createFromParcel(Parcel source) {
            return new TextandImgBean(source);
        }

        @Override
        public TextandImgBean[] newArray(int size) {
            return new TextandImgBean[size];
        }
    };
}
