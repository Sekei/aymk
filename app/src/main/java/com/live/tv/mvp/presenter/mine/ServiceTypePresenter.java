package com.live.tv.mvp.presenter.mine;

import com.king.base.util.LogUtils;
import com.live.tv.App;
import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.mine.IServiceTypeView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public class ServiceTypePresenter extends BasePresenter<IServiceTypeView>{

    public ServiceTypePresenter(App app) {
        super(app);
    }


    //
    public void getHealthManager(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getHealthManager(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<HealthManagerBeans>>>() {


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
                    public void onNext(HttpResult<List<HealthManagerBeans>> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onHealthManager(userBeanHttpResult.getData());
                        }
                    }
                });
    }
    //添加健康档案
    public void getservicetypeList(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getservicetypeList(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<HealthManagerBeans>>>() {


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
                    public void onNext(HttpResult<List<HealthManagerBeans>> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onservicetypeList(userBeanHttpResult.getData());
                        }
                    }
                });
    }
}
