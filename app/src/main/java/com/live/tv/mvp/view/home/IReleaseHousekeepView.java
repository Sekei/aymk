package com.live.tv.mvp.view.home;

import com.live.tv.bean.HouseKeepTypeBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IReleaseHousekeepView extends BaseView {
    void onReleaseHousekeep(String data);
    void uploadImg(String data);
    void getServiceTypes(List<HouseKeepTypeBean> data);

}
