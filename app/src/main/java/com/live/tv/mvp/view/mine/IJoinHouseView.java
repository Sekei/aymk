package com.live.tv.mvp.view.mine;

import com.live.tv.bean.HouseKeepTypeBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IJoinHouseView extends BaseView {
    void onUploadImgs(String[] data, int i);
    void applyHouse(String data);
    void getServiceTypes(List<HouseKeepTypeBean> data);
}
