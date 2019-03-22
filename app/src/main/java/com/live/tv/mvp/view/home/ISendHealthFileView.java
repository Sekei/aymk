package com.live.tv.mvp.view.home;

import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface ISendHealthFileView extends BaseView{
    void onHealthRecordDetail(HealthRecordDetailBean data);
}
