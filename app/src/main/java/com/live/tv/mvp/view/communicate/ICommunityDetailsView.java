package com.live.tv.mvp.view.communicate;

import com.live.tv.bean.PlateListBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/7/24
 */

public interface ICommunityDetailsView extends BaseView {
    void onPlateDetails(PlateListBean plateListBean);
}
