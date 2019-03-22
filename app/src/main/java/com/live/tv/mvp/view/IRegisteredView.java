package com.live.tv.mvp.view;

import com.live.tv.bean.PlateListBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/19
 */

public interface IRegisteredView extends BaseView{
    void onRegisterMember(UserBean data);

    void onSendCode(String data);
    void onbuildnumber(UserBean data);
}
