package com.live.tv.mvp.view.mine;

import com.live.tv.bean.PlateListBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/8/1
 */

public interface IPostedListView extends BaseView {
    void onPostedList(List<PlateListBean> plateListBeenlist);
}
