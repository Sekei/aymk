package com.live.tv.mvp.view;

import com.live.tv.bean.PlateListBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/19
 */

public interface ILoginView extends BaseView{
    void onLoginMember(UserBean data);
    void onThridLogin(UserBean data,String str,String code);
    void ondeviceToken(String devicetoken);
}
