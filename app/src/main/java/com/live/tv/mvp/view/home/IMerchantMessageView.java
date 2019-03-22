package com.live.tv.mvp.view.home;

import com.live.tv.bean.HousekeepBean;
import com.live.tv.mvp.base.BaseView;

/**
 * Created by mac1010 on 2018/8/28.
 */

public interface IMerchantMessageView extends BaseView {
    void onGetHousekeepDetail(HousekeepBean housekeepBean);
    void onChangeSuccess(String str);
    void uploadImg(String data);


}
