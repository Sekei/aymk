package com.live.tv.mvp.view.shop;

import com.live.tv.bean.OrderBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public interface IGoodOrderDetailView extends BaseView{
    void onGoodOrderDetail(OrderBean data);
    void CancelOrder(String data);
    void DelOrder(String data);
}
