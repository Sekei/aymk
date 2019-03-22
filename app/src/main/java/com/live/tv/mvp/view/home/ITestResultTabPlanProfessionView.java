package com.live.tv.mvp.view.home;

import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/25
 */

public interface ITestResultTabPlanProfessionView extends BaseView{

    void onGetDoctorList(List<DoctorDetailBean> data);
}
