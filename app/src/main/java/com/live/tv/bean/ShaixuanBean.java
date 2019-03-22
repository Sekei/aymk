package com.live.tv.bean;

import java.util.List;

/**
 * Created by taoh on 2018/4/27.
 */

public class ShaixuanBean {


    private List<DoctorJobBeansBean> doctorJobBeans;
    private List<HospitalBeansBean> hospitalBeans;

    public List<DoctorJobBeansBean> getDoctorJobBeans() {
        return doctorJobBeans;
    }

    public void setDoctorJobBeans(List<DoctorJobBeansBean> doctorJobBeans) {
        this.doctorJobBeans = doctorJobBeans;
    }

    public List<HospitalBeansBean> getHospitalBeans() {
        return hospitalBeans;
    }

    public void setHospitalBeans(List<HospitalBeansBean> hospitalBeans) {
        this.hospitalBeans = hospitalBeans;
    }

    public static class DoctorJobBeansBean {
        /**
         * job_id : 1
         * job_level : 副主任医师
         * is_delete : 0
         * create_time : 2018-01-15 15:56:56
         * update_time :
         */

        private String job_id;
        private String job_level;
        private String is_delete;
        private String create_time;
        private String update_time;
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getJob_id() {
            return job_id;
        }

        public void setJob_id(String job_id) {
            this.job_id = job_id;
        }

        public String getJob_level() {
            return job_level;
        }

        public void setJob_level(String job_level) {
            this.job_level = job_level;
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

        private String level_id;
        private String hospital_level;
        private String is_delete;
        private String create_time;
        private String update_time;
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getLevel_id() {
            return level_id;
        }

        public void setLevel_id(String level_id) {
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
