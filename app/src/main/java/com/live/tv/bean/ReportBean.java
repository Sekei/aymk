package com.live.tv.bean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/23
 */

public class ReportBean {


    /**
     * test_id : 1
     * member_id : 1
     * health_record_id : 3
     * yinxu_num : 6
     * yangxu_num : 1
     * qixu_num : 0
     * tanshi_num : 0
     * shire_num : 0
     * qiyu_num : 0
     * xueyu_num : 0
     * tebing_num : 0
     * pinghe_num : 0
     * done_ques_num : 1
     * sum_ques_num : 64
     * question_ids : 11
     * test_result : 阴虚
     * done_ques_time : 2018-01-20 14:55:40
     * done_factors_time : 2018-01-20 17:10:42
     * is_done_ques : 0
     * is_done_factors : 1
     * is_buy : 0
     * is_sure : 0
     * lead_factors_ids : 4
     * sum_factors_num : 51
     * done_factors_num : 1
     * factors_ids_checked : 4,4
     * real_name : 李先生
     * plan_title :
     * create_time : 2018-01-19 14:43:59
     * update_time : 2018-01-20 17:10:39
     * is_delete : 0
     * leadFactorsBeans : [{"lead_factors_id":4,"lead_factors_title":"是否常吃辛辣、煎烤、易发热等食物？","physical_type":"阴虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阴虚","is_delete":"0","is_checked":"","count":0}]
     * dreportBeans : [{"report_id":1,"report_desc":"阴虚是否有高血压，常吃利尿药物？是否有高血压，常吃利尿药物？是否有高血压，常吃利尿药物？","create_time":"2018-01-18 14:42:37","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"}]
     * leadResultBeans : [{"result_id":1,"result_title":"是否有高血压，常吃利尿药物？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":2,"result_title":"是否经常工作繁忙很疲劳？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":3,"result_title":"是否经常发热或高强度运动导致大量出汗？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":4,"result_title":"是否常吃辛辣、煎烤、易发热等食物？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":5,"result_title":"是否长抽烟、喝酒？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":6,"result_title":"是否经常11点以后才睡？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":7,"result_title":"是否处在更年期？（女性）","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":8,"result_title":"是否经常频繁行房事（男性）？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"}]
     * planBeans : []
     */

    private int test_id;
    private int member_id;
    private int health_record_id;
    private int yinxu_num;
    private int yangxu_num;
    private int qixu_num;
    private int tanshi_num;
    private int shire_num;
    private int qiyu_num;
    private int xueyu_num;
    private int tebing_num;
    private int pinghe_num;
    private int done_ques_num;
    private int sum_ques_num;
    private String question_ids;
    private String test_result;
    private String done_ques_time;
    private String done_factors_time;
    private String is_done_ques;
    private String is_done_factors;
    private String is_buy;
    private String is_sure;
    private String lead_factors_ids;
    private int sum_factors_num;
    private int done_factors_num;
    private String factors_ids_checked;
    private String real_name;
    private String plan_title;
    private String create_time;
    private String update_time;
    private String is_delete;
    private List<LeadFactorsBeansBean> leadFactorsBeans;
    private List<DreportBeansBean> dreportBeans;
    private List<LeadResultBeansBean> leadResultBeans;
    private List<?> planBeans;

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getHealth_record_id() {
        return health_record_id;
    }

    public void setHealth_record_id(int health_record_id) {
        this.health_record_id = health_record_id;
    }

    public int getYinxu_num() {
        return yinxu_num;
    }

    public void setYinxu_num(int yinxu_num) {
        this.yinxu_num = yinxu_num;
    }

    public int getYangxu_num() {
        return yangxu_num;
    }

    public void setYangxu_num(int yangxu_num) {
        this.yangxu_num = yangxu_num;
    }

    public int getQixu_num() {
        return qixu_num;
    }

    public void setQixu_num(int qixu_num) {
        this.qixu_num = qixu_num;
    }

    public int getTanshi_num() {
        return tanshi_num;
    }

    public void setTanshi_num(int tanshi_num) {
        this.tanshi_num = tanshi_num;
    }

    public int getShire_num() {
        return shire_num;
    }

    public void setShire_num(int shire_num) {
        this.shire_num = shire_num;
    }

    public int getQiyu_num() {
        return qiyu_num;
    }

    public void setQiyu_num(int qiyu_num) {
        this.qiyu_num = qiyu_num;
    }

    public int getXueyu_num() {
        return xueyu_num;
    }

    public void setXueyu_num(int xueyu_num) {
        this.xueyu_num = xueyu_num;
    }

    public int getTebing_num() {
        return tebing_num;
    }

    public void setTebing_num(int tebing_num) {
        this.tebing_num = tebing_num;
    }

    public int getPinghe_num() {
        return pinghe_num;
    }

    public void setPinghe_num(int pinghe_num) {
        this.pinghe_num = pinghe_num;
    }

    public int getDone_ques_num() {
        return done_ques_num;
    }

    public void setDone_ques_num(int done_ques_num) {
        this.done_ques_num = done_ques_num;
    }

    public int getSum_ques_num() {
        return sum_ques_num;
    }

    public void setSum_ques_num(int sum_ques_num) {
        this.sum_ques_num = sum_ques_num;
    }

    public String getQuestion_ids() {
        return question_ids;
    }

    public void setQuestion_ids(String question_ids) {
        this.question_ids = question_ids;
    }

    public String getTest_result() {
        return test_result;
    }

    public void setTest_result(String test_result) {
        this.test_result = test_result;
    }

    public String getDone_ques_time() {
        return done_ques_time;
    }

    public void setDone_ques_time(String done_ques_time) {
        this.done_ques_time = done_ques_time;
    }

