package com.live.tv.mvp.view.live;


import com.live.tv.bean.DepartmentLevelOneBean;
import com.live.tv.bean.LiveBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * Created by taoh on 2017/7/20.
 */

public interface IFamousdoctorLiveListView extends BaseView {

    void  onLiveList(List<LiveBean> data);

    void onGetDepartments(List<DepartmentLevelOneBean> data);

}
