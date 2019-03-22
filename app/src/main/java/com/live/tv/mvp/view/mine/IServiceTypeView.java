package com.live.tv.mvp.view.mine;

import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IServiceTypeView extends BaseView {
    void onHealthManager(List<HealthManagerBeans> data);
    void onservicetypeList(List<HealthManagerBeans> data);


}
