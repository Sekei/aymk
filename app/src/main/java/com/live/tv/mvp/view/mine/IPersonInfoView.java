package com.live.tv.mvp.view.mine;

import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IPersonInfoView extends BaseView {
    void PersonInfo(UserBean data);
    void UpdatePersonInfo(String data);
    void uploadImg(String data);

}
