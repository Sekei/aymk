package com.live.tv.mvp.view.home;

import com.live.tv.bean.MedicalClassBean;
import com.live.tv.bean.MedicalListBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/15
 */

public interface IMedicalRecordsView extends BaseView
{
    void onGetMedicalClassBeans(List<MedicalClassBean> data);

    void onMedicalList(List<MedicalListBean> data);
}
