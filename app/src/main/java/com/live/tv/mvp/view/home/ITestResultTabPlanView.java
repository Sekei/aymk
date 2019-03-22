package com.live.tv.mvp.view.home;

import com.live.tv.bean.PlanPriceBean;
import com.live.tv.bean.SimplePlansBean;
import com.live.tv.bean.TestRecordBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/25
 */

public interface ITestResultTabPlanView extends BaseView{
    void onGetSimplePlans(List<SimplePlansBean> data);

    void onGetPlanPrice(PlanPriceBean data);


    void onTestRecord(TestRecordBean data);
}
