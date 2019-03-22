package com.live.tv.util.payutils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.live.tv.Constants;
import com.live.tv.bean.WxPayMode;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

/**
 *
 * 微信和支付宝支付工具类
 *
 */

public class PayHelper {
    private static PayHelper mPayHelper = null;
    private Context mContext;
    public static PayHelper getInstance(Context context) {
        if (mPayHelper == null) {
            mPayHelper = new PayHelper(context);
        }
        return mPayHelper;
    }

    public PayHelper(Context context) {

        mContext = context;

    }


    public void SetWxPayResp(BaseResp resp) {
        int code = resp.errCode;
        String transaction = resp.transaction;
        String errStr = resp.errStr;

        Log.i("dfc", "code: " + code + "");
        Log.i("dfc", "transaction: " + transaction);
        Log.i("dfc", "errStr: " + errStr);
        if (code == 0) {
            if (mIPayListener != null) {
                mIPayListener.onSuccess();
            }

        } else if (code == -1) {
            if (mIPayListener != null) {
                mIPayListener.onFail();
            }
        } else if (code == -2) {
            if (mIPayListener != null) {
                mIPayListener.onCancel();
            }

        } else {
            if (mIPayListener != null) {
                mIPayListener.onFail();
            }
        }


    }

    IWXAPI msgApi = null;

    public void WxPay(String data) {

        Gson gson = new Gson();
        WxPayMode wxpayMode = gson.fromJson(data, WxPayMode.class);

        if (msgApi == null) {
            msgApi = WXAPIFactory.createWXAPI(mContext, null);
            msgApi.registerApp(Constants.WEXAPPID);// 将该app注册到微信
        }
        PayReq req = new PayReq();
        if (!msgApi.isWXAppInstalled()) {
            Toast.makeText(mContext, "手机中没有安装微信客户端!", Toast.LENGTH_SHORT).show();

            return;
        }
        if (data != null) {

            req.appId = wxpayMode.getAppid();
            req.partnerId = wxpayMode.getPartnerid();
            req.prepayId = wxpayMode.getPrepayid();
            req.nonceStr = wxpayMode.getNoncestr();
            req.timeStamp = wxpayMode.getTimestamp() + "";
            req.packageValue = wxpayMode.getPackageX();
            req.sign = wxpayMode.getSign();
            // req.extData = MaiLiApplication.getInstance().getUserInfo().getPhone();  微信拓展参数
            msgApi.sendReq(req);
        }
    }


    public PayHelper alipayAndWxPay(Activity activity, String pay_type, String result) {

        if ("alipay".equals(pay_type)) {
            AliPay(activity, result);
        } else if ("wx".equals(pay_type)) {
            WxPay(result);
        }

        return this;

    }


    public void AliPay(Activity activity, final String orderInfo) {
        final PayTask alipay = new PayTask(activity);
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {

                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        if (mIPayListener != null) {
                            mIPayListener.onSuccess();
                        }
                    } else if (TextUtils.equals(resultStatus, "4000")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        if (mIPayListener != null) {
                            mIPayListener.onFail();
                        }
                    } else if (TextUtils.equals(resultStatus, "6001")) {

                        if (mIPayListener != null) {
                            mIPayListener.onCancel();
                        }
                    } else {
                        if (mIPayListener != null) {
                            mIPayListener.onFail();
                        }
                    }

                    break;
            }
        }
    };
    private IPayListener mIPayListener;
    public PayHelper setIPayListener(IPayListener mIPayListener) {
        this.mIPayListener = mIPayListener;
        return this;
    }

    public interface IPayListener {
        void onSuccess();

        void onFail();

        void onCancel();

    }


}
