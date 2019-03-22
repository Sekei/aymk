package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by mac1010 on 2018/8/30.
 */

public class MerchantServiceListBean implements Parcelable {


    /**
     * house_service_id : 2
     * service_name : superman
     * service_money : 1
     * service_type : 1月
     * service_desc : supermansupermansupermansuperman
     * serviceRangeImageBeans : [{"service_image_url":"/images/banner/banner1.jpg"},{"service_image_url":"/images/banner/banner1.jpg"},{"service_image_url":"/images/banner/banner1.jpg"},{"service_image_url":"/images/banner/banner1.jpg"},{"service_image_url":"/images/banner/banner1.jpg"},{"service_image_url":"/images/banner/banner1.jpg"}]
     */

    private String house_service_id;
    private String service_name;
    private String service_money;
    private String service_type;
    private String service_desc;

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    private String service_id;
    private List<ServiceRangeImageBeansBean> serviceRangeImageBeans;
public MerchantServiceListBean(){}
    protected MerchantServiceListBean(Parcel in) {
        house_service_id = in.readString();
        service_name = in.readString();
        service_money = in.readString();
        service_type = in.readString();
        service_desc = in.readString();
        service_id=in.readString();
    }

    public static final Creator<MerchantServiceListBean> CREATOR = new Creator<MerchantServiceListBean>() {
        @Override
        public MerchantServiceListBean createFromParcel(Parcel in) {
            return new MerchantServiceListBean(in);
        }

        @Override
        public MerchantServiceListBean[] newArray(int size) {
            return new MerchantServiceListBean[size];
        }
    };

    public String getHouse_service_id() {
        return house_service_id;
    }

    public void setHouse_service_id(String house_service_id) {
        this.house_service_id = house_service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_money() {
        return service_money;
    }

    public void setService_money(String service_money) {
        this.service_money = service_money;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_desc() {
        return service_desc;
    }

    public void setService_desc(String service_desc) {
        this.service_desc = service_desc;
    }

    public List<ServiceRangeImageBeansBean> getServiceRangeImageBeans() {
        return serviceRangeImageBeans;
    }

    public void setServiceRangeImageBeans(List<ServiceRangeImageBeansBean> serviceRangeImageBeans) {
        this.serviceRangeImageBeans = serviceRangeImageBeans;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(house_service_id);
        dest.writeString(service_name);
        dest.writeString(service_money);
        dest.writeString(service_type);
        dest.writeString(service_desc);
        dest.writeString(service_id);
    }

    public static class ServiceRangeImageBeansBean {
        /**
         * service_image_url : /images/banner/banner1.jpg
         */

        private String service_image_url;

        public String getService_image_url() {
            return service_image_url;
        }

        public void setService_image_url(String service_image_url) {
            this.service_image_url = service_image_url;
        }
    }
}
