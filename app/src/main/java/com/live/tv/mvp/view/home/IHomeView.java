package com.live.tv.mvp.view.home;

import com.live.tv.bean.BannerBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.HomeButtonBean;
import com.live.tv.bean.HomeHealthDetail;
import com.live.tv.bean.HomeImgsBean;
import com.live.tv.bean.HomeMessageBean;
import com.live.tv.bean.LiveBean;
import com.live.tv.bean.MerchantHotBean;
import com.live.tv.bean.NewsBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public interface IHomeView extends BaseView {
    void onGetBanner(List<BannerBean> data);
    void onmessageList(List<HomeMessageBean> data);

    void onGetHomeButton(List<HomeButtonBean> data);

    void onGetDoctorList(List<DoctorDetailBean> data);

    void onMessageList(List<NewsBean> data);

    void onHomeImgs(List<HomeImgsBean> data);

    void onLiveList(List<LiveBean> data);

    void onHealthDetail(HomeHealthDetail homeHealthDetail);

    void onMerchantHot(List<MerchantHotBean> merchantHotBeanList);

}
