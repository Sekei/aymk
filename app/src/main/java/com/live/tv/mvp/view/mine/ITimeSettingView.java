package com.live.tv.mvp.view.mine;

import com.live.tv.bean.ConsultTimesBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface ITimeSettingView extends BaseView {
    void onConsultTimeList(List<ConsultTimesBean> data);
    void onsetOpenTime(String data);


}
