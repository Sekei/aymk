package com.live.tv.mvp.view.home;

import com.live.tv.bean.HousekeepBean;
import com.live.tv.mvp.base.BaseView;

/**
 * Created by mac1010 on 2018/8/30.
 */

public interface IMerchantMoreAddressVIew extends BaseView {
    void ondata(HousekeepBean housekeepBean);
    void onDeleteAddress(String str);
}
