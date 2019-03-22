package com.live.tv.mvp.view.mine;


import com.live.tv.bean.HealthPlanBean;
import com.live.tv.mvp.base.BaseView;


public interface IAddPlanView extends BaseView {
    void AddPaln(String data);
    void getHealthPlan(HealthPlanBean data);
    void SendHealthPlan(String data);




}
