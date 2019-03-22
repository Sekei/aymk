package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac1010 on 2018/8/30.
 */

public class ServiceCopeBean implements Parcelable {


    /**
     * service_id : 10
     * house_service_id : 4
     * service_desc : 洗衣服好干净，做的饭超好吃。衣服干净洗衣服好干净，做的饭超好吃。衣服干净洗衣服好干净，做的饭超好吃。衣服净洗衣服好干净，做的饭超好吃。衣服干净洗衣服好干净，做的饭超好吃。衣服干净洗衣服好干净，做的饭超好吃。衣服干净洗衣服好干净，做的饭超好吃。衣服干净洗衣服好干净，做的饭超好吃。衣服干净XP庸人自扰之MSN过来一下曲终人未散一
     * service_name : 洗衣做饭
     * service_type : 月
     * service_money : 800
     * is_delete : 0
     * create_time : 2018-08-30 17:05:30
     * update_time :
     * serviceRangeImageBeans : [{"service_image_id":16,"service_id":10,"service_image_url":"/images/others/15356199303381371113066.png","is_delete":"0","create_time":"2018-08-30 17:05:30","update_time":""},{"service_image_id":17,"service_id":10,"service_image_url":"/images/others/15356199303381832249914.jpg","is_delete":"0","create_time":"2018-08-30 17:05:30","update_time":""},{"service_image_id":18,"service_id":10,"service_image_url":"/images/others/15356199303381676075590.png","is_delete":"0","create_time":"2018-08-30 17:05:30","update_time":""}]
     */

    private int service_id;
    private int house_service_id;
    private String service_desc;
    private String service_name;
    private String service_type;
    private String service_money;
    private String is_delete;
    private String create_time;
    private String update_time;
    private List<ServiceRangeImageBeansBean> serviceRangeImageBeans;

    protected ServiceCopeBean(Parcel in) {
        service_id = in.readInt();
        house_service_id = in.readInt();
        service_desc = in.readString();
        service_name = in.readString();
        service_type = in.readString();
        service_money = in.readString();
        is_delete = in.readString();
        create_time = in.readString();
        update_time = in.readString();
    }
public ServiceCopeBean (){

}
    public static final Creator<ServiceCopeBean> CREATOR = new Creator<ServiceCopeBean>() {
        @Override
        public ServiceCopeBean createFromParcel(Parcel in) {
            return new ServiceCopeBean(in);
        }

        @Override
        public ServiceCopeBean[] newArray(int size) {
            return new ServiceCopeBean[size];
        }
    };

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getHouse_service_id() {
        return house_service_id;
    }

    public void setHouse_service_id(int house_service_id) {
        this.house_service_id = house_service_id;
    }

    public String getService_desc() {
        return service_desc;
    }

    public void setService_desc(String service_desc) {
        this.service_desc = service_desc;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_money() {
        return service_money;
    }

    public void setService_money(String service_money) {
        this.service_money = service_money;
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
        dest.writeInt(service_id);
        dest.writeInt(house_service_id);
        dest.writeString(service_desc);
        dest.writeString(service_name);
        dest.writeString(service_type);
        dest.writeString(service_money);
        dest.writeString(is_delete);
        dest.writeString(create_time);
        dest.writeString(update_time);
    }

    public  class ServiceRangeImageBeansBean implements Parcelable {
        /**
         * service_image_id : 16
         * service_id : 10
         * service_image_url : /images/others/15356199303381371113066.png
         * is_delete : 0
         * create_time : 2018-08-30 17:05:30
         * update_time :
         */

        private int service_image_id;
        private int service_id;
        private String service_image_url;
        private String is_delete;
        private String create_time;
        private String update_time;
        public ServiceRangeImageBeansBean (){}
        protected ServiceRangeImageBeansBean(Parcel in) {
            service_image_id = in.readInt();
            service_id = in.readInt();
            service_image_url = in.readString();
            is_delete = in.readString();
            create_time = in.readString();
            update_time = in.readString();
        }

        public  final Creator<ServiceRangeImageBeansBean> CREATOR = new Creator<ServiceRangeImageBeansBean>() {
            @Override
            public ServiceRangeImageBeansBean createFromParcel(Parcel in) {
                return new ServiceRangeImageBeansBean(in);
            }

            @Override
            public ServiceRangeImageBeansBean[] newArray(int size) {
                return new ServiceRangeImageBeansBean[size];
            }
        };

        public int getService_image_id() {
            return service_image_id;
        }

        public void setService_image_id(int service_image_id) {
            this.service_image_id = service_image_id;
        }

        public int getService_id() {
            return service_id;
        }

        public void setService_id(int service_id) {
            this.service_id = service_id;
        }

        public String getService_image_url() {
            return service_image_url;
        }

        public void setService_image_url(String service_image_url) {
            this.service_image_url = service_image_url;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(service_image_id);
            dest.writeInt(service_id);
            dest.writeString(service_image_url);
            dest.writeString(is_delete);
            dest.writeString(create_time);
            dest.writeString(update_time);
        }
    }
}
