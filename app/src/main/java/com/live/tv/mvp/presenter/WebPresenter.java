package com.live.tv.mvp.presenter;

import com.live.tv.App;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.IWebView;

import javax.inject.Inject;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/21
 */

public class WebPresenter extends BasePresenter<IWebView> {

    @Inject
    public WebPresenter(App app) {
        super(app);
    }




}
