package com.live.tv.mvp.view.home;

import com.live.tv.bean.ReportBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/23
 */

public interface ITestDiagnoseReportView extends BaseView{
    void onGetReport(ReportBean data);
}
