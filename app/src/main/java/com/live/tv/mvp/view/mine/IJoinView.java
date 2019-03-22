package com.live.tv.mvp.view.mine;

import com.live.tv.bean.SystemHtmlBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IJoinView extends BaseView {
    void onGetUserDetail(UserBean data);
    void onhtmllist(List<SystemHtmlBean> data);
}
