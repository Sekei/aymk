package com.live.tv.mvp.view.home;

import com.live.tv.bean.TestRecordBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/26
 */

public interface ITestResultTabView extends BaseView{

    void onTestRecord(TestRecordBean data);
}
