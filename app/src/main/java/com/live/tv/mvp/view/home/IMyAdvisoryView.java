package com.live.tv.mvp.view.home;

import com.live.tv.bean.ConsultBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IMyAdvisoryView extends BaseView {
    void onGetConsultList(List<ConsultBean> data);


}
