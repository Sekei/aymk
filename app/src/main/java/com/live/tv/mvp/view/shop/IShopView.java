package com.live.tv.mvp.view.shop;

import com.live.tv.bean.BannerBean;
import com.live.tv.bean.ClassBean;
import com.live.tv.bean.GoodsBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public interface IShopView extends BaseView{
    void onGetBanner(List<BannerBean> data);
    void onGetClass(List<ClassBean> data);
    void onHotGoods(List<GoodsBean> data);
    void onRecommendGoods(List<GoodsBean> data);
}
