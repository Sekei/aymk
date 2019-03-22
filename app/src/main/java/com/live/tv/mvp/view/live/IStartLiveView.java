package com.live.tv.mvp.view.live;


import com.live.tv.bean.LiveBean;
import com.live.tv.mvp.base.BaseView;

/**
 * Created by taoh on 2017/7/20.
 */

public interface IStartLiveView extends BaseView {

    void  openQiNiuLive(LiveBean data);
    void uploadImg(String data);

}
