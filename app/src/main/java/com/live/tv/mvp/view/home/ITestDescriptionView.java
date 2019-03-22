package com.live.tv.mvp.view.home;

import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.bean.StartTestBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface ITestDescriptionView extends BaseView{
    void onHealthRecordDetail(HealthRecordDetailBean data);

    void onStartTest(StartTestBean data);

    void onGetMemberDetail(UserBean data);
}
