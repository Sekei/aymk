package com.live.tv.mvp.view.shop;

import com.live.tv.bean.AssessmentBean;
import com.live.tv.bean.GoodsBean;
import com.live.tv.bean.GoodsSpecificationBeans;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public interface IGoodDetailView extends BaseView{
    void onGoodDetail(GoodsBean data);
    void oninsertCollection(String data);
    void onInsertShopCar(String data);
    void onGetGoodsSpecificationDetail(GoodsSpecificationBeans data);
    void onAssessmentGoods(List<AssessmentBean> data);
}
