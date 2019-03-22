package com.live.tv.bean;

import java.util.List;

/**
 * Created by taoh on 2018/5/12.
 */

public class AssessmentBean {

    private String assessment_id;
    private String member_id;
    private String member_nick_name;
    private String order_id;
    private String order_no;
    private String assessment_desc;
    private String assessment_type;
    private String assessment_star1;
    private String assessment_star2;
    private String assessment_star3;
    private String goods_id;
    private String merchants_id;
    private String create_time;
    private String update_time;
    private String is_delete;
    private String count;
    private List<AssessmentImgBeans> assessmentImgBeans;

    public String getAssessment_id() {
        return assessment_id;
    }

    public void setAssessment_id(String assessment_id) {
        this.assessment_id = assessment_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_nick_name() {
        return member_nick_name;
    }

    public void setMember_nick_name(String member_nick_name) {
        this.member_nick_name = member_nick_name;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
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

    public String getAssessment_star1() {
        return assessment_star1;
    }

    public void setAssessment_star1(String assessment_star1) {
        this.assessment_star1 = assessment_star1;
    }

    public String getAssessment_star2() {
        return assessment_star2;
    }

    public void setAssessment_star2(String assessment_star2) {
        this.assessment_star2 = assessment_star2;
    }

    public String getAssessment_star3() {
        return assessment_star3;
    }

    public void setAssessment_star3(String assessment_star3) {
        this.assessment_star3 = assessment_star3;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getMerchants_id() {
        return merchants_id;
    }

    public void setMerchants_id(String merchants_id) {
        this.merchants_id = merchants_id;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<AssessmentImgBeans> getAssessmentImgBeans() {
        return assessmentImgBeans;
    }

    public void setAssessmentImgBeans(List<AssessmentImgBeans> assessmentImgBeans) {
        this.assessmentImgBeans = assessmentImgBeans;
    }
}