    public String getDone_factors_time() {
        return done_factors_time;
    }

    public void setDone_factors_time(String done_factors_time) {
        this.done_factors_time = done_factors_time;
    }

    public String getIs_done_ques() {
        return is_done_ques;
    }

    public void setIs_done_ques(String is_done_ques) {
        this.is_done_ques = is_done_ques;
    }

    public String getIs_done_factors() {
        return is_done_factors;
    }

    public void setIs_done_factors(String is_done_factors) {
        this.is_done_factors = is_done_factors;
    }

    public String getIs_buy() {
        return is_buy;
    }

    public void setIs_buy(String is_buy) {
        this.is_buy = is_buy;
    }

    public String getIs_sure() {
        return is_sure;
    }

    public void setIs_sure(String is_sure) {
        this.is_sure = is_sure;
    }

    public String getLead_factors_ids() {
        return lead_factors_ids;
    }

    public void setLead_factors_ids(String lead_factors_ids) {
        this.lead_factors_ids = lead_factors_ids;
    }

    public int getSum_factors_num() {
        return sum_factors_num;
    }

    public void setSum_factors_num(int sum_factors_num) {
        this.sum_factors_num = sum_factors_num;
    }

    public int getDone_factors_num() {
        return done_factors_num;
    }

    public void setDone_factors_num(int done_factors_num) {
        this.done_factors_num = done_factors_num;
    }

    public String getFactors_ids_checked() {
        return factors_ids_checked;
    }

    public void setFactors_ids_checked(String factors_ids_checked) {
        this.factors_ids_checked = factors_ids_checked;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getPlan_title() {
        return plan_title;
    }

    public void setPlan_title(String plan_title) {
        this.plan_title = plan_title;
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

    public List<LeadFactorsBeansBean> getLeadFactorsBeans() {
        return leadFactorsBeans;
    }

    public void setLeadFactorsBeans(List<LeadFactorsBeansBean> leadFactorsBeans) {
        this.leadFactorsBeans = leadFactorsBeans;
    }

    public List<DreportBeansBean> getDreportBeans() {
        return dreportBeans;
    }

    public void setDreportBeans(List<DreportBeansBean> dreportBeans) {
        this.dreportBeans = dreportBeans;
    }

    public List<LeadResultBeansBean> getLeadResultBeans() {
        return leadResultBeans;
    }

    public void setLeadResultBeans(List<LeadResultBeansBean> leadResultBeans) {
        this.leadResultBeans = leadResultBeans;
    }

    public List<?> getPlanBeans() {
        return planBeans;
    }

    public void setPlanBeans(List<?> planBeans) {
        this.planBeans = planBeans;
    }

    public static class LeadFactorsBeansBean {
        /**
         * lead_factors_id : 4
         * lead_factors_title : 是否常吃辛辣、煎烤、易发热等食物？
         * physical_type : 阴虚
         * create_time : 2018-01-18 14:23:50
         * update_time :
         * type : 阴虚
         * is_delete : 0
         * is_checked :
         * count : 0
         */

        private int lead_factors_id;
        private String lead_factors_title;
        private String physical_type;
        private String create_time;
        private String update_time;
        private String type;
        private String is_delete;
        private String is_checked;
        private int count;

        public int getLead_factors_id() {
            return lead_factors_id;
        }

        public void setLead_factors_id(int lead_factors_id) {
            this.lead_factors_id = lead_factors_id;
        }

        public String getLead_factors_title() {
            return lead_factors_title;
        }

        public void setLead_factors_title(String lead_factors_title) {
            this.lead_factors_title = lead_factors_title;
        }

        public String getPhysical_type() {
            return physical_type;
        }

        public void setPhysical_type(String physical_type) {
            this.physical_type = physical_type;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getIs_checked() {
            return is_checked;
        }

        public void setIs_checked(String is_checked) {
            this.is_checked = is_checked;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public static class DreportBeansBean {
        /**
         * report_id : 1
         * report_desc : 阴虚是否有高血压，常吃利尿药物？是否有高血压，常吃利尿药物？是否有高血压，常吃利尿药物？
         * create_time : 2018-01-18 14:42:37
         * update_time :
         * physical_type : 阴虚
         * type : 阴虚
         * is_delete : 0
         */

        private int report_id;
        private String report_desc;
        private String create_time;
        private String update_time;
        private String physical_type;
        private String type;
        private String is_delete;

        public int getReport_id() {
            return report_id;
        }

        public void setReport_id(int report_id) {
            this.report_id = report_id;
        }

        public String getReport_desc() {
            return report_desc;
        }

        public void setReport_desc(String report_desc) {
            this.report_desc = report_desc;
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

        public String getPhysical_type() {
            return physical_type;
        }

        public void setPhysical_type(String physical_type) {
            this.physical_type = physical_type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }
    }

    public static class LeadResultBeansBean {
        /**
         * result_id : 1
         * result_title : 是否有高血压，常吃利尿药物？
         * create_time : 2018-01-18 16:40:14
         * update_time :
         * physical_type : 阴虚
         * type : 阴虚
         * is_delete : 0
         */

        private int result_id;
        private String result_title;
        private String create_time;
        private String update_time;
        private String physical_type;
        private String type;
        private String is_delete;

        public int getResult_id() {
            return result_id;
        }

        public void setResult_id(int result_id) {
            this.result_id = result_id;
        }

        public String getResult_title() {
            return result_title;
        }

        public void setResult_title(String result_title) {
            this.result_title = result_title;
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

        public String getPhysical_type() {
            return physical_type;
        }

        public void setPhysical_type(String physical_type) {
            this.physical_type = physical_type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }
    }
}
