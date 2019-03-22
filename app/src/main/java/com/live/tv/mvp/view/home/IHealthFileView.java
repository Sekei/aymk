package com.live.tv.mvp.view.home;

import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IHealthFileView extends BaseView{
    void onHealthRecordDetail(HealthRecordDetailBean data);
    void onmyHealthRecordDetail(HealthRecordDetailBean data);
    void onUpdateRecord(String data);
}
