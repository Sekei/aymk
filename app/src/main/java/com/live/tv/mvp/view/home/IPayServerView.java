package com.live.tv.mvp.view.home;

import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/26
 */

public interface IPayServerView extends BaseView{
    void onBuyPlan(String data);

    void onSendHeart(String data);

    void onBuyConsult(String data);
}
