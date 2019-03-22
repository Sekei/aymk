package com.live.tv.mvp.view.communicate;

import com.live.tv.bean.PlateListBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/7/18
 */

public interface ICommunityListFragmentView  extends BaseView{
    void onHomeHealthMore(List<PlateListBean> homeHealthDetails);
}
