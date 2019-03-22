package com.live.tv.mvp.view.shop;


import com.live.tv.bean.OrderBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * Created by taoh on 2017/7/20.
 */

public interface IGoodOrderListView extends BaseView {


    void  onGoodOrderList(List<OrderBean> data);
    void  onMoreGoodOrderList(List<OrderBean> data);
    void CancelOrder(String data);
    void DelOrder(String data);
    void onreceiveOrder(String data,int position);//确认收货

}
