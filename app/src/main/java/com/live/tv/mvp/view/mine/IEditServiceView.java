package com.live.tv.mvp.view.mine;

import com.live.tv.mvp.base.BaseView;

/**
 * Created by mac1010 on 2018/8/29.
 */

public interface IEditServiceView extends BaseView {
    void onUploadImgs(String[] data);
    void onUploadImgserror(String data);
    void OnaddService(String str);
}
