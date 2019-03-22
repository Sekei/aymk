package com.live.tv.mvp.view.home;

import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.bean.RelationsBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IUpdateFileView extends BaseView {
    void onInsertRecord(String data);
    void uploadImg(String data);
    void onGetRelations(List<RelationsBean> data);
    void onHealthRecordDetail(HealthRecordDetailBean data);
}
