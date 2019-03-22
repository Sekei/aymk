package com.live.tv.mvp.view.mine;

import com.live.tv.bean.ServiceCopeBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * Created by mac1010 on 2018/8/28.
 */

public interface IServicecopeView extends BaseView {
    void OnServiceCope(List<ServiceCopeBean> list);
}
