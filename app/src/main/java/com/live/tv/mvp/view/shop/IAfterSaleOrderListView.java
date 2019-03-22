package com.live.tv.mvp.view.shop;


import com.live.tv.bean.AfterSaleOrderBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * Created by taoh on 2017/7/20.
 */

public interface IAfterSaleOrderListView extends BaseView {


    void  onGoodOrderList(List<AfterSaleOrderBean> data);
    void  onMoreGoodOrderList(List<AfterSaleOrderBean> data);


}
