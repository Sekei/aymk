package com.live.tv.mvp.view.mine;

import com.live.tv.bean.DepartmentLevelOneBean;
import com.live.tv.bean.DoctorTitleBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IBaseInfoView extends BaseView {
    void onUpdateDoctor(String data);
    void onGetDepartments(List<DepartmentLevelOneBean> data);
    void onDoctorTitleList(List<DoctorTitleBean> data);

}
