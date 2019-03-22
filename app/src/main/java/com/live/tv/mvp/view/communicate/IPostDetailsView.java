package com.live.tv.mvp.view.communicate;

import com.live.tv.bean.PlateListBean;
import com.live.tv.mvp.base.BaseView;

/**
 * Created by mac1010 on 2018/8/21.
 */

public interface IPostDetailsView extends BaseView {
    void onPoastDetial(PlateListBean listBean);
    void onpraisePost(String listBean);
    void onpraisePosterror(Throwable msg);
    void onpcommentPost(String msg);
}
