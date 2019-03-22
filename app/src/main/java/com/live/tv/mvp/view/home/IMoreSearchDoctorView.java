package com.live.tv.mvp.view.home;

import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.GoodsBean;
import com.live.tv.bean.MerchantsBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public interface IMoreSearchDoctorView extends BaseView{
    void onGetDoctorList(List<DoctorDetailBean> data);
    void onGetMerchantsList(List<MerchantsBean> data);
    void onGetGoodList(List<GoodsBean> data);
}
