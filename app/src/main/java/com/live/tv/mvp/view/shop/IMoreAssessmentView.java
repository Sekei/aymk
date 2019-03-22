package com.live.tv.mvp.view.shop;

import com.live.tv.bean.AssessmentBean;
import com.live.tv.bean.ServiceCommentBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public interface IMoreAssessmentView extends BaseView{

    void onAssessmentGoods(List<AssessmentBean> data);
    void onAssessment(List<ServiceCommentBean> data);
}
