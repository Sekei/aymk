package com.live.tv.mvp.view.mine;

import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IDoctorInfoView extends BaseView {
    void PersonInfo(DoctorDetailBean data);
    void uploadImg(String data);
    void updateMemberDetail(String data);

}
