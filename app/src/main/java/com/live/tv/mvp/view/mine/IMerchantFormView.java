package com.live.tv.mvp.view.mine;

import com.live.tv.bean.OrderBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * Created by mac1010 on 2018/8/24.
 */

public interface IMerchantFormView extends BaseView {
    void onformList(List<OrderBean> beanList);
}
