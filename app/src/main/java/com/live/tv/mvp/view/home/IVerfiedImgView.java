package com.live.tv.mvp.view.home;

import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public interface IVerfiedImgView extends BaseView {
    void onCertificationRecord(String data);
    void onUploadImgs(String[] data,int i);
}
