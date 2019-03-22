package com.live.tv.mvp.view.home;

import com.live.tv.bean.PlanDetailBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/26
 */

public interface ITestPlanProfessionView extends BaseView{
    void onGetPlanDetail(List<PlanDetailBean> data);
}
