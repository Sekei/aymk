package com.live.tv.mvp.presenter;

import android.content.Context;

import com.live.tv.App;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.ISeachCityView;

/**
 * @author Created by stone
 * @since 2018/1/16
 */

public class SeachCityPresenter extends BasePresenter<ISeachCityView>{
    public SeachCityPresenter(App app, Context context) {
        super(app);
    }
}
