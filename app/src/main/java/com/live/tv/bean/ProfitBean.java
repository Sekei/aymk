package com.live.tv.bean;

import java.util.List;

/**
 * Created by taoh on 2018/4/12.
 */

public class ProfitBean {


    /**
     * profit_id : 1
     * amount : 0
     * title : 视频咨询
     * time :
     * doctor_id : 1
     * member_id : 1
     * create_time : 2018-03-21 15:19:36
     */

    private String profit_id;
    private String amount;
    private String title;
    private String time;
    private String doctor_id;
    private String member_id;
    private String create_time;
    private String profit_date;
    private String sum_amount;

    private List<ProfitBean>  profitBeans;

    public String getSum_amount() {
        return sum_amount;
    }

    public void setSum_amount(String sum_amount) {
        this.sum_amount = sum_amount;
    }

    public String getProfit_date() {
        return profit_date;
    }

    public void setProfit_date(String profit_date) {
        this.profit_date = profit_date;
    }



    public String getProfit_id() {
        return profit_id;
    }

    public List<ProfitBean> getProfitBeans() {
        return profitBeans;
    }

    public void setProfitBeans(List<ProfitBean> profitBeans) {
        this.profitBeans = profitBeans;
    }

    public void setProfit_id(String profit_id) {
        this.profit_id = profit_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
