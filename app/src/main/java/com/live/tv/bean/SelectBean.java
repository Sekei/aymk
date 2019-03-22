package com.live.tv.bean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/29
 */

public class SelectBean {


    private List<DoctorTitleBeansBean> doctorTitleBeans;
    private List<HospitalBeansBean> hospitalBeans;

    public List<DoctorTitleBeansBean> getDoctorTitleBeans() {
        return doctorTitleBeans;
    }

    public void setDoctorTitleBeans(List<DoctorTitleBeansBean> doctorTitleBeans) {
        this.doctorTitleBeans = doctorTitleBeans;
    }

    public List<HospitalBeansBean> getHospitalBeans() {
        return hospitalBeans;
    }

    public void setHospitalBeans(List<HospitalBeansBean> hospitalBeans) {
        this.hospitalBeans = hospitalBeans;
    }

    public static class DoctorTitleBeansBean {
        /**
         * title_id : 1
         * doctor_title : 心内科
         * is_delete : 0
         * create_time : 2018-01-15 15:56:56
         * update_time :
         */

        private int title_id;
        private String doctor_title;
        private String is_delete;
        private String create_time;
        private String update_time;

        public int getTitle_id() {
            return title_id;
        }

        public void setTitle_id(int title_id) {
            this.title_id = title_id;
        }

        public String getDoctor_title() {
            return doctor_title;
        }

        public void setDoctor_title(String doctor_title) {
            this.doctor_title = doctor_title;
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

    public static class HospitalBeansBean {
        /**
         * level_id : 1
         * hospital_level : 一级医院
         * is_delete : 0
         * create_time : 2018-01-15 15:56:56
         * update_time :
         */

        private int level_id;
        private String hospital_level;
        private String is_delete;
        private String create_time;
        private String update_time;

        public int getLevel_id() {
            return level_id;
        }

        public void setLevel_id(int level_id) {
            this.level_id = level_id;
        }

        public String getHospital_level() {
            return hospital_level;
        }

        public void setHospital_level(String hospital_level) {
            this.hospital_level = hospital_level;
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
