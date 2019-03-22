package com.live.tv.mvp.view.communicate;

import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/7/31
 */

public interface IPostedFragmentView extends BaseView {

    void onUploadImgs(String[] data);
    void onpostsuccess(String data);

}
