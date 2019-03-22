package com.live.tv.mvp.view.mine;


import com.live.tv.bean.HtmlBean;
import com.live.tv.mvp.base.BaseView;


public interface ISettingView extends BaseView {
    void onAboutUs(HtmlBean data);

    void logoutDeviceTokens(String str);
}
