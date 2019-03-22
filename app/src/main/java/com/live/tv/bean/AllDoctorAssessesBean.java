package com.live.tv.bean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/27
 */

public class AllDoctorAssessesBean {


    /**
     * assessment_id : 1
     * member_id : 1
     * assessment_desc : 什么玩意啊
     * assessment_type : doctor
     * assessment_star : 5
     * doctor_id : 1
     * create_time : 2017-12-27 17:22:50
     * update_time :
     * is_delete : 0
     * member_head_image : /images/goods/20171209//1512809354540.jpg
     * member_nick_name : 我的地盘我做主
     * assessmentImgBeans : [{"assessment_img_id":1,"assessment_id":"1","assessment_img":"/images/banner/banner1.jpg","create_time":"2017-12-27 17:22:50","update_time":"2017-12-27 17:22:50","is_delete":"0"},{"assessment_img_id":2,"assessment_id":"1","assessment_img":"/images/banner/banner1.jpg","create_time":"2017-12-27 17:22:50","update_time":"2017-12-27 17:22:50","is_delete":"0"},{"assessment_img_id":3,"assessment_id":"1","assessment_img":"/images/banner/banner1.jpg","create_time":"2017-12-27 17:22:54","update_time":"2017-12-27 17:22:54","is_delete":"0"},{"assessment_img_id":4,"assessment_id":"1","assessment_img":"/images/banner/banner1.jpg","create_time":"2017-12-27 17:22:54","update_time":"2017-12-27 17:22:54","is_delete":"0"}]
     */

    private int assessment_id;
    private String member_id;
    private String assessment_desc;
    private String assessment_type;
    private String assessment_star;
    private String doctor_id;
    private String create_time;
    private String update_time;
    private String is_delete;
    private String member_head_image;
    private String member_nick_name;
    private List<AssessmentImgBeansBean> assessmentImgBeans;

    public int getAssessment_id() {
        return assessment_id;
    }

    public void setAssessment_id(int assessment_id) {
        this.assessment_id = assessment_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getAssessment_desc() {
        return assessment_desc;
    }

    public void setAssessment_desc(String assessment_desc) {
        this.assessment_desc = assessment_desc;
    }

    public String getAssessment_type() {
        return assessment_type;
    }

    public void setAssessment_type(String assessment_type) {
        this.assessment_type = assessment_type;
    }

    public String getAssessment_star() {
        return assessment_star;
    }

    public void setAssessment_star(String assessment_star) {
        this.assessment_star = assessment_star;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getCreate_time() {
        return create_time.substring(0,11);
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

    public String getMember_head_image() {
        return member_head_image;
    }

    public void setMember_head_image(String member_head_image) {
        this.member_head_image = member_head_image;
    }

    public String getMember_nick_name() {
        return member_nick_name;
    }

    public void setMember_nick_name(String member_nick_name) {
        this.member_nick_name = member_nick_name;
    }

    public List<AssessmentImgBeansBean> getAssessmentImgBeans() {
        return assessmentImgBeans;
    }

    public void setAssessmentImgBeans(List<AssessmentImgBeansBean> assessmentImgBeans) {
        this.assessmentImgBeans = assessmentImgBeans;
    }

    public static class AssessmentImgBeansBean {
        /**
         * assessment_img_id : 1
         * assessment_id : 1
         * assessment_img : /images/banner/banner1.jpg
         * create_time : 2017-12-27 17:22:50
         * update_time : 2017-12-27 17:22:50
         * is_delete : 0
         */

        private int assessment_img_id;
        private String assessment_id;
        private String assessment_img;
        private String create_time;
        private String update_time;
        private String is_delete;

        public int getAssessment_img_id() {
            return assessment_img_id;
        }

        public void setAssessment_img_id(int assessment_img_id) {
            this.assessment_img_id = assessment_img_id;
        }

        public String getAssessment_id() {
            return assessment_id;
        }

        public void setAssessment_id(String assessment_id) {
            this.assessment_id = assessment_id;
        }

        public String getAssessment_img() {
            return assessment_img;
        }

        public void setAssessment_img(String assessment_img) {
            this.assessment_img = assessment_img;
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
    }
}
