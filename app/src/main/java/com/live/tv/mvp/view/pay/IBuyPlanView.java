package com.live.tv.mvp.view.pay;

import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/26
 */

public interface IBuyPlanView extends BaseView{

    void onBuyConsult(String data);
    void onGetUserDetail(UserBean data);
}
