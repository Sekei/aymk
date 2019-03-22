package com.live.tv.mvp.view.home;

import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.bean.TextandImgBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/17
 */

public interface IRequestOneView extends BaseView{
    void onUploadImgs(String[] data);

    void onTextConsult(String data);

    void onHealthRecordDetail(HealthRecordDetailBean data);
    void onBuyConsult(TextandImgBean data);//图文混排购买生成订单

}
