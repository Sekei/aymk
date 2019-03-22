package com.live.tv.bean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/15
 */

public class HealthDataBean {


    /**
     * health_class_id : 1
     * health_class_title : 癌症
     * create_time : 2018-01-02 14:27:36
     * update_time :
     * is_delete : 0
     * healthDataBeans : [{"health_data_id":1,"health_data_title":"癌症","health_data_desc":"癌症又犯了","create_time":"2018-01-02 14:27:36","update_time":"","is_delete":"0","health_class_id":1,"image_url":"/images/banner/banner1.jpg"}]
     */

    private String health_class_id;
    private String health_class_title;
    private String create_time;
    private String update_time;
    private String is_delete;
    private List<HealthDataBeansBean> healthDataBeans;

    public String getHealth_class_id() {
        return health_class_id;
    }

    public void setHealth_class_id(String health_class_id) {
        this.health_class_id = health_class_id;
    }

    public String getHealth_class_title() {
        return health_class_title;
    }

    public void setHealth_class_title(String health_class_title) {
        this.health_class_title = health_class_title;
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

    public List<HealthDataBeansBean> getHealthDataBeans() {
        return healthDataBeans;
    }

    public void setHealthDataBeans(List<HealthDataBeansBean> healthDataBeans) {
        this.healthDataBeans = healthDataBeans;
    }

    public static class HealthDataBeansBean {
        /**
         * health_data_id : 1
         * health_data_title : 癌症
         * health_data_desc : 癌症又犯了
         * create_time : 2018-01-02 14:27:36
         * update_time :
         * is_delete : 0
         * health_class_id : 1
         * image_url : /images/banner/banner1.jpg
         */

        private int health_data_id;
        private String health_data_title;
        private String health_data_desc;
        private String create_time;
        private String update_time;
        private String is_delete;
        private int health_class_id;
        private String image_url;

        public int getHealth_data_id() {
            return health_data_id;
        }

        public void setHealth_data_id(int health_data_id) {
            this.health_data_id = health_data_id;
        }

        public String getHealth_data_title() {
            return health_data_title;
        }

        public void setHealth_data_title(String health_data_title) {
            this.health_data_title = health_data_title;
        }

        public String getHealth_data_desc() {
            return health_data_desc;
        }

        public void setHealth_data_desc(String health_data_desc) {
            this.health_data_desc = health_data_desc;
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

        public int getHealth_class_id() {
            return health_class_id;
        }

        public void setHealth_class_id(int health_class_id) {
            this.health_class_id = health_class_id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
