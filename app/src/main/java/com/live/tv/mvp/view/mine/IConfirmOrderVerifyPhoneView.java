package com.live.tv.mvp.view.mine;

import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/19
 */

public interface IConfirmOrderVerifyPhoneView extends BaseView{
    void onSendCode(String data);
    void onVerifyPhone(String data);
}
