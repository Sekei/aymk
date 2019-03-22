package com.live.tv.mvp.view.shop;

import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IAssessmentOrderView extends BaseView {


    void onUploadImgs(List<String> data);
    void onAssessmentOrder(String data);//评价订单



}
