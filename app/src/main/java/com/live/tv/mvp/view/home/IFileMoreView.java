package com.live.tv.mvp.view.home;

import com.live.tv.bean.HealthFileBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IFileMoreView extends BaseView {
    void onRecordList(List<HealthFileBean> data);
    void onDelRecord(String data);
}
