package com.live.tv.mvp.view.home;

import com.live.tv.bean.HealthDataBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/15
 */

public interface IHealthDataView extends BaseView{
    void onHealthDtatList(List<HealthDataBean> data);
}
