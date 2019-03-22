package com.live.tv.mvp.view.home;

import com.live.tv.bean.HealthFileBean;
import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IHealthManageView extends BaseView {

    void onGetHealthManager(List<HealthManagerBeans> data);
    void onRecordList(List<HealthFileBean> data);
}
