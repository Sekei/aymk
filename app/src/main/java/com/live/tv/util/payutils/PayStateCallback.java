package com.live.tv.util.payutils;

/**
 * Created by taoh on 2018/4/19.
 */

public interface PayStateCallback {

    /**
     * 支付成功
     */
    void onPaySuccess(String describe);

    /**
     * 支付失败
     */
    void onPayFailed(String describe);
}
