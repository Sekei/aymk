package com.live.tv.mvp.view.home;

import com.live.tv.bean.CheckLeadFactorsBean;
import com.live.tv.bean.CheckQuestionBean;
import com.live.tv.bean.LeadFactorsBean;
import com.live.tv.bean.QuestionBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface ITestPhysiqueView extends BaseView{
    void onGetQuestionBean(QuestionBean data);

    void onCheckQuestion(CheckQuestionBean data);

    void onCheckResult(String data);

    void onGetLeadFactorsBean(LeadFactorsBean data);

    void onCheckLeadFactors(CheckLeadFactorsBean data);

    void onCheckFactors(String data);

    void onCheckQuestionAgain(String data);
}
