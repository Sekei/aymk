package com.live.tv.mvp.view.communicate;

import com.live.tv.bean.CommAllBean;
import com.live.tv.bean.CommunityBannerBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/7/17
 */

public interface ICommuntiyFragmentView extends BaseView
{

     void OnBannerdata(List<CommunityBannerBean> bannerBean);
     void OnAllData(List<CommAllBean> commAllBeenList);
}
