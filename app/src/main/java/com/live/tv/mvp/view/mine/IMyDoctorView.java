package com.live.tv.mvp.view.mine;

import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IMyDoctorView extends BaseView {
    void onMyDoctorBeans(List<DoctorDetailBean> data);


}
