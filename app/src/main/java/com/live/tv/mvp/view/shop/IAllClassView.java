package com.live.tv.mvp.view.shop;


import com.live.tv.bean.ClassBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * Created by taoh on 2017/7/20.
 */

public interface IAllClassView extends BaseView {


    void  onClass(List<ClassBean> data);//一级分类
    void  onClassTwo(List<ClassBean> data);//二级分类

}
