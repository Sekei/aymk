package com.live.tv.bean;

import java.util.List;

/**
 * Created by mac1010 on 2018/9/4.
 */

public class LikeListBean {


    /**
     * service_id : 5
     * house_service_id : 2
     * service_desc : supermansupermansupermansuperman
     * service_name : superman
     * service_type : 1月
     * service_money : 1
     * is_delete : 0
     * create_time : 2018-08-30 15:18:05
     * update_time : 2018-08-30 15:29:56
     * serviceRangeImageBeans : [{"service_image_id":7,"service_id":5,"service_image_url":"/images/banner/banner1.jpg","is_delete":"0","create_time":"2018-08-30 15:29:56","update_time":""},{"service_image_id":8,"service_id":5,"service_image_url":"/images/banner/banner1.jpg","is_delete":"0","create_time":"2018-08-30 15:29:56","update_time":""},{"service_image_id":9,"service_id":5,"service_image_url":"/images/banner/banner1.jpg","is_delete":"0","create_time":"2018-08-30 15:29:56","update_time":""},{"service_image_id":10,"service_id":5,"service_image_url":"/images/banner/banner1.jpg","is_delete":"0","create_time":"2018-08-30 15:29:56","update_time":""},{"service_image_id":11,"service_id":5,"service_image_url":"/images/banner/banner1.jpg","is_delete":"0","create_time":"2018-08-30 15:29:56","update_time":""},{"service_image_id":12,"service_id":5,"service_image_url":"/images/banner/banner1.jpg","is_delete":"0","create_time":"2018-08-30 15:29:56","update_time":""}]
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

    public static class ServiceRangeImageBeansBean {
        /**
         * service_image_id : 7
         * service_id : 5
         * service_image_url : /images/banner/banner1.jpg
         * is_delete : 0
         * create_time : 2018-08-30 15:29:56
         * update_time :
         */

        private int service_image_id;
        private int service_id;
        private String service_image_url;
        private String is_delete;
        private String create_time;
        private String update_time;

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
    }
}
