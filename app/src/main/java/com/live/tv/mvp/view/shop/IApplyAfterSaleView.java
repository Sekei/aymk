package com.live.tv.mvp.view.shop;

import com.live.tv.bean.RefundsReasons;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IApplyAfterSaleView extends BaseView {


    void onUploadImgs(String[] data);
    void onRefundsReasons(List<RefundsReasons> data);//退款原因
    void onRefundOrder(String data);//申请售后


}
