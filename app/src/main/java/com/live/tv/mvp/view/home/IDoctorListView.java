package com.live.tv.mvp.view.home;

import com.live.tv.bean.DepartmentLevelOneBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.ShaixuanBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public interface IDoctorListView extends BaseView{
    void onGetDoctorList(List<DoctorDetailBean> data);

    void onGetDepartments(List<DepartmentLevelOneBean> data);

    void onGetSelectBeans(ShaixuanBean data);
}
