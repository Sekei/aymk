package com.live.tv.mvp.view.mine;


import com.live.tv.bean.AssociatorBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;


public interface IMyVipView extends BaseView {
    void onAssociators(List<AssociatorBean> data);

    void onGetUserDetail(UserBean data);


}
