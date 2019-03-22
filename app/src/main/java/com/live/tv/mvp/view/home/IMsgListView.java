package com.live.tv.mvp.view.home;

import com.live.tv.bean.MemberMsgsBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/27
 */

public interface IMsgListView extends BaseView{
    void onGetMemberMsgs(List<MemberMsgsBean> data);
}
