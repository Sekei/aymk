package com.live.tv.mvp.presenter.communicate;

import com.live.tv.App;
import com.live.tv.bean.PlateListBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.communicate.ICommunityDetailsView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/7/24
 */

public class CommunityDetailsPresenter extends BasePresenter<ICommunityDetailsView> {
    public CommunityDetailsPresenter(App app) {
        super(app);
    }


    /***
     * 帖子详情
     * @param map
     */
    public void getPlateDetails(Map<String, String> map) {

        if (isViewAttached()) {
            getView().showProgress();
        }

        getAppComponent().getAPIService()
                .getPlateDetail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<PlateListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<PlateListBean> plateListBeanHttpResult) {
                        if (isViewAttached()) {
                            getView().onPlateDetails(plateListBeanHttpResult.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
