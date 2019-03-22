package com.live.tv.mvp.view.home;

import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IHealthStepView extends BaseView {
    void onGetHealthStage(DoctorDetailBean data);
    void onplayCard(String data);
}
