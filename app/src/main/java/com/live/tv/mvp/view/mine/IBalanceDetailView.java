package com.live.tv.mvp.view.mine;


import com.live.tv.bean.BalanceBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;


public interface IBalanceDetailView extends BaseView {
    void onBalanceList(List<BalanceBean> data);
}
