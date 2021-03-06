package com.live.tv.mvp.base;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/10
 */

public abstract class SimpleFragment extends BaseFragment<BaseView, BasePresenter<BaseView>> {

    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>(getApp());
    }

    /***
     * 交流社区的热门模块
     */





}
