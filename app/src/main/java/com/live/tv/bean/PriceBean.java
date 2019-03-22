package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by taoh on 2018/5/9.
 */

public class PriceBean implements Parcelable {


    /**
     * order_total_actual_price : 120.0
     * order_ids : 16
     */

    private String order_total_actual_price;
    private String order_ids;
    private String express_total_price;

    private List<OrderDivideBean> orderDivideBean;

    public List<OrderDivideBean> getOrderDivideBean() {
        return orderDivideBean;
    }

    public void setOrderDivideBean(List<OrderDivideBean> orderDivideBean) {
        this.orderDivideBean = orderDivideBean;
    }

    public String getExpress_total_price() {
        return express_total_price;
    }

    public void setExpress_total_price(String express_total_price) {
        this.express_total_price = express_total_price;
    }

    public String getOrder_total_actual_price() {
        return order_total_actual_price;
    }

    public void setOrder_total_actual_price(String order_total_actual_price) {
        this.order_total_actual_price = order_total_actual_price;
    }

    public String getOrder_ids() {
        return order_ids;
    }

    public void setOrder_ids(String order_ids) {
        this.order_ids = order_ids;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.order_total_actual_price);
        dest.writeString(this.order_ids);
    }

    public PriceBean() {
    }

    protected PriceBean(Parcel in) {
        this.order_total_actual_price = in.readString();
        this.order_ids = in.readString();
    }

    public static final Parcelable.Creator<PriceBean> CREATOR = new Parcelable.Creator<PriceBean>() {
        @Override
        public PriceBean createFromParcel(Parcel source) {
            return new PriceBean(source);
        }

        @Override
        public PriceBean[] newArray(int size) {
            return new PriceBean[size];
        }
    };
}
