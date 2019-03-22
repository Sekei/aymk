package com.live.tv.bean;

/**
 * Created by taoh on 2018/5/12.
 */

public class AssessmentImgBeans {

    /**
     * assessment_img_id : 25
     * assessment_id : 9
     * assessment_img : /images/banner/banner1.jpg
     * create_time : 2018-03-01 09:42:29.0
     * update_time : 2018-03-01 09:42:29.0
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
