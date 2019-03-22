package com.live.tv.mvp.view.home;


import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * Created by taoh on 2017/7/20.
 */

public interface ISearchHistoryView extends BaseView {

    void showHistory(boolean show, List<String> data);

}
