package com.live.tv.mvp.view.home;

import com.live.tv.bean.MedicalListBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IMedicalRecordsDetailView extends BaseView {
    void onDeleteMedicalImg(String data);
    void onMedicalDetail(MedicalListBean data);
}
