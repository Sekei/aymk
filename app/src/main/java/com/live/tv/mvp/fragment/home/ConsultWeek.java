package com.live.tv.mvp.fragment.home;

import com.live.tv.bean.ConsultTimesBean;

import java.util.List;

/**
 * Created by taoh on 2018/3/8.
 */

public class ConsultWeek {

    /**
     * date_id :
     * consult_set_day : 2018-02-02
     * weekday : 星期五
     */

    private String date_id;
    private String consult_set_day;
    private String weekday;
    private List<ConsultTimesBean> consultSetBeans;

    public List<ConsultTimesBean> getConsultSetBeans() {
        return consultSetBeans;
    }

    public void setConsultSetBeans(List<ConsultTimesBean> consultSetBeans) {
        this.consultSetBeans = consultSetBeans;
    }

    public String getDate_id() {
        return date_id;
    }

    public void setDate_id(String date_id) {
        this.date_id = date_id;
    }

    public String getConsult_set_day() {
        return consult_set_day;
    }

    public void setConsult_set_day(String consult_set_day) {
        this.consult_set_day = consult_set_day;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
}
