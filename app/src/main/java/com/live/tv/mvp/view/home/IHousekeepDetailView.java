package com.live.tv.mvp.view.home;

import com.live.tv.bean.HousekeepBean;
import com.live.tv.bean.ServiceCommentBean;
import com.live.tv.bean.ServiceCopeBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/22
 * 家政详情的view
 */

public interface IHousekeepDetailView extends BaseView {
    void onGetHousekeepDetail(HousekeepBean data);
    void onHouseKeeps(List<HousekeepBean> list);
    void OnServiceCope(List<ServiceCopeBean> data);
    void OnserviceComment(List<ServiceCommentBean> data);
    void onCollectiohouse(String data);
}
