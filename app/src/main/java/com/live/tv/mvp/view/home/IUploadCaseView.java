package com.live.tv.mvp.view.home;

import com.live.tv.bean.MedicalClassBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IUploadCaseView extends BaseView {


    void onUploadImgs(String[] data);
    void insertMedical(String data);//上传电子病例
    void onGetMedicalClassBeans(List<MedicalClassBean> data);


}
