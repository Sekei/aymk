package com.ysjk.health.iemk.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.util.payutils.PayHelper;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by taoh on 2018/4/18.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler  {

    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wx);
        api = WXAPIFactory.createWXAPI(this, Constants.WEXAPPID, false);
        try {
            api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }




    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        PayHelper.getInstance(this).SetWxPayResp(resp);
         finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (api != null) {
            api.unregisterApp();
        }
    }

}
