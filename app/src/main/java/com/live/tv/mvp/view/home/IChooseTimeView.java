package com.live.tv.mvp.view.home;

import com.live.tv.bean.ConsultTimesBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/24
 */

public interface IChooseTimeView extends BaseView{
    void onGetConsultTimes(List<ConsultTimesBean> data);
}
