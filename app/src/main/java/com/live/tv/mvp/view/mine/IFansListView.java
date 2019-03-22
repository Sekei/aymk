package com.live.tv.mvp.view.mine;


import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * Created by taoh on 2017/7/20.
 */

public interface IFansListView extends BaseView {


    void  onFansList(List<UserBean> data);//
}
