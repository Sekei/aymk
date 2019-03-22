package com.live.tv.mvp.view.shop;


import com.live.tv.bean.AfterSaleOrderBean;
import com.live.tv.mvp.base.BaseView;

/**
 * Created by taoh on 2017/7/20.
 */

public interface IAfterSaleOrderDetailView extends BaseView {


    void  onAfterSaleOrderDetail(AfterSaleOrderBean data);
    void  onCancelRefundOrder(String data);

}
