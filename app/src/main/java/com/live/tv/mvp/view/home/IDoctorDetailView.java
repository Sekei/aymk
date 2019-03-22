package com.live.tv.mvp.view.home;

import com.live.tv.bean.AllDoctorAssessesBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IDoctorDetailView extends BaseView {
    void onGetDoctorDetail(DoctorDetailBean data);
    void onGetAllDoctorAssesses(List<AllDoctorAssessesBean> data);
    void onCollectionDoctor(String data);
    void onCancelCollection(String data);
}
