package com.live.tv.mvp.view.live;


import com.live.tv.bean.GiftBean;
import com.live.tv.bean.LiveBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.activity.live.bean.MemberAvoidBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * Created by taoh on 2017/7/20.
 */

public interface ILiveRoomView extends BaseView {

    void  inTheLiveRoom(String data);
    void  outTheLiveRoom(String data);
    void  getLiveInfo(LiveBean data);
    void  getLiveGift(List<GiftBean> data);
    void  getAvoidPassword(MemberAvoidBean data);
    void  sendGift(String data);
    void  getReward(String data);
    void onGetUserDetail(UserBean data);

}
