package com.live.tv.mvp.view.shop;

import com.live.tv.bean.ClassBean;
import com.live.tv.bean.GoodsBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public interface IClassGoosListView extends BaseView{

    void onClassGoodsList(List<GoodsBean> data);
    void  onClass(List<ClassBean> data);//一级分类
}
