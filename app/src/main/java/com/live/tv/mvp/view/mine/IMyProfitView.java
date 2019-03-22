package com.live.tv.mvp.view.mine;

import com.live.tv.bean.ProfitBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/19
 */

public interface IMyProfitView extends BaseView{
    void onMyProfit(List<ProfitBean> data);
    void onGetUserDetail(UserBean data);
}
