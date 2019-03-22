package com.live.tv.mvp.view.home;

import com.live.tv.bean.HousekeepBean;
import com.live.tv.bean.TypeServiceBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IHouseKeepingView extends BaseView {
    void onHouseKeeps(List<HousekeepBean> data);
    void ontypeservice(List<TypeServiceBean> data);

}
