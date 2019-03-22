package com.live.tv.mvp.view.shop;

import com.live.tv.bean.MerchantsBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public interface IMerchantsDetailView extends BaseView{

    void oninsertCollection(String data);
    void onMerchantsDetail(MerchantsBean data);
}
