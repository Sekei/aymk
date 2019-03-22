package com.live.tv.mvp.presenter.home;

import com.king.base.util.LogUtils;
import com.live.tv.App;
import com.live.tv.bean.HealthPlanBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.home.IHealthStepDetailView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/1/24
 */

public class HealthStepDetailPresenter extends BasePresenter<IHealthStepDetailView>{
    public HealthStepDetailPresenter(App app) {
        super(app);
    }

    //（健康阶段详情
    public void getHealthStageDetail(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getHealthPlanBean(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<HealthPlanBean>>() {


                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<HealthPlanBean> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onGetHealthStageDetail(userBeanHttpResult.getData());
                        }
                    }
                });
    }



}
