package com.live.tv.mvp.view.home;

import com.live.tv.bean.HealthPlanBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IHealthStepDetailView extends BaseView {
    void onGetHealthStageDetail(HealthPlanBean data);

}
