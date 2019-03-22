package com.live.tv.mvp.view.home;

import com.live.tv.bean.ReportHistoryBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/23
 */

public interface ITestHistoryView extends BaseView {
    void onGetReportList(List<ReportHistoryBean> data);
}
