package com.live.tv.mvp.view.shop;

import com.live.tv.bean.PriceBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public interface IConfirmServiceGoodOrderView extends BaseView{
    void ConfirmOrder(PriceBean data);//提交订单
    void getOrderPrice(PriceBean data);//计算价格


}
