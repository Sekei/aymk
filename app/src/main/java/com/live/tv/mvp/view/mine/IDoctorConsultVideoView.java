package com.live.tv.mvp.view.mine;

import com.live.tv.bean.ConsultBean;
import com.live.tv.bean.FetureDateBeans;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IDoctorConsultVideoView extends BaseView {
    void onGetFetureDay(List<FetureDateBeans> data);
    void ongetDoctorConsultList(List<ConsultBean> data);
    void ongetMoreDoctorConsultList(List<ConsultBean> data);


}
