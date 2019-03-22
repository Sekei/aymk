package com.live.tv.mvp.view.mine;

import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IHealthUserView extends  BaseView {
    void onHealthUser(List<HealthRecordDetailBean> data);


}
